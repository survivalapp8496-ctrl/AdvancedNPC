package com.survivalapp8496.advancednpc;

import com.survivalapp8496.advancednpc.npc.NPCManager;
import com.survivalapp8496.advancednpc.storage.YamlStorage;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedNPCPlugin extends JavaPlugin {

    private static AdvancedNPCPlugin instance;

    private NPCManager npcManager;
    private YamlStorage storage;

    @Override
    public void onEnable() {
        instance = this;

        // Create default files
        saveDefaultConfig();
        saveResource("data.yml", false);

        // Create managers
        npcManager = new NPCManager();
        storage = new YamlStorage(this);

        // Load NPCs from data.yml
        storage.loadNPCs(npcManager);

        getLogger().info("AdvancedNPC Enabled!");
    }

    @Override
    public void onDisable() {

        // Save all NPCs
        if (storage != null && npcManager != null) {
            storage.saveNPCs(npcManager);
        }

        getLogger().info("AdvancedNPC Disabled!");
    }

    public static AdvancedNPCPlugin getInstance() {
        return instance;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public YamlStorage getStorage() {
        return storage;
    }
}
