package com.eddiew.comboy.trick;

import java.util.Random;

public class Raiz extends Trick {

	public Raiz() {
		super();
		typeName = "Raiz";
		validEnds.add("Back");
		validEnds.add("Carry");
		validEnds.add("Doubleleg");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("Swing");
		validEnds.add("Swing");
		validEnds.add("Swing");
	}
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(endName == "Back" || endName == "Swing"){//nobody's going to do a snapu -> double full. Right?
			int nHands = new Random().nextInt(4);
			if (nHands == 0) trickName = "Raiz";
			else if(nHands < 3) trickName = "Touchdown Raiz";
			else trickName = "Gumbi";
		}
		else if(nSpins == 0 || nSpins == 1){
			if(endName == "Back" || endName == "Swing"){
				int nHands = new Random().nextInt(4);
				if (nHands == 0) trickName = "Raiz";
				else if(nHands < 3) trickName = "Touchdown Raiz";
				else trickName = "Gumbi";
			}
			else if(endName == "Carry" || endName == "Right" || endName == "Step") trickName = "Sideswipe";
			else if(endName == "Doubleleg") trickName = "Envergado";
			else if(endName == "Left") trickName = "Raiz";
		}
		else{
			if(endName == "Right" || endName == "Step" || endName == "Carry") trickName = "Hyper ";
			trickName += "Cheat " + Integer.toString(nSpins*360) + " Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
		}
	}
}
