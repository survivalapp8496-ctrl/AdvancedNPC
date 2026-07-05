package com.survivalapp8496.advancednpc;

import com.survivalapp8496.advancednpc.npc.NPCManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedNPCPlugin extends JavaPlugin {

    private static AdvancedNPCPlugin instance;
    private NPCManager npcManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("data.yml", false);

        npcManager = new NPCManager();

        getLogger().info("AdvancedNPC Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AdvancedNPC Disabled!");
    }

    public static AdvancedNPCPlugin getInstance() {
        return instance;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }
}
