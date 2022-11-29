package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnInventoryClick implements Listener {

    private final LobbyPro plugin;

    public final ArrayList<Player> redstoneParticleList = new ArrayList<>();
    public final ArrayList<Player> heartParticleList = new ArrayList<>();
    public final ArrayList<Player> lavaParticleList = new ArrayList<>();
    public final ArrayList<Player> waterParticleList = new ArrayList<>();
    public final ArrayList<Player> potionParticleList = new ArrayList<>();
    public final ArrayList<Player> snowParticleList = new ArrayList<>();
    public final ArrayList<Player> musicParticleList = new ArrayList<>();

    public OnInventoryClick(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        // Cancelling the ability of moving items in the inventory.
        if(e.getWhoClicked() instanceof Player && e.getClickedInventory() != null) {
            List<ItemStack> items = new ArrayList<>();
            items.add(e.getCurrentItem());
            items.add(e.getCursor());
            // Disabling the number_key event on inventory click.
            items.add((e.getClick() == org.bukkit.event.inventory.ClickType.NUMBER_KEY) ? e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) : e.getCurrentItem());
            // if the ItemStack has one of the events above, set cancelled.
            for(ItemStack item : items) {
                if(item != null && item.hasItemMeta()) {
                    e.setCancelled(true);
                }
            }
        }
        // Checking if the clicked person is op, or is in creative mode.
        if(e.getWhoClicked().isOp() && e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(false);
        }

        InventoryView view = e.getView();

        /* ---------- GAME MENU SELECTOR SECTION ---------- */

        // Checking if the menu title == Game Menu, then checking the slots
        if(view.getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Game Menu")){
            // Checking the slot and the item, if correct, execute.
            if(e.getSlot() == 11){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.GRASS_BLOCK)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "Joining Survival...");
                    }
                }
            }
            // Checking the slot and the item, if correct, execute.
            else if(e.getSlot() == 13){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.DIAMOND_SWORD)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "Joining Free For All...");
                    }
                }
            }
            // Checking the slot and the item, if correct, execute.
            else if(e.getSlot() == 15) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.EMERALD)) {
                    if (e.getClick().isLeftClick()) {
                        p.closeInventory();
                        p.sendMessage(ChatColor.GRAY + "Joining Parkour...");
                    }
                }
            }
            // Checking the slot and the item, if correct, execute.
            else if (e.getSlot() == 31) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE)) {
                    if(e.getClick().isLeftClick()) {
                        p.closeInventory();
                    }
                }
            }
        }

        /* ---------- PARTICLE SELECTOR MENU SECTION ---------- */

        // Checking if the menu title == Particle Selector, then checking the slots
        else if(view.getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Particle Selector")){
            // Checking the slot and the item, if correct, execute.
            if(e.getSlot() == 10){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE_TORCH)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (redstoneParticleList.contains(p)) {
                            redstoneParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(potionParticleList.contains(p)){
                            potionParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            redstoneParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else{
                            redstoneParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 11){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.RED_DYE)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (heartParticleList.contains(p)) {
                            heartParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(potionParticleList.contains(p)){
                            potionParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            heartParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else{
                            heartParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 12){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.WATER_BUCKET)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (waterParticleList.contains(p)) {
                            waterParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(potionParticleList.contains(p)){
                            potionParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            waterParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else{
                            waterParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 13){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.LAVA_BUCKET)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (lavaParticleList.contains(p)) {
                            lavaParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(potionParticleList.contains(p)){
                            potionParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            lavaParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else{
                            lavaParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 14){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.POTION)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            potionParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else{
                            potionParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 15){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.SNOWBALL)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (snowParticleList.contains(p)) {
                            snowParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(musicParticleList.contains(p)){
                            musicParticleList.remove(p);
                            snowParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else {
                            snowParticleList.add(p);
                        }
                    }
                }
            }
            else if(e.getSlot() == 16){
                if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.NOTE_BLOCK)){
                    if(e.getClick().isLeftClick()){
                        p.closeInventory();

                        if (musicParticleList.contains(p)) {
                            musicParticleList.remove(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-off-message"))));
                        }
                        else if(heartParticleList.contains(p)){
                            heartParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(waterParticleList.contains(p)){
                            waterParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(redstoneParticleList.contains(p)){
                            redstoneParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if (potionParticleList.contains(p)) {
                            potionParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(lavaParticleList.contains(p)){
                            lavaParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else if(snowParticleList.contains(p)){
                            snowParticleList.remove(p);
                            musicParticleList.add(p);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("particle-trail-on-message"))));
                        }
                        else {
                            musicParticleList.add(p);
                        }
                    }
                }
            }
            else if (e.getSlot() == 31) {
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.REDSTONE)) {
                    if(e.getClick().isLeftClick()) {
                        p.closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        World world = p.getWorld();

        // When a player is moving and a certain particle is selected in the particle GUI, add the trail.

        if (redstoneParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(200, 0, 55), 1.0F);
                p.spawnParticle(Particle.REDSTONE, p.getLocation(), 50, dustOptions);
            }
        }
        else if(heartParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.HEART, p.getLocation(), 1);
            }
        }
        else if(waterParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.WATER_SPLASH, p.getLocation(), 10);
            }
        }
        else if(lavaParticleList.contains(p)){
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.FALLING_LAVA, p.getLocation(), 10);
            }
        }
        else if (potionParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                double red = 0 / 255D;
                double green = 127 / 255D;
                double blue = 255 / 255D;
                p.spawnParticle(Particle.SPELL_MOB, p.getLocation(), 0, red, green, blue, 1);
            }
        }
        else if (snowParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                p.spawnParticle(Particle.SNOWBALL, p.getLocation(), 10);
            }
        }
        else if (musicParticleList.contains(p)) {
            if(!e.getFrom().getBlock().equals(Objects.requireNonNull(e.getTo()).getBlock())){
                // Spawning the particles at the player
                double note = 6 / 24D; // 6 is the value of the red note
                p.spawnParticle(Particle.NOTE, p.getLocation(), 0, note, 0, 0, 1);
            }
        }

    }

}
