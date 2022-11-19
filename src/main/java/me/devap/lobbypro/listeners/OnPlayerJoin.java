package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OnPlayerJoin implements Listener {

    private final LobbyPro plugin;

    public OnPlayerJoin(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        // Getting the player and player name.
        Player p = e.getPlayer();
        String pname = p.getName();

        if(p.getWorld().getName().equals("world")){

            /* Getting the correct lobby location for the player to spawn to on join. */

            // Getting the lobby location from the configuration file and teleporting the player to that location.
            double lobbyLocX = (double) plugin.getConfig().getDouble("lobby-location.X");
            double lobbyLocY = (double) plugin.getConfig().getDouble("lobby-location.Y");
            double lobbyLocZ = (double) plugin.getConfig().getDouble("lobby-location.Z");

            // Creating the lobby location.
            Location lobbyLocation = new Location(p.getWorld(), lobbyLocX, lobbyLocY, lobbyLocZ);

            // Sending the player to lobby location if true in config.
            if(plugin.getConfig().getBoolean("join-spawn")){
                p.teleport(lobbyLocation);
            }
            else {
               return;
            }

            // Clearing player's inventory if true in config.
            if(plugin.getConfig().getBoolean("join-clear-inventory")){
                p.getInventory().clear();
            }
            else{
                return;
            }

            // Setting the player's join message if true in config.
            if(plugin.getConfig().getBoolean("join-message")){
                if(p.hasPlayedBefore()){
                    p.sendMessage(ChatColor.GREEN + "Welcome back!");
                }
                else{
                    p.sendMessage(ChatColor.GREEN + "Hope you enjoy your stay!");
                }
            }
            else{
                return;
            }

            // Setting default settings to the player on join.
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20.0);
            p.setFoodLevel(20);
            p.setWalkSpeed(0.2F);
            p.setInvulnerable(false);
            p.setGlowing(false);

            p.performCommand("sb");

            /* Giving the player the lobby inventory items. */

            // I Don't Know ItemStack + ItemMeta
            ItemStack emerald = new ItemStack(Material.EMERALD);
            ItemMeta emeraldMeta = emerald.getItemMeta();
            assert emeraldMeta != null;
            emeraldMeta.setDisplayName(ChatColor.DARK_GREEN + "Updates");
            emeraldMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to see the server and plugin updates", ChatColor.GRAY + "New, lobby and survival plugin!"));
            emerald.setItemMeta(emeraldMeta);

            // Lobby Navigator ItemStack + ItemMeta
            ItemStack lobbyNavigator = new ItemStack(Material.CLOCK);
            ItemMeta navigatorMeta = lobbyNavigator.getItemMeta();
            assert navigatorMeta != null;
            navigatorMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lobby Navigator");
            navigatorMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to select the gamemode", ChatColor.GRAY + "Enjoy it haha!"));
            lobbyNavigator.setItemMeta(navigatorMeta);

            // Server Information Book ItemStack + ItemMeta
            ItemStack infoBook = new ItemStack(Material.BOOK);
            ItemMeta infoBookMeta = infoBook.getItemMeta();
            assert infoBookMeta != null;
            infoBookMeta.setDisplayName(ChatColor.DARK_GRAY + "Server Information");
            infoBookMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to see the server information", ChatColor.GRAY + "Go to our webshop to see our ranks!"));
            infoBook.setItemMeta(infoBookMeta);

            // Giving the player the items
            p.getInventory().setItem(0, emerald);
            p.getInventory().setItem(4, lobbyNavigator);
            p.getInventory().setItem(8, infoBook);

            /* Checking if the player has played before, change message when has played before.*/

            // If player has played before, send ... else ...
            if(p.hasPlayedBefore()){
                e.setJoinMessage(ChatColor.GRAY + pname + " has joined the server.");
            }
            else{
                e.setJoinMessage(ChatColor.GRAY + pname + " has joined the server for the first time.");
            }

        }
    }
}
