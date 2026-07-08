package com.survivalapp8496.advancednpc;

import com.survivalapp8496.advancednpc.commands.NPCCommand;
import com.survivalapp8496.advancednpc.listener.NPCListener;
import com.survivalapp8496.advancednpc.npc.NPCEditorManager;
import com.survivalapp8496.advancednpc.npc.NPCManager;
import com.survivalapp8496.advancednpc.npc.NPCRegistry;
import com.survivalapp8496.advancednpc.npc.NPCSpawnManager;
import com.survivalapp8496.advancednpc.packet.PacketManager;
import com.survivalapp8496.advancednpc.storage.YamlStorage;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedNPCPlugin extends JavaPlugin {

    private static AdvancedNPCPlugin instance;

    private NPCManager npcManager;
    private NPCRegistry npcRegistry;
    private NPCSpawnManager npcSpawnManager;
    private NPCEditorManager npcEditorManager;
    private PacketManager packetManager;
    private YamlStorage storage;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        saveResource("data.yml", false);

        npcManager = new NPCManager();
        npcRegistry = new NPCRegistry();
        npcSpawnManager = new NPCSpawnManager();
        npcEditorManager = new NPCEditorManager();
        packetManager = new PacketManager();
        storage = new YamlStorage(this);

        storage.loadNPCs(npcManager);

        if (getCommand("npc") != null) {
            getCommand("npc").setExecutor(new NPCCommand(this));
        }

        getServer().getPluginManager().registerEvents(
                new NPCListener(this),
                this
        );

        getLogger().info("==================================");
        getLogger().info(" AdvancedNPC Enabled");
        getLogger().info(" NPC Manager Loaded");
        getLogger().info(" NPC Registry Loaded");
        getLogger().info(" NPC Spawn Manager Loaded");
        getLogger().info(" NPC Editor Manager Loaded");
        getLogger().info(" Packet Manager Loaded");
        getLogger().info(" YAML Storage Loaded");
        getLogger().info("==================================");
    }

    @Override
    public void onDisable() {

        if (storage != null && npcManager != null) {
            storage.saveNPCs(npcManager);
        }

        if (npcSpawnManager != null) {
            npcSpawnManager.despawnAll();
        }

        if (npcRegistry != null) {
            npcRegistry.clear();
        }

        getLogger().info("AdvancedNPC Disabled!");
    }

    public static AdvancedNPCPlugin getInstance() {
        return instance;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public NPCRegistry getNpcRegistry() {
        return npcRegistry;
    }

    public NPCSpawnManager getNpcSpawnManager() {
        return npcSpawnManager;
    }

    public NPCEditorManager getNpcEditorManager() {
        return npcEditorManager;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }

    public YamlStorage getStorage() {
        return storage;
    }
}
