package fr.ezzud.hunting.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import fr.ezzud.hunting.Main;

@SuppressWarnings("deprecation")
public class onChat implements Listener {
    Main plugin;
    
    public onChat(Main instance) {
        plugin = instance;
    }
	@EventHandler
	   public void onMessageChat(PlayerChatEvent event) {
		   Player player = event.getPlayer();
		   String message = event.getMessage();
		   char prefix = message.charAt(0);
		   if(prefix == plugin.getConfig().getString("globalChatPrefix").charAt(0)) {
		    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
		    	  list.forEach((p) -> {
		    		  
		    		  Player playeur = ((Player) p);
		    		  playeur.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("publicChatFormat").replaceAll("%playername%", player.getName()).replaceAll("%message%", message.substring(1))));  
		    	  });
		    	  event.setCancelled(true);
		   } else {
			   boolean inAteam = false;
			   event.setCancelled(true);
			   
	           Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();
	          
	           while(team1Var.hasNext()) {
	              String member = (String)team1Var.next();
	              if(member.equals(player.getName())) {
	            	  inAteam = true;
			           Iterator<?> team1Var2 = plugin.getConfig().getStringList("team1").iterator();

			           while(team1Var2.hasNext()) {
			        	   
			        	  String member2 = (String)team1Var2.next();
			        	  if(Bukkit.getServer().getPlayer(member2) != null) {
				              Player p = Bukkit.getPlayer(member2);
				              p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("privateChatFormat").replaceAll("%teamColor%", plugin.getConfig().getString("team1Color")).replaceAll("%playername%", member)).replaceAll("%team%", plugin.getConfig().getString("team1name")).replaceAll("%message%", message)); 
			        	  }
			           }
	              }
	           }
	           
	           Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
	              String member = (String)team2Var.next();
	              if(member.equals(player.getName())) {
	            	  inAteam = true;
			           Iterator<?> team2Var2 = plugin.getConfig().getStringList("team2").iterator();

			           while(team2Var2.hasNext()) {
			        	  String member2 = (String)team2Var2.next();
			        	  if(Bukkit.getServer().getPlayer(member2) != null) {
				              Player p = Bukkit.getPlayer(member2);
				              p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("privateChatFormat").replaceAll("%teamColor%", plugin.getConfig().getString("team2Color")).replaceAll("%playername%", member)).replaceAll("%team%", plugin.getConfig().getString("team2name")).replaceAll("%message%", message)); 		        		  
			        	  }

			           }
	              }
	           }
	           
	           if(inAteam == false) {
			    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
			    	  list.forEach((p) -> {	  
			    		  Player playeur = ((Player) p);
			    		  playeur.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("publicChatFormat").replaceAll("%playername%", player.getName()).replaceAll("%message%", message)));  
			    	  });		        	   
	           }
		   }
		   
		   
	   }
}
