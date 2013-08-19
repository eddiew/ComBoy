package com.eddiew.comboy.trick;


public class Hook extends Trick {//include scoots here too?

	public Hook() {
		super();
		typeName = "Hook";
		validEnds.add("Hook");
	}
	@Override
	public void complete(int difficulty){
		endName = "Hook";
		trickName = "Hook Kick";
	}

}
