package com.eddiew.comboy.trick;

import java.util.Random;

public class Butterfly extends Trick {

	public Butterfly() {
		super();
		typeName = "Butterfly";
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0){
			if(endName == "Left"){
				boolean hands = new Random().nextBoolean();
				if (hands) trickName = "Monkey";
				else trickName = "Butterfly Twist";//could be spider, but eww
			}
			else if(endName == "Right"){
				trickName = "Butterfly Kick";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Butterfly Twist";
		}
	}
}
