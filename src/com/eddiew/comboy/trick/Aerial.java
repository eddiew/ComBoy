package com.eddiew.comboy.trick;

import java.util.Random;

public class Aerial extends Trick {
	
	public Aerial(){
		super();
		typeName = "Aerial";
//		validEnds.add("Back");
		validEnds.add("Front");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(endName == "Front" || (endName == "Right" && nSpins == 0)){
			int nHands = new Random().nextInt(3);
			if (nHands == 0) trickName = "Aerial";
			else if(nHands == 0) trickName = "Vanek";//make this less common
			else trickName = "Cartwheel";//make this more common
		}
		else{
			if(nSpins == 0){
				trickName = "Aerial Switch";
			}
			else{
				if(endName == "Right") trickName = "Hyper ";
				if(nSpins == 2) trickName += "Double ";
				else if(nSpins == 3) trickName += "Triple ";
				trickName += "Aerial Twist";
			}
		}
	}
}
