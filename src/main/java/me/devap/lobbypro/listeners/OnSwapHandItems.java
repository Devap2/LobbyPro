package me.devap.lobbypro.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class OnSwapHandItems implements Listener {

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent e){

        /* Checking the player swap event */

        // Checking if the item which is swapped in off hand is equal to emerald, book or clock. If true, cancel the event.
        if(e.getOffHandItem().getType().equals(Material.EMERALD) ||
                e.getOffHandItem().getType().equals(Material.BOOK) ||
                e.getOffHandItem().getType().equals(Material.CLOCK)) {
            e.setCancelled(true);
        }
        // Checking if the item which is swapped in main hand is equal to emerald, book or clock. If true, cancel the event.
        if(e.getMainHandItem().getType().equals(Material.EMERALD) ||
                e.getMainHandItem().getType().equals(Material.BOOK) ||
                e.getMainHandItem().getType().equals(Material.CLOCK)) {
            e.setCancelled(true);
        }
    }
}
