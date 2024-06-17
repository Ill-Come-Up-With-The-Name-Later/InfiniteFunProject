package survivaltweaks.infinitefunproject.Combat.RandomCrits;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.AlwaysCritMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.CritProjectileMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.MiniCritsMeta;
import survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata.NoCritsMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class DealRandomCrit implements Listener {

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
                LivingEntity shooter = (LivingEntity) projectile.getShooter();

                if(projectile.hasMetadata("EntityCrit")) {
                    if(damaged instanceof Wither || damaged instanceof EnderDragon || onlyMiniCrits(item) || shooter.hasMetadata("MiniCrits")) {
                        event.setDamage(event.getDamage() * 1.35);
                        stun((LivingEntity) damaged, 15, false);
                    } else {
                        event.setDamage(event.getDamage() * 3);
                        stun((LivingEntity) damaged, 30, false);
                        if(damaged instanceof Player) {
                            if(new Random().nextBoolean()) {
                                ((Player) damaged).chat("random crits are fair and balanced");
                            }
                        }
                    }

                    if(event.getDamage() > 0) {
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

                        TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);
                        if(damaged instanceof Wither || damaged instanceof EnderDragon || onlyMiniCrits(item) || shooter.hasMetadata("MiniCrits")) {
                            display.setText(color("&e&lMINI CRIT!"));
                        } else {
                            display.setText(color("&a&lCRITICAL HIT!"));
                        }

                        drawCircle(damaged.getLocation().add(0, 1, 0), 0.5, Particle.CRIT, 45);
                        drawCircle(damaged.getLocation().add(0, 2, 0), 0.5, Particle.CRIT, 45);
                        drawCircle(damaged.getLocation(), 0.5, Particle.CRIT, 90);

                        display.setGravity(false);
                        display.setInvulnerable(true);
                        display.setBillboard(Display.Billboard.CENTER);

                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, display::remove, 16);
                    }
                }
                return;
            }

            if(isCrit(event)) {
                if(damaged instanceof Wither || damaged instanceof EnderDragon || onlyMiniCrits(item) || damager.hasMetadata("MiniCrits")) {
                    event.setDamage(event.getDamage() * 1.35);
                    stun((LivingEntity) damaged, 15, false);
                } else {
                    event.setDamage(event.getDamage() * 3);
                    stun((LivingEntity) damaged, 30, false);
                    if(damaged instanceof Player) {
                        if(new Random().nextBoolean()) {
                            ((Player) damaged).chat("random crits are fair and balanced");
                        }
                    }
                }

                if(event.getDamage() > 0) {
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

                    TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);
                    if(damaged instanceof Wither || damaged instanceof EnderDragon || onlyMiniCrits(item) || damager.hasMetadata("MiniCrits")) {
                        display.setText(color("&e&lMINI CRIT!"));
                    } else {
                        display.setText(color("&a&lCRITICAL HIT!"));
                    }

                    drawCircle(damaged.getLocation().add(0, 1, 0), 0.5, Particle.CRIT, 45);
                    drawCircle(damaged.getLocation().add(0, 2, 0), 0.5, Particle.CRIT, 45);
                    drawCircle(damaged.getLocation(), 0.5, Particle.CRIT, 45);

                    display.setGravity(false);
                    display.setInvulnerable(true);
                    display.setBillboard(Display.Billboard.CENTER);

                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, display::remove, 16);
                }
            }
        }
    }

    public boolean isCrit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager instanceof LivingEntity) {
            ItemStack item = ((LivingEntity) damager).getEquipment().getItem(EquipmentSlot.HAND);

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

    public static double chance(double damage) {
        return Math.max(100 * Math.pow(0.99, 2.5 * damage), 0);
    }

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

    public static void giveCrits(LivingEntity entity, int duration) {
        entity.setMetadata("AllCrits", new AlwaysCritMeta());
        entity.sendMessage(ChatColor.GREEN + "You are now Crit boosted!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("AllCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "You are no longer Crit boosted!");
        }, duration);
    }

    public static void giveMiniCrits(LivingEntity entity, int duration) {
        entity.setMetadata("MiniCrits", new MiniCritsMeta());
        entity.sendMessage(ChatColor.GREEN + "You are now Mini Crit Boosted!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("MiniCrits", plugin);
            entity.sendMessage(ChatColor.YELLOW + "You are no longer Mini Crit boosted!");
        }, duration);
    }

    public static void giveNoCrits(LivingEntity entity, int duration) {
        entity.setMetadata("NoCrits", new NoCritsMeta());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.removeMetadata("NoCrits", plugin);
        }, duration);
    }
}
