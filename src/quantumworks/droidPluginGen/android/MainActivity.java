package quantumworks.droidPluginGen.android;

import quantumworks.droidPluginGen.pluginGen.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements android.view.View.OnClickListener{

	private final static int ID_BUTTON_NEWPROJECT = 0x80000000,
			ID_BUTTON_OPENPROJECT = 0x80000001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkIO();
		setContentView(getLayout());
	}

	private android.view.View getLayout(){
		LinearLayout r = new LinearLayout(this);
		Button newProject = new Button(this);
		Button openProject = new Button(this);
		r.setOrientation(LinearLayout.VERTICAL);
		newProject.setText(R.string.MainActivity_newProject);
		openProject.setText(R.string.MainActivity_openProject);
		newProject.setId(ID_BUTTON_NEWPROJECT);
		openProject.setId(ID_BUTTON_OPENPROJECT);
		newProject.setOnClickListener(this);
		openProject.setOnClickListener(this);
		r.addView(newProject, Utils.flat);
		r.addView(openProject, Utils.flat);
		return r;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case ID_BUTTON_NEWPROJECT:
			startActivity(new Intent(this, ProjectCreator.class));
			break;
		case ID_BUTTON_OPENPROJECT:
			startActivity(new Intent(this, ProjectSelector.class));
			break;
		}
	}

	public void checkIO(){
		if(Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED){
			Toast.makeText(this, R.string.noIO, Toast.LENGTH_LONG).show();
			finish();
		}
		Utils.config();
		Utils.projectDir();
	}

}
