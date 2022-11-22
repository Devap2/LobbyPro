package me.devap.lobbypro.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnInventoryClick implements Listener {

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
    }
}
