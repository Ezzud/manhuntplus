package fr.ezzud.hunting.commands;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import fr.ezzud.hunting.Main;
import fr.ezzud.hunting.management.CountdownTimer;
import fr.ezzud.hunting.management.colorManager;
import fr.ezzud.hunting.management.compassManager;
import fr.ezzud.hunting.management.gameManager;
import fr.ezzud.hunting.management.teamManager;
import fr.ezzud.hunting.management.whitelistManager;

public class CommandHandler implements CommandExecutor {
    static Main plugin;
    
    public CommandHandler(Main instance) {
        plugin = instance;
    }
	
	   @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		   
		   	sender = Bukkit.getPlayer(sender.getName());
		   	if(args.length <= 0) {
		   		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " &aCreated by ezzud#0001"));
		   	}
		   	
		   	
		      if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
		         if (!sender.hasPermission(plugin.getConfig().getString("permissions_reload")) && !sender.isOp()) {
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("messages_noPermission")));
		            return true;
		         } else {
		            plugin.reloadConfig();
		            plugin.saveConfig();
		            colorManager.setColors();
		            whitelistManager.resetWhitelist();
		            whitelistManager.setWhitelist();	   
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("messages_reloadMessageSuccessful")));
		            return true;
		         }
		      }
		      
		      
		      
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("tp")) {
		    	  if(Main.GameState == false) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("messages_gameNotStarted")));
			            return true;		    		  
		    	  }
		    	  boolean isAGuard = false;
				   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

		           while(teamGVar.hasNext()) {
		               String member = (String)teamGVar.next();		               
		               if(member.equals(sender.getName())) {
		            	   	  isAGuard = true;
		            	   	if (Bukkit.getServer().getPlayer(plugin.getConfig().getString("hunted")) != null){
						    	  Player naykii = Bukkit.getPlayer(plugin.getConfig().getString("hunted"));
						    	  Location naykiiloc = naykii.getLocation();
						    	  ((Entity) sender).teleport(new Location(naykii.getWorld(),Math.round(naykiiloc.getX()) , Math.round(naykiiloc.getY()), naykiiloc.getZ()));
						    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("teleported")));		
						    	  return true;
		            	   	} else {
		            	   		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_speedrunnerNotConnected")));
		            	   		return false;
		            	   	}
		               }
		           }
	               if(isAGuard == false) {
	            	   sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_notAGuard"))); 
	            	   return true;
	               }
		      }
		      if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_help")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }	
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&6&lCommands list"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt help &7[Display this message]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt reload &7[Reload config & whitelist]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt start &7[Start the game]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt stop &7[Stop the game]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt setspawn &7[Set the lobby to your position]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt setlocation <team> &7[Set the starting position of the team] "));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a  > (Teams: team1, team2, guards, spectators)"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt setspeedrunner <Player Name> &7[Change the speedrunner]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt addteammember <Player Name> <Team> &7[Add a member in a team]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a  > (Teams: team1, team2, guards, spectators)"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt removeteammember <Player Name> <Team> &7[Remove a member from a team]"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a  > (Teams: speedrunner, team1, team2, guards)"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt tp &7[Allow guards to teleport to the speedrunner] &cONLY INGAME"));
			         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6- &e/manhunt list &7[Display the list of all teams]"));
			         return true;
		      }
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("setspawn")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_setspawn")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
		    	  		String playername = sender.getName();
		    	  		Player player = Bukkit.getPlayer(playername);
		    	  		Location loc = player.getLocation();
		    	  		String message = String.valueOf(new DecimalFormat("#").format(loc.getX())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getY())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getZ()));
		    	  		plugin.getConfig().set("spawnCoords", message);
		    	  		plugin.saveConfig();
		    	  		plugin.reloadConfig();
		    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + "&eLobby &6spawn point has been set to &a" + message));			        	 
		    	  		return true;
		      }
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("setlocation")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_setlocation")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
			         if(args.length < 2) {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt setlocation <team> (team1, team2, guards, speedrunner)"));
			        	 return true;		        	 
			         }
			         if(args[1].equalsIgnoreCase("speedrunner")) {
			    	  		String playername = sender.getName();
			    	  		Player player = Bukkit.getPlayer(playername);
			    	  		Location loc = player.getLocation();
			    	  		String message = String.valueOf(new DecimalFormat("#").format(loc.getX())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getY())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getZ()));
			    	  		plugin.getConfig().set("huntedCoords", message);
			    	  		plugin.saveConfig();
			    	  		plugin.reloadConfig();
			    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("huntedColor") +  "Speedrunner &6spawn point has been set to &a" + message));			        	 
			    	  		return true;
			         } else if(args[1].equalsIgnoreCase("team1")) {
			    	  		String playername = sender.getName();
			    	  		Player player = Bukkit.getPlayer(playername);
			    	  		Location loc = player.getLocation();
			    	  		String message = String.valueOf(new DecimalFormat("#").format(loc.getX())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getY())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getZ()));
			    	  		plugin.getConfig().set("team1Coords", message);
			    	  		plugin.saveConfig();
			    	  		plugin.reloadConfig();
			    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("team1Color") + plugin.getConfig().getString("team1name")  + " &6spawn point has been set to &a" + message));			        	 
			    	  		return true;
			         } else if(args[1].equalsIgnoreCase("team2")) {
			    	  		String playername = sender.getName();
			    	  		Player player = Bukkit.getPlayer(playername);
			    	  		Location loc = player.getLocation();
			    	  		String message = String.valueOf(new DecimalFormat("#").format(loc.getX())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getY())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getZ()));
			    	  		plugin.getConfig().set("team2Coords", message);
			    	  		plugin.saveConfig();
			    	  		plugin.reloadConfig();
			    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("team2Color") + plugin.getConfig().getString("team2name") + " &6spawn point has been set to &a" + message));			        	 
			    	  		return true;
			         } else if(args[1].equalsIgnoreCase("guards")) {
			    	  		String playername = sender.getName();
			    	  		Player player = Bukkit.getPlayer(playername);
			    	  		Location loc = player.getLocation();
			    	  		String message = String.valueOf(new DecimalFormat("#").format(loc.getX())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getY())) + ", " + String.valueOf(new DecimalFormat("#").format(loc.getZ()));
			    	  		plugin.getConfig().set("guardCoords", message);
			    	  		plugin.saveConfig();
			    	  		plugin.reloadConfig();
			    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("guardColor") + plugin.getConfig().getString("guardTeamName") + " &6spawn point has been set to &a" + message));			        	 
			    	  		return true;
			         } else {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cInvalid team!"));
			        	 return true;
			         }
		    	  
		      }
		      if (args.length > 0 && args[0].equalsIgnoreCase("setspeedrunner")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_setspeedrunner")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
			         if(args.length < 2) {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt setspeedrunner <player name>"));
			        	 return true;		        	 
			         }
			         
			         if(Bukkit.getOfflinePlayer(args[1]) != null) {
			        	 OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
		    	  			if(teamManager.isTeam(player.getName()) == true) {
					        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cPlayer is already in a team!"));
					        	 return true;			    	  				
		    	  			}
			    	  		plugin.getConfig().set("hunted", player.getName());
			    	  		plugin.saveConfig();	
			    	  		plugin.reloadConfig();
			    	  		colorManager.setColors();
			    	  		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&aThe speedrunner is now &6" + player.getName()));
			        	 return true;
			         } else {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUser not found!"));
			        	 return true;
			         }      
		      }
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("addteammember")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_addteam")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
			         if(args.length < 2) {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt addteammember <player name> <team>"));
			        	 return true;		        	 
			         }
			         if(Bukkit.getOfflinePlayer(args[1]) != null) {
			        	 OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
				         if(args.length < 3) {
				        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt addteammember <player name> <team>"));
				        	 return true;		        	 
				         }
		    	  			if(teamManager.isTeam(player.getName()) == true) {
					        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cPlayer is already in a team!"));
					        	 return true;			    	  				
		    	  			}
			    	  	switch(args[2].toLowerCase()) {
			    	  		case "team1":
			    	  			List<String> list = plugin.getConfig().getStringList("team1");
			    	  			list.add(player.getName());
			    	  			plugin.getConfig().set("team1", list);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			colorManager.setColors();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been added to the team " + plugin.getConfig().getString("team1Color") + plugin.getConfig().getString("team1name")));
			    	  			break;
			    	  		case "team2":
			    	  			List<String> list2 = plugin.getConfig().getStringList("team2");
			    	  			list2.add(player.getName());
			    	  			plugin.getConfig().set("team2", list2);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			colorManager.setColors();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been added to the team " + plugin.getConfig().getString("team2Color") + plugin.getConfig().getString("team2name")));
			    	  			break;
			    	  		case "guards":
			    	  			List<String> list3 = plugin.getConfig().getStringList("guards");
			    	  			list3.add(player.getName());
			    	  			plugin.getConfig().set("guards", list3);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			colorManager.setColors();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been added to the team " + plugin.getConfig().getString("guardColor") + plugin.getConfig().getString("guardTeamName")));
			    	  			break;
			    	  		case "spectators":
			    	  			List<String> list4 = plugin.getConfig().getStringList("spectators");
			    	  			list4.add(player.getName());
			    	  			plugin.getConfig().set("spectators", list4);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			colorManager.setColors();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been added to the team " + plugin.getConfig().getString("spectatorColor") + plugin.getConfig().getString("spectatorName")));
			    	  			break;
			    	  		default:
					        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cGive a valid team! (team1, team2, guards, spectators)"));
					        	 break;
			    	  		
			    	  	}
			        	 return true;
			         } else {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUser not found!"));
			        	 return true;
			         }  
		      }
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("removeteammember")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_removeteam")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
			         if(args.length < 2) {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt removeteammember <player name> <team>"));
			        	 return true;		        	 
			         }
			         if(Bukkit.getOfflinePlayer(args[1]) != null) {
			        	 OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
				         if(args.length < 3) {
				        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUsage: /manhunt removeteammember <player name> <team>"));
				        	 return true;		        	 
				         }
		    	  			if(teamManager.isTeam(player.getName()) == false) {
					        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cPlayer is not in a team!"));
					        	 return true;			    	  				
		    	  			}
			    	  	switch(args[2].toLowerCase()) {
			    	  		case "team1":
			    	  			List<String> list = plugin.getConfig().getStringList("team1");
			    	  			int index = list.indexOf(player.getName());
			    	  			list.remove(index);
			    	  			plugin.getConfig().set("team1", list);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been removed from the team " + plugin.getConfig().getString("team1Color") + plugin.getConfig().getString("team1name")));
			    	  			break;
			    	  		case "team2":
			    	  			List<String> list2 = plugin.getConfig().getStringList("team2");
			    	  			int index2 = list2.indexOf(player.getName());
			    	  			list2.remove(index2);
			    	  			plugin.getConfig().set("team2", list2);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been removed from the team " + plugin.getConfig().getString("team2Color") + plugin.getConfig().getString("team2name")));
			    	  			break;
			    	  		case "guards":
			    	  			List<String> list3 = plugin.getConfig().getStringList("guards");
			    	  			int index3 = list3.indexOf(player.getName());
			    	  			list3.remove(index3);
			    	  			plugin.getConfig().set("guards", list3);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been removed from the team " + plugin.getConfig().getString("guardColor") + plugin.getConfig().getString("guardTeamName")));
			    	  			break;
			    	  		case "spectators":
			    	  			List<String> list4 = plugin.getConfig().getStringList("spectators");
			    	  			int index4 = list4.indexOf(player.getName());
			    	  			list4.remove(index4);
			    	  			plugin.getConfig().set("spectators", list4);
			    	  			plugin.saveConfig();
			    	  			plugin.reloadConfig();
			    	  			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&e" + player.getName() + " &ahas been removed from the team " + plugin.getConfig().getString("spectatorColor") + plugin.getConfig().getString("spectatorName")));
			    	  			break;
			    	  		default:
					        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cGive a valid team! (team1, team2, guards, spectators)"));
					        	 break;
			    	  		
			    	  	}
			        	 return true;
			         } else {
			        	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&cUser not found!"));
			        	 return true;
			         }  
		      }
		      if (args.length > 0 && args[0].equalsIgnoreCase("list")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_list")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
				   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();
				   
			       while(team1Var.hasNext()) {
			           String member = (String)team1Var.next();
			           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("team1Color") + plugin.getConfig().getString("team1name") + " "+ member));
			       }
			       
				   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

			       while(team2Var.hasNext()) {
			           String member = (String)team2Var.next();
			           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("team2Color") + plugin.getConfig().getString("team2name") + " "+ member));
			       }
			       
			       
				   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

			       while(teamGVar.hasNext()) {
			           String member = (String)teamGVar.next();
			           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("guardColor") + plugin.getConfig().getString("guardTeamName") + " "+ member));

			       }
				   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

			       while(teamSVar.hasNext()) {
			           String member = (String)teamSVar.next();
			           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("spectatorColor") + plugin.getConfig().getString("spectatorName") + " "+ member));

			       }	
				   String teamHVar = plugin.getConfig().getString("hunted");
				   sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("huntedColor") +"Speedrunner " +  teamHVar));
				   
				   return true;
		      }

		      
		      
		      if (args.length > 0 && args[0].equalsIgnoreCase("start")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_start")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				      }
		    	  if(Main.GameState == true) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("messages_gameAlreadyStarted")));
			            return true;		    		  
		    	  }
		    	  if(plugin.counting == true) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix")  + plugin.getConfig().getString("messages_gameAlreadyStarted")));
			            return true;		    		  
		    	  }		    	  
		    	  Bukkit.getWorld("world").setTime(0);
		    	  plugin.counting = true;
		    	  plugin.counted = false;
		    	  CountdownTimer timer = new CountdownTimer(plugin,
		    		        plugin.getConfig().getInt("counterToStart"),
		    		        () ->  {
		    		        	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_gameIsStarting")));
		    		        },
		    		        () -> {    
		    		              plugin.counted = true;
		    		              plugin.counting = false;
		    		    		  new gameManager().start();
		    			    	  Main.GameState = true;
		    			    	  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_gameStarted")));
		    			    	  
		    			    	  compassManager.calibrate(plugin.getConfig().getString("hunted"), plugin.getConfig());	
		    		        },
		    		        (t) -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages_gameStartingCount").replaceAll("%seconds%", String.valueOf(t.getSecondsLeft()))))

		    		);
		    	  timer.scheduleTimer();




		      }
		      if (args.length > 0 && args[0].equalsIgnoreCase("stop")) {
			         if (!sender.hasPermission(plugin.getConfig().getString("permissions_stop")) && !sender.isOp()) {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_noPermission")));
				            return true;
				         }
		    	  if(Main.GameState == false) {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("messages_gameNotStarted")));
			            return true;		    		  
		    	  }
		    	  	gameManager.stop();
		            return true;
		      }		      
		      return false;
	   }
}
