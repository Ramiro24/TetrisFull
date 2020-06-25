package com.tetris.model.music;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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


        IntroducirFx("Efecto0.wav", 2);//comienco juego
        IntroducirFx("Efecto1.wav", 3);//cuando completas fila
        IntroducirFx("Efecto2.wav", 4);//botones velocidad
        IntroducirFx("Efecto3.wav", 5);//botones seleccion dificultad medio y alto
        IntroducirFx("Efecto4.wav", 6);// grafico y grafico total
        IntroducirFx("Efecto5.wav", 7);//cuenta score cuando bajas
        IntroducirFx("Efecto6.wav",8);//game over


    }

    public void IntroducirFx(String Ruta, int i) {

    	
    	InputStream inputStream = this.getClass().getResourceAsStream("/effects/" + Ruta);
        File targetFile = new File(System.getProperty("user.home") + "/targetFile"+i+".tmp");

        
        try {
            Files.copy(
                    inputStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            EFX.add(targetFile);

        } catch (NullPointerException | IOException e2) {
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
