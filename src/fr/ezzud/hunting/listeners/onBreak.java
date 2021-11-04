package fr.ezzud.hunting.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

import fr.ezzud.hunting.Main;
public class onBreak implements Listener {
    Main plugin;
    
    public onBreak(Main instance) {
        plugin = instance;
    }
	@EventHandler
    public void onPaintingBreak(HangingBreakByEntityEvent e) {
		   if(Main.GameState == false) {
			   e.setCancelled(true);
		
		   }		    	
	}
}
