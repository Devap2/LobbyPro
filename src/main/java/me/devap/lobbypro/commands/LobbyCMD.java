package me.devap.lobbypro.commands;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCMD implements CommandExecutor {

    private final LobbyPro plugin;

    public LobbyCMD(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        /* Setting the '/lobby' command */

        if(sender != null){
            if(command.getName().equals("lobby")){

                // Getting the lobby location from the configuration file.
                int lobbyLocX = plugin.getConfig().getInt("lobby-location.X");
                int lobbyLocY = plugin.getConfig().getInt("lobby-location.Y");
                int lobbyLocZ = plugin.getConfig().getInt("lobby-location.Z");

                Location lobbyLocation = new Location(p.getWorld(), lobbyLocX, lobbyLocY, lobbyLocZ);

                // Checking if the world != null and the player is in the same world.
                if(lobbyLocation.getWorld() != null && p.getWorld().getName().equalsIgnoreCase(lobbyLocation.getWorld().getName())){
                    if(lobbyLocation != null){

                        // Teleporting the player to the lobby location
                        p.teleport(lobbyLocation);

                        // Sending the send to lobby message. (Make this configurable pls)
                        p.sendMessage(ChatColor.GRAY + "Send you to the lobby.");

                        // Setting the default settings when the player has teleported to the lobby location.
                        p.setHealth(20.0);
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setInvulnerable(false);
                        p.setFoodLevel(20);
                    }
                    // If the lobby location == null || the player is not in the same world do ...
                    else{
                        p.sendMessage(ChatColor.RED + "The lobby location has not been set yet.");
                        p.sendMessage(ChatColor.GRAY + "Use the '/lp setspawn' command to set it.");
                    }
                }
            }
        }

        return false;
    }
}
