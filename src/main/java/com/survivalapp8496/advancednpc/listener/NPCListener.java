package com.survivalapp8496.advancednpc.listener;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.EntityNPC;
import com.survivalapp8496.advancednpc.npc.NPCData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCListener implements Listener {

    private final AdvancedNPCPlugin plugin;

    public NPCListener(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();

        EntityNPC entityNPC =
                plugin.getNpcSpawnManager().getNPC(event.getRightClicked());

        if (entityNPC == null) {
            return;
        }

        event.setCancelled(true);

        NPCData npc = entityNPC.getData();

        // Permission Check
        if (npc.isPermissionEnabled()) {

            if (!player.hasPermission("advancednpc.bypass.permission")
                    && !player.hasPermission(npc.getPermission())) {

                player.sendMessage(npc.getPermissionMessage());
                return;
            }
        }

        executeCommands(player, npc);
    }

    private void executeCommands(Player player, NPCData npc) {

        for (String command : npc.getCommands()) {

            command = command.replace("%player%", player.getName());

            if (command.startsWith("console:")) {

                String cmd = command.substring(8);

                plugin.getServer().dispatchCommand(
                        plugin.getServer().getConsoleSender(),
                        cmd
                );

                continue;
            }

            if (command.startsWith("player:")) {

                String cmd = command.substring(7);

                player.performCommand(cmd);

                continue;
            }

            plugin.getServer().dispatchCommand(
                    plugin.getServer().getConsoleSender(),
                    command
            );
        }
    }
}
