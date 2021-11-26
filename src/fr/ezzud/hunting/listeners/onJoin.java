package fr.ezzud.hunting.listeners;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

import fr.ezzud.hunting.Main;

public class onJoin implements Listener {
	
    Main plugin; 
    public onJoin(Main instance) {
        plugin = instance;
    }

	   @EventHandler
	   public void onPlayerJoin(PlayerJoinEvent event) {
		   event.setJoinMessage(null);
		   String p = event.getPlayer().getName();
		   if(Main.GameState == true) {
			   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("joinMessage").replaceAll("%player%", p)));
			   Player player = event.getPlayer();
			   List<String> team2Var = plugin.getConfig().getStringList("team2");
			   if(team2Var.contains(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setGameMode(GameMode.SURVIVAL);
	           }


			   List<String> team1Var = plugin.getConfig().getStringList("team1");
			   if(team1Var.contains(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setGameMode(GameMode.SURVIVAL);
	                }
	           
			   String teamHVar = plugin.getConfig().getString("hunted");
               if(teamHVar.equals(player.getName())) {
            	   player.setDisplayName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§")+ player.getName() + ChatColor.RESET);
            	   player.setPlayerListName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
            	   player.setGameMode(GameMode.SURVIVAL);
                }			
	           
			   List<String> teamGVar = plugin.getConfig().getStringList("guards");
			   if(teamGVar.contains(player.getName())) {
            	   player.setDisplayName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
            	   player.setPlayerListName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
            	   player.setGameMode(GameMode.SURVIVAL);
			   }
	           
	           
			   List<String> teamSVar = plugin.getConfig().getStringList("spectators");
			   if(teamSVar.contains(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setGameMode(GameMode.SPECTATOR);
	           }
		   } else {
			   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + p + " " + plugin.getConfig().getString("spawnCoords").split(", ")[0]  + " " + plugin.getConfig().getString("spawnCoords").split(", ")[1]   + " " + plugin.getConfig().getString("spawnCoords").split(", ")[2] );
			   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("joinMessage").replaceAll("%player%", p)));
			   Player player = event.getPlayer();
			   player.getActivePotionEffects().clear();
             	 for (Object cItem : player.getActivePotionEffects()) {
	               String potionEffectName = cItem.toString().split(":")[0];
	               PotionEffectType effect = PotionEffectType.getByName(potionEffectName);
	               player.removePotionEffect(effect);
	           }
			   player.setGameMode(GameMode.ADVENTURE);
			   player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[0]), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[1]), Double.parseDouble(plugin.getConfig().getString("spawnCoords").split(", ")[2])));	
			   player.setHealth(20.0);
			   player.setFoodLevel(20);
			   player.setSaturation(20);
			   player.setLevel(0);
			   player.setExp(0);
			   player.getInventory().clear();
			   

			   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

	           while(team1Var.hasNext()) {
	               String member = (String)team1Var.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_joinTeam").replaceAll("%teamColor%", plugin.getConfig().getString("team1Color")).replaceAll("%team%", plugin.getConfig().getString("team1name"))));
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_groupChatActivated").replace("%groupPrefix%", plugin.getConfig().getString("globalChatPrefix"))));
	                }
	           }
	           
			   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
	               String member = (String)team2Var.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_joinTeam").replaceAll("%teamColor%", plugin.getConfig().getString("team2Color")).replaceAll("%team%", plugin.getConfig().getString("team2name"))));
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_groupChatActivated").replace("%groupPrefix%", plugin.getConfig().getString("globalChatPrefix"))));
	                }
	           }
	           
	           
			   String teamHVar = plugin.getConfig().getString("hunted");
               if(teamHVar.equals(player.getName())) {
            	   player.setDisplayName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
            	   player.setPlayerListName(plugin.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
                }
	           
			   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

	           while(teamGVar.hasNext()) {
	               String member = (String)teamGVar.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_joinTeam").replaceAll("%teamColor%", plugin.getConfig().getString("guardColor")).replaceAll("%team%", plugin.getConfig().getString("guardTeamName"))));
	               }
	           }
	           
			   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

	           while(teamSVar.hasNext()) {
	               String member = (String)teamSVar.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_joinTeam").replaceAll("%teamColor%", plugin.getConfig().getString("spectatorColor")).replaceAll("%team%", plugin.getConfig().getString("spectatorName"))));
	                }
	           }
		   }

	   }
}
