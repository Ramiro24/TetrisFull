package com.tetris.model.Observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class guiPuntuationObserver implements Observer, Initializable { // OBSERVADOR DE PUNTUACION

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane punctuation;

    @FXML
    private Text title;

    @FXML
    private ListView<Integer> listView;

    private static ArrayList<Integer> points = new ArrayList<Integer>();

    private Subject filesObserving;

    private int puntuacion;


    public guiPuntuationObserver() {
    }

    public guiPuntuationObserver(Subject filesObserving, guiPuntuationObserver s) {
        this.filesObserving = filesObserving;
        filesObserving.registerObserver((Observer) s);
    }

    public void initialize(URL location, ResourceBundle resources) {
        readData();
        Collections.sort(points);
        Collections.reverse(points);
        listView.getItems().addAll(points);
    }

    /*
     * Comparar los valores
     */
    public void control() {
        for (int i = 0; i < points.size(); i++) {
try {
    if (puntuacion > points.get(i)) {
        System.out.println(points.size());

        points.set(i, puntuacion);
        listView.getItems().clear();
        Collections.sort(points);
        Collections.reverse(points);
        listView.getItems().addAll(points);

    }
}catch (Exception e){
    System.out.println("error del if");
}

        }

       /* if (puntuacion > points.get(0)) {
            points.set(0, puntuacion);
            lista.getItems().clear();
            Collections.sort(points);
            Collections.reverse(points);
            lista.getItems().addAll(points);
        }
        if (puntuacion > points.get(1)) {
            points.set(1, puntuacion);
            lista.getItems().clear();
            Collections.sort(points);
            Collections.reverse(points);
            lista.getItems().addAll(points);
        }
        if (puntuacion > points.get(2)) {
            points.set(2, puntuacion);
            lista.getItems().clear();
            Collections.sort(points);
            Collections.reverse(points);
            lista.getItems().addAll(points);
        }*/
    }

    public ArrayList<Integer> readData() {
        Scanner INPUT_STREAM;

        try {

            File file = new File("log1.txt");
            INPUT_STREAM = new Scanner(file);

            while (INPUT_STREAM.hasNext()) {

                String line = INPUT_STREAM.next();
                String[] temp = line.split("\n");
                StringBuffer cadena = new StringBuffer();
                for (int x = 0; x < temp.length; x++) {
                    cadena = cadena.append(temp[x]);
                }
                String str = cadena.toString();
                int i = Integer.parseInt(str);
                points.add(i);

            }
            INPUT_STREAM.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return points;
    }

    public static void saveData() {
        String filepath = "log1.txt";
        String value = "";

        try {
            FileWriter fw = new FileWriter(filepath, true);
            fw.close();
            //BufferedWriter bw = new BufferedWriter(fw);
            File file = new File(filepath);
            file.delete();
            File file1 = new File(filepath);
            FileWriter fw1 = new FileWriter(filepath, true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);
            for (int i = 0; i < points.size(); i++) {
                pw1.println(points.get(i));
                pw1.flush();
            }

            pw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void Update(int value) {
        // TODO Auto-generated method stub

        puntuacion = value;
        try {
            control();
        }catch (Exception e){
            System.out.println("error en update");
            e.printStackTrace();
        }
        System.out.println("valor");
    }
}