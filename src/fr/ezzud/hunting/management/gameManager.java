package fr.ezzud.hunting.management;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.ezzud.hunting.Main;

public class gameManager {
	static Main plugin = Main.getInstance(); 
	
	   public void start() {
	    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
	    	  list.forEach((p) -> {
	    		  
	    		  Player player = ((Player) p);
	    		  player.getActivePotionEffects().clear();
	              player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0);
	              Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

	              while(team1Var.hasNext()) {
	                 String member = (String)team1Var.next();
	                 if(member.toString().equals(player.getName())) {
	               	 player.getActivePotionEffects().clear();
	               	 for (Object cItem : player.getActivePotionEffects()) {
	   	  	               String potionEffectName = cItem.toString().split(":")[0];
	   	  	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	   	  	               player.removePotionEffect(effect);
	   	  	           }
	                	 player.getInventory().clear();
	                	 player.setGameMode(GameMode.SURVIVAL);
	                	 player.setHealth(20.0);
	                	 player.setSaturation(20);
	                	 player.setLevel(0);
	                	 player.setExp(0);
	                	 player.setDisplayName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setPlayerListName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);
	                	 player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(plugin.getConfig().getString("team1Coords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("team1Coords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("team1Coords").split(", ")[2])));             	 
	                	 for (String cItem : plugin.getConfig().getStringList("teamItems")) {
	   	             	    String[] asArray = cItem.split(", ");
	   	             	    if(Material.valueOf(asArray[0]) != null ) {
	   	             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
	   		             	    player.getInventory().addItem(item);
	   		             	    	
	   	             	    }
	                	 }
	                  	 for (String cItem : plugin.getConfig().getStringList("teamEffects")) {
	   	               	    String[] asArray = cItem.split(", ");
	   	               	    if(PotionEffectType.getByName(asArray[0]) != null) {
	   		               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
	   		               	    player.addPotionEffect(effect);
	   	               	    }

	   	             }       	 
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +   plugin.getConfig().getString("messages_teamReveal").replaceAll("%team%", plugin.getConfig().getString("team1name")).replaceAll("%teamColor%", plugin.getConfig().getString("team1Color"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +   plugin.getConfig().getString("messages_objectives_team").replaceAll("%player%", plugin.getConfig().getString("hunted"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +  plugin.getConfig().getString("messages_feature_team").replaceAll("%delay%", plugin.getConfig().getString("coordinatesRefreshDelay"))));

	                	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + plugin.getConfig().getString("team1Coords").split(", ")[0]  + " " + plugin.getConfig().getString("team1Coords").split(", ")[1]  + " " + plugin.getConfig().getString("team1Coords").split(", ")[2]);
	                 }
	              }
	              Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	              while(team2Var.hasNext()) {
	                 String member = (String)team2Var.next();
	                 if(member.toString().equals(player.getName())) {
	               	 player.getActivePotionEffects().clear();
	               	 for (Object cItem : player.getActivePotionEffects()) {
	   	  	               String potionEffectName = cItem.toString().split(":")[0];
	   	  	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	   	  	               player.removePotionEffect(effect);
	   	  	           }
	                	 player.getInventory().clear();
	                	 player.setGameMode(GameMode.SURVIVAL);
	                	 player.setDisplayName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setPlayerListName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setHealth(20.0);
	                	 player.setSaturation(20);
	                	 player.setFoodLevel(20);
	                	 player.setLevel(0);
	                	 player.setExp(0);
	                	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);	             	 
	                	 player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(plugin.getConfig().getString("team2Coords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("team2Coords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("team2Coords").split(", ")[2])));             	 
	                	 for (String cItem : plugin.getConfig().getStringList("teamItems")) {
	   	             	    String[] asArray = cItem.split(", ");
	   	             	    if(Material.valueOf(asArray[0]) != null ) {
	   	             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
	   		             	    player.getInventory().addItem(item);
	   		             	    	
	   	             	    }
	   	             }
	                  	 for (String cItem : plugin.getConfig().getStringList("teamEffects")) {
	   	               	    String[] asArray = cItem.split(", ");
	   	               	    if(PotionEffectType.getByName(asArray[0]) != null) {
	   		               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
	   		               	    player.addPotionEffect(effect);
	   	               	    }

	   	                }
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +  plugin.getConfig().getString("messages_teamReveal").replaceAll("%team%", plugin.getConfig().getString("team2name")).replaceAll("%teamColor%", plugin.getConfig().getString("team2Color"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +  plugin.getConfig().getString("messages_objectives_team").replaceAll("%player%", plugin.getConfig().getString("hunted"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_feature_team").replaceAll("%delay%", plugin.getConfig().getString("coordinatesRefreshDelay"))));
	                	 
	                	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + plugin.getConfig().getString("team2Coords").split(", ")[0]  + " " + plugin.getConfig().getString("team2Coords").split(", ")[1]  + " " + plugin.getConfig().getString("team2Coords").split(", ")[2]);
	                 
	                 }
	              }

	              
	              Iterator<?> guardVar = plugin.getConfig().getStringList("guards").iterator();

	              while(guardVar.hasNext()) {
	                 String member = (String)guardVar.next();
	                 if(member.toString().equals(player.getName())) {
	               	 player.getActivePotionEffects().clear(); 
	               	 for (Object cItem : player.getActivePotionEffects()) {
	   	  	               String potionEffectName = cItem.toString().split(":")[0];
	   	  	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	   	  	               player.removePotionEffect(effect);
	   	  	           }
	                	 player.getInventory().clear();
	                	 player.setGameMode(GameMode.SURVIVAL);
	                	 player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.RESET);
	               	 player.setPlayerListName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setHealth(20.0);
	                	 player.setSaturation(20);
	                	 player.setFoodLevel(20);
	                	 player.setLevel(0);
	                	 player.setExp(0);
	                	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);	            	 
	                	 player.teleport(new Location(player.getWorld(), Double.parseDouble(plugin.getConfig().getString("guardCoords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("guardCoords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("guardCoords").split(", ")[2])));	             	 
	                	 for (String cItem : plugin.getConfig().getStringList("guardItems")) {
	   	             	    String[] asArray = cItem.split(", ");
	   	             	    if(Material.valueOf(asArray[0]) != null ) {
	   	             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
	   		             	    player.getInventory().addItem(item);
	   		             	    	
	   	             	    };
	   	             	 }
	                  	 for (String cItem : plugin.getConfig().getStringList("guardEffects")) {
	   	               	    String[] asArray = cItem.split(", ");
	   	               	    if(PotionEffectType.getByName(asArray[0]) != null) {
	   		               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
	   		               	    player.addPotionEffect(effect);
	   	               	    }

	   	                }
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_teamReveal").replaceAll("%team%", plugin.getConfig().getString("guardname")).replaceAll("%teamColor%", plugin.getConfig().getString("guardColor"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_objectives_guard").replaceAll("%player%", plugin.getConfig().getString("hunted"))));
	                	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") +  plugin.getConfig().getString("messages_feature_guard")));
	                	 
	                	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + plugin.getConfig().getString("guardCoords").split(", ")[0] + " " + plugin.getConfig().getString("guardCoords").split(", ")[1] + " " + plugin.getConfig().getString("guardCoords").split(", ")[2]);
	                	 
	                 }
	              }
	              
	              
	              String huntedVar = plugin.getConfig().getString("hunted");

	                 if(huntedVar.toString().equals(player.getName())) {
	                	 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_teleporting").replaceAll("%player%", player.getDisplayName()))); 
	                	 player.getActivePotionEffects().clear();
	                	for (Object cItem : player.getActivePotionEffects()) {
	   	  	               String potionEffectName = cItem.toString().split(":")[0];
	   	  	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	   	  	               player.removePotionEffect(effect);
	   	  	           }
	                	 player.getInventory().clear();
	                	 player.setGameMode(GameMode.SURVIVAL);
	                	 player.setHealth(20.0);
	                	 player.setFoodLevel(20);
	                	 player.setSaturation(20);
	                	 player.setLevel(0);
	                	 player.setExp(0);
	                	 player.setDisplayName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	               	 player.setPlayerListName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                	 player.setStatistic(Statistic.TIME_SINCE_REST, 0); 
	                	 player.teleport(new Location(player.getWorld(), Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[2])));           	 
	                  	 
	                	 for (String cItem : plugin.getConfig().getStringList("huntedItems")) {
	   	             	    String[] asArray = cItem.split(", ");
	   	             	    if(Material.valueOf(asArray[0]) != null ) {
	   	             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
	   		             	    player.getInventory().addItem(item);
	   		             	    	
	   	             	    }
	   	             }
	                	 for (String cItem : plugin.getConfig().getStringList("huntedEffects")) {
	   	               	    String[] asArray = cItem.split(", ");
	   	               	    if(PotionEffectType.getByName(asArray[0]) != null) {
	   		               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
	   		               	    player.addPotionEffect(effect);
	   	               	    }

	   	                }
	                 }
	   			   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

	   	           while(teamSVar.hasNext()) {
	   	               String member = (String)teamSVar.next();
	   	               if(member.equals(player.getName())) {
	   	    			   player.getActivePotionEffects().clear();
	   	               	 for (Object cItem : player.getActivePotionEffects()) {
	   	  	               String potionEffectName = cItem.toString().split(":")[0];
	   	  	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	   	  	               player.removePotionEffect(effect);
	   	  	           }
	   		             	 player.getInventory().clear();
	   		             	 player.setGameMode(GameMode.SPECTATOR);
	   		             	 player.setHealth(20.0);
	   		             	 player.setFoodLevel(20);
	   		             	 player.setSaturation(20);
	   		             	 player.setLevel(0);
	   		             	 player.setExp(0);
	   		             	 player.teleport(new Location(player.getWorld(), Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[0]) , Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("huntedCoords").split(", ")[2])));
	   	                }
	   	           }
	    	  });

           
	   }
	   
	   public static void stop() {
	    	  Main.GameState = false;
	    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
	    	  list.forEach((p) -> {  
	    		  Player player = ((Player) p);
				   player.getActivePotionEffects().clear();
				   player.setGameMode(GameMode.ADVENTURE);
				   player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[2])));	
				   player.setHealth(20.0);
				   player.setFoodLevel(20);
				   player.setSaturation(20);
				   player.setLevel(0);
				   player.setExp(0);
				   player.getInventory().clear();
	    	  });

	    	  
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_gameStopped")));

	   }
}
