package com.survivalapp8496.advancednpc.tasks;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.EntityNPC;
import com.survivalapp8496.advancednpc.npc.NPCData;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NPCLookTask extends BukkitRunnable {

    private final AdvancedNPCPlugin plugin;

    public NPCLookTask(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        for (EntityNPC entityNPC :
                plugin.getNpcSpawnManager().getSpawnedNPCs().values()) {

            NPCData data = entityNPC.getData();

            if (!data.isLookAtPlayer()) {
                continue;
            }

            Entity entity = entityNPC.getEntity();

            if (entity == null || entity.isDead()) {
                continue;
            }

            Player nearest = null;
            double nearestDistance = Double.MAX_VALUE;

            for (Player player : entity.getWorld().getPlayers()) {

                double distance = player.getLocation().distanceSquared(
                        entity.getLocation()
                );

                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearest = player;
                }
            }

            if (nearest == null) {
                continue;
            }

            Location npcLoc = entity.getLocation();
            Location playerLoc = nearest.getEyeLocation();

            npcLoc.setDirection(
                    playerLoc.toVector().subtract(
                            npcLoc.toVector()
                    )
            );

            entity.teleport(npcLoc);
        }
    }
                  }
