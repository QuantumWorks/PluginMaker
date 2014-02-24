package quantumworks.droidPluginGen.generator;

import quantumworks.droidPluginGen.generator.objects.Command;
import quantumworks.droidPluginGen.generator.objects.Event;
import quantumworks.droidPluginGen.generator.objects.Function;
import android.os.Bundle;


public class Generator extends Object{
	private Bundle args;
	public Event[] events;
	public Command[] cmds;
	public Generator(Bundle args){
		this.args=args;
	}
	public boolean addEvent(String event, Function fx){
		return addEvent(new Event(event, fx));
	}
	public boolean addEvent(Event event){
		for(int i=0; i<events.length; i++){
			if(events[i].equals(event))
				return false;
		}
		events[events.length]=event;
		return true;
	}
	public boolean addCmd(String cmd, String help, boolean whitelist, Function fx){
		return addCmd(new Command(cmd, help, whitelist, fx));
	}
	public boolean addCmd(Command cmd){
		for(int i=0; i<cmds.length; i++){
			if(cmds[i].equals(cmd))
				return false;
		}
		cmds[cmds.length]=cmd;
		return true;
	}
	@Override public String toString(){
		//TODO Eclipse-satisfactory stuff
		return null;
	}
	public void outWrite (java.io.OutputStreamWriter osw) throws java.io.IOException{
		osw.write(toString());
	}
}
