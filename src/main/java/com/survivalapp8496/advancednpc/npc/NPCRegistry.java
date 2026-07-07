package com.survivalapp8496.advancednpc.npc;

import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NPCRegistry {

    private final Map<Integer, EntityNPC> npcById = new HashMap<>();
    private final Map<UUID, EntityNPC> npcByUUID = new HashMap<>();

    public void register(EntityNPC npc) {

        npcById.put(npc.getData().getId(), npc);

        Entity entity = npc.getEntity();

        if (entity != null) {
            npcByUUID.put(entity.getUniqueId(), npc);
        }
    }

    public void unregister(int id) {

        EntityNPC npc = npcById.remove(id);

        if (npc == null) {
            return;
        }

        Entity entity = npc.getEntity();

        if (entity != null) {
            npcByUUID.remove(entity.getUniqueId());
        }
    }

    public EntityNPC getById(int id) {
        return npcById.get(id);
    }

    public EntityNPC getByEntity(Entity entity) {

        if (entity == null) {
            return null;
        }

        return npcByUUID.get(entity.getUniqueId());
    }

    public boolean isNPC(Entity entity) {

        if (entity == null) {
            return false;
        }

        return npcByUUID.containsKey(entity.getUniqueId());
    }

    public Collection<EntityNPC> getAllNPCs() {
        return npcById.values();
    }

    public void clear() {
        npcById.clear();
        npcByUUID.clear();
    }

    public int size() {
        return npcById.size();
    }
  }
