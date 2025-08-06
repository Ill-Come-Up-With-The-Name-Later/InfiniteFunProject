package survivaltweaks.infinitefunproject;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import survivaltweaks.infinitefunproject.AntiLag.InitAntiLag;
import survivaltweaks.infinitefunproject.Champions.ChampionInit;
import survivaltweaks.infinitefunproject.Commands.InitCommands;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities;
import survivaltweaks.infinitefunproject.CustomItems.InitCustomItems;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.*;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.InitDragon;
import survivaltweaks.infinitefunproject.Combat.CombatInit;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.EventListeners.DamageModification;
import survivaltweaks.infinitefunproject.EventListeners.DamageTax;
import survivaltweaks.infinitefunproject.EventListeners.DisplayHP;
import survivaltweaks.infinitefunproject.EventListeners.OnEntityDamaged;
import survivaltweaks.infinitefunproject.MonsterAbilities.InitMobAbilities;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;
import survivaltweaks.infinitefunproject.Mobs.MobInit;
import survivaltweaks.infinitefunproject.ChallengeMode.ChallengeMode;
import survivaltweaks.infinitefunproject.ChallengeMode.ChallengeModeManager;
import survivaltweaks.infinitefunproject.Player.PlayerInit;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;
import survivaltweaks.infinitefunproject.StatusMeters.StatusInit;
import survivaltweaks.infinitefunproject.Bosses.Wither.InitWither;
import survivaltweaks.infinitefunproject.TimeEvents.InitTimeEvents;
import survivaltweaks.infinitefunproject.World.Infection.InfectionManager;
import survivaltweaks.infinitefunproject.World.WorldInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.*;

import static survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners.ProjectileShieldManager.destroyShield;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getMaxLevel;
import static survivaltweaks.infinitefunproject.ChallengeMode.ChallengeModeManager.save;

public final class InfiniteFunProject extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        setNaggable(false);

        System.out.println("Enabled Project: Infinite Fun");

        if(Bukkit.getServer().getBossBar(NamespacedKey.minecraft("events")) != null) {
            Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("events"));
        }

        if(Bukkit.getServer().getBossBar(NamespacedKey.minecraft("modifiers")) != null) {
            Bukkit.getServer().removeBossBar(NamespacedKey.minecraft("modifiers"));
        }

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
        ChallengeModeManager.init();
        InitMobAbilities.init();
        InitAntiLag.init();
        InitTimeEvents.init();

        setGamerules();
        removeMonsters();
        removeTextDisplays();
        killProjectiles();
        resetCritsMetadata();

        Bukkit.getServer().getPluginManager().registerEvents(new OnEntityDamaged(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageTax(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageModification(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DisplayHP(), InfiniteFunProject.plugin);

        System.out.println("Reset and prepared all features.");
    }

    @Override
    public void onDisable() {
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
            resetAttributes(player);
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            for(ChallengeMode mode : ChallengeMode.values()) {
                save(player, mode);
            }
        }

        System.out.println("Reset features.");
        System.out.println("Disabled Project: Infinite Fun");
    }

    /**
     * Creates a large explosion
     *
     * @param location: The location of the explosion
     * @param radius: The radius of the explosion
     * @param damage: The amount of damage the explosion does
     */
    public static void createNuke(Location location, int radius, int damage, LivingEntity source) {
        int height = (int) (radius * 0.9);
        int shockwaveExpandRate = radius/ 10;

        for(int i = 0; i <= 20; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                    location.getWorld().spawnParticle(Particle.FLASH, location, 10, 0, 0, 0,
                            0.02, null, true), i);
        }

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            for(int i = 0; i < height; i++) {
                int finalI = i;

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(finalI == height / 2) {
                        drawExpandingCircle(location.clone().add(0, finalI, 0), 5, 10, shockwaveExpandRate,
                                3, Particle.EXPLOSION);
                        drawExpandingCircle(location.clone().add(0, finalI, 0), 5, 10, shockwaveExpandRate,
                                3, Particle.CAMPFIRE_SIGNAL_SMOKE);
                    } else {
                        drawExpandingCircle(location.clone().add(0, finalI, 0), 2, 3, 0,
                                5, Particle.EXPLOSION_EMITTER);
                    }

                    drawExpandingCircle(location.clone().add(0, finalI, 0), 5, 10, 0,
                            3, Particle.CAMPFIRE_SIGNAL_SMOKE);

                    if(finalI % 2 == 0) {
                        location.getWorld().createExplosion(location.clone().add(0, finalI, 0), radius, true, true);
                    }
                }, i * 2L);
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                drawExpandingCircle(location.clone().add(0, height, 0), 3, 10,
                        shockwaveExpandRate, 3, Particle.EXPLOSION_EMITTER);
                drawExpandingCircle(location.clone().add(0, height + 3, 0), 2, 10,
                        shockwaveExpandRate, 2, Particle.EXPLOSION_EMITTER);
                drawExpandingCircle(location.clone().add(0, height + 6, 0), 1, 10,
                        shockwaveExpandRate, 2, Particle.EXPLOSION_EMITTER);

                drawExpandingCircle(location.clone().add(0, height + 3, 0), 3, 10,
                        shockwaveExpandRate, 2, Particle.CAMPFIRE_SIGNAL_SMOKE);

                location.getWorld().createExplosion(location.clone().add(0, height, 0), radius, true, true);
            }, (height * 2L) + 3);

            for(int i = 0; i < radius; i += (shockwaveExpandRate * 2)) {
                int finalI = i;

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(location, finalI);

                    for(Entity entity : entities) {
                        if(entity instanceof Item || entity instanceof Projectile) {
                            entity.remove();
                        }

                        if(entity instanceof LivingEntity living) {
                            living.damage(damage, source);
                        }
                    }
                }, i * 2L);
            }

            for(int i = 0; i < radius * 2; i += shockwaveExpandRate) {
                int finalI = i;

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(location, finalI);

                    for(Entity entity : entities) {
                        if(entity instanceof LivingEntity living) {
                            applyDOT(source, living, 60, 8, 20);

                            ArrayList<Particle> particles = new ArrayList<>() {
                                {
                                    add(Particle.HAPPY_VILLAGER);
                                    add(Particle.ASH);
                                    add(Particle.TOTEM_OF_UNDYING);
                                }
                            };

                            drawExpandingCircle(entity, 1.2, 8, 0, 17, particles);
                        }
                    }
                }, i * 2L);
            }

            drawExpandingCircle(location, 4, 10, (double) radius / 10, 3, Particle.EXPLOSION_EMITTER);
            drawExpandingCircle(location, 4, 10, (double) radius / 10, 3, Particle.CAMPFIRE_SIGNAL_SMOKE);
            location.getWorld().createExplosion(location, radius * 1.33f, true, true);
        }, 3);
    }

    /**
     * Removes all Critical Hit related
     * meta for all players
     */
    public static void resetCritsMetadata() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.removeMetadata("AllCrits", plugin);
            player.removeMetadata("SuperCrits", plugin);
            player.removeMetadata("MiniCrits", plugin);
            player.removeMetadata("NoCrits", plugin);
        }
    }

    /**
     * Resets the player's attributes
     *
     * @param player: The player
     */
    public static void resetAttributes(Player player) {
        player.getAttribute(Attribute.SCALE).setBaseValue(1);
        player.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE).setBaseValue(4.5);
        player.getAttribute(Attribute.ENTITY_INTERACTION_RANGE).setBaseValue(3);
        player.getAttribute(Attribute.ATTACK_KNOCKBACK).setBaseValue(0);
        player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.JUMP_STRENGTH).setBaseValue(0.42);
        player.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1);
        player.getAttribute(Attribute.STEP_HEIGHT).setBaseValue(0.6);
    }

    /**
     * Removes all monsters
     */
    public static void removeMonsters() {
        for(World world : Bukkit.getWorlds()) {
            for(Enemy monster : world.getEntitiesByClass(Enemy.class)) {
                monster.remove();
            }
        }
    }

    /**
     * Removes all text displays
     */
    public static void removeTextDisplays() {
        for(World world : Bukkit.getWorlds()) {
            for(TextDisplay display : world.getEntitiesByClass(TextDisplay.class)) {
                display.remove();
            }
        }
    }

    /**
     * Sets server game rules
     */
    public static void setGamerules() {
        List<World> worlds = Bukkit.getWorlds();

        for (World world : worlds) {
            world.setDifficulty(Difficulty.HARD);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            world.setGameRule(GameRule.MOB_GRIEFING, true);

            if(Bukkit.getOnlinePlayers().size() == 1) {
                world.setTime(15000);
            }

            world.getWorldBorder().setSize(20000);
        }
    }

    /**
     * Rotate Vector by angle
     *
     * @param vector: Vector to rotate
     * @param angle: The angle to rotate vector by (in radians)
     * @return The rotated vector
     */
    public static Vector rotateVector(Vector vector, double angle) {
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;

        return vector.setX(x).setZ(z);
    }

    /**
     * Fix capital lettering and underscores
     *
     * @param s: The string to correct
     * @return A corrected string
     */
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

    /**
     * Calculate distance between
     * two points
     *
     * @param point1: A point
     * @param point2: A point
     * @return Distance between point1 and point2
     */
    public static double distanceBetween(@NotNull Vector point1, @NotNull Vector point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2)
                + Math.pow(point2.getY() - point1.getY(), 2)
                + Math.pow(point2.getZ() - point1.getZ(), 2));
    }

    /**
     * Calculate distance between two
     * entities
     *
     * @param entity: An entity
     * @param entity2: An entity
     * @return Distance between entity1 and entity2
     */
    public static double distanceBetween(@NotNull Entity entity, @NotNull Entity entity2) {
        return entity.getLocation().distance(entity2.getLocation());
    }

    /**
     * Calculate distance between two
     * locations
     *
     * @param loc: A location
     * @param loc2: A location
     * @return Distance between loc and loc2
     */
    public static double distanceBetween(@NotNull Location loc, @NotNull Location loc2) {
        return loc.distance(loc2);
    }

    /**
     * Add color to string using "&"
     * character
     *
     * @param string: A string to color
     * @return A colored string
     */
    @Contract("_ -> new")
    public static @NotNull String color(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Finds if a raid is occurring
     *
     * @return If a raid is active
     */
    public static boolean raidsActive() {
        for(World world : Bukkit.getServer().getWorlds()) {
            if(!world.getRaids().isEmpty() && !world.getPlayers().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds if the dragon is alive
     *
     * @return If the dragon is alive
     */
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

    /**
     * Finds out if a Wither is alive
     *
     * @return If a Wither is alive
     */
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

    /**
     * Draws a circle of particles
     *
     * @param center: Center of the circle
     * @param size: Circle radius
     * @param particle: The particle to draw with
     * @param iterations: Number of particles to make the circle
     */
    public static void drawCircle(Location center, double size, Particle particle, int iterations) {
        for (int i = 0; i <= iterations; i++) {
            Location particleLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
            particleLoc.setX(center.getX() + Math.cos(i) * size);
            particleLoc.setZ(center.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            center.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08, null, true);
        }
    }

    public static void drawDustCircle(Location center, double size, Particle.DustOptions options) {
        for (int i = 0; i <= 180; i++) {
            Location particleLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
            particleLoc.setX(center.getX() + Math.cos(i) * size);
            particleLoc.setZ(center.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            center.getWorld().spawnParticle(Particle.DUST, particleLoc, 1, 0.02, 0.02, 0.02, 1, options, true);
        }
    }

    /**
     * Draws a circle of particles
     *
     * @param center: Center of the circle
     * @param size: Circle radius
     * @param particles: The particles to draw with
     * @param iterations: Number of particles to make the circle
     */
    public static void drawCircle(Location center, double size, ArrayList<Particle> particles, int iterations) {
        for (int i = 0; i <= iterations; i++) {
            Location particleLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
            particleLoc.setX(center.getX() + Math.cos(i) * size);
            particleLoc.setZ(center.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            for(Particle particle : particles) {
                center.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08, null, true);
            }
        }
    }

    /**
     * Draws a circle of particles around
     * an entity
     *
     * @param entity: Entity at the center of the circle
     * @param size: Circle radius
     * @param particle: The particle to draw with
     * @param iterations: Number of particles to make the circle
     */
    public static void drawCircle(Entity entity, double size, Particle particle, int iterations) {
        for (int i = 0; i <= iterations; i++) {
            Location particleLoc = new Location(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ());
            particleLoc.setX(entity.getX() + Math.cos(i) * size);
            particleLoc.setZ(entity.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            entity.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08, null, true);
        }
    }

    /**
     * Draws a circle of particles around
     * an entity
     *
     * @param entity: Entity at the center of the circle
     * @param size: Circle radius
     * @param particles: The particles to draw with
     * @param iterations: Number of particles to make the circle
     */
    public static void drawCircle(Entity entity, double size, ArrayList<Particle> particles, int iterations) {
        for (int i = 0; i <= iterations; i++) {
            Location particleLoc = new Location(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ());
            particleLoc.setX(entity.getX() + Math.cos(i) * size);
            particleLoc.setZ(entity.getZ() + Math.sin(i) * size);

            findAir(particleLoc);

            for(Particle particle : particles) {
                entity.getWorld().spawnParticle(particle, particleLoc, 1, 0.02, 0.02, 0.02, 0.08, null, true);
            }
        }
    }

    /**
     * Go upward until air is found
     * or up a maximum of 10 blocks
     *
     * @param loc: Starting location
     */
    public static void findAir(@NotNull Location loc) {
        int i = 0;
        while(!(loc.getWorld().getBlockAt(loc).isPassable() || loc.getWorld().getBlockAt(loc).isEmpty()) && i < 10) {
            loc.setY(loc.getY() + 1);
            i++;
        }
    }

    /**
     * Go downward until solid ground
     * is found
     *
     * @param loc: Starting location
     */
    public static void findGround(@NotNull Location loc) {
        while((loc.getWorld().getBlockAt(loc).isPassable() || loc.getWorld().getBlockAt(loc).isEmpty())) {
            loc.setY(loc.getY() - 1);
        }
        loc.add(new Vector(0, 1, 0));
    }

    /**
     * Find entities near an entity
     *
     * @param entity: The entity at the center to search from
     * @param searchDist: The radius to search
     * @return A list of nearby entities
     */
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

    /**
     * Find entities near a location
     *
     * @param loc: The location at the center to search from
     * @param searchDist: The radius to search
     * @return A list of nearby entities
     */
    public static @NotNull List<Entity> circularNearbyEntities(@NotNull Location loc, double searchDist) {
        List<Entity> entities = new ArrayList<>();
        for(Entity entity1 : loc.getWorld().getEntities()) {
            if(distanceBetween(entity1.getLocation().toVector(), loc.toVector()) <= searchDist) {
                entities.add(entity1);
            }
        }

        return entities;
    }

    /**
     * Find living entities near an entity
     *
     * @param entity: The entity at the center to search from
     * @param searchDist: The radius to search
     * @return A list of nearby living entities
     */
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

    /**
     * Find entities near a location
     *
     * @param loc: The location at the center to search from
     * @param searchDist: The radius to search
     * @return A list of nearby living entities
     */
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

    /**
     * Face an entity to another
     *
     * @param entity: The entity
     * @param faceTarget: The entity to look at
     */
    public static void faceEntityToAnother(@NotNull Entity entity, @NotNull Entity faceTarget) {
        Location location = entity.getLocation();
        location.setDirection(faceTarget.getLocation().toVector().subtract(entity.getLocation().toVector()));
        entity.teleport(location);
    }

    /**
     * Sets an entity to look at another
     *
     * @param entity: The entity
     * @param faceTarget: The entity to look at
     */
    public static void setHeadRotationToFaceEntity(@NotNull Entity entity, @NotNull Entity faceTarget) {
        Vector direction = faceTarget.getLocation().toVector().subtract(entity.getLocation().toVector());

        float yaw = (float) Math.toDegrees(Math.atan2(direction.getY(), direction.getX()));
        float XYDistance = (float) Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2));
        float pitch = (float) Math.toDegrees(Math.atan2(direction.getZ(), XYDistance));

        entity.setRotation(yaw, pitch);
    }

    /**
     * Determine if the event timer should be active
     *
     * @return If the event timer should be active
     */
    public static boolean eventCountActive() {
        return !(raidsActive() || dragonActive() || witherActive() || WorldModInit.getActiveModifier() == WorldModifier.CALM_BEFORE_THE_STORM)
                && !Bukkit.getOnlinePlayers().isEmpty();
    }

    /**
     * Stun an entity
     *
     * @param entity: The entity to stun
     * @param duration: The duration of the stun
     * @param showParticles: If particles should be shown
     */
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
                    entity.getWorld().spawnParticle(Particle.EXPLOSION, entity.getEyeLocation()
                            .add(entity.getEyeLocation().getDirection().normalize()), 5, 0.01, 0.01, 0.01, 0.05);
                }, i);
            }
        }

        if(entity instanceof Monster) {
            Monster monster = (Monster) entity;
            monster.setTarget(null);
        }

        entity.setVelocity(new Vector(0, 0, 0));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, (int) duration, 6, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, (int) duration, 255, false, false, false));
    }

    /**
     * Calculate multiplier to reduce
     * a stun
     *
     * @param entity: The stunned entity
     * @return The factor to reduce the stun duration by
     */
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

    /**
     * All hostile entity types
     *
     * @return A list of all hostile entity types
     */
    public static @NotNull ArrayList<EntityType> hostiles() {
        ArrayList<EntityType> hostile = new ArrayList<>();

        for(World world : Bukkit.getWorlds()) {
            world.getEntitiesByClass(Enemy.class).forEach((x) -> hostile.add(x.getType()));
        }

        hostile.addAll(bosses());
        return hostile;
    }

    /**
     * All bosses
     *
     * @return A list of all bosses
     */
    public static @NotNull ArrayList<EntityType> bosses() {
        ArrayList<EntityType> bosses = new ArrayList<>();
        bosses.add(EntityType.ENDER_DRAGON);
        bosses.add(EntityType.WITHER);

        return bosses;
    }

    /**
     * Player entity type
     *
     * @return A list containing the player entity type
     */
    @Contract(" -> new")
    public static @NotNull ArrayList<EntityType> players() {
        return new ArrayList<>() {
            {
                add(EntityType.PLAYER);
            }
        };
    }

    /**
     * All living entity types
     *
     * @return A list of all living entity types
     */
    public static @NotNull ArrayList<EntityType> any() {
        ArrayList<EntityType> all = new ArrayList<>(List.of(EntityType.values()));
        all.removeIf(x -> !x.isAlive());

        return all;
    }

    /**
     * All living entity types except
     * for player
     *
     * @return All living entity types except player
     */
    public static @NotNull ArrayList<EntityType> allExceptPlayers() {
        ArrayList<EntityType> all = new ArrayList<>(List.of(EntityType.values()));
        all.remove(EntityType.PLAYER);
        all.removeIf(x -> !x.isAlive());

        return all;
    }

    /**
     * Give an entity a projectile shield
     *
     * @param entity: The entity to shield
     * @param duration: The shield duration
     * @param deflectEffect: The effect to show when a projectile is destroyed
     * @param aura: The appearance of the shield
     * @param radius: The radius of the shield
     */
    public static void giveProjectileShield(@NotNull LivingEntity entity, int duration, ArrayList<Particle> deflectEffect, ArrayList<Particle> aura, int radius) {
        entity.setMetadata("ProjShield", new ProjectileShieldMetadata(deflectEffect, aura, radius));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.removeMetadata("ProjShield", InfiniteFunProject.plugin), duration);
    }

    /**
     * Create a ray
     *
     * @param entity: The entity to start from
     * @param range: The length of the ray
     * @param teleport: If the entity should teleport to the endpoint
     * @param particles: The appearance of the ray
     * @return A list of entities the ray hit
     */
    public static @NotNull ArrayList<LivingEntity> createRay(@NotNull Entity entity, int range, boolean teleport, ArrayList<Particle> particles) {
        Location shootLoc = entity.getLocation();
        Location entityLoc = entity.getLocation();
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

    /**
     * Create a ray
     *
     * @param entity: The entity to start from
     * @param range: The length of the ray
     * @param teleport: If the entity should teleport to the endpoint
     * @param particles: The appearance of the ray
     * @return A list of entities the ray hit
     */
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

    /**
     * Create a ray
     *
     * @param entity: The entity to start from
     * @param range: The length of the ray
     * @param damageRadius: The radius where the ray can deal damage
     * @param teleport: If the entity should teleport to the endpoint
     * @param particles: The appearance of the ray
     * @return A list of entities the ray hit
     */
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

    /**
     * Creates a ray that damages entities
     *
     * @param entity: The entity to start at
     * @param range: The length of the ray
     * @param damage: The ray's damage
     * @param pierce: The number of entities the ray can hit
     * @param pierceShield: If the ray pierces projectile shields
     * @param destroyShield: If the ray destroys projectile shields
     * @param throughBlocks: If the ray travels through blocks
     * @param particles: The appearance of the ray
     * @param headShots: If the ray can deal headshots for 2x damage
     * @return A HashMap of entities hit by the ray and if they were headshot
     */
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

                            if(((LivingEntity) e).hasPotionEffect(PotionEffectType.RESISTANCE) && (pierceShield || destroyShield)) {
                                ((LivingEntity) e).removePotionEffect(PotionEffectType.RESISTANCE);
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

    /**
     * Creates a ray that damages entities that
     * has additional direction
     *
     * @param entity: The entity to start at
     * @param variance: The maximum possible rotation of the ray
     * @param range: The length of the ray
     * @param damage: The ray's damage
     * @param pierce: The number of entities the ray can hit
     * @param pierceShield: If the ray pierces projectile shields
     * @param destroyShield: If the ray destroys projectile shields
     * @param throughBlocks: If the ray travels through blocks
     * @param particles: The appearance of the ray
     * @param headShots: If the ray can deal headshots for 2x damage
     * @return A HashMap of entities hit by the ray and if they were headshot
     */
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

                            if(((LivingEntity) e).hasPotionEffect(PotionEffectType.RESISTANCE) && (pierceShield || destroyShield)) {
                                ((LivingEntity) e).removePotionEffect(PotionEffectType.RESISTANCE);
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

    /**
     * Determines if two vectors are
     * facing a similar direction
     *
     * @param v1: A Vector
     * @param v2: A vector
     * @return If the dot product of v1 and v2 is >= 0.8
     */
    public static boolean facingSameWay(@NotNull Vector v1, @NotNull Vector v2) {
        return v1.dot(v2) >= 0.8;
    }

    /**
     * Determines if two entities are
     * facing a similar direction
     *
     * @param entity: An entity
     * @param entity2: An entity
     * @return If the dot product of entity and entity2's directions is >= 0.8
     */
    public static boolean facingSameWay(@NotNull Entity entity, @NotNull Entity entity2) {
        Vector entity1Direction = entity.getLocation().getDirection();
        Vector entity2Direction = entity2.getLocation().getDirection();

        return facingSameWay(entity1Direction, entity2Direction);
    }

    /**
     * Make an entity invisible
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void makeInvisible(@NotNull LivingEntity entity, int duration) {
        entity.setVisibleByDefault(false);
        entity.setInvisible(true);
        entity.setInvulnerable(true);

        entity.getWorld().spawnParticle(Particle.SMOKE, entity.getLocation(),
                10, 0.25, 0.25, 0.25, 0.03);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            entity.setVisibleByDefault(true);
            entity.setInvisible(false);
            entity.setInvulnerable(false);

            entity.getWorld().spawnParticle(Particle.SMOKE, entity.getLocation(),
                    10, 0.25, 0.25, 0.25, 0.03);
        }, duration);
    }

    /**
     * Give an entity damage tax exemption
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void taxEvade(@NotNull LivingEntity entity, int duration, boolean message) {
        if(entity.hasMetadata("TaxExempt")) {
            entity.removeMetadata("TaxExempt", plugin);
        }

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

    /**
     * Returns if a number is within
     * a certain range of two numbers
     *
     * @param number: A number
     * @param other: A number
     * @param range: Maximum difference between number and other
     * @return If number and other are within range of each other
     */
    public static boolean withinBound(double number, double other, double range) {
        return number - range <= other && number + range >= other;
    }

    /**
     * Set an enemy as having been hit
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void setHasBeenHit(@NotNull LivingEntity entity, int duration) {
        entity.setMetadata("BeenHit", new PreventMutiHit());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.removeMetadata("BeenHit", InfiniteFunProject.plugin), duration);
    }

    /**
     * Draws an expanding circle
     *
     * @param location: The center location
     * @param startRadius: The starting radius
     * @param expansions: The number of times the circle expands
     * @param expandAmount: The amount the circle expands
     * @param delay: The time between expansions
     * @param particles: The look of the circle
     */
    public static void drawExpandingCircle(Location location, double startRadius, double expansions, double expandAmount, int delay, ArrayList<Particle> particles) {
        for(int i = 0; i < expansions; i++) {
            int finalI = i;
            if (i < 5) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(location, startRadius + (finalI * expandAmount), particles, 45), (long) i * delay);
            } else {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                    drawCircle(location, startRadius + (finalI * expandAmount), particles, 90), (long) i * delay);
            }
        }
    }

    /**
     * Draws an expanding circle
     *
     * @param location: The center location
     * @param startRadius: The starting radius
     * @param expansions: The number of times the circle expands
     * @param expandAmount: The amount the circle expands
     * @param delay: The time between expansions
     * @param particle: The look of the circle
     */
    public static void drawExpandingCircle(Location location, double startRadius, double expansions, double expandAmount, int delay, Particle particle) {
        for(int i = 0; i < expansions; i++) {
            int finalI = i;
            if(i < 5) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(location, startRadius + (finalI * expandAmount), particle, 45), (long) i * delay);
            } else {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(location, startRadius + (finalI * expandAmount), particle, 90), (long) i * delay);
            }
        }
    }

    /**
     * Draws an expanding circle around
     * an entity
     *
     * @param entity: The center entity
     * @param startRadius: The starting radius
     * @param expansions: The number of times the circle expands
     * @param expandAmount: The amount the circle expands
     * @param delay: The time between expansions
     * @param particles: The look of the circle
     */
    public static void drawExpandingCircle(Entity entity, double startRadius, double expansions, double expandAmount, int delay, ArrayList<Particle> particles) {
        for(int i = 0; i < expansions; i++) {
            int finalI = i;

            if(entity.isDead()) {
                return;
            }

            if(i < 5) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(entity.getLocation(), startRadius + (finalI * expandAmount), particles, 45), (long) i * delay);
            } else {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(entity.getLocation(), startRadius + (finalI * expandAmount), particles, 90), (long) i * delay);
            }
        }
    }

    /**
     * Draws an expanding circle around
     * an entity
     *
     * @param entity: The center entity
     * @param startRadius: The starting radius
     * @param expansions: The number of times the circle expands
     * @param expandAmount: The amount the circle expands
     * @param delay: The time between expansions
     * @param particle: The look of the circle
     */
    public static void drawExpandingCircle(Entity entity, double startRadius, double expansions, double expandAmount, int delay, Particle particle) {
        for(int i = 0; i < expansions; i++) {
            int finalI = i;

            if(entity.isDead()) {
                return;
            }

            if(i < 5) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(entity.getLocation(), startRadius + (finalI * expandAmount), particle, 45), (long) i * delay);
            } else {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                        drawCircle(entity.getLocation(), startRadius + (finalI * expandAmount), particle, 90), (long) i * delay);
            }
        }
    }

    /**
     * Set an enemy to glow
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void glow(@NotNull Entity entity, int duration) {
        entity.setGlowing(true);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(!entity.isDead()) {
                entity.setGlowing(false);
            }
        }, duration);
    }

    /**
     * Apply damage ove time to
     * an entity. Only one effect
     * can be active at once
     *
     * @param damager: The entity applying the DOT
     * @param target: The enemy taking DOT
     * @param damage: Amount of damage per tick of DOT
     * @param ticks: The number of ticks of DOT to deal
     * @param delay: The delay between each tick of DOT
     */
    public static void applyDOT(LivingEntity damager, LivingEntity target, double damage, int ticks, int delay) {
        if(!target.hasMetadata("DOT")) {
            target.setMetadata("DOT", new DamageOverTime());

            for(int i = 1; i <= ticks; i++) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(!target.isDead()) {
                        target.damage(damage, damager);
                    }
                }, (long) i * delay);
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> target.removeMetadata("DOT", InfiniteFunProject.plugin), (long) ticks * delay);
        }
    }

    /**
     * Set an enemy on fire
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void setFire(LivingEntity entity, int duration) {
        entity.setVisualFire(true);
        entity.setFireTicks(duration);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.setVisualFire(false), duration);
    }

    /**
     * Removes all projectiles
     */
    public static void killProjectiles() {
        for(World world : Bukkit.getWorlds()) {
            for(Projectile projectile : world.getEntitiesByClass(Projectile.class)) {
                projectile.remove();
            }
        }
    }

    /**
     * Creates a villager trade
     *
     * @param result: The resulting item
     * @param uses: The maximum times the trade can be
     *            performed before restocking
     * @param ingredients: The items being traded for result
     * @return The villager trade
     */
    public static MerchantRecipe createTrade(ItemStack result, int uses, ArrayList<ItemStack> ingredients) {
        MerchantRecipe recipe = new MerchantRecipe(result, uses);

        for(ItemStack ingredient : ingredients) {
            recipe.addIngredient(ingredient);
        }

        return recipe;
    }

    /**
     * Creates a villager trade
     *
     * @param result: The resulting item
     * @param uses: The maximum times the trade can be
     *            performed before restocking
     * @param ingredients: The materials being traded for result
     *                   and the quantities of each
     * @return The villager trade
     */
    public static MerchantRecipe createTrade(ItemStack result, int uses, HashMap<Material, Integer> ingredients) {
        MerchantRecipe recipe = new MerchantRecipe(result, uses);

        for(Material material : ingredients.keySet()) {
            recipe.addIngredient(new ItemStack(material, ingredients.get(material)));
        }

        return recipe;
    }

    /**
     * Gives a player extra
     * guaranteed multi hits
     *
     * @param player: The player to buff
     * @param hits: The number of guaranteed hits to give
     * @param duration: Duration of the effect
     */
    public static void giveMultiHits(Player player, int hits, int duration) {
        player.setMetadata("AddMultiHits", new GiveBonusMultiHits(hits));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> player.removeMetadata("AddMultiHits", InfiniteFunProject.plugin), duration);
    }

    /**
     * Creates a particle trail for a
     * projectile
     *
     * @param projectile: The projectile
     * @param particles: The particles to use
     */
    public static void addProjectileTrail(Projectile projectile, ArrayList<Particle> particles) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(projectile.isDead() || projectile.isOnGround()) {
                    cancel();
                    return;
                }

                for(Particle p : particles) {
                    projectile.getWorld().spawnParticle(p, projectile.getLocation(),
                            4, 0.2, 0.2, 0.2, 0.05, null, true);
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    /**
     * Creates a particle trail for a
     * projectile
     *
     * @param projectile: The projectile
     * @param unusual: The unusual containing effects
     */
    public static void addProjectileTrail(Projectile projectile, Unusual unusual) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(projectile.isDead() || projectile.isOnGround()) {
                    cancel();
                    return;
                }

                Location spawnLoc = projectile.getLocation();

                for(Particle particle : unusual.getParticles()) {
                    if(particle == Particle.DUST) {
                        if(unusual.getDustOptions() != null) {
                            projectile.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.05,
                                    unusual.getDustOptions(), true);
                            continue;
                        }
                    }

                    projectile.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.05, null, true);
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    /**
     * Creates a particle trail for a
     * projectile
     *
     * @param projectile: The projectile
     * @param particle: The particle to use
     */
    public static void addProjectileTrail(Projectile projectile, Particle particle) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(projectile.isDead() || projectile.isOnGround()) {
                    cancel();
                    return;
                }

                projectile.getWorld().spawnParticle(particle, projectile.getLocation(),
                        4, 0.2, 0.2, 0.2, 0.05, null, true);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    /**
     * Removes all items on
     * the server
     */
    public static void removeItems() {
        for(World world : Bukkit.getWorlds()) {
            for(Item item : world.getEntitiesByClass(Item.class)) {
                item.remove();
            }
        }
    }

    /**
     * Returns if an array contains an
     * item
     *
     * @param list: The array to search
     * @param item: The item to search for
     * @return If item is in list
     * @param <T> The type of the item
     */
    @Contract(pure = true)
    public static <T> boolean contains(T[] list, T item) {
        for(T x : list) {
            if(x == null) {
                continue;
            }

            if(x.equals(item)) {
                return true;
            }
        }

        return false;
    }
}
