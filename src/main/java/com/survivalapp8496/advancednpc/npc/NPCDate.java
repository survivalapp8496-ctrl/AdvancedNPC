package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class NPCData {

    private final int id;
    private String name;
    private EntityType type;
    private Location location;

    public NPCData(int id, String name, EntityType type, Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EntityType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }
}
