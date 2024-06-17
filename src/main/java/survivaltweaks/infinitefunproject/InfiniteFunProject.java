package survivaltweaks.infinitefunproject;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import survivaltweaks.infinitefunproject.Champions.ChampionInit;
import survivaltweaks.infinitefunproject.Commands.InitCommands;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities;
import survivaltweaks.infinitefunproject.CustomItems.InitCustomItems;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.PreventMutiHit;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ProjectileShieldMetadata;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.TaxExemptMetadata;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.InitDragon;
import survivaltweaks.infinitefunproject.Combat.CombatInit;
import survivaltweaks.infinitefunproject.EventListeners.DamageTax;
import survivaltweaks.infinitefunproject.EventListeners.OnEntityDamaged;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;
import survivaltweaks.infinitefunproject.Mobs.MobInit;
import survivaltweaks.infinitefunproject.Noise.NoiseInit;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeMode;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager;
import survivaltweaks.infinitefunproject.Player.PlayerInit;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;
import survivaltweaks.infinitefunproject.StatusMeters.StatusInit;
import survivaltweaks.infinitefunproject.Bosses.Wither.InitWither;
import survivaltweaks.infinitefunproject.World.Infection.InfectionManager;
import survivaltweaks.infinitefunproject.World.WorldInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.*;

import static survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners.ProjectileShieldManager.destroyShield;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getMaxLevel;
import static survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager.save;

public final class InfiniteFunProject extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        System.out.println("Enabled Project: Infinite Fun");

        if(Bukkit.getServer().getBossBar(NamespacedKey.minecraft("events")) != null) {
            Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("events"));
        }

        if(Bukkit.getServer().getBossBar(NamespacedKey.minecraft("modifiers")) != null) {
            Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("modifiers"));
        }

        setNaggable(false);

        PlayerInit.init();
        WorldInit.init();
        MobInit.init();
        EventInit.init();
        InitDragon.init();
        InitWither.init();
        InitCustomItems.init();
        InitCommands.init();
        WorldModInit.init();
        StatusInit.init();
        CombatInit.init();
        ChampionInit.init();
        NoiseInit.init();
        ChallengeModeManager.init();

        setGamerules();
        removeMonsters();
        removeTextDisplays();

        Bukkit.getServer().getPluginManager().registerEvents(new OnEntityDamaged(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageTax(), plugin);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getServer().getBossBar(NamespacedKey.minecraft("events")).setVisible(false);
        Bukkit.getServer().getBossBar(NamespacedKey.minecraft("events")).removeAll();
        Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("events"));

        Bukkit.getServer().getBossBar(NamespacedKey.minecraft("modifiers")).setVisible(false);
        Bukkit.getServer().getBossBar(NamespacedKey.minecraft("modifiers")).removeAll();
        Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("modifiers"));

        InfectionManager.disinfectAll();
        InitAbilities.reset();

        for(Player player : Bukkit.getOnlinePlayers()) {
            InitUpgrades.saveData(player);
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            for(ChallengeMode mode : ChallengeMode.values()) {
                save(player, mode);
            }
        }

        System.out.println("Disabled Project: Infinite Fun");
    }

    public static void removeMonsters() {
        for(World world : Bukkit.getWorlds()) {
            for(Enemy monster : world.getEntitiesByClass(Enemy.class)) {
                monster.remove();
            }
        }
    }

    public static void removeTextDisplays() {
        for(World world : Bukkit.getWorlds()) {
            for(TextDisplay display : world.getEntitiesByClass(TextDisplay.class)) {
                display.remove();
            }
        }
    }

    public static void setGamerules() {
        List<World> worlds = Bukkit.getWorlds();

        for (World world : worlds) {
            world.setDifficulty(Difficulty.HARD);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            world.setGameRule(GameRule.KEEP_INVENTORY, true);

            if(Bukkit.getOnlinePlayers().size() == 1) {
                world.setTime(15000);
            }

            world.getWorldBorder().setSize(20000);
        }
    }

    public static @NotNull String fixCaps(@NotNull String s) {
        StringBuilder fixed = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            if(i == 0 || s.charAt(i - 1) == '_') {
                fixed.append(Character.toString(s.charAt(i)).toUpperCase());
                continue;
            }
            if(s.charAt(i) == '_') {
                fixed.append(' ');
                continue;
            }
            fixed.append(Character.toString(s.charAt(i)).toLowerCase());
        }
        return fixed.toString();
    }

    public static double distanceBetween(@NotNull Vector point1, @NotNull Vector point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2)
                + Math.pow(point2.getY() - point1.getY(), 2)
                + Math.pow(point2.getZ() - point1.getZ(), 2));
    }

    public static double distanceBetween(@NotNull Entity entity, @NotNull Entity entity2) {
        return entity.getLocation().distance(entity2.getLocation());
    }

    public static double distanceBetween(@NotNull Location loc, @NotNull Location loc2) {
        return loc.distance(loc2);
    }

    @Contract("_ -> new")
    public static @NotNull String color(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static boolean raidsActive() {
        for(World world : Bukkit.getServer().getWorlds()) {
            if(!world.getRaids().isEmpty() && !world.getPlayers().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean dragonActive() {
        for(World world : Bukkit.getServer().getWorlds()) {
            try {
                if(world.getEnvironment().equals(World.Environment.THE_END)) {
                    EnderDragon dragon = world.getEnderDragonBattle().getEnderDragon();
                    return dragon != null && !world.getPlayers().isEmpty();
                }
            } catch (NullPointerException ignored) {
            }
        }
        return false;
    }

    public static boolean witherActive() {
        for(World world : Bukkit.getServer().getWorlds()) {
            for(LivingEntity entity : world.getLivingEntities()) {
                if(entity instanceof Wither && !world.getPlayers().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void drawCircle(Location center, double size, Particle particle, int iterations) {
        for (int i = 0; i <= iterations; i += 1) {
            Location particleLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
            particleLoc.setX(center.getX() + Math.cos(i) * size);
            particleLoc.setZ(center.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            center.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08);
        }
    }

    public static void drawCircle(Location center, double size, ArrayList<Particle> particles, int iterations) {
        for (int i = 0; i <= iterations; i += 1) {
            Location particleLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
            particleLoc.setX(center.getX() + Math.cos(i) * size);
            particleLoc.setZ(center.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            for(Particle particle : particles) {
                center.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08);
            }
        }
    }

    public static void findAir(@NotNull Location loc) {
        int i = 0;
        while(!(loc.getWorld().getBlockAt(loc).isPassable() || loc.getWorld().getBlockAt(loc).isEmpty()) && i < 10) {
            loc.setY(loc.getY() + 1);
            i++;
        }
    }

    public static void findGround(@NotNull Location loc) {
        while((loc.getWorld().getBlockAt(loc).isPassable() || loc.getWorld().getBlockAt(loc).isEmpty())) {
            loc.setY(loc.getY() - 1);
        }
        loc.add(new Vector(0, 1, 0));
    }

    public static @NotNull List<Entity> circularNearbyEntities(@NotNull Entity entity, double searchDist) {
        List<Entity> entities = new ArrayList<>();
        for(Entity entity1 : entity.getWorld().getEntities()) {
            if(distanceBetween(entity1.getLocation().toVector(), entity.getLocation().toVector()) <= searchDist) {
                if(entity1.equals(entity)) {
                    continue;
                }
                entities.add(entity1);
            }
        }
        return entities;
    }

    public static @NotNull List<Entity> circularNearbyEntities(@NotNull Location loc, double searchDist) {
        List<Entity> entities = new ArrayList<>();
        for(Entity entity1 : loc.getWorld().getEntities()) {
            if(distanceBetween(entity1.getLocation().toVector(), loc.toVector()) <= searchDist) {
                entities.add(entity1);
            }
        }

        return entities;
    }

    public static @NotNull ArrayList<LivingEntity> nearbyLivingEntities(@NotNull Entity entity, double searchDist) {
        ArrayList<LivingEntity> entities = new ArrayList<>();
        for(Entity entity1 : entity.getWorld().getEntities()) {
            if(distanceBetween(entity1.getLocation().toVector(), entity.getLocation().toVector()) <= searchDist) {
                if(entity1.equals(entity)) {
                    continue;
                }
                if(entity1 instanceof LivingEntity) {
                    entities.add((LivingEntity) entity1);
                }
            }
        }
        return entities;
    }

    public static @NotNull ArrayList<LivingEntity> nearbyLivingEntities(@NotNull Location loc, double searchDist) {
        ArrayList<LivingEntity> entities = new ArrayList<>();
        for(Entity entity1 : loc.getWorld().getEntities()) {
            if(distanceBetween(entity1.getLocation().toVector(), loc.toVector()) <= searchDist) {
                if(entity1 instanceof LivingEntity) {
                    entities.add((LivingEntity) entity1);
                }
            }
        }
        return entities;
    }

    public static void faceEntityToAnother(@NotNull Entity entity, @NotNull Entity faceTarget) {
        Location location = entity.getLocation();
        location.setDirection(faceTarget.getLocation().toVector().subtract(entity.getLocation().toVector()));
        entity.teleport(location);
    }

    public static void setHeadRotationToFaceEntity(@NotNull Entity entity, @NotNull Entity faceTarget) {
        Vector direction = faceTarget.getLocation().toVector().subtract(entity.getLocation().toVector());

        float yaw = (float) Math.toDegrees(Math.atan2(direction.getY(), direction.getX()));
        float XYDistance = (float) Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2));
        float pitch = (float) Math.toDegrees(Math.atan2(direction.getZ(), XYDistance));

        entity.setRotation(yaw, pitch);
    }

    public static boolean eventCountActive() {
        return !(raidsActive() || dragonActive() || witherActive() || WorldModInit.getActiveModifier() == WorldModifier.CALM_BEFORE_THE_STORM)
                && !Bukkit.getOnlinePlayers().isEmpty();
    }

    public static void stun(@NotNull LivingEntity entity, double duration, boolean showParticles) {
        if(entity.isDead()) {
            return;
        }

        duration *= getStunMultiplier(entity);
        duration = Math.round(duration);

        if(showParticles) {
            for (int i = 0; i <= Math.min(40, duration); i++) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    entity.getWorld().spawnParticle(Particle.FLASH, entity.getEyeLocation()
                            .add(entity.getEyeLocation().getDirection().normalize()), 5, 0.01, 0.01, 0.01, 0.05);
                    entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getEyeLocation()
                            .add(entity.getEyeLocation().getDirection().normalize()), 5, 0.01, 0.01, 0.01, 0.05);
                }, i);
            }
        }

        if(entity instanceof Monster) {
            Monster monster = (Monster) entity;
            monster.setTarget(null);
        }

        entity.setVelocity(new Vector(0, 0, 0));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int) duration, 255, false, false, false));
    }

    public static double getStunMultiplier(LivingEntity entity) {
        if(entity instanceof Player) {
            return 1;
        }
        if(entity instanceof Boss) {
            return 0;
        }

        int level = getLevel(entity);

        return 1 - ((((double) level - 15) / getMaxLevel()));
    }

    public static boolean modifierCountActive() { return !(raidsActive() || dragonActive() || witherActive()) && !Bukkit.getOnlinePlayers().isEmpty(); }

    public static @NotNull ArrayList<EntityType> hostiles() {
        ArrayList<EntityType> hostile = new ArrayList<>();

        for(World world : Bukkit.getWorlds()) {
            world.getEntitiesByClass(Enemy.class).forEach((x) -> hostile.add(x.getType()));
        }

        hostile.addAll(bosses());
        return hostile;
    }

    public static @NotNull ArrayList<EntityType> bosses() {
        ArrayList<EntityType> bosses = new ArrayList<>();
        bosses.add(EntityType.ENDER_DRAGON);
        bosses.add(EntityType.WITHER);

        return bosses;
    }

    @Contract(" -> new")
    public static @NotNull ArrayList<EntityType> players() {
        return new ArrayList<>() {
            {
                add(EntityType.PLAYER);
            }
        };
    }

    public static @NotNull ArrayList<EntityType> any() {
        ArrayList<EntityType> all = new ArrayList<>(List.of(EntityType.values()));
        all.removeIf(x -> !x.isAlive());

        return all;
    }

    public static @NotNull ArrayList<EntityType> allExceptPlayers() {
        ArrayList<EntityType> all = new ArrayList<>(List.of(EntityType.values()));
        all.remove(EntityType.PLAYER);
        all.removeIf(x -> !x.isAlive());

        return all;
    }

    public static void giveProjectileShield(@NotNull LivingEntity entity, int duration, ArrayList<Particle> deflectEffect, ArrayList<Particle> aura, int radius) {
        entity.setMetadata("ProjShield", new ProjectileShieldMetadata(deflectEffect, aura, radius));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.removeMetadata("ProjShield", InfiniteFunProject.plugin), duration);
    }

    public static @NotNull ArrayList<LivingEntity> createRay(@NotNull LivingEntity entity, int range, boolean teleport, ArrayList<Particle> particles) {
        Location shootLoc = entity.getEyeLocation().subtract(0, 0.4, 0);
        Location entityLoc = entity.getEyeLocation();
        Vector direction = entity.getLocation().getDirection();

        shootLoc.setX(Math.floor(shootLoc.getX()) + (entityLoc.getX() - Math.floor(entityLoc.getX())));
        shootLoc.setZ(Math.floor(shootLoc.getZ()) + (entityLoc.getZ() - Math.floor(entityLoc.getZ())));

        BlockIterator iterator = new BlockIterator(shootLoc, 0, range);
        ArrayList<LivingEntity> hitEntityList = new ArrayList<>();

        while(iterator.hasNext()) {
            Block block = iterator.next();

            if(block.getType().isSolid()) {
                break;
            } else {
                Location loc = block.getLocation();

                if(teleport) {
                    loc.setDirection(direction);
                    entity.teleport(loc);
                }

                for(Particle particle : particles) {
                    block.getWorld().spawnParticle(particle, loc.add(0, 0.6, 0), 6, 0.1, 0.1, 0.1, 0.03);
                }

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(block.getLocation(), 1.8);

                for(Entity e : nearby) {
                    if(e instanceof LivingEntity) {
                        if(hitEntityList.contains(e)) {
                            continue;
                        }

                        if(!e.equals(entity)) {
                            hitEntityList.add((LivingEntity) e);
                        }
                    }
                }
            }
        }

        return hitEntityList;
    }

    public static @NotNull ArrayList<LivingEntity> createRay(@NotNull LivingEntity entity, int range, double damageRadius, boolean teleport, ArrayList<Particle> particles) {
        Location shootLoc = entity.getEyeLocation().subtract(0, 0.4, 0);
        Location entityLoc = entity.getEyeLocation();
        Vector direction = entity.getLocation().getDirection();

        shootLoc.setX(Math.floor(shootLoc.getX()) + (entityLoc.getX() - Math.floor(entityLoc.getX())));
        shootLoc.setZ(Math.floor(shootLoc.getZ()) + (entityLoc.getZ() - Math.floor(entityLoc.getZ())));

        BlockIterator iterator = new BlockIterator(shootLoc, 0, range);
        ArrayList<LivingEntity> hitEntityList = new ArrayList<>();

        while(iterator.hasNext()) {
            Block block = iterator.next();

            if(block.getType().isSolid()) {
                break;
            } else {
                Location loc = block.getLocation();

                if(teleport) {
                    loc.setDirection(direction);
                    entity.teleport(loc);
                }

                for(Particle particle : particles) {
                    block.getWorld().spawnParticle(particle, loc.add(0, 0.6, 0), 6, 0.1, 0.1, 0.1, 0.03);
                }

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(block.getLocation(), damageRadius);

                for(Entity e : nearby) {
                    if(e instanceof LivingEntity) {
                        if(hitEntityList.contains(e)) {
                            continue;
                        }

                        if(!e.equals(entity)) {
                            hitEntityList.add((LivingEntity) e);
                        }
                    }
                }
            }
        }

        return hitEntityList;
    }

    public static @NotNull HashMap<LivingEntity, Boolean> createDamageRay(@NotNull LivingEntity entity, int range, float damage,
                                                                          int pierce, boolean pierceShield, boolean destroyShield, boolean throughBlocks,
                                                                          List<Particle> particles, boolean headShots) {
        Location shootLoc = entity.getEyeLocation().subtract(0, 0.4, 0);
        Location entityLoc = entity.getEyeLocation();
        shootLoc.setX(Math.floor(shootLoc.getX()) + (entityLoc.getX() - Math.floor(entityLoc.getX())));
        shootLoc.setZ(Math.floor(shootLoc.getZ()) + (entityLoc.getZ() - Math.floor(entityLoc.getZ())));

        BlockIterator iterator = new BlockIterator(shootLoc, 0, range);
        int hitEntities = 0;
        double headshotRange = 1;
        double yBound = 0.9;

        HashMap<LivingEntity, Boolean> hitEntityList = new HashMap<>();

        while(iterator.hasNext()) {
            Block block = iterator.next();

            if(block.getType().isSolid() && !throughBlocks) {
                break;
            } else {
                for(Particle particle : particles) {
                    block.getWorld().spawnParticle(particle, block.getLocation(), 4, 0.1, 0.1, 0.1, 0.03);
                }

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(block.getLocation(), 1.7);

                for(Entity e : nearby) {
                    if(e instanceof LivingEntity) {
                        if(hitEntityList.containsKey(e)) {
                            continue;
                        }

                        if(!e.equals(entity)) {
                            if(e.hasMetadata("ProjShield") && !(destroyShield || pierceShield)) {
                                continue;
                            }

                            if(e.getTicksLived() < 10) {
                                continue;
                            }

                            if(((LivingEntity) e).hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && (pierceShield || destroyShield)) {
                                ((LivingEntity) e).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            }

                            if(e.hasMetadata("ProjShield") && destroyShield) {
                                LivingEntity living = (LivingEntity) e;

                                if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                        && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                    hitEntityList.put(living, true);
                                } else {
                                    hitEntityList.put(living, false);
                                }

                                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                        () -> destroyShield(living), 2);

                                if(pierceShield) {
                                    if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                            && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)
                                    && headShots) {
                                        if(damage > 0) {
                                            living.damage(damage * 2, entity);
                                        }
                                    } else {
                                        if(damage > 0) {
                                            living.damage(damage, entity);
                                        }
                                    }
                                }

                                return hitEntityList;
                            }

                            if(e.hasMetadata("ProjShield") && pierceShield) {
                                LivingEntity living = (LivingEntity) e;
                                living.damage(damage, entity);
                                hitEntities++;

                                if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                        && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                    hitEntityList.put(living, true);
                                } else {
                                    hitEntityList.put(living, false);
                                }

                                if(hitEntities > pierce) {
                                    return hitEntityList;
                                }
                                continue;
                            }

                            LivingEntity living = (LivingEntity) e;

                            if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                    && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)
                            && headShots) {
                                if(damage > 0) {
                                    living.damage(damage * 2, entity);
                                }
                            } else {
                                if(damage > 0) {
                                    living.damage(damage, entity);
                                }
                            }

                            hitEntities++;

                            if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                    && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                hitEntityList.put(living, true);
                            } else {
                                hitEntityList.put(living, false);
                            }

                            if(hitEntities > pierce) {
                                return hitEntityList;
                            }
                        }
                    }
                }
            }
        }
        return hitEntityList;
    }

    public static @NotNull HashMap<LivingEntity, Boolean> createSpreadDamageRay(@NotNull LivingEntity entity, double variance, int range, float damage,
                                                                                int pierce, boolean pierceShield, boolean destroyShield, boolean throughBlocks,
                                                                                List<Particle> particles, boolean headShots) {
        Location shootLoc = entity.getEyeLocation().subtract(0, 0.4, 0);

        Vector direction = shootLoc.getDirection();
        shootLoc.setDirection(direction.add(new Vector(direction.getX() + new Random().nextDouble(-variance, variance),
                direction.getY() + new Random().nextDouble(-variance, variance),
                direction.getZ() + new Random().nextDouble(-variance, variance))));
        shootLoc.getDirection().normalize();

        Location entityLoc = entity.getEyeLocation();
        shootLoc.setX(Math.floor(shootLoc.getX()) + (entityLoc.getX() - Math.floor(entityLoc.getX())));
        shootLoc.setZ(Math.floor(shootLoc.getZ()) + (entityLoc.getZ() - Math.floor(entityLoc.getZ())));

        BlockIterator iterator = new BlockIterator(shootLoc, 0, range);
        int hitEntities = 0;
        double headshotRange = 1;
        double yBound = 0.9;

        HashMap<LivingEntity, Boolean> hitEntityList = new HashMap<>();

        while(iterator.hasNext()) {
            Block block = iterator.next();

            if(block.getType().isSolid() && !throughBlocks) {
                break;
            } else {
                for(Particle particle : particles) {
                    block.getWorld().spawnParticle(particle, block.getLocation(), 4, 0.1, 0.1, 0.1, 0.03);
                }

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(block.getLocation(), 1.7);

                for(Entity e : nearby) {
                    if(e instanceof LivingEntity) {
                        if(hitEntityList.containsKey(e)) {
                            continue;
                        }

                        if(!e.equals(entity)) {
                            if(e.hasMetadata("ProjShield") && !(destroyShield || pierceShield)) {
                                continue;
                            }

                            if(e.getTicksLived() < 10) {
                                continue;
                            }

                            if(((LivingEntity) e).hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && (pierceShield || destroyShield)) {
                                ((LivingEntity) e).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            }

                            if(e.hasMetadata("ProjShield") && destroyShield) {
                                LivingEntity living = (LivingEntity) e;

                                if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                        && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                    hitEntityList.put(living, true);
                                } else {
                                    hitEntityList.put(living, false);
                                }

                                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                        () -> destroyShield(living), 2);

                                if(pierceShield) {
                                    if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                            && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)
                                    && headShots) {
                                        if(damage > 0) {
                                            living.damage(damage * 2, entity);
                                            living.setNoDamageTicks(0);
                                        }
                                    } else {
                                        if(damage > 0) {
                                            living.damage(damage, entity);
                                            living.setNoDamageTicks(0);
                                        }
                                    }
                                }

                                return hitEntityList;
                            }

                            if(e.hasMetadata("ProjShield") && pierceShield) {
                                LivingEntity living = (LivingEntity) e;
                                living.damage(damage, entity);
                                hitEntities++;

                                if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                        && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                    hitEntityList.put(living, true);
                                } else {
                                    hitEntityList.put(living, false);
                                }

                                if(hitEntities > pierce) {
                                    return hitEntityList;
                                }
                                continue;
                            }

                            LivingEntity living = (LivingEntity) e;

                            if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                    && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)
                            && headShots) {
                                if(damage > 0) {
                                    living.damage(damage * 2, entity);
                                    living.setNoDamageTicks(0);
                                }
                            } else {
                                if(damage > 0) {
                                    living.damage(damage, entity);
                                    living.setNoDamageTicks(0);
                                }
                            }

                            hitEntities++;

                            if(block.getLocation().distance(living.getEyeLocation()) < headshotRange
                                    && withinBound(block.getLocation().getY(), living.getEyeHeight() + living.getLocation().getY(), yBound)) {
                                hitEntityList.put(living, true);
                            } else {
                                hitEntityList.put(living, false);
                            }

                            if(hitEntities > pierce) {
                                return hitEntityList;
                            }
                        }
                    }
                }
            }
        }
        return hitEntityList;
    }

    public static boolean facingSameWay(@NotNull Vector v1, @NotNull Vector v2) {
        return v1.dot(v2) >= 0.8;
    }

    public static boolean facingSameWay(@NotNull Entity entity, @NotNull Entity entity2) {
        Vector entity1Direction = entity.getLocation().getDirection();
        Vector entity2Direction = entity2.getLocation().getDirection();

        return facingSameWay(entity1Direction, entity2Direction);
    }

    public static void makeInvisible(@NotNull LivingEntity entity, int duration) {
        entity.setVisibleByDefault(false);
        entity.setInvisible(true);
        entity.setInvulnerable(true);

        entity.getWorld().spawnParticle(Particle.SMOKE_NORMAL, entity.getLocation(),
                10, 0.25, 0.25, 0.25, 0.03);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            entity.setVisibleByDefault(true);
            entity.setInvisible(false);
            entity.setInvulnerable(false);

            entity.getWorld().spawnParticle(Particle.SMOKE_NORMAL, entity.getLocation(),
                    10, 0.25, 0.25, 0.25, 0.03);
        }, duration);
    }

    public static void taxEvade(@NotNull LivingEntity entity, int duration, boolean message) {
        entity.setMetadata("TaxExempt", new TaxExemptMetadata());

        if(message) {
            entity.sendMessage(ChatColor.RED + "You are now tax evading!");
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("TaxExempt", plugin);

            if(message) {
                entity.sendMessage(ChatColor.GREEN + "You are no longer tax evading!");
            }
        }, duration);
    }

    public static boolean withinBound(double number, double other, double range) {
        return number - range <= other && number + range >= other;
    }

    public static void setHasBeenHit(@NotNull LivingEntity entity, int duration) {
        entity.setMetadata("BeenHit", new PreventMutiHit());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.removeMetadata("BeenHit", InfiniteFunProject.plugin), duration);
    }

    public static void drawExpandingCircle(Location location, int startRadius, int expansions, int expandAmount, int delay, ArrayList<Particle> particles) {
        for(int i = 0; i < expansions; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                    drawCircle(location, startRadius + (finalI * expandAmount), particles, 90), (long) i * delay);
        }
    }

    public static void glow(@NotNull Entity entity, int duration) {
        entity.setGlowing(true);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(!entity.isDead()) {
                entity.setGlowing(false);
            }
        }, duration);
    }
}
