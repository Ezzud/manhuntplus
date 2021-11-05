package fr.ezzud.hunting.api.methods;

import java.util.List;

import fr.ezzud.hunting.Main;

public class manhuntTeam {
	static Main plugin = Main.getInstance();
	private List<String> team1;
	private List<String> team2;
	private List<String> guards;
	private List<String> spectators;
	private String speedrunner; 
	
	public manhuntTeam() {
		this.team1 = plugin.getConfig().getStringList("team1");
		this.team2 = plugin.getConfig().getStringList("team2");
		this.guards = plugin.getConfig().getStringList("guards");
		this.spectators = plugin.getConfig().getStringList("spectators");
		this.speedrunner = plugin.getConfig().getString("hunted");
	}
	
	
	public List<String> getTeam1() {
		return this.team1;
	}
	
	public List<String> getTeam2() {
		return this.team2;
	}
	
	public List<String> getTeamGuard() {
		return this.guards;
	}
	
	public List<String> getTeamSpectator() {
		return this.spectators;
	}
	
	public String getSpeedrunner() {
		return this.speedrunner;
	}
}
