package me.betun.mineborn;

import com.github.rumsfield.konquest.api.KonquestAPI;
import me.betun.mineborn.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mineborn extends JavaPlugin {

    private KonquestAPI api;
    private boolean isApiEnabled;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Enable API
        isApiEnabled = false;
        Plugin konquest = Bukkit.getPluginManager().getPlugin("Konquest");
        if (konquest != null && konquest.isEnabled()) {
            RegisteredServiceProvider<KonquestAPI> provider = Bukkit.getServicesManager().getRegistration(KonquestAPI.class);
            if (provider != null) {
                api = provider.getProvider();
                isApiEnabled = true;
                Bukkit.getServer().getConsoleSender().sendMessage("Successfully enabled Konquest API");
            } else {
                Bukkit.getServer().getConsoleSender().sendMessage("Failed to enable Konquest API, invalid provider");
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("Failed to enable Konquest API, plugin not found or disabled");
        }

        // Register listener
        getServer().getPluginManager().registerEvents(new PlayerListener(api), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public KonquestAPI getAPI() {
        if(isApiEnabled) {
            return api;
        } else {
            return null;
        }
    }
}
