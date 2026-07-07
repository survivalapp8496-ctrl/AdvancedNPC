package com.survivalapp8496.advancednpc.listener;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCListener implements Listener {

    private final AdvancedNPCPlugin plugin;

    public NPCListener(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {

        plugin.getLogger().info(
                event.getPlayer().getName()
                        + " clicked "
                        + event.getRightClicked().getType()
        );

    }

          }
