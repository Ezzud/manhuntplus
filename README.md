![Img](https://media.discordapp.net/attachments/886217701930987623/905948716677070888/band1.png)<br />
<h3>Manhunt Plus is a plugin, who allow you to have custom manhunt in your server!
Plugin is fully configurable</h3><br />



![Img](https://media.discordapp.net/attachments/886217701930987623/905948717402701864/band2.png)<br />
<p>

- Multiple Teams (Customisable name, color)
- Compass who point the speedrunner, customisable refresh cooldown
- Whitelist system who can whitelist only players in the config
- Guard team (can't do damage to speedrunner)
- Customisable lobby spawnpoint, team spawnpoint, guard spawnpoint, speedrunner spawnpoint
- Spectator team!
- ✨Customisable effects for every team
- ⚔ Customisable starting items for every team
- Custom join/leave messages
- Customisable messages
- ✅ Customisable permissions
- ⌨ Win/Loose commands
- Private Teamchat

</p>


![Img](https://cdn.discordapp.com/attachments/886217701930987623/905948718489030716/band3.png)<br />
<p>

![Img](https://media.discordapp.net/attachments/886217701930987623/906163894903525407/unknown.png)<br />
- /manhunt - *Main command*​
- /manhunt help - *Display the help message* `Permission: manhunt.default`​
- /manhunt list - *List all team members* `Permission: manhunt.default`​
- /manhunt setspawn - *Change lobby spawnpoint* `Permission: manhunt.admin` ​
- /manhunt setlocation <Team> - *Change team's spawnpoint* `Permission: manhunt.admin`
  <br>**Teams: team1, team2, guards, speedrunner** ​
- /manhunt addteammember <Player Name> <Team> - *Add a member in a team* `Permission: manhunt.admin`
<br>**Teams: team1, team2, guards, spectators**​
- /manhunt removeteammember <Player Name> <Team> - *Remove a member from a team* `Permission: manhunt.admin`
  <br>**Teams: team1, team2, guards, spectators**​
- /manhunt start - *Start the game* `Permission: manhunt.admin` ​
- /manhunt stop - *Stop the game* `Permission: manhunt.admin`​
- /manhunt reload - *Reload the config* `Permission: manhunt.admin`​
- /manhunt tp - *Allow guards to teleport to speedrunner*
  
</p>

![Img](https://cdn.discordapp.com/attachments/886217701930987623/905948719587934279/band4.png)<br />
<p>
Default config: <a href="https://github.com/Ezzud/manhuntplus/blob/main/config.yml">Click here!</a><br />
Support: <a href="https://ezzud.fr/discord">Click here!</a><br />
Spigot link: <a href="https://www.spigotmc.org/resources/%E2%AD%90-manhunt-plus-%E2%AD%90-teams-guards-effects-%E2%AD%90-1-16-5-%E2%AD%90.97396">Click here!</a><br />
API Usage example:<br />

```Java

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
	    getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onGameStart(manhuntGameStartEvent e)
	{
		String speedrunner = e.getSpeedrunner().getSpeedrunnerName();
		String speedrunnerColor = e.getSpeedrunner().getColor();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "Let's chase " + speedrunnerColor + speedrunner));
	}
	
	@EventHandler
	public void onGameStop(manhuntGameStopEvent e)
	{
		String speedrunner = e.getSpeedrunner().getSpeedrunnerName();
		String speedrunnerColor = e.getSpeedrunner().getColor();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "Ok Stop chasing " + speedrunnerColor + speedrunner));
	}
	
	@EventHandler
	public void onTeamWin(manhuntSpeedrunnerKilledEvent e)
	{
		manhuntTeam winnerteam = e.getWinner();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "The winner team is " + winnerteam.getColor() + winnerteam.getName()));
		manhuntTeam looserteam = e.getLooser();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "The looser team is " + looserteam.getColor() + looserteam.getName()));
	}
	   
	@EventHandler
	public void onSpeedrunnerIsBad(manhuntSpeedrunnerDiedEvent e)
	{
		String speedrunner = e.getSpeedrunner().getSpeedrunnerName();
		String speedrunnerColor = e.getSpeedrunner().getColor();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "Lol " + speedrunnerColor + speedrunner + " &rdied"));
	}
	
	@EventHandler
	public void onSpeedrunnerWin(manhuntSpeedrunnerWinEvent e)
	{
		String speedrunner = e.getSpeedrunner().getSpeedrunnerName();
		String speedrunnerColor = e.getSpeedrunner().getColor();
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "GG TO " + speedrunnerColor + speedrunner));
	}
	
	boolean GameState = new manhuntGameState().getGameState();
	
	manhuntTeamManager manager = new manhuntTeamManager();
	manhuntTeam team1 = manager.getTeam1();
	List<String> team1_members = team1.getMembers();
	
	manhuntSpeedrunner speedrunner = new manhuntSpeedrunner();
	String player = speedrunner.getSpeedrunnerName();

```

</p>
