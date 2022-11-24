package me.devap.lobbypro.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class OnPlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();

        /* Getting the player interact event, if it is the lobby navigator, do this ... */

        // Checking if the item in the main hand of the player is a clock
        // And checking if the item has a display name of "lobby navigator"
        if (p.getInventory().getItemInMainHand().getType() == Material.CLOCK) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lobby Navigator")) {

                // Checking if the player action is equals to right clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        // Opens the game-mode gui menu
                        p.performCommand("lp gui");

                    }
                }
            }
        }
        // Doing the same check's for the player interact event as above.
        else if (p.getInventory().getItemInMainHand().getType() == Material.EMERALD) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Updates")) {

                // Checking if the player action is equals to right clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        p.performCommand("lp updates");

                    }
                }
            }
        }
        else if (p.getInventory().getItemInMainHand().getType() == Material.BOOK) {
            if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "Server Information")) {

                // Checking if the player action is equals to right clicking air.
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    EquipmentSlot slot = e.getHand();

                    // Get the slot, so that it doesn't call twice.
                    assert slot != null;
                    if (slot.equals(EquipmentSlot.HAND)) {

                        p.performCommand("lp info");

                    }
                }
            }
        }
    }
}
