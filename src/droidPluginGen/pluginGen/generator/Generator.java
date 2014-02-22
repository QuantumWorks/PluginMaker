package droidPluginGen.pluginGen.generator;

import java.io.*;

public class Generator extends Object{
	public Event[] events;
	public Generator(){
		
	}
	public boolean addEvent(String event, Function fx){
		return addEvent(new Event(event, fx));
	}
	public boolean addEvent(Event event){
		for(int i=0; i<events.length; i++){
			if(events[i].equals(event)){
				return false;
		}
		events[events.length]=event;
		return true;
	}
	@Override public String toString(){
		
	}
	public void outWrite (OutputStreamWriter osw) throws IOException{
		
	}
}
