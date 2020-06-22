package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;



public abstract class Display { // definicion de interfaz display qiue muestra la hora y el puntaje

	GuiController pantalla;
//	DisplayPuntuacion puntuacion ;
	
	public Display(GuiController pantalla) {

		
		this.pantalla = pantalla;
	}
      
	
	
	
}
