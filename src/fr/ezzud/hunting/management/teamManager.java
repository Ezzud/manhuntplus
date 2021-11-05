package fr.ezzud.hunting.management;

import java.util.Iterator;

import fr.ezzud.hunting.Main;

public class teamManager {
	static Main plugin = Main.getInstance(); 
	

	
		public static boolean isTeam(String user) {
   			boolean isTeam = false;
   			
   			
			   Iterator<?> team1Var = plugin.getConfig().getStringList("team1").iterator();
			   
	           while(team1Var.hasNext()) {
	               String member = (String)team1Var.next();
	               if(member.equals(user)) {
	            	   return true;
	                }
	           }
	           
			   Iterator<?> team2Var = plugin.getConfig().getStringList("team2").iterator();

	           while(team2Var.hasNext()) {
	               String member = (String)team2Var.next();
	               if(member.equals(user)) {
	            	   isTeam = true;
	                }
	           }
	           
			   String teamHVar = plugin.getConfig().getString("hunted");
               if(teamHVar.equals(user)) {
            	   isTeam = true;
                }			
	           
			   Iterator<?> teamGVar = plugin.getConfig().getStringList("guards").iterator();

	           while(teamGVar.hasNext()) {
	               String member = (String)teamGVar.next();
	               if(member.equals(user)) {
	            	   isTeam = true;
	                }
	           }
			   Iterator<?> teamSVar = plugin.getConfig().getStringList("spectators").iterator();

	           while(teamSVar.hasNext()) {
	               String member = (String)teamSVar.next();
	               if(member.equals(user)) {
	            	   isTeam = true;
	                }
	           }
	           return isTeam;
   		}
}
