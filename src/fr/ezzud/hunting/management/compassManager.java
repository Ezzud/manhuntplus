package fr.ezzud.hunting.management;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.ezzud.hunting.Main;

public class compassManager {
	static Main plugin = Main.getInstance(); 
    
	public static boolean calibrate(String hunted, FileConfiguration config) {
    	new Timer().scheduleAtFixedRate(new TimerTask(){
    	    public void run(){
    	    	if(Main.GameState == true) {
    	    		ArrayList<?> list2 = new ArrayList<>(Bukkit.getOnlinePlayers());
			    	  if (Bukkit.getServer().getPlayer(hunted) != null){
				    	  Player naykii = Bukkit.getPlayer(hunted);
				    	  Location naykiiloc = naykii.getLocation();
				    	  String env = naykii.getWorld().getEnvironment().toString();
				    	  list2.forEach((p) -> {	
				    		  Player player = ((Player) p);
				    		  if(player.getName().equals(hunted)) {
				    			  
				    		  } else {
				    			  player.setCompassTarget(naykiiloc);
				    			  String coordinates = String.valueOf(Math.round(naykiiloc.getX())) + " " + String.valueOf(Math.round(naykiiloc.getY())) + " "  + String.valueOf(Math.round(naykiiloc.getZ()) + " [" + env + "]");
				    			  String configMessage = config.getString("prefix") + config.getString("messages_coordinatesMessage").replace("%player%", hunted).replace("%coordinates%", coordinates);
				    			  player.sendMessage(ChatColor.translateAlternateColorCodes('&', configMessage));
				    		  }
				    	  });					    	  
			    	  } else {    	  
				    	  list2.forEach((p) -> {	
				    		  Player player = ((Player) p);
				    		  if(player.getName().equals(hunted)) {
				    			  
				    		  } else {
				    			  player.setCompassTarget(new Location(player.getWorld(), Double.parseDouble(config.getString("spawnCoords").split(", ")[0]), Double.parseDouble(config.getString("spawnCoords").split(", ")[1]), Double.parseDouble(config.getString("spawnCoords").split(", ")[2])));
				    			  player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("prefix") +  config.getString("messages_coordinatesNotConnected").replace("%player%", hunted)));
				    		  }
				    	  }); 
			    	  }
		    	

    	    	} else {
    	    		this.cancel();
    	    	}
    	    }
    	},0,plugin.getConfig().getLong("coordinatesRefreshDelay")*1000);

	return false;
}
}
