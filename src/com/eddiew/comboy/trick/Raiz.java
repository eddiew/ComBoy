package com.eddiew.comboy.trick;

import java.util.Random;

public class Raiz extends Trick {

	public Raiz() {
		super();
		typeName = "Raiz";
		validEnds.add("Back");
		validEnds.add("Back");
		validEnds.add("Doubleleg");
		validEnds.add("Hook");
		validEnds.add("Left");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Round");
	}
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(endName == "Back"){
			int nHands = new Random().nextInt(3);
			if (nHands == 0) trickName = "Raiz";
			else if (nHands == 1) trickName = "Touchdown Raiz";//no love for sailor moon
			else trickName = "Gumbi";
		}
		else{
			if(nSpins == 0 || nSpins == 1){
				if(endName == "Right"){
					trickName = "Sideswipe";
				}
				else if(endName == "Hook"){
					trickName = "Swipeknife";
				}
				else if(endName == "Doubleleg"){
					trickName = "Envergado";//these are awkward. Force the move to change endings?
				}
				else if(endName == "Round"){
					trickName = "Raiz";
				}
				else {//Left
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
				if(endName == "Doubleleg") trickName += " Doubleleg";
				if(endName == "Hook") trickName += " Hyper Hook";
				if(endName == "Round") trickName += " Round";
			}
		}
	}
}
