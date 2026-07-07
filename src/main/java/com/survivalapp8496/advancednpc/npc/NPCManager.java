package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.Collection;
import java.util.Collections;
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

    public void addNPC(NPCData npc) {

        npcs.put(npc.getId(), npc);

        if (npc.getId() > lastId) {
            lastId = npc.getId();
        }

    }

    public boolean containsNPC(int id) {
        return npcs.containsKey(id);
    }

    public NPCData getNPC(int id) {
        return npcs.get(id);
    }

    public boolean removeNPC(int id) {

        if (!npcs.containsKey(id)) {
            return false;
        }

        npcs.remove(id);

        return true;
    }

    public void clear() {
        npcs.clear();
        lastId = 0;
    }

    public Collection<NPCData> getNPCs() {
        return Collections.unmodifiableCollection(npcs.values());
    }

    public Map<Integer, NPCData> getNPCMap() {
        return npcs;
    }

    public int size() {
        return npcs.size();
    }

    public boolean isEmpty() {
        return npcs.isEmpty();
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

}
