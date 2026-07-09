package com.survivalapp8496.advancednpc.protocol;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;

public class ProtocolManager {

    private final AdvancedNPCPlugin plugin;
    private final ProtocolManager protocolManager;

    public ProtocolManager(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public void register() {

        protocolManager.addPacketListener(
                new ProtocolListener(plugin)
        );

        plugin.getLogger().info("--------------------------------");
        plugin.getLogger().info("ProtocolLib Loaded");
        plugin.getLogger().info("Left Click NPC Enabled");
        plugin.getLogger().info("--------------------------------");
    }

    public void unregister() {
        protocolManager.removePacketListeners(plugin);
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
