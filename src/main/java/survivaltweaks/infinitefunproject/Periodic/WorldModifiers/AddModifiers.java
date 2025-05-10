package survivaltweaks.infinitefunproject.Periodic.WorldModifiers;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.BounceProjectiles;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.RevivedMeta;
import survivaltweaks.infinitefunproject.Mobs.ShadowCloneMeta;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttackedAbility;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttacksAbility;

import java.util.*;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;
import static survivaltweaks.infinitefunproject.Mobs.MobInit.setNoLevel;
import static survivaltweaks.infinitefunproject.World.Infection.InfectionManager.infectEntity;

public class AddModifiers implements Listener {

    @EventHandler
    public void activateSpawnModifiers(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();
        if(spawned instanceof LivingEntity) {
            if(spawned instanceof EnderDragon || spawned instanceof Wither || spawned instanceof Player) {
                return;
            }
            ArrayList<WorldModifier> activeModifiers = WorldModInit.activeModifiers();

            for(WorldModifier activeModifier : activeModifiers) {
                switch (activeModifier) {
                    case SHIELDED_MOBS:
                        shieldMob((LivingEntity) spawned);
                        break;
                    case WARP_SPEED:
                        warpSpeed((LivingEntity) spawned);
                        break;
                    case SLASHED_HEALTH:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, ()
                                -> slashedHealth((LivingEntity) spawned), 2);
                        break;
                    case EMPOWERED_ENEMIES:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, ()
                                -> empoweredEnemies((LivingEntity) spawned), 2);
                        break;
                    case SUPER_GEARED:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                            if (spawned instanceof Zombie || spawned instanceof AbstractSkeleton || spawned instanceof Piglin) {
                                superGeared((LivingEntity) spawned);
                            }
                        }, 2);
                        break;
                    case SHADOW_CLONES:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                            if (spawned.hasMetadata("ShadowClone")) {
                                return;
                            }
                            shadowClone((LivingEntity) spawned);
                        }, 3);
                        break;
                    case ANOMALOUS:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                () -> anomalousSpawn((LivingEntity) spawned), 3);
                        break;
                    case CALM_BEFORE_THE_STORM:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                () -> calmBeforeStorm((LivingEntity) spawned), 3);
                        break;
                    case GOO:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                () -> goo((LivingEntity) spawned), 2);
                        break;
                    case SICKNESS:
                        sickness((LivingEntity) spawned);
                        break;
                    case ETHEREAL_AUGMENTATION:
                        etherealAugmentation((LivingEntity) spawned);
                        break;
                }
            }
        }
    }

    public static void etherealAugmentation(LivingEntity entity) {
        if(entity instanceof Monster monster) {
            if(new Random().nextInt(0, 4) == 1) {
                OnMobAttacksAbility.addAbility(monster, OnMobAttacksAbility.values()
                        [new Random().nextInt(0, OnMobAttacksAbility.values().length)]);
            }

            if(new Random().nextInt(0, 4) == 1) {
                OnMobAttackedAbility.addAbility(monster, OnMobAttackedAbility.values()
                        [new Random().nextInt(0, OnMobAttackedAbility.values().length)]);
            }
        }
    }

    public static void goo(LivingEntity entity) {
        if(new Random().nextInt(0, 4) == 1) {
            if (!entity.getPassengers().isEmpty()) return;
            if (!(entity instanceof Monster)) return;
            if (entity instanceof Slime || entity instanceof Drowned) return;

            if (entity.getWorld().getEnvironment() != World.Environment.NETHER) {
                Slime slime = entity.getWorld().spawn(entity.getLocation(), Slime.class);
                slime.addPassenger(entity);
            } else {
                MagmaCube cube = entity.getWorld().spawn(entity.getLocation(), MagmaCube.class);
                cube.addPassenger(entity);
            }
        }
    }

    public static void calmBeforeStorm(LivingEntity entity) {
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.75);
        if(entity instanceof Creeper creeper) {
            creeper.setPowered(false);
        }
        if(entity instanceof Zombie zombie) {
            zombie.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
            zombie.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.CHAINMAIL_HELMET));
            zombie.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.GOLDEN_BOOTS));
        }
        if(entity instanceof AbstractSkeleton skeleton) {
            skeleton.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.CHAINMAIL_HELMET));
            skeleton.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.GOLDEN_BOOTS));
        }
        for(PotionEffect potionEffect : entity.getActivePotionEffects()) {
            entity.getActivePotionEffects().remove(potionEffect);
        }
    }

    public static void shieldMob(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 12000, 0, false, false, false));
    }

    public static void warpSpeed(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1, false, false, false));
    }

    public static void slashedHealth(LivingEntity entity) {
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / 2);
    }

    public static void empoweredEnemies(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 4800, 0, false, false, false));
    }

    public static void superGeared(LivingEntity entity) {
        if(entity.getEquipment().getItemInMainHand().getType() == Material.BOW) {
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();

            bowMeta.addEnchant(Enchantment.POWER, 3, false);

            bow.setItemMeta(bowMeta);
            entity.getEquipment().setItem(EquipmentSlot.HAND, bow);
            entity.getEquipment().setItemInMainHandDropChance(0);
        } else {
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordMeta = sword.getItemMeta();

            swordMeta.addEnchant(Enchantment.SHARPNESS, 3, false);

            sword.setItemMeta(swordMeta);
            entity.getEquipment().setItem(EquipmentSlot.HAND, sword);
            entity.getEquipment().setItemInMainHandDropChance(0);
        }

        if(entity instanceof Drowned) {
            ItemStack trident = new ItemStack(Material.TRIDENT);

            ItemMeta tridentMeta = trident.getItemMeta();

            tridentMeta.addEnchant(Enchantment.IMPALING, 3, true);
            tridentMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
            tridentMeta.addEnchant(Enchantment.CHANNELING, 3, true);

            trident.setItemMeta(tridentMeta);
            entity.getEquipment().setItem(EquipmentSlot.HAND, trident);
            entity.getEquipment().setItemInMainHandDropChance(0);
        }

        if(!(entity.getEquipment() == null)) {
            entity.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            entity.getEquipment().setChestplateDropChance(0);
        }
    }

    public static void shadowClone(LivingEntity entity) {
        if(!(entity instanceof Slime)) {
            LivingEntity clone = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                clone.setMetadata("ShadowClone", new ShadowCloneMeta());
                clone.setHealth(clone.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.66);
            }, 1);
        }
    }

    public static void sickness(LivingEntity entity) {
        if(new Random().nextInt(0, 33) == 2) {
            infectEntity(entity);
        }
    }

    public static void anomalousSpawn(LivingEntity entity) {
        goo(entity);

        if(!entity.hasMetadata("ShadowClone")) {
            shadowClone(entity);
        }

        superGeared(entity);
        empoweredEnemies(entity);
        warpSpeed(entity);
        shieldMob(entity);
        sickness(entity);
        etherealAugmentation(entity);
    }

    @EventHandler
    public void activateDamageModifiers(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        if(damaged instanceof LivingEntity) {
            if(damaged instanceof EnderDragon || damaged instanceof Wither) {
                return;
            }
            ArrayList<WorldModifier> activeModifiers = WorldModInit.activeModifiers();

            for(WorldModifier activeModifier : activeModifiers) {
                switch (activeModifier) {
                    case SHARD_OF_GLASS:
                        shardOfGlass((LivingEntity) damaged);
                        break;
                    case DOUBLE_DOWN:
                        doubleDown(event);
                        break;
                    case DAMAGE_BARGAIN:
                        damageBargain(event);
                        break;
                    case THORNS:
                        thorns((LivingEntity) event.getDamager(), event.getDamage());
                        break;
                    case ANOMALOUS:
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                () -> anomalousDamage((LivingEntity) event.getEntity(), event.getDamage(), event), 10);
                        break;
                }
            }
        }
    }

    public static void shardOfGlass(LivingEntity entity) {
        entity.setHealth(0);
    }

    public static void doubleDown(EntityDamageByEntityEvent event) {
        event.setDamage(event.getDamage() * 2);
    }

    public static void damageBargain(EntityDamageByEntityEvent event) { event.setDamage(event.getDamage() / 2); }

    public static void thorns(LivingEntity entity, double damage) {
        entity.damage(damage / 3);
    }

    public static void anomalousDamage(LivingEntity entity, double damage, EntityDamageByEntityEvent event) {
        doubleDown(event);
        thorns(entity, damage);
    }

    @EventHandler
    public void activateBlockBrokenModifiers(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location blockLoc = block.getLocation();

        ArrayList<WorldModifier> activeModifiers = WorldModInit.activeModifiers();

        for(WorldModifier activeModifier : activeModifiers) {
            switch (activeModifier) {
                case EXTREME_FORTUNE:
                    if (event.isDropItems()) {
                        doubleUp(player, blockLoc, block);
                    }
                    break;
                case BLOCK_PROTECTION:
                    blockProtection(player, block);
                    break;
                case ANOMALOUS:
                    if (event.isDropItems()) {
                        anomalousBreak(player, blockLoc, block, event);
                    }
                    break;
                case BLOCK_SENTIENCE:
                    event.setCancelled(true);
                    blockFightBack(block.getType(), blockLoc);
            }
        }
    }

    public static void blockFightBack(Material material, Location location) {
        location.getWorld().setBlockData(location, Material.AIR.createBlockData());
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

        setNoLevel(zombie);
        zombie.setBaby();
        zombie.setInvisible(true);
        zombie.setCustomName(color("&a" + fixCaps(material.toString())));
        zombie.setCustomNameVisible(true);

        zombie.getEquipment().setHelmet(new ItemStack(material));
        zombie.getEquipment().setChestplate(new ItemStack(Material.AIR));
        zombie.getEquipment().setLeggings(new ItemStack(Material.AIR));
        zombie.getEquipment().setBoots(new ItemStack(Material.AIR));

        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        zombie.setHealth(zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

        zombie.getEquipment().setHelmetDropChance(1);
    }

    public static void doubleUp(Player player, Location spawnLoc, Block block) {
        Collection<ItemStack> drops = block.getDrops();

        for(ItemStack drop : drops) {
            player.getWorld().dropItem(spawnLoc, drop);
        }
    }

    public static void blockProtection(Player player, Block block) {
        player.damage(block.getType().getHardness());
    }

    public static void anomalousBreak(Player player, Location spawnLoc, Block block, BlockBreakEvent event) {
        doubleUp(player, spawnLoc, block);
        blockProtection(player, block);

        event.setCancelled(true);
        blockFightBack(block.getType(), block.getLocation());
    }

    @EventHandler
    public void activateOnKillModifiers(EntityDeathEvent event) {
        LivingEntity killed = event.getEntity();
        if(killed instanceof EnderDragon || killed instanceof Wither || killed instanceof Player) {
            return;
        }
        ArrayList<WorldModifier> activeModifiers = WorldModInit.activeModifiers();

        for(WorldModifier activeModifier : activeModifiers) {
            switch (activeModifier) {
                case NECROMANCY:
                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        if (killed.hasMetadata("Revived")) {
                            return;
                        }
                        necromancy(killed);
                    }, 3);
                    break;
                case SUPER_LOOTING:
                    extremeFortune(killed, event.getDrops());
                    break;
                case ANOMALOUS:
                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                            () -> anomalousKill(killed, event.getDrops()), 5);
                    break;
                case EXPLOSIVE_CORPSES:
                    if(new Random().nextInt(0, 4) == 1) {
                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> explosiveCorpse(killed), 6);
                    }
                    break;
            }
        }
    }

    public static void necromancy(LivingEntity entity) {
        if(!(entity instanceof Slime)) {
            LivingEntity revived = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                revived.setMetadata("Revived", new RevivedMeta());
                drawCircle(revived.getLocation(), 2, Particle.TOTEM_OF_UNDYING, 180);
                revived.setHealth(revived.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.75);
            }, 1);
        }
    }

    public static void extremeFortune(LivingEntity entity, List<ItemStack> drops) {
        for(ItemStack drop : drops) {
            entity.getWorld().dropItem(entity.getLocation(), drop);
        }
    }

    public static void anomalousKill(LivingEntity entity, List<ItemStack> drops) {
        extremeFortune(entity, drops);
        if(new Random().nextInt(0, 4) == 1) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> explosiveCorpse(entity), 6);
        }
    }

    public static void explosiveCorpse(LivingEntity entity) {
        entity.getWorld().createExplosion(entity.getLocation(), 2.7f, false, false, entity);
    }

    @EventHandler
    public void activateProjectileShotModifiers(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(!(projectile.getShooter() instanceof LivingEntity)) {
            return;
        }

        ArrayList<WorldModifier> activeModifiers = WorldModInit.activeModifiers();

        for(WorldModifier activeModifier : activeModifiers) {
            switch(activeModifier) {
                case BOOM_SHOT:
                    boomShot(projectile);
                    break;
                case ANOMALOUS:
                    anomalousProjectileShot(projectile);
                    break;
                case LIMP_SHOT:
                    limpShot(projectile);
                    break;
                case INACCURACY:
                    inaccuracy(projectile);
                    break;
                case AIMBOT:
                    aimbot(projectile);
                    break;
                case SLIMY_SHOTS:
                    slimyShots(projectile);
                    break;
            }
        }
    }

    public static void boomShot(Projectile projectile) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(!(projectile.isDead() || projectile.hasMetadata("Explosive"))) {
                projectile.setMetadata("Explosive", new ExplosiveMeta(3f, false, false));
            }
        }, 2);
    }

    public static void limpShot(Projectile projectile) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            projectile.setVelocity(projectile.getVelocity().multiply(0.5));
        }, 1);
    }

    public static void anomalousProjectileShot(Projectile projectile) {
        boomShot(projectile);
        inaccuracy(projectile);
        aimbot(projectile);
    }

    public static void inaccuracy(Projectile projectile) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Vector velocity = projectile.getVelocity();
            velocity.add(new Vector(new Random().nextDouble(-0.1, 0.1),
                    new Random().nextDouble(-0.1, 0.1),
                    new Random().nextDouble(-0.1, 0.1))
            ).normalize();

            projectile.setVelocity(velocity);
        }, 1);
    }

    public static void aimbot(Projectile projectile) {
        if(!(projectile.isDead() || projectile.hasMetadata("Seeking"))) {
            projectile.setMetadata("Seeking", new SeekingMetadata(8, 200, any()));
        }
    }

    public static void slimyShots(Projectile projectile) {
        if(!(projectile.isDead() || projectile.hasMetadata("Bouncy"))) {
            projectile.setMetadata("Bouncy", new BounceProjectiles(7, 300));
        }
    }
}
