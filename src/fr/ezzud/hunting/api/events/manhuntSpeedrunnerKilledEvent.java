package fr.ezzud.hunting.api.events;

import java.util.Date;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.ezzud.hunting.api.methods.manhuntSpeedrunner;
import fr.ezzud.hunting.api.methods.manhuntTeam;
import fr.ezzud.hunting.api.methods.manhuntTeamManager;

public class manhuntSpeedrunnerKilledEvent extends Event {

	private manhuntTeam winnerTeam;
	private manhuntTeam looserTeam;
	private Date endDate;
	private manhuntSpeedrunner speedrunner;
	private manhuntTeam team1;
	private manhuntTeam team2;
	private manhuntTeam guards;
	private manhuntTeam spectators;
	public manhuntSpeedrunnerKilledEvent(manhuntTeam winnerTeam, manhuntTeam looserTeam) {
		this.speedrunner = new manhuntTeamManager().getSpeedrunner();
		this.endDate = new Date();
		this.team1 =  new manhuntTeamManager().getTeam1();
		this.team2 =  new manhuntTeamManager().getTeam2();
		this.guards =  new manhuntTeamManager().getTeamGuard();
		this.spectators =  new manhuntTeamManager().getTeamSpectator();
		this.winnerTeam = winnerTeam;
		this.looserTeam = looserTeam;
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
	
	public manhuntTeam getWinner() {
		return this.winnerTeam;
	}
	
	public manhuntTeam getLooser() {
		return this.looserTeam;
	}

	
	public Date getEndDate() {
		return this.endDate;
	}
	
	private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    
}
