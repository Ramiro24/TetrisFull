package com.tetris.model.music;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class ReproduceMusic {
    
    private final BasicPlayer Audio = new BasicPlayer();
    private ArrayList<File> Music = new ArrayList<>();
    private float volumen=0.1f;
    private File file;
    private int Index;
    private boolean Repetir=true;
    private boolean Silenciar;
    private int TimeBytes;
   

    public ReproduceMusic() {
        
     
        IntroducirFx("Efecto16.mp3");
     
        
        Audio.addBasicPlayerListener(new BasicPlayerListener() {
            @Override
            public void opened(Object o, Map map) {
                TimeBytes = (int) map.get("mp3.length.bytes"); 
            }
 
            @Override
            public void progress(int i, long l, byte[] bytes, Map map) {
                
                if (TimeBytes<=i){
                    try {
                        Audio.stop();
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    RepetirMusica();
                }

                
                if (Silenciar){
                    try {
                        Audio.setGain(0f);
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        Audio.setGain(volumen);
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }

            @Override
            public void stateUpdated(BasicPlayerEvent bpe) {
 
            }

            @Override
            public void setController(BasicController bc) {
                
            }
        });

    }
   
    public void RepetirMusica(){
        
        Thread repetir = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Repetir){
                        Index++;
                        if (Index>=Music.size()){
                            Index=0;
                        }
                        ReproduceMusic(Index);
                    }   
            }
        });
        repetir.start();  
    }
    
    
    public void IntroducirFx(String Ruta){

        try {
 
            file = new File("").getAbsoluteFile();
            
            String rutt = file + "/Efectos/" + Ruta;
            
            file = new File(rutt);
            
            Music.add(file);
            
            } catch(NullPointerException e2){
                System.out.println("Error la ruta o archivo no encontrado de audio....");
            }
    }
    
    public void DetenerMusica(){

        try {
            Audio.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void ReproduceMusic(int index){
        
        Index = index;
        
        File file = Music.get(index);
        
        try {
            Audio.open(file);
            
            Audio.play();
            
            Audio.setGain(volumen);
            
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
  /* public void setVolumen(float volumen) {
        this.volumen = volumen;
    }*/

    public void setRepetir(boolean Repetir) {
        this.Repetir = Repetir;
    }

    public void setSilenciar(boolean Silenciar) {
        this.Silenciar = Silenciar;
    }

    public ArrayList<File> getMusic() {
        return Music;
    }

 }
