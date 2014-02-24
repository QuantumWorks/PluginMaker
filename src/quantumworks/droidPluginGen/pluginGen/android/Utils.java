package quantumworks.droidPluginGen.pluginGen.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup.LayoutParams;

public class Utils{
	public final static LayoutParams flat = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT),
			wrap = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	public final static String BUNDLE_PROPERTY_NAME = "name",
			BUNDLE_PROPERTY_VALUE = "value",
			BUNDLE_LINE_NUMBER = "line",
			BUNDLE_PROPERTY_RAW = "raw";
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
		File r= new File(dir(), "config.properties");
		initConfig(r);
		return r;
	}
	private final static void initConfig(File f){
		if(f.isFile())
			return;
		OutputStreamWriter osw = null;
		try{
			f.createNewFile();
			osw = new OutputStreamWriter(new FileOutputStream(f));
			osw.write("#CONFIG FILE OF PluginGen. Do NOT modify.\n");
			//NOTE: Always leave new line at the end.
		}catch(IOException e){
		}finally{
			if(osw != null){
				try{
					osw.close();
				}catch(IOException e){
				}
			}
		}
	}
	public static Bundle[] getConfig()throws IOException{
		String[] lines = readAll(config()).split("\n");
		Bundle first = new Bundle();
		first.putStringArray(BUNDLE_PROPERTY_RAW, lines);
		Bundle[] data = {};
		for(int i = 0; i < lines.length; i++){
			String line = lines[i];
			while(line.startsWith(" "))
				line = line.substring(1);
			while(line.startsWith("\t"))
				line = line.substring(1);
			if(line.length() == 0 || line.startsWith("#") || line.startsWith("REM")){
				continue;
			}
			int offset = data.length;
			data[offset] = new Bundle();
			String[] values = line.split("=", 2);
			data[offset].putString(BUNDLE_PROPERTY_NAME, values[0]);
			data[offset].putString(BUNDLE_PROPERTY_VALUE, values[1]);
			data[offset].putInt(BUNDLE_LINE_NUMBER, i);
		}
		return data;
	}
	public static void incrementUsage() throws IOException{
		Bundle[] properties = getConfig();
		String[] raw = properties[0].getStringArray(BUNDLE_PROPERTY_RAW);
		for(int i = 1; i < properties.length; i++){
			if(properties[i].getString(BUNDLE_PROPERTY_NAME) == "usage"){
				String value = properties[i].getString(BUNDLE_PROPERTY_VALUE);
				try{
					int times = Integer.parseInt(value, 16);//QUESTION: The difference between Integer.decode(), Integer.getInteger(), Integer.parseInt(), new Integer(String) and Integer.valueOf()?
					times++;
					String newValue = Integer.toHexString(times);
					raw[properties[i].getInt(BUNDLE_LINE_NUMBER)] = "usage="+newValue;
				}catch(Exception e){
					if(e instanceof NumberFormatException){
						
					}
				}
				
			}
		}
	}
	public static String readAll(File file)throws IOException{
		String ret = "";
		BufferedReader read = new BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file)));
		String line;
		while ((line = read.readLine()) != null) {
			ret += (line + "\n");
		}
		read.close();
		return ret;
	}
}
