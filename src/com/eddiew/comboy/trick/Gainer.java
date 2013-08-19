package com.eddiew.comboy.trick;

import java.util.Random;

public class Gainer extends Trick {

	public Gainer() {
		super();
		typeName = "Gainer";
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0){
			if(endName == "Left"){
				boolean hands = new Random().nextBoolean();
				if (hands) trickName = "Valdez";
				else trickName = "Gainer Switch";
			}
			else if(endName == "Right"){
				trickName = "Gainer Flash";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Corkscrew";
		}
	}
}
