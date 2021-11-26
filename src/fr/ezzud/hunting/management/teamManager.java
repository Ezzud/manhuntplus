package fr.ezzud.hunting.management;

import java.util.List;

import fr.ezzud.hunting.Main;

public class teamManager {
	static Main plugin = Main.getInstance(); 
	

	
		public static boolean isTeam(String user) {
   			boolean isTeam = false;
   			
   			
			   List<String> team1Var = plugin.getConfig().getStringList("team1");
			   
	           if(team1Var.contains(user)) {
	        	   isTeam = true;
	           }
	           
			   List<String> team2Var = plugin.getConfig().getStringList("team2");
			   
	           if(team2Var.contains(user)) {
	        	   isTeam = true;
	           }
	           
			   String teamHVar = plugin.getConfig().getString("hunted");
               if(teamHVar.equals(user)) {
            	   isTeam = true;
                }			
	           
			   List<String> teamGVar = plugin.getConfig().getStringList("guards");
			   
	           if(teamGVar.contains(user)) {
	        	   isTeam = true;
	           }
			   List<String> teamSVar = plugin.getConfig().getStringList("spectators");
			   
	           if(teamSVar.contains(user)) {
	        	   isTeam = true;
	           }
	           return isTeam;
   		}
}
