package com.survivalapp8496.advancednpc;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedNPCPlugin extends JavaPlugin {

    private static AdvancedNPCPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("data.yml", false);

        getLogger().info("AdvancedNPC enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AdvancedNPC disabled!");
    }

    public static AdvancedNPCPlugin getInstance() {
        return instance;
    }
}
