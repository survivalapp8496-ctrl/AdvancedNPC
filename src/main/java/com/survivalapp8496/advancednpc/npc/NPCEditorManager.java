package com.survivalapp8496.advancednpc.npc;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NPCEditorManager {

    private final Map<UUID, Integer> editingNPCs = new HashMap<>();

    public void startEditing(Player player, int npcId) {
        editingNPCs.put(player.getUniqueId(), npcId);
    }

    public void stopEditing(Player player) {
        editingNPCs.remove(player.getUniqueId());
    }

    public boolean isEditing(Player player) {
        return editingNPCs.containsKey(player.getUniqueId());
    }

    public int getEditingNPC(Player player) {
        return editingNPCs.getOrDefault(player.getUniqueId(), -1);
    }
  }
