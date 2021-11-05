package fr.ezzud.hunting.api.methods;

import fr.ezzud.hunting.Main;

public class manhuntSpeedrunner {
	static Main plugin = Main.getInstance();
	private String color;
	private String members;
	private String name; 
	
	public manhuntSpeedrunner() {
		this.members = plugin.getConfig().getString("hunted");
		this.name = plugin.getConfig().getString("speedrunnername");
		this.color = plugin.getConfig().getString("huntedColor");
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public String getSpeedrunnerName() {
		return this.members;
	}
	
}
