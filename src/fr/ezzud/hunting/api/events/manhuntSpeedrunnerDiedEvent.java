package fr.ezzud.hunting.api.events;

import java.util.Date;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.ezzud.hunting.api.methods.manhuntTeam;

public class manhuntSpeedrunnerDiedEvent extends Event {

	private Date endDate;
	private List<String> team1;
	private List<String> team2;
	private List<String> guards;
	private List<String> spectators;
	private String speedrunner;
	public manhuntSpeedrunnerDiedEvent() {
		this.team1 = new manhuntTeam().getTeam1();
		this.team2 = new manhuntTeam().getTeam2();
		this.guards = new manhuntTeam().getTeamGuard();
		this.spectators = new manhuntTeam().getTeamSpectator();
		this.speedrunner = new manhuntTeam().getSpeedrunner();
		this.endDate = new Date();

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
