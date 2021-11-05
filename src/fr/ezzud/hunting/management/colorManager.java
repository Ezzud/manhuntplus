package fr.ezzud.hunting.management;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.ezzud.hunting.Main;

public class colorManager {
	static Main plugin = Main.getInstance(); 
	   public static void setColors() {
           ArrayList<?> list2 = new ArrayList<>(Bukkit.getOnlinePlayers());
           list2.forEach((p) -> {	
		    		  Player player = ((Player) p);

	           

			   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

	           while(team1Var.hasNext()) {
	               String member = (String)team1Var.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                }
	           }
	           
			   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
	               String member = (String)team2Var.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
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
	            	   player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                }
	           }
			   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

	           while(teamSVar.hasNext()) {
	               String member = (String)teamSVar.next();
	               if(member.equals(player.getName())) {
	            	   player.setDisplayName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(plugin.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                }
	           }
		    	  });
	   }
}
