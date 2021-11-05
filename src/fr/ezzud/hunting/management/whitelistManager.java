package fr.ezzud.hunting.management;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import fr.ezzud.hunting.Main;

public class whitelistManager {
    static Main plugin = Main.getInstance(); 
	   @SuppressWarnings("deprecation")
	public static void setWhitelist() {
		   if(plugin.getConfig().getBoolean("whitelistEnabled") == true) {
		   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

           while(team2Var.hasNext()) {
	        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) team2Var.next());
	        	   member.setWhitelisted(true);
	               Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team 2 member " + member.getName() + " to the whitelist");
           }
           

		   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

           while(team1Var.hasNext()) {
	        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) team1Var.next());
	        	   member.setWhitelisted(true);
	               Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team 1 member " + member.getName() + " to the whitelist");
           }
           
		   String teamHVar = plugin.getConfig().getString("hunted");
		   OfflinePlayer hunted = Bukkit.getOfflinePlayer(teamHVar); 
    	   hunted.setWhitelisted(true);
           Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team hunted member " + hunted.getName() + " to the whitelist");
           
		   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

           while(teamGVar.hasNext()) {
	        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) teamGVar.next());
	        	   member.setWhitelisted(true);
	               Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team guard member " + member.getName() + " to the whitelist");
        	   

           }
		   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

           while(teamSVar.hasNext()) {
        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) teamSVar.next());
        	   member.setWhitelisted(true);
               Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added spectator member " + member.getName() + " to the whitelist");
    	   
           }
		   }
	   }
	   
	   	public static void resetWhitelist() {
	   		if(plugin.getConfig().getBoolean("whitelistEnabled") == true) {
		   		ArrayList<?> list = new ArrayList<>(Bukkit.getWhitelistedPlayers());
		    	  list.forEach((p) -> {  		  
		    		  OfflinePlayer player = ((OfflinePlayer) p);
		    		  player.setWhitelisted(false);
		    		  Bukkit.getLogger().info(plugin.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.RED + "Removed " + player.getName() + " from the whitelist");
		    		  
		    	  });		   			
	   		}

	   	}
}
