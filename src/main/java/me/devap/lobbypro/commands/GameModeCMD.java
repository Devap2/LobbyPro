package me.devap.lobbypro.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        /* Creating shortcuts for the /gamemode creative/survival/adventure/spectator commands. */

        if(command.getName().equalsIgnoreCase("gmc")){
            if(p.isOp()){
                p.setGameMode(GameMode.CREATIVE);
            }
        }
        if(command.getName().equalsIgnoreCase("gms")){
            if(p.isOp()){
                p.setGameMode(GameMode.SURVIVAL);
            }
        }
        if(command.getName().equalsIgnoreCase("gma")){
            if (p.isOp()) {
                p.setGameMode(GameMode.ADVENTURE);
            }
        }
        if(command.getName().equalsIgnoreCase("gmp")){
            if (p.isOp()) {
                p.setGameMode(GameMode.SPECTATOR);
            }
        }

        return false;
    }
}
