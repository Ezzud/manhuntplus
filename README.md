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
API Usage example:<br>
```YAML
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
Default config:<br>
```YAML
########################################################################################################################
#                                                                                                                      #
#                                                          CORE                                                        #
#                                                                                                                      #
########################################################################################################################

prefix: "&8[&3Manhunt&8] &a"
whitelistEnabled: true 
# Whitelist: 
# - When plugin is enabled, whitelist is cleared, and all peoples in teams configured below will be added to the whitelist
# - When plugin is disabled, whitelist is cleared
globalChatPrefix: '@' #ONLY 1 CHARACTER ALLOWED
privateChatFormat: "%teamColor%[%team%] &r%playername%: %message%" #Variables: %teamColor%, %team%, %playername% & %message%
publicChatFormat: "&e[GLOBAL] &r%playername%: %message%" # Variables: %playername% & %message%
joinMessage: "&a%player% joined the party!" # Variables: %player%
leaveMessage: "&a%player% leaved the party!" # Variables: %player%




########################################################################################################################
#                                                                                                                      #
#                                                      Permissions                                                     #
#                                                                                                                      #
########################################################################################################################

permissions_start: "manhunt.admin"
permissions_stop: "manhunt.admin"
permissions_reload: "manhunt.admin"
permissions_setspawn: "manhunt.admin"
permissions_setlocation: "manhunt.admin"
permissions_setspeedrunner: "manhunt.admin"
permissions_addteam: "manhunt.admin"
permissions_removeteam: "manhunt.admin"
permissions_list: "manhunt.default"
permissions_help: "manhunt.default"




########################################################################################################################
#                                                                                                                      #
#                                                   GAME SETTINGS                                                      #
#                                                                                                                      #                                                               #
#    Colors list: https://www.spigotmc.org/wiki/textcosmetics-colors-and-formats/                                      #
#                                                                                                                      #
########################################################################################################################

hunted: 'Speedrunner'
team1:
   - 'user1'   
team2:
   - 'user2'
guards:
   - "user3"  
spectators:
   - "user4"
     
team1name: "Blue"
team2name: "Red"
guardname: "Guards"
spectatorname: "Spectators"
speedrunnername: "Speedrunner"
team1Color: "&9"
team2Color: "&c"
huntedColor: "&e"
guardColor: "&6"
spectatorColor: "&7"
coordinatesRefreshDelay: 45 #Commas do not work
counterToStart: 5 #Countdown before game starts

winCommands:
   - "tell %player% You win!"
   
# Only speedrunner receive defeat commands WHEN HE DIED
defeatCommands:
   - "tell %player% You lost!"
   




########################################################################################################################
#                                                                                                                      #
#                                                    Coordinates                                                       #
#                                                                                                                      #
#    /!\ Commas don't work                                                                                             #
#                                                                                                                      #
########################################################################################################################

spawnCoords: 0, 80, 0

huntedCoords: 0, 80, 0
guardCoords: 0, 80, 0
team1Coords: -200, 80, -200
team2Coords: 200, 80, -200




########################################################################################################################
#                                                                                                                      #
#                                                      Items                                                           #
#                                                                                                                      #
#    Format: ITEM NAME, AMOUNT                                                                                         #
#    Item list: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html                                      #
#                                                                                                                      #
########################################################################################################################

teamItems:
   - COMPASS, 1
huntedItems:
   - COOKED_BEEF, 64
   - STONE_SWORD, 1
   - STONE_PICKAXE, 1
   - STONE_AXE, 1
   - LEATHER_HELMET, 1
   - LEATHER_CHESTPLATE, 1
   - LEATHER_LEGGINGS, 1
   - LEATHER_BOOTS, 1
guardItems:
   - COOKED_BEEF, 64
   - STONE_SWORD, 1
   
   
   
      
########################################################################################################################
#                                                                                                                      #
#                                                      EFFECTS                                                         #
#                                                                                                                      #
#    Format: EFFECT NAME, NUMBER OF SECONDS, AMPLIFIER                                                                 #
#    Effects list: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html                    #
#                                                                                                                      #
########################################################################################################################

# Effects given at the beginning of the game to each TEAM
teamEffects:
   - REGENERATION, 15, 0
# Effects given at the beginning of the game to the speedrunner
huntedEffects: 
   - HEALTH_BOOST, 999999, 9
   - HEAL, 10, 100
# Effects given at the beginning of the game to guards
guardEffects:
   - REGENERATION, 15, 0
   
# Effects given to the speedrunner when he eats 
huntedEatEffects:
   - REGENERATION, 15, 0




########################################################################################################################
#                                                                                                                      #
#                                                      MESSAGES                                                        #
#                                                                                                                      #                                                               #
#    Colors list: https://www.spigotmc.org/wiki/textcosmetics-colors-and-formats/                                      #
#                                                                                                                      #
########################################################################################################################

messages_noPermission: '&cYou dont have permission to use this command!'

messages_reloadMessageSuccessful: '&7Reload Successful'
messages_notPlayer: '&cYou are not a player.'

messages_speedrunnerNotConnected: "&cSpeedrunner is not online"
messages_notAGuard: "&cYou are not a guard"

messages_gameAlreadyStarted: '&cGame already started'
messages_gameStarted: "&aGame just started"
messages_gameIsStarting: "&aStarting in..."
messages_gameStartingCount: "&b%seconds%"

messages_gameNotStarted: "&cGame is not started"
messages_gameStopped: "&cGame has been stopped"

messages_coordinatesMessage: "&6%player%'s coordinates are: &3&l%coordinates%" # Variables: %player%
messages_coordinatesNotConnected: "&cCoordinates cannot be sent because the speedrunner isn't connected!"

messages_joinTeam: "&7You joined the %teamColor%%team% &7team" # Variables: %teamColor%, %team%
messages_groupChatActivated: "&7TEAMCHAT &aON &7> Start your message by &e%groupPrefix% &7to talk in the global chat" # Variables: %groupPrefix%

messages_teleporting: "&7Teleporting &6%player%.." # Variables: %player%
messages_teleported: "&aYou have been teleported!"
messages_teamReveal: "&7[&a&l!&r&7] &7You are in the %teamColor%%team% &7team " # Variables: %teamColor%, %team%
messages_objectives_team: "&7[&a&l!&r&7] &7You can locate %player% with the compass in your inventory or in the chat" # Variables: %player%
messages_objectives_guard: "&7[&a&l!&r&7] &7You have to protect %player%" # Variables: %player%
messages_feature_team: "&7[&a&l!&r&7] &cWARNING &7Compass direction is refreshed every %delay%s and coordinates are sent in chat every %delay%s" # Variables: %delay%
messages_feature_guard: "&7[&a&l!&r&7] &7You can teleport to him using &e/manhunt tp"

messages_huntedKilled: "&6&l%player% &3has been killed!" # Variables: %player%
messages_victory: "&eVictory of the %teamColor%%team%&r team" # Variables: %teamColor%, %team%
messages_enderdragon_win: "&aVictory of %player%" # Variables: %player%


# End of the config file
```
Support: <a href="https://ezzud.fr/discord">Click here!</a><br />
Spigot link: <a href="https://www.spigotmc.org/resources/%E2%AD%90-manhunt-plus-%E2%AD%90-teams-guards-effects-%E2%AD%90-1-16-5-%E2%AD%90.97396">Click here!</a><br />

</p>
