package com.eddiew.comboy.trick;

import java.util.ArrayList;
import java.util.Random;

public class Trick{
	public String typeName, trickName, endName;
	public ArrayList<String> validEnds;
	public Trick(){
		typeName = "";
		trickName = "";
		endName = "";
		validEnds = new ArrayList<String>();
		validEnds.add("Left");
		validEnds.add("Right");
	}
	public String getTrickName(){
		return trickName;
	}
	public String getTypeName(){
		return typeName;
	}
	public ArrayList<String> getEnds(){
		return validEnds;
	}
	//override to allow for custom naming / variations
	public void complete(int difficulty){
		int endIdx = new Random().nextInt(validEnds.size());
		endName = validEnds.get(endIdx);
		trickName = typeName + ' ' + endName;
	}
}
