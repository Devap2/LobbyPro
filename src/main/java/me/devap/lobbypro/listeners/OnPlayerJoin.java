package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

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

            String joinMessage = plugin.getConfig().getString("join-message");
            String defaultJoinMessage = (ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY + pname + " has joined the server.");

            // Setting the player's custom join message if set in config.
            if(plugin.getConfig().getBoolean("enable-join-message")){
                assert joinMessage != null;
                e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
            }
            else{
                return;
            }

            // Enabling a sound played when a player joins.
            if(plugin.getConfig().getBoolean("enable-sound-on-join")){
                p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.E));
            }
            else {
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
            emeraldMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Updates");
            emeraldMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to see the server and plugin updates", ChatColor.GRAY + "New: lobby and survival plugin!"));
            emerald.setItemMeta(emeraldMeta);

            // Particle Selector ItemStack + ItemMeta
            ItemStack feather = new ItemStack(Material.FEATHER);
            ItemMeta featherMeta = feather.getItemMeta();
            assert featherMeta != null;
            featherMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Leap Jump");
            featherMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Right click to launch yourself into the air!"));
            feather.setItemMeta(featherMeta);

            // Lobby Navigator ItemStack + ItemMeta
            ItemStack lobbyNavigator = new ItemStack(Material.CLOCK);
            ItemMeta navigatorMeta = lobbyNavigator.getItemMeta();
            assert navigatorMeta != null;
            navigatorMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lobby Navigator");
            navigatorMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to open the gamemode navigator menu", ChatColor.GRAY + "Try to win!"));
            lobbyNavigator.setItemMeta(navigatorMeta);

            // Particle Selector ItemStack + ItemMeta
            ItemStack particleSelector = new ItemStack(Material.FIRE_CHARGE);
            ItemMeta particleSelectorMeta = particleSelector.getItemMeta();
            assert particleSelectorMeta != null;
            particleSelectorMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Particle Selector");
            particleSelectorMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to open the particle selector menu", ChatColor.GRAY + "New: redstone, cloud and coal!"));
            particleSelector.setItemMeta(particleSelectorMeta);

            // Server Information Book ItemStack + ItemMeta
            ItemStack infoBook = new ItemStack(Material.BOOK);
            ItemMeta infoBookMeta = infoBook.getItemMeta();
            assert infoBookMeta != null;
            infoBookMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Server Information");
            infoBookMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right click to see the server information", ChatColor.GRAY + "Go to our webshop to see our ranks!"));
            infoBook.setItemMeta(infoBookMeta);

            // Giving the player the items
            p.getInventory().setItem(0, emerald);
            p.getInventory().setItem(1, feather);
            p.getInventory().setItem(4, lobbyNavigator);
            p.getInventory().setItem(8, infoBook);
            p.getInventory().setItem(7, particleSelector);

        }
    }
}