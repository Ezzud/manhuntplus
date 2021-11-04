package fr.ezzud.hunting.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.ezzud.hunting.Main;

public class onExplode implements Listener {
    Main plugin;
    
    public onExplode(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void onEntityExplode(EntityExplodeEvent event) {
		   if(Main.GameState == false) {
			   event.setCancelled(true);
		   }			    
	   }
}
