package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class NPCSpawner {

    public NPCEntity spawnNPC(Location location,
                              EntityType entityType) {

        NPCEntity npc = new NPCEntity();

        npc.spawn(location, entityType);

        return npc;
    }
}
