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

        if(command.getName().equalsIgnoreCase("gmc")){
            p.setGameMode(GameMode.CREATIVE);
        }
        if(command.getName().equalsIgnoreCase("gms")){
            p.setGameMode(GameMode.SURVIVAL);
        }
        if(command.getName().equalsIgnoreCase("gma")){
            p.setGameMode(GameMode.ADVENTURE);
        }
        if(command.getName().equalsIgnoreCase("gmp")){
            p.setGameMode(GameMode.SPECTATOR);
        }

        return false;
    }
}
