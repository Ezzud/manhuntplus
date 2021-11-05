package fr.ezzud.hunting.api.methods;

import java.util.List;

import fr.ezzud.hunting.Main;

public class manhuntTeam {
	static Main plugin = Main.getInstance();
	private String color;
	private List<String> members;
	private String name; 
	
	public manhuntTeam(String team) {
		this.members = plugin.getConfig().getStringList(team);
		this.name = plugin.getConfig().getString(team + "name");
		this.color = plugin.getConfig().getString(team + "Color");
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public List<String> getMembers() {
		return this.members;
	}
	
}
