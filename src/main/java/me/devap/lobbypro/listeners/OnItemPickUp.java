package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class OnItemPickUp implements Listener {

    private final LobbyPro plugin;

    public OnItemPickUp(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e){

        Player p = (Player) e.getEntity();

        if(e.getEntityType().equals(EntityType.PLAYER)){
            if(!p.isOp()){
                if(plugin.getConfig().getBoolean("disable-item-pick-up")){
                    e.setCancelled(true);
                }
            }
        }
    }
}
