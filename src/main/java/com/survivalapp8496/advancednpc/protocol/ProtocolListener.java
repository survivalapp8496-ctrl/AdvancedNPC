package com.survivalapp8496.advancednpc.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;

public class ProtocolListener extends PacketAdapter {

    private final AdvancedNPCPlugin plugin;

    public ProtocolListener(AdvancedNPCPlugin plugin) {

        super(
                plugin,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.USE_ENTITY
        );

        this.plugin = plugin;
    }

    @Override
public void onPacketReceiving(PacketEvent event) {

    int entityId = event.getPacket()
            .getIntegers()
            .read(0);

    plugin.getLogger().info(
            "USE_ENTITY Packet -> Entity ID: " + entityId
    );
}
}
