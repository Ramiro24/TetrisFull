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

public class ReproduceAudio {


    private ArrayList<File> EFX = new ArrayList<>();

    public ReproduceAudio() {


        IntroducirFx("Efecto0.wav");//comienco juego
        IntroducirFx("Efecto1.wav");//cuando completas fila
        IntroducirFx("Efecto2.wav");//botones velocidad
        IntroducirFx("Efecto3.wav");//botones seleccion dificultad medio y alto
        IntroducirFx("Efecto4.wav");// grafico y grafico total
        IntroducirFx("Efecto5.wav");//cuenta score cuando bajas
        IntroducirFx("Efecto6.wav");//game over


    }

    public void IntroducirFx(String Ruta) {
    	//String rutt = file + "/src/main/resources/effects/" + Ruta;
        try {

            File file = new File("").getAbsoluteFile();

            String rutt = file + "/src/main/resources/effects/" + Ruta;

            file = new File(rutt);

            EFX.add(file);

        } catch (NullPointerException e2) {
            System.out.println("Error la ruta o archivo no encontrado de audio....");
        }


    }

    public void Fx(int indice) {

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
