package com.eddiew.comboy.trick;

import java.util.Random;

public class Aerial extends Trick {
	
	public Aerial(){
		super();
		typeName = "Aerial";
		validEnds.add("Back");
		validEnds.add("Carry");
		validEnds.add("Front");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("Swing");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(endName == "Front" || (endName == "Carry" && nSpins == 0)){
			int nHands = new Random().nextInt(10);//frequency: cartwheel > aerial > vanek
			if (nHands < 3) trickName = "Aerial";
			else if(nHands < 5) trickName = "Vanek";
			else trickName = "Cartwheel";
		}
		else if(endName == "Back"){
			int nHands = new Random().nextInt(10);
			if(nHands < 3) trickName = "Roundoff";
			else trickName = "Brandy";
		}
		else{
			if(nSpins == 0){
				if(endName == "Left" || endName == "Swing") trickName = "Aerial Switch";
				else if(endName == "Right" || endName == "Step") trickName = "Aerial";//somehow make this less common
			}
			else{
				if(endName == "Right" || endName == "Step") trickName = "Hyper ";
				if(nSpins == 2) trickName += "Double ";
				else if(nSpins == 3) trickName += "Triple ";
				trickName += "Aerial Twist";
			}
		}
	}
}
