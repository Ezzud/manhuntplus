package fr.ezzud.hunting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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


@SuppressWarnings({ "deprecation" })
public class Main extends JavaPlugin implements Listener {
		public static Boolean GameState = false;
		
		   public void onEnable() {
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
			      this.getCommand("manhunt").setExecutor(this);
			      this.setWhitelist();
			   }
		   public void onDisable() {
			   this.resetWhitelist();
		   }
		
		   
	   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		   
		   
		   
		   
		   	if(args.length <= 0) {
		   		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + " &aCreated by ezzud#0001"));
		   	}
		   	
	   	
		      if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
		         if (!sender.hasPermission(this.getConfig().getString("permissions_reload")) && !sender.isOp()) {
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("messages_noPermission")));
		            return true;
		         } else {
		            this.reloadConfig();
		            this.saveConfig();
		            this.setColors();
		            this.resetWhitelist();
		            this.setWhitelist();	   
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("messages_reloadMessageSuccessful")));
		            return true;
		         }
		      }
		      
		      
		      
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("tp")) {
		    	  if(GameState == false) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("messages_gameNotStarted")));
			            return true;		    		  
		    	  }
		    	  boolean isAGuard = false;
				   Iterator<?> teamGVar = this.getConfig().getStringList("guards").iterator();

		           while(teamGVar.hasNext()) {
		               String member = (String)teamGVar.next();		               
		               if(member.equals(sender.getName())) {
		            	   	  isAGuard = true;
		            	   	if (Bukkit.getServer().getPlayer(this.getConfig().getString("hunted")) != null){
						    	  Player naykii = Bukkit.getPlayer(this.getConfig().getString("hunted"));
						    	  Location naykiiloc = naykii.getLocation();
						    	  ((Entity) sender).teleport(new Location(naykii.getWorld(),Math.round(naykiiloc.getX()) , Math.round(naykiiloc.getY()), naykiiloc.getZ()));
						    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("teleported")));		
						    	  return true;
		            	   	} else {
		            	   		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("prefix") + this.getConfig().getString("messages_speedrunnerNotConnected")));
		            	   		return false;
		            	   	}
		               }
		           }
	               if(isAGuard == false) {
	            	   sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("prefix") + this.getConfig().getString("messages_notAGuard"))); 
	            	   return true;
	               }
		      }
		      
		      
		      
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("start")) {
			         if (!sender.hasPermission(this.getConfig().getString("permissions_start")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_noPermission")));
				            return true;
				         }
		    	  if(GameState == true) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix")  + this.getConfig().getString("messages_gameAlreadyStarted")));
			            return true;		    		  
		    	  }
		    	  GameState = true;
		    	  Bukkit.getWorld("world").setTime(0);
		    	  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_gameIsStarting")));  
		    	  	try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}
		    		  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b5"));
		    		  try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}
		    		  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b4")); 
		    		  try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}
		    		  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b3"));
		    		  try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}
		    		  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b2")); 
		    		  try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}
		    		  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b1"));  
		    		  try {Thread.sleep(1000L);} catch (InterruptedException e) {e.printStackTrace();}  
		    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
		    	  list.forEach((p) -> {
		    		  
		    		  Player player = ((Player) p);
		    		  giveItems(player);
		    		  	
		              player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0);
		    	  });
		    	  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_gameStarted")));
		    	  
		    	  compassCalibrate(this.getConfig().getString("hunted"), this.getConfig());

		      }
		      if (args.length > 0 && args[0].equalsIgnoreCase("stop")) {
			         if (!sender.hasPermission(this.getConfig().getString("permissions_stop")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_noPermission")));
				            return true;
				         }
		    	  if(GameState == false) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_gameNotStarted")));
			            return true;		    		  
		    	  }
		    	  GameState = false;
		    	  ArrayList<?> list = new ArrayList<>(Bukkit.getOnlinePlayers());
		    	  list.forEach((p) -> {
		    		  
		    		  Player player = ((Player) p);
					   player.getActivePotionEffects().clear();
					   player.setGameMode(GameMode.ADVENTURE);
					   player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(this.getConfig().getString("spawnCoords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("spawnCoords").split(", ")[1]), Double.parseDouble(this.getConfig().getString("spawnCoords").split(", ")[2])));	
					   player.setHealth(20.0);
					   player.setFoodLevel(20);
					   player.setSaturation(20);
					   player.setLevel(0);
					   player.setExp(0);
					   player.getInventory().clear();
		    	  });

		    	  
		            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("prefix") + this.getConfig().getString("messages_gameStopped")));
		            return true;
		      }		      
		      return false;
	   }

	   
		   
		   public boolean compassCalibrate(String hunted, FileConfiguration config) {
			    	new Timer().scheduleAtFixedRate(new TimerTask(){
			    	    public void run(){
			    	    	if(GameState == true) {
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
					    	
 
			    	    	}
			    	    }
			    	},0,this.getConfig().getLong("coordinatesRefreshDelay")*1000);

				return false;
		   }
		   
		  
		   public void setColors() {
	            ArrayList<?> list2 = new ArrayList<>(Bukkit.getOnlinePlayers());
	            list2.forEach((p) -> {	
			    		  Player player = ((Player) p);

		           

				   Iterator<?> team1Var = this.getConfig().getStringList("team1").iterator();

		           while(team1Var.hasNext()) {
		               String member = (String)team1Var.next();
		               if(member.equals(player.getName())) {
		            	   player.setDisplayName(this.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		            	   player.setPlayerListName(this.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		                }
		           }
		           
				   Iterator<?> team2Var = this.getConfig().getStringList("team2").iterator();

		           while(team2Var.hasNext()) {
		               String member = (String)team2Var.next();
		               if(member.equals(player.getName())) {
		            	   player.setDisplayName(this.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		            	   player.setPlayerListName(this.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		                }
		           }
		           
				   String teamHVar = this.getConfig().getString("hunted");
	               if(teamHVar.equals(player.getName())) {
	            	   player.setDisplayName(this.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	   player.setPlayerListName(this.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	                }			
		           
				   Iterator<?> teamGVar = this.getConfig().getStringList("guards").iterator();

		           while(teamGVar.hasNext()) {
		               String member = (String)teamGVar.next();
		               if(member.equals(player.getName())) {
		            	   player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.RESET);
		            	   player.setPlayerListName(this.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		                }
		           }
				   Iterator<?> teamSVar = this.getConfig().getStringList("spectators").iterator();

		           while(teamSVar.hasNext()) {
		               String member = (String)teamSVar.next();
		               if(member.equals(player.getName())) {
		            	   player.setDisplayName(this.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		            	   player.setPlayerListName(this.getConfig().getString("spectatorColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
		                }
		           }
			    	  });
		   }
		   
		   
		   public void giveItems(Player player) {
	           Iterator<?> team1Var = this.getConfig().getStringList("team1").iterator();

	           while(team1Var.hasNext()) {
	              String member = (String)team1Var.next();
	              if(member.toString().equals(player.getName())) {
	             	 player.getInventory().clear();
	             	 player.setGameMode(GameMode.SURVIVAL);
	             	 player.setHealth(20.0);
	             	 player.setSaturation(20);
	             	 player.setLevel(0);
	             	 player.setExp(0);
	             	 player.setDisplayName(this.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	 player.setPlayerListName(this.getConfig().getString("team1Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	             	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);
	             	 player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(this.getConfig().getString("team1Coords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("team1Coords").split(", ")[1]), Double.parseDouble(this.getConfig().getString("team1Coords").split(", ")[2])));             	 
	             	 for (String cItem : this.getConfig().getStringList("teamItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    }
	             	 }
	               	 for (String cItem : this.getConfig().getStringList("teamEffects")) {
		               	    String[] asArray = cItem.split(", ");
		               	    if(PotionEffectType.getByName(asArray[0]) != null) {
			               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
			               	    player.addPotionEffect(effect);
		               	    }

		                }
	             	 player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
	             	 player.getInventory().addItem(new ItemStack(Material.COOKIE, 1));        	 
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +   this.getConfig().getString("messages_teamReveal").replaceAll("%team%", this.getConfig().getString("team1name")).replaceAll("%teamColor%", this.getConfig().getString("team1Color"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +   this.getConfig().getString("messages_objectives_team").replaceAll("%player%", this.getConfig().getString("hunted"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +  this.getConfig().getString("messages_feature_team").replaceAll("%delay%", this.getConfig().getString("coordinatesRefreshDelay"))));

	             	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + this.getConfig().getString("team1Coords").split(", ")[0]  + " " + this.getConfig().getString("team1Coords").split(", ")[1]  + " " + this.getConfig().getString("team1Coords").split(", ")[2]);
	              }
	           }
	           Iterator<?> team2Var = this.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
	              String member = (String)team2Var.next();
	              if(member.toString().equals(player.getName())) {
	             	 
	             	 player.getInventory().clear();
	             	 player.setGameMode(GameMode.SURVIVAL);
	             	 player.setDisplayName(this.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	 player.setPlayerListName(this.getConfig().getString("team2Color").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	             	 player.setHealth(20.0);
	             	 player.setSaturation(20);
	             	 player.setFoodLevel(20);
	             	 player.setLevel(0);
	             	 player.setExp(0);
	             	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);	             	 
	             	 player.teleport(new Location(Bukkit.getWorld("world"), Double.parseDouble(this.getConfig().getString("team2Coords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("team2Coords").split(", ")[1]), Double.parseDouble(this.getConfig().getString("team2Coords").split(", ")[2])));             	 
	             	 for (String cItem : this.getConfig().getStringList("teamItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    }
		             }
	               	 for (String cItem : this.getConfig().getStringList("teamEffects")) {
		               	    String[] asArray = cItem.split(", ");
		               	    if(PotionEffectType.getByName(asArray[0]) != null) {
			               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
			               	    player.addPotionEffect(effect);
		               	    }

		                }
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +  this.getConfig().getString("messages_teamReveal").replaceAll("%team%", this.getConfig().getString("team2name")).replaceAll("%teamColor%", this.getConfig().getString("team2Color"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +  this.getConfig().getString("messages_objectives_team").replaceAll("%player%", this.getConfig().getString("hunted"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_feature_team").replaceAll("%delay%", this.getConfig().getString("coordinatesRefreshDelay"))));
	             	 
	             	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + this.getConfig().getString("team2Coords").split(", ")[0]  + " " + this.getConfig().getString("team2Coords").split(", ")[1]  + " " + this.getConfig().getString("team2Coords").split(", ")[2]);
	              
	              }
	           }

	           
	           Iterator<?> guardVar = this.getConfig().getStringList("guards").iterator();

	           while(guardVar.hasNext()) {
	              String member = (String)guardVar.next();
	              if(member.toString().equals(player.getName())) {
	             	 
	             	 player.getInventory().clear();
	             	 player.setGameMode(GameMode.SURVIVAL);
	             	 player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.RESET);
	            	 player.setPlayerListName(this.getConfig().getString("guardColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	             	 player.setHealth(20.0);
	             	 player.setSaturation(20);
	             	 player.setFoodLevel(20);
	             	 player.setLevel(0);
	             	 player.setExp(0);
	             	 player.setStatistic(Statistic.TIME_SINCE_REST, 0);	            	 
	             	 player.teleport(new Location(player.getWorld(), Double.parseDouble(this.getConfig().getString("guardCoords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("guardCoords").split(", ")[1]), Double.parseDouble(this.getConfig().getString("guardCoords").split(", ")[2])));	             	 
	             	 for (String cItem : this.getConfig().getStringList("guardItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    };
		             	 }
	               	 for (String cItem : this.getConfig().getStringList("guardEffects")) {
		               	    String[] asArray = cItem.split(", ");
		               	    if(PotionEffectType.getByName(asArray[0]) != null) {
			               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
			               	    player.addPotionEffect(effect);
		               	    }

		                }
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages_teamReveal").replaceAll("%team%", this.getConfig().getString("guardname")).replaceAll("%teamColor%", this.getConfig().getString("guardColor"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages_objectives_guard").replaceAll("%player%", this.getConfig().getString("hunted"))));
	             	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") +  this.getConfig().getString("messages_feature_guard")));
	             	 
	             	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnpoint " + player.getName() + " " + this.getConfig().getString("guardCoords").split(", ")[0] + " " + this.getConfig().getString("guardCoords").split(", ")[1] + " " + this.getConfig().getString("guardCoords").split(", ")[2]);
	             	 
	              }
	           }
	           
	           
	           String huntedVar = this.getConfig().getString("hunted");

	              if(huntedVar.toString().equals(player.getName())) {
	             	 Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix") + this.getConfig().getString("messages_teleporting").replaceAll("%player%", player.getDisplayName()))); 
	             	 player.getInventory().clear();
	             	 player.setGameMode(GameMode.SURVIVAL);
	             	 player.setHealth(20.0);
	             	 player.setFoodLevel(20);
	             	 player.setSaturation(20);
	             	 player.setLevel(0);
	             	 player.setExp(0);
	             	 player.setDisplayName(this.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	            	 player.setPlayerListName(this.getConfig().getString("huntedColor").replaceAll("&",  "§") + player.getName() + ChatColor.RESET);
	             	 player.setStatistic(Statistic.TIME_SINCE_REST, 0); 
	             	 player.teleport(new Location(player.getWorld(), Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[1]), Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[2])));           	 
	               	 
	             	 for (String cItem : this.getConfig().getStringList("huntedEffects")) {
	               	    String[] asArray = cItem.split(", ");
	               	    if(PotionEffectType.getByName(asArray[0]) != null) {
		               	    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(asArray[0]), Integer.parseInt(asArray[1])*20, Integer.parseInt(asArray[2]));
		               	    player.addPotionEffect(effect);
	               	    }

	                }
	             	 for (String cItem : this.getConfig().getStringList("huntedItems")) {
		             	    String[] asArray = cItem.split(", ");
		             	    if(Material.valueOf(asArray[0]) != null ) {
		             	    	ItemStack item = new ItemStack(Material.valueOf(asArray[0]), Integer.parseInt(asArray[1]));
			             	    player.getInventory().addItem(item);
			             	    	
		             	    }
		             }
	              
	              }
				   Iterator<?> teamSVar = this.getConfig().getStringList("spectators").iterator();

		           while(teamSVar.hasNext()) {
		               String member = (String)teamSVar.next();
		               if(member.equals(player.getName())) {
			             	 player.getInventory().clear();
			             	 player.setGameMode(GameMode.SPECTATOR);
			             	 player.setHealth(20.0);
			             	 player.setFoodLevel(20);
			             	 player.setSaturation(20);
			             	 player.setLevel(0);
			             	 player.setExp(0);
			             	 player.teleport(new Location(player.getWorld(), Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[0]) , Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[0]), Double.parseDouble(this.getConfig().getString("huntedCoords").split(", ")[2])));
		                }
		           }
	           
		   }
		   public void setWhitelist() {
			   if(this.getConfig().getBoolean("whitelistEnabled") == true) {
			   Iterator<?> team2Var = this.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
		        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) team2Var.next());
		        	   member.setWhitelisted(true);
		               Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team 2 member " + member.getName() + " to the whitelist");
	           }
	           

			   Iterator<?> team1Var = this.getConfig().getStringList("team1").iterator();

	           while(team1Var.hasNext()) {
		        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) team1Var.next());
		        	   member.setWhitelisted(true);
		               Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team 1 member " + member.getName() + " to the whitelist");
	           }
	           
			   String teamHVar = this.getConfig().getString("hunted");
			   OfflinePlayer hunted = Bukkit.getOfflinePlayer(teamHVar); 
	    	   hunted.setWhitelisted(true);
	           Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team hunted member " + hunted.getName() + " to the whitelist");
	           
			   Iterator<?> teamGVar = this.getConfig().getStringList("guards").iterator();

	           while(teamGVar.hasNext()) {
		        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) teamGVar.next());
		        	   member.setWhitelisted(true);
		               Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added team guard member " + member.getName() + " to the whitelist");
	        	   

	           }
			   Iterator<?> teamSVar = this.getConfig().getStringList("spectators").iterator();

	           while(teamSVar.hasNext()) {
	        	   OfflinePlayer member = Bukkit.getOfflinePlayer((String) teamSVar.next());
	        	   member.setWhitelisted(true);
	               Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.GREEN + "Added spectator member " + member.getName() + " to the whitelist");
	    	   
	           }
			   }
		   }
		   
		   	public void resetWhitelist() {
		   		if(this.getConfig().getBoolean("whitelistEnabled") == true) {
			   		ArrayList<?> list = new ArrayList<>(Bukkit.getWhitelistedPlayers());
			    	  list.forEach((p) -> {  		  
			    		  OfflinePlayer player = ((OfflinePlayer) p);
			    		  player.setWhitelisted(false);
			    		  Bukkit.getLogger().info(this.getConfig().getString("prefix").replaceAll("&", "§") + ChatColor.RED + "Removed " + player.getName() + " from the whitelist");
			    		  
			    	  });		   			
		   		}

		   	}	   
		   
}
