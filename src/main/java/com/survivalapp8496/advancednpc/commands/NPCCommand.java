package com.survivalapp8496.advancednpc.commands;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.NPCData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import com.survivalapp8496.advancednpc.npc.EntityNPC;

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
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        // /npc
        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        // /npc help
        if (args[0].equalsIgnoreCase("help")) {
            sendHelp(player);
            return true;
        }

// /npc create <name> <mob>
if (args[0].equalsIgnoreCase("create")) {

    if (args.length < 3) {
        player.sendMessage("§cUsage: /npc create <name> <mob>");
        return true;
    }

    String name = args[1];

    EntityType type;

    try {
        type = EntityType.valueOf(args[2].toUpperCase());
    } catch (IllegalArgumentException ex) {
        player.sendMessage("§cUnknown mob type: " + args[2]);
        return true;
    }

    Location location = player.getLocation();

    NPCData data = plugin.getNpcManager().createNPC(
            name,
            type,
            location
    );

    EntityNPC spawned = plugin
            .getNpcSpawnManager()
            .spawn(data);

    if (spawned == null) {
        player.sendMessage("§cFailed to spawn NPC.");
        return true;
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage("§aNPC Created!");
    player.sendMessage("§7ID: §e" + data.getId());
    player.sendMessage("§7Name: §f" + data.getName());
    player.sendMessage("§7Type: §b" + data.getEntityType());

    return true;
}

        // /npc list
        if (args[0].equalsIgnoreCase("list")) {

            if (plugin.getNpcManager().isEmpty()) {
                player.sendMessage("§cThere are no NPCs.");
                return true;
            }

            player.sendMessage("§6========== NPC List ==========");

            for (NPCData npc : plugin.getNpcManager().getNPCs()) {

                player.sendMessage(
                        "§e#" + npc.getId()
                        + " §7| §f" + npc.getName()
                        + " §7| §b" + npc.getEntityType().name()
                );
            }

            player.sendMessage("§6==============================");

            return true;
        }

        player.sendMessage("§cUnknown sub-command.");
        sendHelp(player);
        return true;
    }

    private void sendHelp(Player player) {

        player.sendMessage("§6========== AdvancedNPC ==========");
        player.sendMessage("§e/npc help");
        player.sendMessage("§e/npc list");
        player.sendMessage("§e/npc create <name> <mob>");
        player.sendMessage("§e/npc remove <id>");
        player.sendMessage("§e/npc tp <id>");
        player.sendMessage("§e/npc movehere <id>");
        player.sendMessage("§e/npc edit <id>");
        player.sendMessage("§6=================================");
    }
}
