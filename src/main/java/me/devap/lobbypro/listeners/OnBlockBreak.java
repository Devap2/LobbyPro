package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    private final LobbyPro plugin;

    public OnBlockBreak(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player p = e.getPlayer();

        if(plugin.getConfig().getBoolean("disable-block-breaking")){
            // If the player is not op, cancel the event.
            if(!p.isOp()){
                e.setCancelled(true);
            }
        }
    }
}
