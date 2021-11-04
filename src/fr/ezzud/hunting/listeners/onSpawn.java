package fr.ezzud.hunting.listeners;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import fr.ezzud.hunting.Main;

public class onSpawn implements Listener {
    Main plugin;
    
    public onSpawn(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void onPlayerSpawn(PlayerRespawnEvent e)
	   {
		   Player player = e.getPlayer();
		   
		   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();

	        while(team1Var.hasNext()) {
	            String member = (String)team1Var.next();
	            if(member.equals(player.getName())) {
	             	 for (String cItem : plugin.getConfig().getStringList("teamItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    }
	             	 }
	             }
	        }
		   
		   
		   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	        while(team2Var.hasNext()) {
	            String member = (String)team2Var.next();
	            if(member.equals(player.getName())) {
	             	 for (String cItem : plugin.getConfig().getStringList("teamItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    }
	             	 }
	             }
	        }

        
		   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

        while(teamGVar.hasNext()) {
            String member = (String)teamGVar.next();
            if(member.equals(player.getName())) {
            	 for (String cItem : plugin.getConfig().getStringList("guardsItems")) {
	             	    String[] asArray = cItem.split(", ");
	             	    if(Material.valueOf(asArray[0]) != null ) {
	             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
		             	    player.getInventory().addItem(item);
		             	    	
	             	    }
            	 }
             }
        }
	   }
}
