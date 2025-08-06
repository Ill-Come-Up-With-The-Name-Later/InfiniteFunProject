package survivaltweaks.infinitefunproject.EventListeners;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitScheduler;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class OnEntityDamaged implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(event.isCancelled()) {
            return;
        }

        if(!(damaged instanceof LivingEntity)) {
            return;
        }

        ArrayList<EntityType> ignore = new ArrayList<>();
        ignore.add(EntityType.ITEM_FRAME);
        ignore.add(EntityType.ITEM);
        ignore.add(EntityType.GLOW_ITEM_FRAME);
        ignore.add(EntityType.FIREBALL);
        ignore.add(EntityType.SMALL_FIREBALL);
        ignore.add(EntityType.DRAGON_FIREBALL);
        ignore.add(EntityType.FIREWORK_ROCKET);
        ignore.add(EntityType.FISHING_BOBBER);
        ignore.add(EntityType.ARMOR_STAND);
        ignore.add(EntityType.TEXT_DISPLAY);
        ignore.add(EntityType.ARROW);
        ignore.add(EntityType.SPECTRAL_ARROW);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.EGG);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.END_CRYSTAL);
        ignore.add(EntityType.SHULKER_BULLET);
        ignore.add(EntityType.BLOCK_DISPLAY);
        ignore.add(EntityType.ITEM_DISPLAY);
        ignore.add(EntityType.INTERACTION);
        ignore.add(EntityType.WIND_CHARGE);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            double damage = event.getFinalDamage();

            if(!ignore.contains(damaged.getType())  && event.getFinalDamage() > 0) {
                Location spawnLoc;

                spawnLoc = new Location(damaged.getWorld(),
                        damaged.getLocation().getX() + new Random().nextDouble(-0.45, 0.25),
                        damaged.getLocation().getY() + 0.4 + new Random().nextDouble(-0.45, 0.5),
                        damaged.getLocation().getZ() + new Random().nextDouble(-0.45, 0.45));

                TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);

                display.setCustomNameVisible(false);
                display.setGravity(false);
                display.setInvulnerable(true);
                display.setBillboard(Display.Billboard.CENTER);

                if(damage <= 1) {
                    display.setText(color("&7-" + String.format("%.2f", damage)));
                } else if(damage < 5) {
                    display.setText(color("&e-" + String.format("%.2f", damage)));
                } else if(damage < 10) {
                    display.setText(color("&6-" + String.format("%.2f", damage)));
                } else if(damage < 15) {
                    display.setText(color("&c-" + String.format("%.2f", damage)));
                } else if(damage < 20) {
                    display.setText(color("&4-" + String.format("%.2f", damage)));
                } else {
                    display.setText(color("&5-" + String.format("%.2f", damage)));
                }

                display.setCustomNameVisible(false);

                /**
                Player shooter;
                if(event.getDamager().getType().equals(EntityType.ARROW) || event.getDamager().getType().equals(EntityType.TRIDENT) ||
                        event.getDamager().getType().equals(EntityType.SPECTRAL_ARROW) || event.getDamager().getType().equals(EntityType.FIREWORK_ROCKET)) {
                    Projectile damageSource = (Projectile) event.getDamager();

                    if(event.getDamager().getType().equals(EntityType.FIREWORK_ROCKET)) {
                        ((LivingEntity) damaged).setNoDamageTicks(0);
                    }

                    if(damageSource.getShooter() instanceof Player) {
                        shooter = (Player) damageSource.getShooter();
                    } else {
                        shooter = null;
                    }
                } else {
                    shooter = null;
                }


                if(event.getDamager() instanceof Player) {
                    LivingEntity entity = (LivingEntity) damaged;
                    Player damager = (Player) event.getDamager();

                    String output = "";

                    if(!entity.isDead()) {
                        if(damaged.getType().equals(EntityType.PLAYER)) {
                            Player player = (Player) event.getEntity();
                            output = ChatColor.AQUA + player.getName() + ChatColor.WHITE + ": " +
                                    ChatColor.GREEN + String.format("%.2f", entity.getHealth()) + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")"
                                    + ChatColor.GRAY + " Tax Amount: " + ChatColor.GREEN + String.format("%.2f", getTaxedDamage(damage));
                        } else {
                            output = ChatColor.AQUA + InfiniteFunProject.fixCaps(entity.getType().toString()) + ChatColor.WHITE + ": " +
                                    ChatColor.GREEN + String.format("%.2f", entity.getHealth()) + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        }
                    } else {
                        if(damaged.getType().equals(EntityType.PLAYER)) {
                            Player player = (Player) event.getEntity();
                            output = ChatColor.AQUA + player.getName() + ChatColor.WHITE + ": " +
                                    ChatColor.RED + "DEAD" + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        } else {
                            output = ChatColor.AQUA + InfiniteFunProject.fixCaps(entity.getType().toString()) + ChatColor.WHITE + ": " +
                                    ChatColor.RED + "DEAD" + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        }
                    }

                    BukkitScheduler showMsg = Bukkit.getScheduler();

                    String finalOutput = output;
                    //showMsg.runTaskLaterAsynchronously(InfiniteFunProject.plugin, bukkitTask ->
                    //        damager.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(finalOutput)), 2L);
                }

                if(shooter != null) {
                    String output = "";
                    LivingEntity entity = (LivingEntity) damaged;

                    if(!entity.isDead()) {
                        if(damaged.getType().equals(EntityType.PLAYER)) {
                            Player player = (Player) event.getEntity();
                            output = ChatColor.AQUA + player.getName() + ChatColor.WHITE + ": " +
                                    ChatColor.GREEN + String.format("%.2f", entity.getHealth()) + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        } else {
                            output = ChatColor.AQUA + InfiniteFunProject.fixCaps(entity.getType().toString()) + ChatColor.WHITE + ": " +
                                    ChatColor.GREEN + String.format("%.2f", entity.getHealth()) + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        }
                    } else {
                        if(damaged.getType().equals(EntityType.PLAYER)) {
                            Player player = (Player) event.getEntity();
                            output = ChatColor.AQUA + player.getName() + ChatColor.WHITE + ": " +
                                    ChatColor.RED + "DEAD" + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        } else {
                            output = ChatColor.AQUA + InfiniteFunProject.fixCaps(entity.getType().toString()) + ChatColor.WHITE + ": " +
                                    ChatColor.RED + "DEAD" + ChatColor.WHITE + "/" +
                                    ChatColor.GREEN + String.format("%.2f", entity.getAttribute(Attribute.MAX_HEALTH).getValue())
                                    + ChatColor.WHITE + " (-" + ChatColor.RED + String.format("%.2f", damage) + ChatColor.WHITE + ")";
                        }
                    }

                    String finalOutput = output;

                    if(damage <= 1) {
                        shooter.sendTitle("", color("&7-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    } else if(damage < 5) {
                        shooter.sendTitle("", color("&e-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    } else if(damage < 10) {
                        shooter.sendTitle("", color("&6-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    } else if(damage < 15) {
                        shooter.sendTitle("", color("&c-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    } else if(damage < 20) {
                        shooter.sendTitle("", color("&4-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    } else {
                        shooter.sendTitle("", color("&5-" + String.format("%.2f", damage)), 5 ,10 ,5);
                    }

                    // BukkitScheduler showMsg = Bukkit.getScheduler();
                    // showMsg.runTaskLaterAsynchronously(InfiniteFunProject.plugin, bukkitTask ->
                    //        shooter.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(finalOutput)), 2L);
                }
                 */

                //Bukkit.spigot().broadcast(new TextComponent("Damage: " + display.getText()));

                BukkitScheduler killStand = Bukkit.getScheduler();
                killStand.runTaskLater(InfiniteFunProject.plugin, display::remove, 16L);
            }
        }, 2);
    }

    @EventHandler
    public void onNonEntityAttack(EntityDamageEvent event) {
        Entity damaged = event.getEntity();
        double damage = event.getFinalDamage();

        if(event.isCancelled()) {
            return;
        }

        if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) || event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)
                || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            return;
        }

        ArrayList<EntityType> ignore = new ArrayList<>();
        ignore.add(EntityType.ITEM_FRAME);
        ignore.add(EntityType.ITEM);
        ignore.add(EntityType.GLOW_ITEM_FRAME);
        ignore.add(EntityType.FIREBALL);
        ignore.add(EntityType.SMALL_FIREBALL);
        ignore.add(EntityType.DRAGON_FIREBALL);
        ignore.add(EntityType.FIREWORK_ROCKET);
        ignore.add(EntityType.FISHING_BOBBER);
        ignore.add(EntityType.ARMOR_STAND);
        ignore.add(EntityType.TEXT_DISPLAY);
        ignore.add(EntityType.ARROW);
        ignore.add(EntityType.SPECTRAL_ARROW);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.EGG);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.END_CRYSTAL);
        ignore.add(EntityType.SHULKER_BULLET);
        ignore.add(EntityType.BLOCK_DISPLAY);
        ignore.add(EntityType.ITEM_DISPLAY);
        ignore.add(EntityType.INTERACTION);
        ignore.add(EntityType.WIND_CHARGE);

        if(!ignore.contains(damaged.getType()) && ((LivingEntity)damaged).getNoDamageTicks() == 0) {
            Location spawnLoc;

            spawnLoc = new Location(damaged.getWorld(),
                    damaged.getLocation().getX() + new Random().nextDouble(-0.45, 0.25),
                    damaged.getLocation().getY() + 0.4 + new Random().nextDouble(-0.45, 0.5),
                    damaged.getLocation().getZ() + new Random().nextDouble(-0.45, 0.45));

            TextDisplay display = damaged.getWorld().spawn(spawnLoc, TextDisplay.class);

            display.setGravity(false);
            display.setInvulnerable(true);
            display.setBillboard(Display.Billboard.CENTER);

            if(damage <= 1) {
                display.setText(color("&7-" + String.format("%.2f", damage)));
            } else if (damage < 5) {
                display.setText(color("&e-" + String.format("%.2f", damage)));
            } else if (damage < 10) {
                display.setText(color("&6-" + String.format("%.2f", damage)));
            } else if (damage < 15) {
                display.setText(color("&c-" + String.format("%.2f", damage)));
            } else if (damage < 20) {
                display.setText(color("&4-" + String.format("%.2f", damage)));
            } else {
                display.setText((color("&5-" + String.format("%.2f", damage))));
            }

            display.setCustomNameVisible(false);

            BukkitScheduler killStand = Bukkit.getScheduler();
            killStand.runTaskLater(InfiniteFunProject.plugin, display::remove, 16L);
        }
    }
}
