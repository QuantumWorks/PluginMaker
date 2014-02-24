package quantumworks.droidPluginGen.generator.objects;

public class Command {
	public String cmd, help;
	public boolean whitelist;
	public Function callback;
	public Command(String cmd, String help, boolean whitelist, Function fx) {
		this.cmd=cmd;
		this.help=help;
		this.whitelist=whitelist;
		this.callback=fx;
	}
}
