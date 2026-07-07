package com.survivalapp8496.advancednpc.packet;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class PacketManager {

    private final ProtocolManager protocolManager;

    public PacketManager() {
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

}
