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

// /npc remove <id>
if (args[0].equalsIgnoreCase("remove")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc remove <id>");
        return true;
    }

    int id;

    try {
        id = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
        player.sendMessage("§cInvalid NPC ID.");
        return true;
    }

    if (!plugin.getNpcManager().containsNPC(id)) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    plugin.getNpcSpawnManager().despawn(id);

    plugin.getNpcManager().removeNPC(id);

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage("§aNPC #" + id + " removed.");

    return true;

}
    

    // /npc edit <id>
if (args.length >= 2 &&
        args[0].equalsIgnoreCase("edit")) {

    try {

        int id = Integer.parseInt(args[1]);

        NPCData npc = plugin.getNpcManager().getNPC(id);

        if (npc == null) {

            player.sendMessage("§cNPC not found.");
            return true;

        }

        plugin.getNpcEditorManager().startEditing(player, id);

        player.sendMessage("§a==========================");
        player.sendMessage("§aEditing NPC #" + id);
        player.sendMessage("§7Name: §f" + npc.getName());
        player.sendMessage("§7Type: §f" + npc.getEntityType());
        player.sendMessage("§e/npc edit <id>");
        player.sendMessage("§7Use editor commands to modify it.");
        player.sendMessage("§a==========================");

    } catch (NumberFormatException e) {

        player.sendMessage("§cInvalid NPC ID.");

    }

    return true;
}

// /npc rename <newName>
if (args[0].equalsIgnoreCase("rename")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc rename <name>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int id = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(id);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    StringBuilder builder = new StringBuilder();

    for (int i = 1; i < args.length; i++) {

        builder.append(args[i]);

        if (i != args.length - 1) {
            builder.append(" ");
        }

    }

    String newName = builder.toString();

    npc.setName(newName);

    if (plugin.getNpcSpawnManager().getNPC(id) != null) {

        plugin.getNpcSpawnManager()
                .getNPC(id)
                .setCustomName(newName);

    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage("§aNPC renamed to §e" + newName);

    return true;
}

// /npc glow <on|off>
if (args[0].equalsIgnoreCase("glow")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc glow <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean glow;

    if (args[1].equalsIgnoreCase("on")) {

        glow = true;

    } else if (args[1].equalsIgnoreCase("off")) {

        glow = false;

    } else {

        player.sendMessage("§cUsage: /npc glow <on|off>");
        return true;

    }

    npc.setGlowing(glow);

    EntityNPC entityNPC = plugin.getNpcSpawnManager().getNPC(npcId);

    if (entityNPC != null) {
        entityNPC.setGlowing(glow);
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    if (glow) {
        player.sendMessage("§aNPC glowing enabled.");
    } else {
        player.sendMessage("§aNPC glowing disabled.");
    }

    return true;
}

// /npc ai <on|off>
if (args[0].equalsIgnoreCase("ai")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc ai <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean enabled;

    if (args[1].equalsIgnoreCase("on")) {
        enabled = true;
    } else if (args[1].equalsIgnoreCase("off")) {
        enabled = false;
    } else {
        player.sendMessage("§cUsage: /npc ai <on|off>");
        return true;
    }

    npc.setAI(enabled);

    EntityNPC entityNPC = plugin.getNpcSpawnManager().getNPC(npcId);

    if (entityNPC != null) {
        entityNPC.setAI(enabled);
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage(
            enabled
                    ? "§aNPC AI enabled."
                    : "§cNPC AI disabled."
    );

    return true;
}

// /npc gravity <on|off>
if (args[0].equalsIgnoreCase("gravity")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc gravity <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean enabled;

    if (args[1].equalsIgnoreCase("on")) {
        enabled = true;
    } else if (args[1].equalsIgnoreCase("off")) {
        enabled = false;
    } else {
        player.sendMessage("§cUsage: /npc gravity <on|off>");
        return true;
    }

    npc.setGravity(enabled);

    EntityNPC entityNPC = plugin.getNpcSpawnManager().getNPC(npcId);

    if (entityNPC != null) {
        entityNPC.setGravity(enabled);
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage(
            enabled
                    ? "§aNPC gravity enabled."
                    : "§cNPC gravity disabled."
    );

    return true;
}

// /npc invulnerable <on|off>
if (args[0].equalsIgnoreCase("invulnerable")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc invulnerable <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean enabled;

    if (args[1].equalsIgnoreCase("on")) {
        enabled = true;
    } else if (args[1].equalsIgnoreCase("off")) {
        enabled = false;
    } else {
        player.sendMessage("§cUsage: /npc invulnerable <on|off>");
        return true;
    }

    npc.setInvulnerable(enabled);

    EntityNPC entityNPC = plugin.getNpcSpawnManager().getNPC(npcId);

    if (entityNPC != null) {
        entityNPC.setInvulnerable(enabled);
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage(
            enabled
                    ? "§aNPC is now invulnerable."
                    : "§cNPC is now vulnerable."
    );

    return true;
}

// /npc silent <on|off>
if (args[0].equalsIgnoreCase("silent")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc silent <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean enabled;

    if (args[1].equalsIgnoreCase("on")) {
        enabled = true;
    } else if (args[1].equalsIgnoreCase("off")) {
        enabled = false;
    } else {
        player.sendMessage("§cUsage: /npc silent <on|off>");
        return true;
    }

    npc.setSilent(enabled);

    EntityNPC entityNPC = plugin.getNpcSpawnManager().getNPC(npcId);

    if (entityNPC != null) {
        entityNPC.setSilent(enabled);
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage(
            enabled
                    ? "§aNPC is now silent."
                    : "§aNPC can now make sounds."
    );

    return true;
}  

// /npc look <on|off>
if (args[0].equalsIgnoreCase("look")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc look <on|off>");
        return true;
    }

    if (!plugin.getNpcEditorManager().isEditing(player)) {
        player.sendMessage("§cYou are not editing any NPC.");
        return true;
    }

    int npcId = plugin.getNpcEditorManager().getEditingNPC(player);

    NPCData npc = plugin.getNpcManager().getNPC(npcId);

    if (npc == null) {
        player.sendMessage("§cNPC not found.");
        return true;
    }

    boolean enabled;

    if (args[1].equalsIgnoreCase("on")) {
        enabled = true;
    } else if (args[1].equalsIgnoreCase("off")) {
        enabled = false;
    } else {
        player.sendMessage("§cUsage: /npc look <on|off>");
        return true;
    }

    npc.setLookAtPlayer(enabled);

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage(
            enabled
                    ? "§aLook At Player enabled."
                    : "§cLook At Player disabled."
    );

    return true;
        }

// /npc tp <id>
if (args[0].equalsIgnoreCase("tp")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc tp <id>");
        return true;
    }

    int id;

    try {
        id = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
        player.sendMessage("§cInvalid NPC ID.");
        return true;
    }

    EntityNPC npc = plugin.getNpcSpawnManager().getNPC(id);

    if (npc == null) {
        player.sendMessage("§cNPC not spawned.");
        return true;
    }

    player.teleport(npc.getEntity().getLocation());

    player.sendMessage("§aTeleported to NPC #" + id);

    return true;
}

// /npc movehere <id>
if (args[0].equalsIgnoreCase("movehere")) {

    if (args.length < 2) {
        player.sendMessage("§cUsage: /npc movehere <id>");
        return true;
    }

    int id;

    try {
        id = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
        player.sendMessage("§cInvalid NPC ID.");
        return true;
    }

    plugin.getNpcSpawnManager().teleport(
            id,
            player.getLocation()
    );

    NPCData data = plugin.getNpcManager().getNPC(id);

    if (data != null) {
        data.setLocation(player.getLocation());
    }

    plugin.getStorage().saveNPCs(plugin.getNpcManager());

    player.sendMessage("§aNPC moved to your location.");

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
        player.sendMessage("§e/npc rename <name>");
        player.sendMessage("§e/npc glow <on|off>");
        player.sendMessage("§e/npc ai <on|off>");
        player.sendMessage("§e/npc gravity <on|off>");
        player.sendMessage("§e/npc look <on|off>");
        player.sendMessage("§e/npc invulnerable <on|off>");
        player.sendMessage("§e/npc silent <on|off>");
        player.sendMessage("§6=================================");
    }
}
