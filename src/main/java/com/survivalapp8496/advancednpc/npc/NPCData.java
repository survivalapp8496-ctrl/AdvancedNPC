package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class NPCData {

    private final int id;
    private UUID uuid;

    private String name;
    private EntityType entityType;
    private Location location;

    private NPCPose pose;
    private String skinName;

    private boolean lookAtPlayer;
    private boolean glowing;
    private boolean gravity;
    private boolean ai;
    private boolean invulnerable;
    private boolean silent;
    private boolean collidable;

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
        this.gravity = false;
        this.invulnerable = true;
        this.silent = true;
        this.collidable = true;
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

    public boolean hasAI() {
        return ai;
    }

    public boolean hasGravity() {
        return gravity;
    }
    
    public boolean isInvulnerable() {
        return invulnerable;
    }
    
    public boolean isSilent() {
        return silent;
    }

    public boolean isCollidable() {
        return collidable;
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

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
}
