package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class OnExplosion implements Listener {

    private final LobbyPro plugin;

    public OnExplosion(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTNTExplosion(BlockExplodeEvent e){
        if(e.getBlock().equals(Material.TNT)){
            if(plugin.getConfig().getBoolean("disable-tnt-explosion")){
                e.setCancelled(true);
            }
        }
    }
}
