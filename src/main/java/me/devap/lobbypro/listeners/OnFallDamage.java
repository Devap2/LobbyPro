package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnFallDamage implements Listener {

    private final LobbyPro plugin;

    public OnFallDamage(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e){

        // If the damage cause is fall damage for a player, cancel the event.
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            if(e.getEntityType().equals(EntityType.PLAYER)){
                if(plugin.getConfig().getBoolean("disable-fall-damage")){
                    e.setCancelled(true);
                }
            }
        }
    }
}
