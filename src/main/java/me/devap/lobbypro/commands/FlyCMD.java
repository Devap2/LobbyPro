package me.devap.lobbypro.commands;

import me.devap.lobbypro.LobbyPro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCMD implements CommandExecutor {

    private final LobbyPro plugin;

    private final ArrayList<Player> flying_people_list = new ArrayList<>();

    public FlyCMD(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("fly")){
            if(plugin.getConfig().getBoolean("enable-fly-command")){
                if(sender.isOp()) {
                    if(!p.getGameMode().equals(GameMode.SPECTATOR)){
                        String onMessage = plugin.getConfig().getString("fly-on-message");
                        String offMessage = plugin.getConfig().getString("fly-off-message");

                        if (flying_people_list.contains(p)) {
                            flying_people_list.remove(p);
                            assert offMessage != null;
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', offMessage));
                            p.setAllowFlight(false);
                        }
                        else if (!flying_people_list.contains(p)) {
                            flying_people_list.add(p);
                            assert onMessage != null;
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));
                            p.setAllowFlight(true);
                        }
                    }
                }
                else{
                    p.sendMessage(ChatColor.GRAY + "You don't have the permission required to use this command.");
                }
            }
        }
        return true;
    }

}
