package com.survivalapp8496.advancednpc;

import com.survivalapp8496.advancednpc.commands.NPCCommand;
import com.survivalapp8496.advancednpc.listener.NPCListener;
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

        getLogger().info("AdvancedNPC Enabled!");
    }

    @Override
    public void onDisable() {

        if (storage != null && npcManager != null) {
            storage.saveNPCs(npcManager);
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

    public PacketManager getPacketManager() {
        return packetManager;
    }

    public YamlStorage getStorage() {
        return storage;
    }
}
