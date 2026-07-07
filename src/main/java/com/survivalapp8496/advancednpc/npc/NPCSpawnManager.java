package com.survivalapp8496.advancednpc.npc;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class NPCSpawnManager {

    private final Map<Integer, EntityNPC> spawnedNPCs = new HashMap<>();

    public EntityNPC spawn(NPCData data) {

        NPCEntity npcEntity = new NPCEntity();

        npcEntity.spawn(
                data.getLocation(),
                data.getEntityType()
        );

        Entity entity = npcEntity.getEntity();

        if (entity == null) {
            return null;
        }

        entity.customName(net.kyori.adventure.text.Component.text(data.getName()));
        entity.setCustomNameVisible(true);
        entity.setPersistent(true);
        entity.setInvulnerable(true);
        entity.setSilent(true);
        entity.setGravity(false);
        entity.setGlowing(data.isGlowing());

        EntityNPC entityNPC = new EntityNPC(data, npcEntity);

        spawnedNPCs.put(data.getId(), entityNPC);

        AdvancedNPCPlugin.getInstance()
                .getNpcRegistry()
                .register(entityNPC);

        return entityNPC;
    }

    public void despawn(int id) {

        EntityNPC npc = spawnedNPCs.remove(id);

        if (npc == null) {
            return;
        }

        npc.remove();

        AdvancedNPCPlugin.getInstance()
                .getNpcRegistry()
                .unregister(id);
    }

    public void respawn(NPCData data) {

        despawn(data.getId());
        spawn(data);

    }

    public void teleport(int id, Location location) {

        EntityNPC npc = spawnedNPCs.get(id);

        if (npc == null) {
            return;
        }

        npc.teleport(location);
    }

    public EntityNPC getNPC(int id) {
        return spawnedNPCs.get(id);
    }

    public boolean isSpawned(int id) {
        return spawnedNPCs.containsKey(id);
    }

    public Map<Integer, EntityNPC> getSpawnedNPCs() {
        return spawnedNPCs;
    }

    public void despawnAll() {

        for (EntityNPC npc : spawnedNPCs.values()) {
            npc.remove();
        }

        spawnedNPCs.clear();

        AdvancedNPCPlugin.getInstance()
                .getNpcRegistry()
                .clear();
    }
}
