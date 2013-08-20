package com.eddiew.comboy.trick;


public class Hook extends Trick {//Misslegs/stepovers?

	public Hook() {
		super();
		typeName = "Hook";
		validEnds.add("Step");
		validEnds.add("Right");
	}
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "Hook Kick";
	}

}
