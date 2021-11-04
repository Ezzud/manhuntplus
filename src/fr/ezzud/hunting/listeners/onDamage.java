package fr.ezzud.hunting.listeners;

import java.util.Iterator;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.ezzud.hunting.Main;

public class onDamage implements Listener {
    Main plugin;
    
    public onDamage(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void onEntityDamage(EntityDamageEvent event) {
		   if (event.getEntity() instanceof Player) {
			   if(Main.GameState == false) {
				   event.setCancelled(true);
			   }
		   }
	   }
	   @EventHandler
	   public void onPlayerDamage(EntityDamageByEntityEvent event) {
		   if (event.getEntity() instanceof Player) {
			   if(Main.GameState == false) {
				   event.setCancelled(true);
			
			   }
		   }
		   if(event.getEntity().getName().equals(plugin.getConfig().getString("hunted"))) {
			   Entity damager = event.getDamager();
			   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

	           while(teamGVar.hasNext()) {
	               String member = (String)teamGVar.next();
	               if(member.equals(damager.getName())) {
				    	  event.setCancelled(true);	            	   
	               }
	           }				   
		   }
		   if(event.getDamager() instanceof Player) {
			   if(event.getEntity().getType().equals(EntityType.ENDER_DRAGON)) {
	               Player p = ((Player) event.getDamager()).getPlayer();
	               if(p.getName().equals(plugin.getConfig().getString("hunted"))) {
	               	
	               } else {
	            	   event.setCancelled(true);
	               }
			   }			   
		   }

	   }
}
