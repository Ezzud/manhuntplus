package fr.ezzud.hunting.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.ezzud.hunting.Main;

public class onEat implements Listener {
    Main plugin;
    
    public onEat(Main instance) {
        plugin = instance;
    }
	   @EventHandler
	   public void onPlayerEat(PlayerItemConsumeEvent event) {
		   Player player = event.getPlayer();
		   if(player.getName().equals(plugin.getConfig().getString("hunted"))) {
           	 for (String cItem : plugin.getConfig().getStringList("huntedEatEffects")) {
          	    String[] asArray = cItem.split(", ");
          	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1]) * 20, Integer.parseInt(asArray[2]));
          	    player.addPotionEffect(effect);
           	 }
		   }
	   }	
}
