package fr.ezzud.hunting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.ezzud.hunting.Main;

public class onLeave implements Listener {
	
    Main plugin; 
    public onLeave(Main instance) {
        plugin = instance;
    }

	   @EventHandler
	   public void onPlayerLeave(PlayerQuitEvent event) {
		   event.setQuitMessage(null);
		   String p = event.getPlayer().getName();
		   Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("leaveMessage").replaceAll("%player%", p)));
	   }
}