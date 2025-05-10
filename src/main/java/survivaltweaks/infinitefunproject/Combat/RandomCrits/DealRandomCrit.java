package survivaltweaks.infinitefunproject.Combat.RandomCrits;

import org.bukkit.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.AlwaysCritMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.MiniCritsMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.NoCritsMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.SuperCritMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class DealRandomCrit implements Listener {

    /**
     * Handles Critical Hits
     *
     * @param event: Entity damaged by entity event
     */
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        EntityDamageEvent.DamageCause cause = event.getCause();

        ItemStack item = null;

        if(cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
            return;
        }

        if(damaged instanceof LivingEntity) {
            if(damager instanceof Projectile) {
                if(((Projectile) damager).getShooter() instanceof Player) {
                    Player player = (Player) ((Projectile) damager).getShooter();
                    item = player.getInventory().getItemInMainHand();
                }
            }

            if(damager instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) damager;
                item = entity.getEquipment().getItemInMainHand();
            }

            if(damager instanceof Projectile) {
                Projectile projectile = (Projectile) damager;

                if(projectile.getShooter() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) projectile.getShooter();
                    item = entity.getEquipment().getItemInMainHand();
                }
            }

            if(noCrits(item)) {
                return;
            }

            if(damager instanceof Projectile) {
                Projectile projectile = (Projectile) damager;
                damager = (LivingEntity) projectile.getShooter();
            }

            if(isSuperCrit(event)) {
                doSuperCrit(event);
            } else if(isCrit(event)) {
                if(damaged instanceof Wither || damaged instanceof EnderDragon || onlyMiniCrits(item) || damager.hasMetadata("MiniCrits")) {
                    doMiniCrit(event);
                } else {
                    doCrit(event);
                }
            }
        }
    }

    /**
     * Determines if a Super Crit should
     * be dealt
     *
     * @param event: Entity damaged by entity event
     * @return If event should be a Super Crit
     */
    public boolean isSuperCrit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;

            if(projectile.getShooter() instanceof LivingEntity) {
                damager = (LivingEntity) projectile.getShooter();
            }
        }

        if(damager instanceof LivingEntity) {
            ItemStack item = ((LivingEntity) damager).getEquipment().getItem(EquipmentSlot.HAND);

            return isCrit(event) && (superCrits(item) || damager.hasMetadata("SuperCrits"));
        }

        return false;
    }

    /**
     * Determines if a Crit should
     * be dealt
     *
     * @param event: Entity damaged by entity event
     * @return If event should be a Crit
     */
    public boolean isCrit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;

            if(projectile.getShooter() instanceof LivingEntity) {
                damager = (LivingEntity) projectile.getShooter();
            }
        }

        if(damager instanceof LivingEntity) {
            ItemStack item = ((LivingEntity) damager).getEquipment().getItem(EquipmentSlot.HAND);

            if(onlyMiniCrits(item) && !damager.hasMetadata("AllCrits")) {
                return false;
            }

            if(noCrits(item) && !damager.hasMetadata("AllCrits")) {
                return false;
            }

            double damage = event.getDamage();
            double chance = chance(damage);

            return (new Random().nextDouble(0, 100) < chance || WorldModInit.modifierActive(WorldModifier.CRIT_FRENZY)
                    || WorldModInit.modifierActive(WorldModifier.ANOMALOUS)
                    || alwaysCrits(item) || damager.hasMetadata("AllCrits") || damager.hasMetadata("MiniCrits"))
                    && !(WorldModInit.modifierActive(WorldModifier.DAMAGE_BARGAIN) ||
                    damager.hasMetadata("NoCrits"));
        }

        return false;
    }

    /*
    @Deprecated
    @EventHandler
    public void projectileCrit(ProjectileLaunchEvent event) {
        if(event.getEntity().getShooter() instanceof LivingEntity) {
            Projectile projectile = event.getEntity();
            LivingEntity shooter = ((LivingEntity) event.getEntity().getShooter());
            ItemStack item = shooter.getEquipment().getItem(EquipmentSlot.HAND);

            double damage = projectile.getVelocity().multiply(3.2).length();
            double chance = chance(damage);

            if((new Random().nextDouble(0, 100) < chance || WorldModInit.modifierActive(WorldModifier.CRIT_FRENZY)
            || WorldModInit.modifierActive(WorldModifier.ANOMALOUS)
            || alwaysCrits(item) || shooter.hasMetadata("AllCrits") || shooter.hasMetadata("MiniCrits"))
            && !(WorldModInit.modifierActive(WorldModifier.DAMAGE_BARGAIN) ||
                    shooter.hasMetadata("NoCrits"))) {
                if(projectile instanceof AbstractArrow) {
                    AbstractArrow arrow = (AbstractArrow) projectile;
                    projectile.setMetadata("EntityCrit", new CritProjectileMeta());
                    arrow.setCritical(true);
                } else {
                    projectile.setMetadata("EntityCrit", new CritProjectileMeta());
                }
            }
        }
    }
    */

    /**
     * Perform a Critical Hit
     *
     * @param event: The event to Crit
     */
    public static void doCrit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        Location spawnLoc;

        if(new Random().nextInt(0, 2) == 1) {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() + new Random().nextDouble(0.1, 0.25),
                    damaged.getLocation().getY() + 0.4 + new Random().nextDouble(0.1, 0.8),
                    damaged.getLocation().getZ() + new Random().nextDouble(0.1, 0.25));
        } else {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getY() + 0.4 - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getZ() - new Random().nextDouble(0, 0.45));
        }

        event.setDamage(event.getDamage() * 3);

        TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);

        drawCircle(damaged.getLocation().add(0, 1, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation().add(0, 2, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation(), 0.5, Particle.CRIT, 45);

        display.setGravity(false);
        display.setInvulnerable(true);
        display.setBillboard(Display.Billboard.CENTER);
        display.setText(color("&a&lCRITICAL HIT!"));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, display::remove, 16);

        stun((LivingEntity) damaged, 30, false);
        if(damaged instanceof Player) {
            if(new Random().nextBoolean()) {
                ((Player) damaged).chat("random crits are fair and balanced");
            }
        }
    }

    /**
     * Perform a Super Critical Hit
     *
     * @param event: The event to Super Crit
     */
    public static void doSuperCrit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        Location spawnLoc;

        if(new Random().nextInt(0, 2) == 1) {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() + new Random().nextDouble(0.1, 0.25),
                    damaged.getLocation().getY() + 0.4 + new Random().nextDouble(0.1, 0.8),
                    damaged.getLocation().getZ() + new Random().nextDouble(0.1, 0.25));
        } else {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getY() + 0.4 - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getZ() - new Random().nextDouble(0, 0.45));
        }

        event.setDamage(event.getDamage() * 5);

        TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);

        drawCircle(damaged.getLocation().add(0, 1, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation().add(0, 2, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation(), 0.5, Particle.CRIT, 45);

        display.setGravity(false);
        display.setInvulnerable(true);
        display.setBillboard(Display.Billboard.CENTER);
        display.setText(color("&5&lSUPER CRITICAL HIT!"));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, display::remove, 16);

        stun((LivingEntity) damaged, 60, false);
    }

    /**
     * Perform a Mini Critical Hit
     *
     * @param event: The event to Mini Crit
     */
    public static void doMiniCrit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        Location spawnLoc;

        if(new Random().nextInt(0, 2) == 1) {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() + new Random().nextDouble(0.1, 0.25),
                    damaged.getLocation().getY() + 0.4 + new Random().nextDouble(0.1, 0.8),
                    damaged.getLocation().getZ() + new Random().nextDouble(0.1, 0.25));
        } else {
            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getY() + 0.4 - new Random().nextDouble(0, 0.45),
                    damaged.getLocation().getZ() - new Random().nextDouble(0, 0.45));
        }

        event.setDamage(event.getDamage() * 1.35);

        TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);

        drawCircle(damaged.getLocation().add(0, 1, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation().add(0, 2, 0), 0.5, Particle.CRIT, 45);
        drawCircle(damaged.getLocation(), 0.5, Particle.CRIT, 45);

        display.setGravity(false);
        display.setInvulnerable(true);
        display.setBillboard(Display.Billboard.CENTER);
        display.setText(color("&e&lMINI CRIT!"));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, display::remove, 16);
    }

    /**
     * Calculates Crit chance base
     * on damage
     *
     * @param damage: The damage
     * @return The chance for a Critical Hit
     */
    public static double chance(double damage) {
        return Math.max((100 * Math.pow(0.9945, 4.2 * damage)) - 10, 0);
    }

    /**
     * If an item is ineligible for Critical
     * Hits
     *
     * @param itemStack: An item stack
     * @return If itemStack cannot Crit
     */
    public static boolean noCrits(ItemStack itemStack) {
        if(itemStack == null) {
            return false;
        }

        ItemMeta meta = itemStack.getItemMeta();

        if(meta == null) {
            return false;
        }
        if(!meta.hasLore()) {
            return false;
        }
        if(meta.hasLore()) {
            return meta.getLore().contains(color("&cNo Critical Hits or Mini Crits"));
        }
        return false;
    }

    public static boolean onlyMiniCrits(ItemStack itemStack) {
        if(itemStack == null) {
            return true;
        }

        ItemMeta meta = itemStack.getItemMeta();

        if(meta == null && itemStack.getMaxStackSize() != 1) {
            return true;
        }
        if(!meta.hasLore() && itemStack.getMaxStackSize() == 1) {
            return false;
        }
        if(meta.hasLore()) {
            return meta.getLore().contains(color("&cCritical Hits become Mini Crits"));
        }
        return true;
    }

    /**
     * If an item is guaranteed to Critical
     * Hit
     *
     * @param itemStack: An item stack
     * @return If itemStack always Crits
     */
    public static boolean alwaysCrits(ItemStack itemStack) {
        if(itemStack == null || itemStack.getType().equals(Material.AIR)) {
            return false;
        }

        ItemMeta meta = itemStack.getItemMeta();

        if(meta == null && itemStack.getMaxStackSize() != 1) {
            return true;
        }
        if(!meta.hasLore() && itemStack.getMaxStackSize() == 1) {
            return false;
        }
        if(meta.hasLore()) {
            return meta.getLore().contains(color("&aGuaranteed Critical Hits"));
        }
        return true;
    }

    /**
     * If an item is eligible for Super Critical
     * Hits
     *
     * @param itemStack: An item stack
     * @return If itemStack can Super Crit
     */
    public static boolean superCrits(ItemStack itemStack) {
        if(itemStack == null || itemStack.getType().equals(Material.AIR)) {
            return false;
        }

        ItemMeta meta = itemStack.getItemMeta();

        if(meta == null && itemStack.getMaxStackSize() != 1) {
            return true;
        }
        if(!meta.hasLore() && itemStack.getMaxStackSize() == 1) {
            return false;
        }
        if(meta.hasLore()) {
            return meta.getLore().contains(color("&5Critical Hits are Enhanced"));
        }
        return true;
    }

    /**
     * Set an entity to deal guaranteed Super
     * Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void giveGuaranteedSuperCrits(LivingEntity entity, int duration) {
        giveCrits(entity, duration);
        entity.setMetadata("SuperCrits", new SuperCritMeta());
        entity.sendMessage(ChatColor.GREEN + "Your Crits are enhanced!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("SuperCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "Your Crits are no longer enhanced!");
        }, duration);
    }

    /**
     * Set an entity to deal Super Critical
     * Hits instead of Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void giveSuperCrits(LivingEntity entity, int duration) {
        entity.setMetadata("SuperCrits", new SuperCritMeta());
        entity.sendMessage(ChatColor.GREEN + "Your Crits are enhanced!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("SuperCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "Your Crits are no longer enhanced!");
        }, duration);
    }

    /**
     * Set an entity to deal guaranteed
     * Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void giveCrits(LivingEntity entity, int duration) {
        entity.setMetadata("AllCrits", new AlwaysCritMeta());
        entity.sendMessage(ChatColor.GREEN + "You are now Crit boosted!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("AllCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "You are no longer Crit boosted!");
        }, duration);
    }

    /**
     * Set an entity to deal guaranteed
     * Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     * @param message: Whether to show message
     */
    public static void giveCrits(LivingEntity entity, int duration, boolean message) {
        entity.setMetadata("AllCrits", new AlwaysCritMeta());

        if(message) {
            entity.sendMessage(ChatColor.GREEN + "You are now Crit boosted!");
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("AllCrits", plugin);

            if(message) {
                entity.sendMessage(ChatColor.YELLOW + "You are no longer Crit boosted!");
            }
        }, duration);
    }

    /**
     * Set an entity to deal guaranteed Mini
     * Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void giveMiniCrits(LivingEntity entity, int duration) {
        entity.setMetadata("MiniCrits", new MiniCritsMeta());
        entity.sendMessage(ChatColor.GREEN + "You are now Mini Crit Boosted!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("MiniCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "You are no longer Mini Crit boosted!");
        }, duration);
    }

    /**
     * Set an entity to be unable to dela
     * Critical Hits
     *
     * @param entity: The entity
     * @param duration: Duration of effect
     */
    public static void giveNoCrits(LivingEntity entity, int duration) {
        entity.setMetadata("NoCrits", new NoCritsMeta());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("NoCrits", plugin);
        }, duration);
    }
}
