package com.tetris.model.music;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    private float volumen = 0.5f;
    private File file;
    private int Index;
    private boolean repeat = true;
    private boolean silence;
    private int timeBytes;


    public ReproduceMusic() {


        introduceFx("Efecto16.mp3");


        Audio.addBasicPlayerListener(new BasicPlayerListener() {
            @Override
            public void opened(Object o, Map map) {
                timeBytes = (int) map.get("mp3.length.bytes");
            }

            @Override
            public void progress(int i, long l, byte[] bytes, Map map) {

                if (timeBytes <= i) {
                    try {
                        Audio.stop();
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repeatMusic();
                }


                if (silence) {
                    try {
                        Audio.setGain(0f);
                    } catch (BasicPlayerException ex) {
                        Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
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

    public void repeatMusic() {

        Thread repeat = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ReproduceMusic.this.repeat) {
                    Index++;
                    if (Index >= Music.size()) {
                        Index = 0;
                    }
                    ReproduceMusic(Index);
                }
            }
        });
        repeat.start();
    }


    public void introduceFx(String Ruta) {
        InputStream inputStream = this.getClass().getResourceAsStream("/effects/" + Ruta);
        File targetFile = new File(System.getProperty("user.home") + "/targetFile.tmp");

        try {
            Files.copy(
                    inputStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            Music.add(targetFile);

        } catch (NullPointerException e2) {
            System.out.println("Error la ruta o archivo no encontrado de audio....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {

        try {
            Audio.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ReproduceMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReproduceMusic(int index) {

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
    


    public void setRepeat(boolean Repetir) {
        this.repeat = Repetir;
    }

    public void setSilence(boolean Silenciar) {
        this.silence = Silenciar;
    }

    public ArrayList<File> getMusic() {
        return Music;
    }

}
