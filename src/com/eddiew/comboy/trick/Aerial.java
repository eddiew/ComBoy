package com.eddiew.comboy.trick;

import java.util.Random;

public class Aerial extends Trick {
	
	public Aerial(){
		super();
		typeName = "Aerial";
//		validEnds.add("Back");
		validEnds.add("Front");//because this is so much more common
		validEnds.add("Front");
		validEnds.add("Front");
		validEnds.add("Hook");
		validEnds.add("Left");
		validEnds.add("Left");
		validEnds.add("Round");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(endName == "Front" || (endName == "Right" && nSpins == 0)){
			int nHands = new Random().nextInt(10);//frequency: cartwheel > aerial > vanek
			if (nHands < 3) trickName = "Aerial";
			else if(nHands < 5) trickName = "Vanek";
			else trickName = "Cartwheel";
		}
		else{
			if(nSpins == 0){
				if(endName == "Left") trickName = "Aerial Switch";
				else if(endName == "Round") trickName = "Aerial Twist Round";
				else if(endName == "Right") ;
				else if(endName == "Hook") trickName = "Aerial Knife";
			}
			else{
				if(endName == "Right") trickName = "Hyper ";
				if(nSpins == 2) trickName += "Double ";
				else if(nSpins == 3) trickName += "Triple ";
				trickName += "Aerial Twist";
				if(endName == "Round") trickName += " Round";
				else if(endName == "Hook") trickName += " Hyper Hook";
			}
		}
	}
}
