package com.eddiew.comboy.trick;

import java.util.Random;

public class Raiz extends Trick {

	public Raiz() {
		super();
		typeName = "Raiz";
		validEnds.add("Back");
	}
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0 || nSpins == 1){
			if(endName == "Right"){
				trickName = "Sideswipe";
			}
			else {
				int nHands = new Random().nextInt(3);
				if (nHands == 0) trickName = "Raiz";
				else if (nHands == 1) trickName = "Touchdown Raiz";//no love for sailor moon
				else trickName = "Gumbi";
			}
		}
		else{
			int degrees = nSpins*360;
			if(endName == "Right") trickName = "Hyper ";
			trickName += "Cheat " + Integer.toString(degrees) + " Twist";
		}
	}
}
