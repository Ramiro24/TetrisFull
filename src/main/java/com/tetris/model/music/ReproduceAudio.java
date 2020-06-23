package com.tetris.model.music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Yacoobs
 * Esta clase se encarga de Reproducir los efectos de audio almacenando los en un ArrayList las rutas
 * de ubicacion de cada uno de ellos y luego llamando a su metodo Fx() carga dicha ruta, reproduciendo
 * el audio con la interface Clip, pasando como argumento al metodo el indice de la ruta. 
 * 
 */
/**
*
* @author Yacoobs
* Martes 19 Diciembre del 2017 8:41AM 
* ultima modificacion 18 Sabado Enero del 2020
*/


public class ReproduceAudio {
    
    
    private ArrayList<File> EFX = new ArrayList<>();

    public ReproduceAudio() {
    
        
        IntroducirFx("Efecto0.wav");
        IntroducirFx("Efecto1.wav");
        IntroducirFx("Efecto2.wav");
        IntroducirFx("Efecto3.wav");//si
        IntroducirFx("Efecto4.wav");
        IntroducirFx("Efecto5.wav");
        IntroducirFx("Efecto6.wav");
    
        
    }

    public void IntroducirFx(String Ruta){

        try {
 
            File file = new File("").getAbsoluteFile();
            
            String rutt = file + "/Efectos/" + Ruta;
            
            file = new File(rutt);
            
            EFX.add(file);
            
            } catch(NullPointerException e2){
                System.out.println("Error la ruta o archivo no encontrado de audio....");
            }

        
        
    }
    
    public void Fx(int indice){
        
        try {

            File file = EFX.get(indice);
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(file));
            
            // Comienza la reproducción
            sonido.start();
            
            
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    }
