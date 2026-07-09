package com.survivalapp8496.advancednpc.protocol;

import com.comphenix.protocol.ProtocolLibrary;
import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;

public class ProtocolHook {

    private final AdvancedNPCPlugin plugin;
    private final com.comphenix.protocol.ProtocolManager protocolManager;

    public ProtocolHook(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public void register() {

        // Register packet listeners here later
        // protocolManager.addPacketListener(new ProtocolListener(plugin));

        plugin.getLogger().info("--------------------------------");
        plugin.getLogger().info("ProtocolLib Loaded");
        plugin.getLogger().info("Protocol Hook Enabled");
        plugin.getLogger().info("--------------------------------");
    }

    public void unregister() {
        protocolManager.removePacketListeners(plugin);
    }

    public com.comphenix.protocol.ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
