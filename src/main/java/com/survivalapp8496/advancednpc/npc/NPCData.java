package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class NPCData {

    private final int id;

    private boolean ai;

    private UUID uuid;

    private String name;

    private EntityType entityType;

    private Location location;

    private NPCPose pose;

    private String skinName;

    private boolean lookAtPlayer;

    private boolean glowing;

    public NPCData(
            int id,
            String name,
            EntityType entityType,
            Location location
    ) {

        this.id = id;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.entityType = entityType;
        this.location = location;
        this.pose = NPCPose.STANDING;
        this.skinName = "";
        this.lookAtPlayer = true;
        this.glowing = false;
        this.ai = false;
    }

    public int getId() {
        return id;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
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

    public String getSkinName() {
        return skinName;
    }

    public boolean isLookAtPlayer() {
        return lookAtPlayer;
    }

    public boolean isGlowing() {
        return glowing;
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

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public void setLookAtPlayer(boolean lookAtPlayer) {
        this.lookAtPlayer = lookAtPlayer;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }
    public void setAI(boolean ai) {
        this.ai = ai;
    }
    public boolean hasAI() {
        return ai;
    }
    
}
