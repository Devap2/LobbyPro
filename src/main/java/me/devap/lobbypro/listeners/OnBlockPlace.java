package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {

    private final LobbyPro plugin;

    public OnBlockPlace(LobbyPro plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

        Player p = e.getPlayer();

        // If the player is not op, cancel the event.
        if(plugin.getConfig().getBoolean("disable-block-placing")){
            if(!p.isOp()){
                e.setCancelled(true);
            }
        }
    }
}
