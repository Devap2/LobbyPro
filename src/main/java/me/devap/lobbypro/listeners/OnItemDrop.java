package me.devap.lobbypro.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnItemDrop implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){

        // Another way to do: Making a list with the ItemStacks and checking if that item is in it.

        //Checking if the dropped item is equal to an emerald, clock, or book.
        if(e.getItemDrop().getItemStack().getType().equals(Material.EMERALD) ||
                (e.getItemDrop().getItemStack().getType().equals(Material.CLOCK) ||
                        (e.getItemDrop().getItemStack().getType().equals(Material.BOOK)))
        ){
            e.setCancelled(true);
        }
    }
}
