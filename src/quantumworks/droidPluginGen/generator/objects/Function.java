package quantumworks.droidPluginGen.generator.objects;

import quantumworks.droidPluginGen.generator.objects.function.Statement;
import android.os.Bundle;

public class Function {
	public Statement[] statements;
	public Function(Statement[] actions){
		this.statements[0]=new Statement(){};
	}
}
