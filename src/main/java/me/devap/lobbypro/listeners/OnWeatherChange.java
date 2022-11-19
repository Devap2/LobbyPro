package me.devap.lobbypro.listeners;

import me.devap.lobbypro.LobbyPro;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class OnWeatherChange implements Listener {

    private final LobbyPro plugin;

    public OnWeatherChange(LobbyPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(plugin.getConfig().getBoolean("disable-weather-change")){
            e.setCancelled(true);
        }
    }
}
