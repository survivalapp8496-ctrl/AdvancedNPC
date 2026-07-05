package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class NPCData {

    private final int id;
    private String name;
    private EntityType entityType;
    private Location location;
    private NPCPose pose;

    public NPCData(
            int id,
            String name,
            EntityType entityType,
            Location location
    ) {
        this.id = id;
        this.name = name;
        this.entityType = entityType;
        this.location = location;
        this.pose = NPCPose.STANDING;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Location getLocation() {
        return location;
    }

    public NPCPose getPose() {
        return pose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPose(NPCPose pose) {
        this.pose = pose;
    }
  }
