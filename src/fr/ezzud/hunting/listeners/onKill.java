package fr.ezzud.hunting.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.ezzud.hunting.Main;

public class onKill implements Listener {
    Main plugin;
    
    public onKill(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void onPlayerKill(PlayerDeathEvent e)
	   {
		   if (e.getEntity() instanceof Player) {
			   String killed = e.getEntity().getName();
			   Player killer = e.getEntity().getKiller();
			   if(killed.equals(plugin.getConfig().getString("hunted"))) {
				   Main.GameState = false;
				   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_huntedKilled").replaceAll("%player%", killed)));
			    	  ArrayList<?> list2 = new ArrayList<>(Bukkit.getOnlinePlayers());
			    	  list2.forEach((p) -> {
			    		  Player player = ((Player) p);
			    		  player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 100, 100);
			    		  player.setGameMode(GameMode.SPECTATOR);
			    		  player.setDisplayName(ChatColor.RESET + player.getName());
			    		  player.setPlayerListName(ChatColor.RESET + player.getName());
			    	  });
			    if(e.getEntity().getKiller() instanceof Player) {
				       Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

				       while(team1Var.hasNext()) {
				    	   String member = (String)team1Var.next();
				    	   if(member.equals(killer.getName())) {
				    		   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_victory").replaceAll("%teamColor%", plugin.getConfig().getString("team1Color")).replaceAll("%team%", plugin.getConfig().getString("team1name")))); 
				               
				    		   Iterator<?> team1Var2 = plugin.getConfig().getStringList("team1").iterator();
				    		   while(team1Var2.hasNext()) {
						    	   String pl = (String)team1Var2.next();
					    		   Iterator<?> var7 = plugin.getConfig().getStringList("winCommands").iterator();
					               while(var7.hasNext()) {
					                  String consolecommand = (String)var7.next();
					                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", pl));
					               }
				    		   }
				    	   }
				       }					   
					   
					   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

			           while(team2Var.hasNext()) {
			               String member = (String)team2Var.next();
			               if(member.equals(killer.getName())) {
			            	   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_victory").replaceAll("%teamColor%", plugin.getConfig().getString("team2Color")).replaceAll("%team%", plugin.getConfig().getString("team2name")))); 
				    		   Iterator<?> team2Var2 = plugin.getConfig().getStringList("team2").iterator();
				    		   while(team2Var2.hasNext()) {
						    	   String pl = (String)team2Var2.next();
					    		   Iterator<?> var7 = plugin.getConfig().getStringList("winCommands").iterator();
					               while(var7.hasNext()) {
					                  String consolecommand = (String)var7.next();
					                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", pl));
					               }
				    		   }
			            	   
			               }
			           }	
			    }
	    		   Iterator<?> var7 = plugin.getConfig().getStringList("defeatCommands").iterator();
	               while(var7.hasNext()) {
	                  String consolecommand = (String)var7.next();
	                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", killed));
	               }
		           
		           
			   } 
		           
			   } else {
				   String killed = e.getEntity().getName();
				   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_huntedKilled").replaceAll("%player%", killed)));
			   }
		   
	   }
}
