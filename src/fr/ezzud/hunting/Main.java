package fr.ezzud.hunting;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.ezzud.hunting.commands.CommandHandler;
import fr.ezzud.hunting.listeners.onBreak;
import fr.ezzud.hunting.listeners.onChat;
import fr.ezzud.hunting.listeners.onDamage;
import fr.ezzud.hunting.listeners.onEat;
import fr.ezzud.hunting.listeners.onEntityKill;
import fr.ezzud.hunting.listeners.onExplode;
import fr.ezzud.hunting.listeners.onJoin;
import fr.ezzud.hunting.listeners.onKill;
import fr.ezzud.hunting.listeners.onLeave;
import fr.ezzud.hunting.listeners.onSpawn;
import fr.ezzud.hunting.management.whitelistManager;


public class Main extends JavaPlugin implements Listener {
	public static Boolean GameState = false;
		   
		  private static Main plugin;
		   public static Main getInstance() {
			      return plugin;
		   }

		public boolean counted;
		public boolean sended;
		public boolean counting;
		   
		   
		   public void onEnable() {
			   plugin = this;
			   	  this.saveDefaultConfig();
			      PluginManager pm = Bukkit.getPluginManager(); 
			      pm.registerEvents(this, this);
			      pm.registerEvents(new onChat(this), this);
			      pm.registerEvents(new onExplode(this), this);
			      pm.registerEvents(new onDamage(this), this);
			      pm.registerEvents(new onJoin(this), this);
			      pm.registerEvents(new onEat(this), this);
			      pm.registerEvents(new onKill(this), this);
			      pm.registerEvents(new onSpawn(this), this);
			      pm.registerEvents(new onBreak(this), this);
			      pm.registerEvents(new onLeave(this), this);
			      pm.registerEvents(new onEntityKill(this), this);
			      this.getCommand("manhunt").setExecutor(new CommandHandler(this));
			      whitelistManager.setWhitelist();
		   }
		   
		   
		   public void onDisable() {
			   whitelistManager.resetWhitelist();
		   }	
	

		  
		   
}
