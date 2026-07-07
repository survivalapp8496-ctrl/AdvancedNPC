package com.survivalapp8496.advancednpc.npc;

import org.bukkit.entity.EntityType;

public enum NPCType {

    PLAYER(EntityType.PLAYER),

    // Passive
    ALLAY(EntityType.ALLAY),
    ARMADILLO(EntityType.ARMADILLO),
    AXOLOTL(EntityType.AXOLOTL),
    BAT(EntityType.BAT),
    BEE(EntityType.BEE),
    CAMEL(EntityType.CAMEL),
    CAT(EntityType.CAT),
    CHICKEN(EntityType.CHICKEN),
    COD(EntityType.COD),
    COW(EntityType.COW),
    DONKEY(EntityType.DONKEY),
    DOLPHIN(EntityType.DOLPHIN),
    FOX(EntityType.FOX),
    FROG(EntityType.FROG),
    GLOW_SQUID(EntityType.GLOW_SQUID),
    GOAT(EntityType.GOAT),
    HORSE(EntityType.HORSE),
    LLAMA(EntityType.LLAMA),
    MOOSHROOM(EntityType.MOOSHROOM),
    MULE(EntityType.MULE),
    OCELOT(EntityType.OCELOT),
    PANDA(EntityType.PANDA),
    PARROT(EntityType.PARROT),
    PIG(EntityType.PIG),
    POLAR_BEAR(EntityType.POLAR_BEAR),
    PUFFERFISH(EntityType.PUFFERFISH),
    RABBIT(EntityType.RABBIT),
    SALMON(EntityType.SALMON),
    SHEEP(EntityType.SHEEP),
    SNIFFER(EntityType.SNIFFER),
    SQUID(EntityType.SQUID),
    STRIDER(EntityType.STRIDER),
    TADPOLE(EntityType.TADPOLE),
    TRADER_LLAMA(EntityType.TRADER_LLAMA),
    TROPICAL_FISH(EntityType.TROPICAL_FISH),
    TURTLE(EntityType.TURTLE),
    WOLF(EntityType.WOLF),

    // Neutral
    ENDERMAN(EntityType.ENDERMAN),
    IRON_GOLEM(EntityType.IRON_GOLEM),
    SNOW_GOLEM(EntityType.SNOW_GOLEM),
    PIGLIN(EntityType.PIGLIN),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN),
    SPIDER(EntityType.SPIDER),
    CAVE_SPIDER(EntityType.CAVE_SPIDER),

    // Hostile
    BLAZE(EntityType.BLAZE),
    BOGGED(EntityType.BOGGED),
    BREEZE(EntityType.BREEZE),
    CREEPER(EntityType.CREEPER),
    DROWNED(EntityType.DROWNED),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN),
    ENDERMITE(EntityType.ENDERMITE),
    EVOKER(EntityType.EVOKER),
    GHAST(EntityType.GHAST),
    GUARDIAN(EntityType.GUARDIAN),
    HOGLIN(EntityType.HOGLIN),
    HUSK(EntityType.HUSK),
    MAGMA_CUBE(EntityType.MAGMA_CUBE),
    PHANTOM(EntityType.PHANTOM),
    PILLAGER(EntityType.PILLAGER),
    RAVAGER(EntityType.RAVAGER),
    SHULKER(EntityType.SHULKER),
    SILVERFISH(EntityType.SILVERFISH),
    SKELETON(EntityType.SKELETON),
    SLIME(EntityType.SLIME),
    STRAY(EntityType.STRAY),
    VEX(EntityType.VEX),
    VINDICATOR(EntityType.VINDICATOR),
    WARDEN(EntityType.WARDEN),
    WITCH(EntityType.WITCH),
    WITHER_SKELETON(EntityType.WITHER_SKELETON),
    ZOGLIN(EntityType.ZOGLIN),
    ZOMBIE(EntityType.ZOMBIE),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER),

    // Boss
    ENDER_DRAGON(EntityType.ENDER_DRAGON),
    WITHER(EntityType.WITHER),

    // Villagers
    VILLAGER(EntityType.VILLAGER),
    WANDERING_TRADER(EntityType.WANDERING_TRADER),

    // Utility
    ARMOR_STAND(EntityType.ARMOR_STAND);

    private final EntityType entityType;

    NPCType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public static NPCType fromString(String input) {
        for (NPCType type : values()) {
            if (type.name().equalsIgnoreCase(input.replace("-", "_"))) {
                return type;
            }
        }
        return PLAYER;
    }
  }
