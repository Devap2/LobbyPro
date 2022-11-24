package me.devap.lobbypro.commands;

import me.devap.lobbypro.LobbyPro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class LobbyProCMDS implements CommandExecutor {

    private final LobbyPro plugin;

    public LobbyProCMDS(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        /* Setting different functions within the '/lp <insert command here>' commands. */

        // Checking if the sender != null and is and player.
        if(sender != null) {
            if (command.getName().equalsIgnoreCase("lp")) {
                // If the command is equal to 'lp' and the argument length is 0 do ...
                if (args.length == 0) {
                    p.sendMessage(ChatColor.GRAY + "You must provide an argument, for example: /lp <help>");
                }
                // Else, if the arguments 0 are 'setspawn' or 'setlobby' set the server lobby/spawn location.
                else {
                    if (args[0].equalsIgnoreCase("setspawn") || args[0].equalsIgnoreCase("setlobby")) {

                        Location location = p.getLocation();

                        p.sendMessage(ChatColor.GREEN + "Successfully set the lobby spawn location.");

                        // Getting the configuration file and setting the location values.
                        plugin.getConfig().set("lobby-location.World-name", Objects.requireNonNull(location.getWorld()).getName());
                        plugin.getConfig().set("lobby-location.X", location.getX());
                        plugin.getConfig().set("lobby-location.Y", location.getY());
                        plugin.getConfig().set("lobby-location.Z", location.getZ());
                        plugin.getConfig().set("lobby-location.Yaw", location.getYaw());
                        plugin.getConfig().set("lobby-location.Pitch", location.getPitch());

                        // Saving the changes made in the configuration file.
                        plugin.getConfig().options().copyDefaults(true);
                        plugin.saveConfig();

                    }
                    // Else if the arguments 0 are 'gui' or 'menu' do ...
                    else if(args[0].equalsIgnoreCase("gui") || args[0].equalsIgnoreCase("menu")){

                        Inventory gui = Bukkit.createInventory(p, 36, ChatColor.DARK_RED + "Game Menu");

                        // The item stacks for in the gui.
                        ItemStack survival = new ItemStack(Material.GRASS_BLOCK);
                        ItemStack ffa = new ItemStack(Material.DIAMOND_SWORD);
                        ItemStack parkour = new ItemStack(Material.EMERALD);
                        ItemStack closeGUI = new ItemStack(Material.REDSTONE);

                        // Setting the survival meta/lore.
                        ItemMeta survivalMeta = survival.getItemMeta();
                        assert survivalMeta != null;
                        survivalMeta.setDisplayName(ChatColor.DARK_AQUA + "Survival");
                        ArrayList<String> survivalLore = new ArrayList<>();
                        survivalLore.add(ChatColor.GREEN + "Click to join survival.");
                        survivalMeta.setLore(survivalLore);
                        survival.setItemMeta(survivalMeta);

                        // Setting the ffa meta/lore
                        ItemMeta ffaMeta = ffa.getItemMeta();
                        assert ffaMeta != null;
                        ffaMeta.setDisplayName(ChatColor.DARK_RED + "FFA");
                        ArrayList<String> ffaLore = new ArrayList<>();
                        ffaLore.add(ChatColor.GREEN + "Click to join ffa.");
                        ffaMeta.setLore(ffaLore);
                        ffa.setItemMeta(ffaMeta);

                        // setting the parkour meta/lore
                        ItemMeta parkourMeta = parkour.getItemMeta();
                        assert parkourMeta != null;
                        parkourMeta.setDisplayName(ChatColor.DARK_GREEN + "Parkour");
                        ArrayList<String> parkourLore = new ArrayList<>();
                        parkourLore.add(ChatColor.GREEN + "Click to join parkour.");
                        parkourMeta.setLore(parkourLore);
                        parkour.setItemMeta(parkourMeta);

                        // Setting the close menu meta/lore.
                        ItemMeta closeGUIMeta = closeGUI.getItemMeta();
                        assert closeGUIMeta != null;
                        closeGUIMeta.setDisplayName(ChatColor.DARK_GRAY + "Close Menu");
                        ArrayList<String> closeGUILore = new ArrayList<>();
                        closeGUILore.add(ChatColor.GRAY + "Left click to leave the menu.");
                        closeGUIMeta.setLore(closeGUILore);
                        closeGUI.setItemMeta(closeGUIMeta);

                        // Setting the items in the gui.
                        gui.setItem(11, survival);
                        gui.setItem(13, ffa);
                        gui.setItem(15, parkour);
                        gui.setItem(31, closeGUI);


                        // Open the lobby game-mode gui.
                        p.openInventory(gui);


                    }
                    else if(args[0].equalsIgnoreCase("info")){

                        // Sending the player the server information message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED +  "DevCraft Plugin Information:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Author: " + org.bukkit.ChatColor.GRAY + "Devap");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Developer: " + org.bukkit.ChatColor.GRAY + "Devap");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Version: " + org.bukkit.ChatColor.GRAY + "v1.0.0");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Release: " + org.bukkit.ChatColor.GRAY + "11-24-2022");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Downloads: " + org.bukkit.ChatColor.GRAY + "See spigot page.");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Language: " + org.bukkit.ChatColor.GRAY + "English.");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");

                    }
                    else if(args[0].equalsIgnoreCase("help")){

                        // Sending the player the server information message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "---------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED +  "DevCraft Plugin Help:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "---------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp help " + org.bukkit.ChatColor.GRAY + "To show this page");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp info " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp updates " + org.bukkit.ChatColor.GRAY + "To show the plugin update information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp version " + org.bukkit.ChatColor.GRAY + "To show the plugin information");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp reload " + org.bukkit.ChatColor.GRAY + "To reload the LobbyPro plugin");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp setlobby " + org.bukkit.ChatColor.GRAY + "To set the lobby location");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "/lp gui " + org.bukkit.ChatColor.GRAY + "To open the game selector gui");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "---------------------");

                    }
                    else if(args[0].equalsIgnoreCase("updates")){

                        // Sending the player the server update message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED +  "DevCraft Update Information:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "New Lobby Plugin");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "New Survival Plugin");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Added: " + org.bukkit.ChatColor.GRAY + "New Features In Hub");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "----------------------------");

                    }
                    else if(args[0].equalsIgnoreCase("version")){

                        // Sending the player the server information message.
                        p.sendMessage("");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "------------------------");
                        p.sendMessage(org.bukkit.ChatColor.RED +  "LobbyPro Plugin Version:");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "------------------------");
                        p.sendMessage(org.bukkit.ChatColor.GOLD + "Current Plugin Version " + org.bukkit.ChatColor.GRAY + "v1.0.0");
                        p.sendMessage(org.bukkit.ChatColor.GRAY + "------------------------");

                    }
                    else {
                        if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {

                            p.sendMessage(ChatColor.GRAY + "Reloading the plugin configuration file.");
                            plugin.reloadConfig();
                            plugin.saveDefaultConfig();
                            p.sendMessage(ChatColor.GREEN + "Reload complete.");

                        }
                    }
                }
            }
        }

        return false;
    }
}
