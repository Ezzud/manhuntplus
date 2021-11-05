package fr.ezzud.hunting.api.events;

import java.util.Date;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.ezzud.hunting.api.methods.manhuntSpeedrunner;
import fr.ezzud.hunting.api.methods.manhuntTeam;
import fr.ezzud.hunting.api.methods.manhuntTeamManager;

public class manhuntGameStopEvent extends Event {
	private manhuntTeam team1;
	private manhuntTeam team2;
	private manhuntTeam guards;
	private manhuntTeam spectators;
	private manhuntSpeedrunner speedrunner;
	private Date endDate; 
	
	public manhuntGameStopEvent(manhuntTeamManager teams) {
		this.team1 = teams.getTeam1();
		this.team2 = teams.getTeam2();
		this.guards = teams.getTeamGuard();
		this.spectators = teams.getTeamSpectator();
		this.speedrunner = teams.getSpeedrunner();
		this.endDate = new Date();
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
	
	public manhuntSpeedrunner getSpeedrunner() {
		return this.speedrunner;
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
