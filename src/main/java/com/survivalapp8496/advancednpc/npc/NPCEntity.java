package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.World;

import java.util.UUID;

public class NPCEntity {

    private final UUID uuid;
    private Entity entity;

    public NPCEntity() {
        this.uuid = UUID.randomUUID();
    }

    public void spawn(Location location, EntityType type) {

        World world = location.getWorld();

        if (world == null) {
            return;
        }

        entity = world.spawnEntity(location, type);

        entity.setPersistent(true);
        entity.setInvulnerable(true);
        entity.setSilent(true);
        entity.setGravity(false);
    }

    public void remove() {

        if (entity != null && !entity.isDead()) {
            entity.remove();
        }
    }

    public void teleport(Location location) {

        if (entity != null) {
            entity.teleport(location);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public UUID getUUID() {
        return uuid;
    }
                               }
