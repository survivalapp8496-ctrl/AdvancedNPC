package com.survivalapp8496.advancednpc.commands;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.NPCData;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class NPCCommand implements CommandExecutor {

    private final AdvancedNPCPlugin plugin;

    public NPCCommand(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        // /npc create <name>
        if (args.length >= 2 && args[0].equalsIgnoreCase("create")) {

            String name = args[1];
            Location location = player.getLocation();

            NPCData npc = plugin.getNpcManager().createNPC(
                    name,
                    EntityType.PLAYER,
                    location
            );

            plugin.getStorage()
                    .saveNPCs(plugin.getNpcManager());

            player.sendMessage("§aCreated NPC '" +
                    name +
                    "' with ID: " +
                    npc.getId());

            return true;
        }

        // /npc list
        if (args.length == 1 &&
                args[0].equalsIgnoreCase("list")) {

            player.sendMessage("§6===== NPC List =====");

            for (NPCData npc :
                    plugin.getNpcManager().getNPCs()) {

                player.sendMessage(
                        "§eID: " +
                        npc.getId() +
                        " §7| §f" +
                        npc.getName() +
                        " §7| §b" +
                        npc.getEntityType()
                );
            }

            return true;
        }

        // /npc remove <id>
        if (args.length >= 2 &&
                args[0].equalsIgnoreCase("remove")) {

            try {
                int id = Integer.parseInt(args[1]);

                if (plugin.getNpcManager()
                        .removeNPC(id)) {

                    plugin.getStorage()
                            .saveNPCs(
                                    plugin.getNpcManager()
                            );

                    player.sendMessage(
                            "§cRemoved NPC #" + id
                    );

                } else {
                    player.sendMessage(
                            "§cNPC not found."
                    );
                }

            } catch (NumberFormatException e) {
                player.sendMessage(
                        "§cInvalid NPC ID."
                );
            }

            return true;
        }

        player.sendMessage("§6AdvancedNPC Commands:");
        player.sendMessage("§e/npc create <name>");
        player.sendMessage("§e/npc list");
        player.sendMessage("§e/npc remove <id>");

        return true;
    }
}
