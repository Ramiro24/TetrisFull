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


        inputFx("Efecto0.wav");//comienco juego
        inputFx("Efecto1.wav");//cuando completas fila
        inputFx("Efecto2.wav");//botones velocidad
        inputFx("Efecto3.wav");//botones seleccion dificultad medio y alto
        inputFx("Efecto4.wav");// grafico y grafico total
        inputFx("Efecto5.wav");//cuenta score cuando bajas
        inputFx("Efecto6.wav");//game over


    }

    public void inputFx(String Ruta) {

        try {

            File file = new File("").getAbsoluteFile();

            String rutt = file + "/effects/" + Ruta;

            file = new File(rutt);

            EFX.add(file);

        } catch (NullPointerException e2) {
            System.out.println("Error la ruta o archivo no encontrado de audio....");
        }


    }

    public void Fx(int index) {

        try {

            File file = EFX.get(index);

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
