package com.eddiew.comboy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Combo extends ScrollView implements OnGestureListener{
	//variables for scrolling/view selection
	private GestureDetector gestureDetector;
	private float prevY;
    //view stuff ABSTRACT THIS OUT SOME TIME
    private LinearLayout layout;
	//everything else
	private Random random;
	public ArrayList<Trick> trickList;
	private Stack<Trick> newTricks;
    public String comboName;
	//private int difficulty;
	private static final String trickClassPackage = "com.eddiew.comboy.trick.";
	private static final ArrayList<String> allTypes = new ArrayList<String>();
	private static final HashMap<String, ArrayList<String>> validTypes = new HashMap<String, ArrayList<String>>();
	private static final HashMap<String, String> actualTransitionNames = new HashMap<String, String>();
	static{
		//list of all tricks
		allTypes.add("Aerial");
		allTypes.add("Back");
		allTypes.add("Backside");
		allTypes.add("Butterfly");
		allTypes.add("Carry");
		allTypes.add("Cheat");
		allTypes.add("Front");
		allTypes.add("Gainer");
		allTypes.add("Hook");
		allTypes.add("Pop");
		allTypes.add("Raiz");
		allTypes.add("Swing");
		allTypes.add("Wrap");
		
		//valid types given a transition. Change lists of strings to lists of string, int (relative probability) pairs?
        //Add some sort of preference loading here
        addTransition("Left", "Vanish");
        addTypeToTransition("Left", "Cheat", 1);
        addTypeToTransition("Left", "Pop", 1);

		addTransition("Right", "Vanish");
        addTypeToTransition("Right","Aerial",1);
        addTypeToTransition("Right","Backside",1);
        addTypeToTransition("Right","Butterfly",1);

        addTransition("Swing", "Swing Through");
        addTypeToTransition("Swing", "Gainer", 1);

        addTransition("Carry", "Carry Through");
        addTypeToTransition("Carry", "Raiz", 1);
        addTypeToTransition("Carry", "Wrap", 1);

        addTransition("Back", "Punch");
        addTypeToTransition("Back", "Back", 1);

        addTransition("Front", "Punch");
        addTypeToTransition("Front", "Front", 1);
		
		//Step-transitions
		//step.add("Cheat");
		//step.add("Hook");//step-over
        addTransition("Step", "Step");
        addTypeToTransition("Step", "Raiz", 1);

        addTransition("Hook", "Step");
        addTypeToTransition("Hook", "Hook", 1);
		
		//rarer, non-inverted transitions
        addTransition("Doubleleg", "Punch");
        addTypeToTransition("Doubleleg", "Backside", 1);
		
		//non-inverted carry
        addTransition("Wrap", "Wrap Through");
        addTypeToTransition("Wrap", "Carry", 1);

        //non-inverted swing
        addTransition("vSwing", "Swing Through");
        addTypeToTransition("vSwing", "Swing", 1);
	}
	
	public Combo(Context context){
		super(context);
		trickList = new ArrayList<Trick>();
		random = new Random();
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		addView(layout);
		gestureDetector = new GestureDetector(context, this);
        comboName = "New Combo";
	}

    public Combo(Context context, String jsonTricks) throws JSONException {
        this(context);
        JSONArray tricks = new JSONArray(jsonTricks);
        comboName = tricks.getString(0);
        for(int idx = 1; idx < tricks.length(); idx++){
            JSONObject jsonTrick = tricks.getJSONObject(idx);
            Trick trick = new Trick();
            try {
                trick = (Trick)(Class.forName(trickClassPackage + jsonTrick.getString("typeName")).getConstructor().newInstance());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            trick.trickName = jsonTrick.getString("trickName");
            trick.endName = jsonTrick.getString("endName");
            try{
            	trick.transitionName = jsonTrick.getString("transitionName");
            }
            catch (JSONException e){
            	trick.transitionName = "";
            }
            trickList.add(trick);
            TrickView trickView = new TrickView(context, layout, trick);
            layout.addView(trickView);
        }
    }

    public static void addTransition(String transitionName, String alias){
        ArrayList<String> types = new ArrayList<String>();
        validTypes.put(transitionName,types);
        actualTransitionNames.put(transitionName, alias);
    }

    public static void addTypeToTransition(String transitionName, String type, int freq){
        for(int n = 0; n < freq; n++){
            validTypes.get(transitionName).add(type);
        }
    }
	
	//allows user to manually add a move
	public void addTrick(int index){
		//check if new move is valid (check prev move -> this move -> next move)
	}
	
	private int gaussianDifficulty(int difficulty){
		int rawDifficulty = difficulty + (int)(random.nextGaussian()*(float)difficulty/3f);
		if(rawDifficulty < 0) rawDifficulty = 0;
		if(rawDifficulty > 10) rawDifficulty = 10;
		return rawDifficulty;
	}
	
	private boolean canFlow(String end, String type){
		return validTypes.get(end).contains(type);
	}
	
	private boolean makeSequence(String prevEnd, String nextType, int length){
		if(length == 0) return true;
		ArrayList<String> possibleTypes = new ArrayList<String>();
		if(prevEnd != null){
			for(String type : validTypes.get(prevEnd)){
				possibleTypes.add(type);
			}
		}
		else {
			for(String type : allTypes){
				possibleTypes.add(type);
			}
		}
		while(!possibleTypes.isEmpty()){
			int typeIdx = random.nextInt(possibleTypes.size());
			String trickType = possibleTypes.get(typeIdx);
			possibleTypes.remove(trickType);
			Trick newTrick = new Trick();
			try {
                newTrick = (Trick)(Class.forName(trickClassPackage + trickType).getConstructor().newInstance());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			//get list of valid endings
			ArrayList<String> ends = new ArrayList<String>();
			for(String end : newTrick.validEnds){
				if(length != 1 || nextType == null || canFlow(end, nextType)){
					ends.add(end);
				}
			}
			while(!ends.isEmpty()){
				String end = ends.get(random.nextInt(ends.size()));
				ends.remove(end);
				//do the bare minimum to ensure trick flow
				newTrick.endName = end;
				newTrick.setTransitionName(actualTransitionNames.get(prevEnd));//this doesn't need to be here, but it's not as easy to do anywhere else
				newTricks.push(newTrick);
				if(makeSequence(newTrick.endName, nextType, length-1)){
					return true;
				}
				else{
					newTricks.pop();
				}
			}
		}
		return false;
	}
	
	public void addTricks(int index, int length, int difficulty){
		newTricks = new Stack<Trick>();
		String prevEnd = null, nextType = null;
		if(index != 0){
			prevEnd = trickList.get(index-1).endName;
		}
		if(index < trickList.size()-1){
			nextType = trickList.get(index).typeName;
		}
		if(makeSequence(prevEnd, nextType, length)){
            //update the next trick's transition to follow the last trick we add
            if(nextType != null){
                trickList.get(index).setTransitionName(actualTransitionNames.get(newTricks.peek().endName));
                TrickView nextView = new TrickView(getContext(), layout, trickList.get(index));
                layout.removeViewAt(index);
                layout.addView(nextView, index);
            }
			int i = 0;//for view insertion to the right spots
			for(Trick trick : newTricks){
				trick.complete(gaussianDifficulty(difficulty));
				TrickView newView = new TrickView(getContext(), layout, trick);
				layout.addView(newView, index+i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				i++;
			}
			trickList.addAll(index, newTricks);
		}
		else{
			//there isn't a combo of the specified length that can join the two moves together
			return;
		}
	}
	public void clear(){
		trickList.clear();
		layout.removeAllViews();
	}
	//Touch event handling
	@Override
	public boolean onTouchEvent(MotionEvent event){
		return gestureDetector.onTouchEvent(event);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event){
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float vX, float vY) {
		fling((int)-vY);
		prevY = -1;
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent motionStart, MotionEvent event, float arg2, float arg3) {
		if(prevY == -1) prevY = motionStart.getY();
		smoothScrollBy(0,(int)(prevY - event.getY()));
		prevY = event.getY();
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
