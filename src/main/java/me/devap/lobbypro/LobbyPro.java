package me.devap.lobbypro;

import me.devap.lobbypro.commands.LobbyCMD;
import me.devap.lobbypro.commands.LobbyProCMDS;
import me.devap.lobbypro.commands.ScoreBoardCMD;
import me.devap.lobbypro.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.List;

public final class LobbyPro extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic.

        // Registering the listeners.
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnFoodLevelChange(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamage(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnSwapHandItems(), this);
        getServer().getPluginManager().registerEvents(new OnItemDrop(), this);

        // Registering the commands.
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCMD(this));
        Objects.requireNonNull(getCommand("sb")).setExecutor(new ScoreBoardCMD(this));
        Objects.requireNonNull(getCommand("lp")).setExecutor(new LobbyProCMDS(this));
        Objects.requireNonNull(getCommand("lp")).setTabCompleter(this);

        // Config
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        // Dispatch the '/help' command
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "/help");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("LobbyPro plugin shutting down...");

        this.saveConfig();

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // Creating the list of strings
        List<String> list = Arrays.asList("help", "setspawn", "setlobby", "gui", "info", "author");
        String input = args[0].toLowerCase();

        List<String> completions = null;
        for(String s : list){
            if(s.startsWith(input)){

                if(completions == null){
                    completions = new ArrayList<>();
                }

                completions.add(s);

            }
        }

        if(completions != null)
            Collections.sort(completions);

        return completions;
    }


}
