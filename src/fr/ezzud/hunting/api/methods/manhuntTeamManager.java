package fr.ezzud.hunting.api.methods;

public class manhuntTeamManager {
	private manhuntSpeedrunner speedrunner;
	private manhuntTeam team1;
	private manhuntTeam team2;
	private manhuntTeam guards;
	private manhuntTeam spectators;
	
	public manhuntTeamManager() {
		this.speedrunner = new manhuntSpeedrunner();
		this.team1 =  new manhuntTeam("team1");
		this.team2 =  new manhuntTeam("team2");
		this.guards =  new manhuntTeam("guard");
		this.spectators =  new manhuntTeam("spectator");
	}
	
	public manhuntSpeedrunner getSpeedrunner() {
		return this.speedrunner;
	}
	
	public manhuntTeam getTeam1() {
		return this.team1;
	}
	
	public manhuntTeam getTeam2() {
		return this.team2;
	}
	
	public manhuntTeam getTeamGuard() {
		return this.guards;
	}
	
	public manhuntTeam getTeamSpectator() {
		return this.spectators;
	}
}
