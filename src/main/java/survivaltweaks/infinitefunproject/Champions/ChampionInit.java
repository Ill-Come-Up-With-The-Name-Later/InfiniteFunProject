package survivaltweaks.infinitefunproject.Champions;

import org.bukkit.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.Champions.All.AllChampions;
import survivaltweaks.infinitefunproject.Champions.Blaze.FireballExplosion;
import survivaltweaks.infinitefunproject.Champions.Breeze.DropDeflectorHelm;
import survivaltweaks.infinitefunproject.Champions.Creeper.EnlargeBlastRadius;
import survivaltweaks.infinitefunproject.Champions.Drowned.DropTrident;
import survivaltweaks.infinitefunproject.Champions.Skeleton.DropCallisto;
import survivaltweaks.infinitefunproject.Champions.Spider.WebPlayers;
import survivaltweaks.infinitefunproject.Champions.Witch.EnhancePotions;
import survivaltweaks.infinitefunproject.Champions.Zombie.ResurrectKilled;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttacksAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;
import static survivaltweaks.infinitefunproject.Champions.All.AllChampions.buffSameType;
import static survivaltweaks.infinitefunproject.Mobs.MobInit.setNoLevel;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;

public class ChampionInit implements Listener {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new MakeChampion(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AllChampions(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new FireballExplosion(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ResurrectKilled(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EnlargeBlastRadius(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EnhancePotions(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropTrident(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropDeflectorHelm(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropCallisto(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WebPlayers(), plugin);

        championParticles();
        buffSurrounding();
    }

    public static void makeChampion(Monster monster) {
        setNoLevel(monster);
        monster.setCustomName(color("&6&l" + fixCaps(monster.getType().toString()) + " Champion"));
        monster.setMetadata("Champion", new ChampionMetadata());
        monster.setPersistent(true);

        monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(calculateChampionHealth(monster));
        monster.setHealth(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        monster.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        monster.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(
                monster.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getBaseValue() * 2);

        if(!(monster.getType() == EntityType.CREEPER)) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, -1, 0, false, false, false));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1, false, false, false));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, -1, 1, false, false, false));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 0, false, false, false));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0, false, false, false));
        }

        OnMobAttacksAbility.addAbility(monster, OnMobAttacksAbility.GAIN_HEALTH_FROM_TARGET);

        Bukkit.getScheduler().runTaskLater(plugin,
                () -> equipChampion(monster), 1);
    }

    public static void showParticles(Monster monster) {
        drawCircle(monster.getLocation(), 1.8, Particle.SMALL_FLAME, 90);
    }

    public static double calculateChampionHealth(Monster monster) {
        return (monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 2) + (getLevel(monster) * 5)
                + 45 + ((double) getLevel(monster) / 2);
    }

    public static void equipChampion(Monster monster) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            monster.getEquipment().setItemInMainHandDropChance(0);
            monster.getEquipment().setHelmetDropChance(2f);
            monster.getEquipment().setChestplateDropChance(0);
            monster.getEquipment().setLeggingsDropChance(0);
            monster.getEquipment().setBootsDropChance(0);
        }, 3);

        if(monster instanceof AbstractSkeleton) {
            monster.getEquipment().setItem(EquipmentSlot.HAND, createBow());
        } else if(monster instanceof Pillager) {
            monster.getEquipment().setItem(EquipmentSlot.HAND, createCrossbow());
        } else if(monster instanceof Drowned) {
            monster.getEquipment().setItem(EquipmentSlot.HAND, createTrident());
        } else {
            monster.getEquipment().setItem(EquipmentSlot.HAND, createSword());
        }

        monster.getEquipment().setItem(EquipmentSlot.HEAD, createHelmet());
        monster.getEquipment().setItem(EquipmentSlot.CHEST, createChestplate());
        monster.getEquipment().setItem(EquipmentSlot.LEGS, createLeggings());
        monster.getEquipment().setItem(EquipmentSlot.FEET, createBoots());
    }

    public static ItemStack createSword() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();

        swordMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
        swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        swordMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        sword.setItemMeta(swordMeta);
        return sword;
    }

    public static ItemStack createTrident() {
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta tridentMeta = trident.getItemMeta();

        tridentMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
        tridentMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        tridentMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        tridentMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        trident.setItemMeta(tridentMeta);
        return trident;
    }

    public static ItemStack createBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.addEnchant(Enchantment.POWER, 4, true);
        bowMeta.addEnchant(Enchantment.FLAME, 1, true);
        bowMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        bow.setItemMeta(bowMeta);
        return bow;
    }

    public static ItemStack createCrossbow() {
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta crossbowMeta = crossbow.getItemMeta();

        crossbowMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        crossbowMeta.addEnchant(Enchantment.MULTISHOT, 1, true);
        crossbowMeta.addEnchant(Enchantment.PIERCING, 4, true);
        crossbowMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        crossbow.setItemMeta(crossbowMeta);
        return crossbow;
    }

    public static ItemStack createHelmet() {
        ItemStack banner = new ItemStack(Material.YELLOW_BANNER);
        BannerMeta meta = (BannerMeta) banner.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Champion's Banner");

        meta.addPattern(new Pattern(DyeColor.ORANGE, PatternType.GRADIENT_UP));
        meta.addPattern(new Pattern(DyeColor.BLACK, PatternType.BORDER));
        meta.addPattern(new Pattern(DyeColor.RED, PatternType.SKULL));

        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        banner.setItemMeta(meta);
        return banner;
    }

    public static ItemStack createChestplate() {
        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.getItemMeta();

        chestplateMeta.addEnchant(Enchantment.PROTECTION, 3, true);
        chestplateMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        chestplate.setItemMeta(chestplateMeta);
        return chestplate;
    }

    public static ItemStack createLeggings() {
        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta leggingsMeta = leggings.getItemMeta();

        leggingsMeta.addEnchant(Enchantment.PROTECTION, 3, true);
        leggingsMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        leggings.setItemMeta(leggingsMeta);
        return leggings;
    }

    public static ItemStack createBoots() {
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();

        bootsMeta.addEnchant(Enchantment.PROTECTION, 3, true);
        bootsMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        bootsMeta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
        bootsMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        boots.setItemMeta(bootsMeta);
        return boots;
    }

    public static void championParticles() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    for(Monster m : world.getEntitiesByClass(Monster.class)) {
                        if(m.hasMetadata("Champion")) {
                            showParticles(m);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 3, 3);
    }

    public static void buffSurrounding() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    for(Monster m : world.getEntitiesByClass(Monster.class)) {
                        if(m.hasMetadata("Champion")) {
                            ArrayList<Particle> effects = new ArrayList<>() {
                                {
                                    add(Particle.ENCHANTED_HIT);
                                    add(Particle.WITCH);
                                }
                            };

                            giveProjectileShield(m, 150, effects, effects, 5);

                            for(int i = 2; i <= 12; i++) {
                                int finalI = i;
                                Bukkit.getScheduler().runTaskLater(plugin,
                                        () -> buffSameType(m, finalI), i * 10);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 320, 320);
    }
}
