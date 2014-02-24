package quantumworks.droidPluginGen.pluginGen.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ProjectMainView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_main_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.project_main_view, menu);
		return true;
	}

}
