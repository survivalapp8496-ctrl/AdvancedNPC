package com.survivalapp8496.advancednpc.storage;

import com.survivalapp8496.advancednpc.AdvancedNPCPlugin;
import com.survivalapp8496.advancednpc.npc.NPCData;
import com.survivalapp8496.advancednpc.npc.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;

public class YamlStorage {

    private final AdvancedNPCPlugin plugin;
    private final File file;
    private final YamlConfiguration data;

    public YamlStorage(AdvancedNPCPlugin plugin) {
        this.plugin = plugin;
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

        if (!data.contains("npcs")) {
            return;
        }

        for (String idString :
                data.getConfigurationSection("npcs").getKeys(false)) {

            int id = Integer.parseInt(idString);

            String path = "npcs." + id;

            String world = data.getString(path + ".world");
            if (Bukkit.getWorld(world) == null) {
                continue;
            }

            Location loc = new Location(
                    Bukkit.getWorld(world),
                    data.getDouble(path + ".x"),
                    data.getDouble(path + ".y"),
                    data.getDouble(path + ".z"),
                    (float) data.getDouble(path + ".yaw"),
                    (float) data.getDouble(path + ".pitch")
            );

            NPCData npc = new NPCData(
                    id,
                    data.getString(path + ".name"),
                    EntityType.valueOf(
                            data.getString(path + ".type")
                    ),
                    loc
            );

            npc.setAI(
                    data.getBoolean(path + ".ai", false)
            );

            manager.getNPCMap().put(id, npc);
        }
    }
}
