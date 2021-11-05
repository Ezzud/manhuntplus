package fr.ezzud.hunting.api.methods;

import fr.ezzud.hunting.Main;

public class manhuntGameState {
	static Main plugin = Main.getInstance();
	private Boolean GameState; 
	
	public manhuntGameState() {
		this.GameState = Main.GameState;
	}
	
	
	public Boolean getGameState() {
		return this.GameState;
	}

}
