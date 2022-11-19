package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamage implements Listener {

    private final LobbyPro plugin;

    public OnPlayerDamage(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
            if(plugin.getConfig().getBoolean("disable-fire-damage")){
                e.setCancelled(true);
            }
        }
        else if(e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){
            if(plugin.getConfig().getBoolean("disable-lava-damage")){
                e.setCancelled(true);
            }
        }
        else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            if(e.getEntityType().equals(EntityType.PLAYER)){
                if(plugin.getConfig().getBoolean("disable-fall-damage")){
                    e.setCancelled(true);
                }
            }
        }
        else{
            e.setCancelled(false);
        }
    }
}
