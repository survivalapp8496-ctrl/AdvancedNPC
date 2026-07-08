package com.survivalapp8496.advancednpc.npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class EntityNPC {

    private final NPCData data;
    private final NPCEntity npcEntity;

    public EntityNPC(NPCData data, NPCEntity npcEntity) {
        this.data = data;
        this.npcEntity = npcEntity;
    }

    public NPCData getData() {
        return data;
    }

    public Entity getEntity() {
        return npcEntity.getEntity();
    }

    public void teleport(Location location) {
        npcEntity.teleport(location);
        data.setLocation(location);
    }

    public void remove() {
        npcEntity.remove();
    }

    public void setGlowing(boolean glowing) {

        if (getEntity() != null) {
            getEntity().setGlowing(glowing);
        }

        data.setGlowing(glowing);
    }

    public void setCustomName(String name) {

        if (getEntity() != null) {
            getEntity().customName(net.kyori.adventure.text.Component.text(name));
            getEntity().setCustomNameVisible(true);
        }

        data.setName(name);
    }

    public void setHelmet(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setHelmet(item);
            }
        }
    }

    public void setChestplate(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setChestplate(item);
            }
        }
    }

    public void setLeggings(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setLeggings(item);
            }
        }
    }

    public void setBoots(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setBoots(item);
            }
        }
    }

    public void setMainHand(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setItemInMainHand(item);
            }
        }
    }

    public void setOffHand(ItemStack item) {

        if (getEntity() instanceof LivingEntity living) {

            EntityEquipment eq = living.getEquipment();

            if (eq != null) {
                eq.setItemInOffHand(item);
            }
        }
    }

    // AI
    public void setAI(boolean enabled) {

        if (getEntity() instanceof LivingEntity living) {
            living.setAI(enabled);
        }

        data.setAI(enabled);
    }

    // Gravity
    public void setGravity(boolean enabled) {

        if (getEntity() != null) {
            getEntity().setGravity(enabled);
        }

        data.setGravity(enabled);
    }
    
    public void setInvulnerable(boolean enabled) {

        if (getEntity() != null) {
            getEntity().setInvulnerable(enabled);
    }

        data.setInvulnerable(enabled);
    }
}
