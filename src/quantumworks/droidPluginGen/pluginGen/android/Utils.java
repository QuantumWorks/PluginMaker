package quantumworks.droidPluginGen.pluginGen.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;

public class Utils{
	public final static File dir(){
		File r = new File(Environment.getExternalStorageDirectory()+"PluginGen/");
		r.mkdirs();
		return r;
	}
	public final static File projectDir(){
		File r = new File(dir(), "projects/");
		r.mkdir();
		return r;
	}
	public final static File config(){
		File r= new File(dir(), "config.cfg");
		initConfig();
		return r;
	}
	private final static void initConfig(File f){
		OutputStreamWriter osw = null;
		try{
			f.createNewFile();
			osw = new OutputStreamWriter(new FileOutputStream(f));
		}finally{
			try{
				osw.close();
			}catch(IOException e){}
		}
		
	}
}
