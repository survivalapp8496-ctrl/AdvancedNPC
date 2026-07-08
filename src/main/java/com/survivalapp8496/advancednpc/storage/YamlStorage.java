package com.survivalapp8496.advancednpc.storage;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.NPCData;
import com.survivalapp8496.advancednpc.npc.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;

public class YamlStorage {

    private final File file;
    private final YamlConfiguration data;

    public YamlStorage(AdvancedNPCPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "data.yml");

        if (!file.exists()) {
            plugin.saveResource("data.yml", false);
        }

        this.data = YamlConfiguration.loadConfiguration(file);
    }

    public void saveNPCs(NPCManager manager) {

        data.set("last-id", manager.getLastId());
        data.set("npcs", null);

        for (NPCData npc : manager.getNPCs()) {

            String path = "npcs." + npc.getId();

            data.set(path + ".name", npc.getName());
            data.set(path + ".type", npc.getEntityType().name());
            data.set(path + ".ai", npc.hasAI());
            data.set(path + ".gravity", npc.hasGravity());
            data.set(path + ".look", npc.isLookAtPlayer());
            data.set(path + ".invulnerable", npc.isInvulnerable());
            data.set(path + ".silent", npc.isSilent());
            data.set(path + ".collidable", npc.isCollidable());

            Location loc = npc.getLocation();

            data.set(path + ".world", loc.getWorld().getName());
            data.set(path + ".x", loc.getX());
            data.set(path + ".y", loc.getY());
            data.set(path + ".z", loc.getZ());
            data.set(path + ".yaw", loc.getYaw());
            data.set(path + ".pitch", loc.getPitch());
        }

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNPCs(NPCManager manager) {

        manager.setLastId(data.getInt("last-id", 0));

        ConfigurationSection section = data.getConfigurationSection("npcs");
        if (section == null) {
            return;
        }

        for (String idString : section.getKeys(false)) {

            int id;

            try {
                id = Integer.parseInt(idString);
            } catch (NumberFormatException ignored) {
                continue;
            }

            String path = "npcs." + id;

            World world = Bukkit.getWorld(data.getString(path + ".world"));
            if (world == null) {
                continue;
            }

            EntityType type;
            try {
                type = EntityType.valueOf(data.getString(path + ".type"));
            } catch (Exception e) {
                type = EntityType.PLAYER;
            }

            Location location = new Location(
                    world,
                    data.getDouble(path + ".x"),
                    data.getDouble(path + ".y"),
                    data.getDouble(path + ".z"),
                    (float) data.getDouble(path + ".yaw"),
                    (float) data.getDouble(path + ".pitch")
            );

            NPCData npc = new NPCData(
                    id,
                    data.getString(path + ".name", "NPC"),
                    type,
                    location
            );

            npc.setAI(data.getBoolean(path + ".ai", false));
            npc.setGravity(data.getBoolean(path + ".gravity", false));
            npc.setLookAtPlayer(data.getBoolean(path + ".look", true));
            npc.setInvulnerable(data.getBoolean(path + ".invulnerable", true));
            npc.setSilent(data.getBoolean(path + ".silent", true));
            npc.setCollidable(data.getBoolean(path + ".collidable", true)           
            );

            manager.getNPCMap().put(id, npc);
        }
    }
}
