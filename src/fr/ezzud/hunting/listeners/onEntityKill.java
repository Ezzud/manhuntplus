package fr.ezzud.hunting.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.ezzud.hunting.Main;

public class onEntityKill implements Listener {
    Main plugin;
    
    public onEntityKill(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void EntityKill(EntityDeathEvent event) {
	        Entity entity = event.getEntity();
	            if (entity.getType().equals(EntityType.ENDER_DRAGON)) {
			    	  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_enderdragon_win").replaceAll("%player%", plugin.getConfig().getString("hunted"))));
			    	  ArrayList<?> list2 = new ArrayList<>(Bukkit.getOnlinePlayers());
			    	  list2.forEach((pl) -> {
			    		  Player player = ((Player) pl);
			    		  player.setGameMode(GameMode.SPECTATOR);
			    		  player.setPlayerListName(ChatColor.RESET + player.getName());

			    	  });
		    		   Iterator<?> var7 = plugin.getConfig().getStringList("winCommands").iterator();
		               while(var7.hasNext()) {
		                  String consolecommand = (String)var7.next();
		                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", plugin.getConfig().getString("hunted")));
		               }
					   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();
			           while(team1Var.hasNext()) {
			               String member = (String)team1Var.next();
			    		   Iterator<?> var71 = plugin.getConfig().getStringList("defeatCommands").iterator();
			               while(var71.hasNext()) {
			                  String consolecommand = (String)var71.next();
			                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", member));
			               }
			           }
					   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();
			           while(team2Var.hasNext()) {
			               String member = (String)team2Var.next();
			    		   Iterator<?> var72 = plugin.getConfig().getStringList("defeatCommands").iterator();
			               while(var72.hasNext()) {
			                  String consolecommand = (String)var72.next();
			                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consolecommand.replace("%player%", member));
			               }
			           }
	            }
	   }
}
