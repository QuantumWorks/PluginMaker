package quantumworks.droidPluginGen.pluginGen.android;

import java.io.File;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProjectSelector extends Activity implements View.OnClickListener{

	public static final String INTENT_TAG_PROJECT = "quantumwworks.doroidPluginGen.pluginGen.android.ProjectSelector.project.name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		setupActionBar();
	}

	private android.view.View getLayout(){
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView title = new TextView(this);
		title.setText(R.string.title_activity_project_selector);
		ll.addView(title, Utils.flat);
		Button[] projects = {};
		String[] list = projectList();
		for(int i = 0; i < list.length; i++){
			projects[i] = new Button(this);
			projects[i].setText(list[i]);
			projects[i].setId(0x80001000 + i);
			projects[i].setOnClickListener(this);
			ll.addView(projects[i], Utils.wrap);
		}
		return ll;
	}

	private String[] projectList(){
		File dir = Utils.projectDir();
		String[] ret = {};
		String[] list = dir.list();
		for(int i = 0; i < list.length; i++){
			File project = new File(dir.getAbsolutePath() + list[i]);
			if(!project.isDirectory())
				continue;
			File settings = new File(project.getAbsolutePath() + ".settings/");
			if(settings.isDirectory())
				ret[ret.length] = list[i];
		}
		return ret;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.project_selector, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		startActivity(new android.content.Intent(this, ProjectMainView.class).putExtra(INTENT_TAG_PROJECT, projectList()[v.getId() - 0x80000000]));
	}

}
