package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private final Map<Integer, NPCData> npcs = new HashMap<>();
    private int lastId = 0;

    public NPCData createNPC(String name,
                             EntityType type,
                             Location location) {

        int id = ++lastId;

        NPCData npc = new NPCData(
                id,
                name,
                type,
                location
        );

        npcs.put(id, npc);
        return npc;
    }

    public boolean removeNPC(int id) {
        return npcs.remove(id) != null;
    }

    public NPCData getNPC(int id) {
        return npcs.get(id);
    }

    public Collection<NPCData> getNPCs() {
        return npcs.values();
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public Map<Integer, NPCData> getNPCMap() {
        return npcs;
    }
          }
