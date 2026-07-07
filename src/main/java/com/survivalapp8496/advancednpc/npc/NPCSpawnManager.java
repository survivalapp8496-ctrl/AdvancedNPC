package com.survivalapp8496.advancednpc.npc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class NPCSpawnManager {

    private final Map<Integer, EntityNPC> spawnedNPCs = new HashMap<>();

    public EntityNPC spawn(NPCData data) {

        NPCEntity npcEntity = new NPCEntity();

        npcEntity.spawn(
                data.getLocation(),
                data.getEntityType()
        );

        Entity entity = npcEntity.getEntity();

        if (entity != null) {

            entity.setCustomName(data.getName());
            entity.setCustomNameVisible(true);

            entity.setInvulnerable(true);
            entity.setSilent(true);
            entity.setGravity(false);
            entity.setPersistent(true);

            if (data.isGlowing()) {
                entity.setGlowing(true);
            }

        }

        EntityNPC entityNPC = new EntityNPC(data, npcEntity);

        spawnedNPCs.put(data.getId(), entityNPC);

        return entityNPC;
    }

    public void despawn(int id) {

        EntityNPC npc = spawnedNPCs.remove(id);

        if (npc != null) {
            npc.remove();
        }

    }

    public void respawn(NPCData data) {

        despawn(data.getId());

        spawn(data);

    }

    public void teleport(int id, Location location) {

        EntityNPC npc = spawnedNPCs.get(id);

        if (npc != null) {

            npc.teleport(location);

        }

    }

    public EntityNPC getNPC(int id) {

        return spawnedNPCs.get(id);

    }

    public Map<Integer, EntityNPC> getSpawnedNPCs() {

        return spawnedNPCs;

    }

}
