package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;
import static survivaltweaks.infinitefunproject.Mobs.MobInit.setNoLevel;

public class DragonAttacks implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) damaged;

            if(new Random().nextInt(0, 100) <= event.getFinalDamage() * 6.8 && !dragon.isDead()) {
                pickAttack(dragon);
            }
        }
    }

    @EventHandler
    public void onPhaseChange(EnderDragonChangePhaseEvent event) {
        EnderDragon dragon = event.getEntity();

        if(new Random().nextInt(0, 100) <= 15 && !dragon.isDead()) {
            pickAttack(dragon);
        }
    }

    @EventHandler
    public void onCrystalDestruction(EntityExplodeEvent event) {
        Entity dead = event.getEntity();

        if(dead instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) dead;

            ArrayList<Entity> nearby = (ArrayList<Entity>) crystal.getNearbyEntities(120, 120, 120);

            for(Entity e : nearby) {
                if(e instanceof EnderDragon) {
                    EnderDragon dragon = (EnderDragon) e;
                    if(new Random().nextInt(0, 100) <= 35 && !dragon.isGlowing()) {
                        pickAttack(dragon);
                    }
                    break;
                }
            }
        }
    }

    private void pickAttack(EnderDragon dragon) {
        int attack = new Random().nextInt(0, 11);

        double dragonHpPercent = (dragon.getHealth() / dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 100;

        dragon.setGlowing(true);
        Objects.requireNonNull(dragon.getBossBar()).setColor(BarColor.RED);
        dragon.setCustomName(color("&cEnder Dragon"));
        dragon.setCustomNameVisible(true);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(!(dragon.getPhase().equals(EnderDragon.Phase.DYING) || dragon.isDead())) {
                switch (attack) {
                    case 0:
                        lightningAttack(dragon);
                        break;
                    case 1:
                        biteAttack(dragon);
                        break;
                    case 2:
                        if (dragonHpPercent >= 80) {
                            spawnDisciple(dragon);
                        } else {
                            lightningAttack(dragon);
                        }
                        break;
                    case 3:
                        fireball(dragon);
                        break;
                    case 4:
                        if (dragonHpPercent <= 68 && dragonHpPercent > 35) {
                            levitateAttack(dragon);
                        } else {
                            biteAttack(dragon);
                        }
                        break;
                    case 5:
                        if (dragonHpPercent < 20) {
                            shulkerSpawn(dragon);
                        } else {
                            bodySlam(dragon);
                        }
                        break;
                    case 6:
                        if (dragonHpPercent >= 70) {
                            noxiousBlast(dragon);
                        } else {
                            biteAttack(dragon);
                        }
                        break;
                    case 7:
                        bodySlam(dragon);
                        break;
                    case 8:
                        if (dragonHpPercent <= 15) {
                            fireballRain(dragon);
                        } else {
                            biteAttack(dragon);
                        }
                        break;
                    case 9:
                        if (dragonHpPercent <= 45 && dragonHpPercent >= 20) {
                            summonPhantom(dragon);
                        } else {
                            lightningAttack(dragon);
                        }
                        break;
                    case 10:
                        if (dragonHpPercent <= 50 && dragonHpPercent >= 30) {
                            chargePlayer(dragon);
                        } else {
                            bodySlam(dragon);
                        }
                        break;
                }
            }
            dragon.setGlowing(false);
            dragon.getBossBar().setColor(BarColor.PINK);
            dragon.setCustomName(color("&fEnder Dragon"));
            dragon.setCustomNameVisible(false);
        }, 35);
    }

    private void chargePlayer(EnderDragon dragon) {
        ArrayList<Player> endPlayers = (ArrayList<Player>) dragon.getWorld().getPlayers();

        Player target = endPlayers.get(new Random().nextInt(0, endPlayers.size()));

        new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<Entity> nearby = (ArrayList<Entity>) dragon.getNearbyEntities(0.2, 0.2, 0.2);
                if(dragon.isDead() || nearby.contains(target)) {
                    cancel();
                    return;
                }

                dragon.getWorld().spawnParticle(Particle.DRAGON_BREATH, dragon.getLocation(), 50, 4.5, 2.7, 4.5, 0.3);

                faceEntityToAnother(dragon, target);
                setHeadRotationToFaceEntity(dragon, target);

                Vector targetPos = target.getLocation().toVector();
                Vector dragonPos = dragon.getLocation().toVector();

                Vector velocity =  targetPos.subtract(dragonPos).normalize();

                dragon.setTarget(target);
                dragon.setVelocity(velocity);
            }
        }.runTaskTimer(plugin, 1, 1);
    }

    private void summonPhantom(EnderDragon dragon) {
        Phantom phantom = dragon.getWorld().spawn(new Location(dragon.getWorld(), 0, 75, 0), Phantom.class);
        setNoLevel(phantom);
        phantom.setCustomName(color("&eChild of the Dragon"));
        phantom.setSize(9);
        phantom.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        phantom.setHealth(phantom.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        phantom.getWorld().spawnParticle(Particle.SMOKE_LARGE, phantom.getLocation(), 500, 6, 3, 6, 0.3);
        phantom.setTarget(dragon.getTarget());
    }

    private void fireballRain(EnderDragon dragon) {
        for(int i = 0; i < 10; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                int x = new Random().nextInt(0, 51);
                int z = new Random().nextInt(0, 51);
                if(new Random().nextBoolean()) {
                    x *= -1;
                }
                if(new Random().nextBoolean()) {
                    z *= -1;
                }
                Location location = new Location(dragon.getWorld(), x, 90, z);
                DragonFireball fireball = dragon.getWorld().spawn(location, DragonFireball.class);
                fireball.setShooter(dragon);
                fireball.setVelocity(new Vector(0, -2.9, 0));
            }, i * 2);
        }
    }

    private void bodySlam(EnderDragon dragon) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        ArrayList<Player> endPlayers = new ArrayList<>();

        for(Player player : players) {
            if(player.getWorld().equals(dragon.getWorld())) {
                endPlayers.add(player);
            }
        }

        dragon.setPhase(EnderDragon.Phase.CIRCLING);
        dragon.teleport(endPlayers.get(new Random().nextInt(0, endPlayers.size())).getLocation().add(0, 35, 0));
        dragon.setVelocity(new Vector(0, -4, 0));
    }

    private void lightningAttack(EnderDragon dragon) {
        dragon.getWorld().strikeLightning(dragon.getEyeLocation());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.getWorld().equals(dragon.getWorld())) {
                    dragon.getWorld().strikeLightning(player.getLocation());
                }
            }
        }, 35);
    }

    private void biteAttack(EnderDragon dragon) {
        ArrayList<Player> endPlayers = (ArrayList<Player>) dragon.getWorld().getPlayers();

        Player player = endPlayers.get(new Random().nextInt(0, endPlayers.size()));

        dragon.teleport(player);
        faceEntityToAnother(dragon, player);
        player.addPotionEffect(PotionEffectType.POISON.createEffect(100, 0));

        player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation().add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
        player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation().add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);

        dragon.setPhase(EnderDragon.Phase.CIRCLING);
    }

    private void levitateAttack(EnderDragon dragon) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            if(player.getWorld().equals(dragon.getWorld())) {
                player.teleport(new Location(player.getWorld(), 0, 68, 0));
                player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(140, 0));
            }
        }
    }

    private void spawnDisciple(EnderDragon dragon) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            if(player.getWorld().equals(dragon.getWorld())) {
                Enderman disciple = (Enderman) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
                setNoLevel(disciple);
                disciple.setTarget(player);
                disciple.setCustomName(color("&cDragon's Disciple"));
                disciple.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(disciple.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + 3);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(disciple.isDead()) {
                            cancel();
                            return;
                        }
                        Enderman extraDisciple = (Enderman) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
                        setNoLevel(extraDisciple);
                        extraDisciple.setTarget(player);
                        extraDisciple.setCustomName(color("&cDragon's Disciple"));
                        extraDisciple.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(extraDisciple.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + 3);
                    }
                }.runTaskLater(plugin, 1200);
            }
        }
    }

    private void shulkerSpawn(EnderDragon dragon) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            if(player.getWorld().equals(dragon.getWorld())) {
                Shulker shulker = (Shulker) player.getWorld().spawnEntity(player.getLocation(), EntityType.SHULKER);
                setNoLevel(shulker);
                shulker.setTarget(player);
                shulker.getWorld().spawnParticle(Particle.END_ROD, shulker.getLocation(), 28, 0.3, 0.3, 0.3, 0.3);
            }
        }
    }

    private void fireball(EnderDragon dragon) {
        Collection<? extends Player> players = dragon.getWorld().getPlayers();

        for(Player player : players) {
            Location spawnLoc = player.getLocation();
            spawnLoc.add(new Vector(0, 4.5, 0));
            DragonFireball fireball = dragon.getWorld().spawn(spawnLoc, DragonFireball.class);
            fireball.setShooter(dragon);
            fireball.setVelocity(new Vector(1, -1.6, 0));
        }
    }

    private void noxiousBlast(EnderDragon dragon) {
        dragon.getWorld().spawnParticle(Particle.DRAGON_BREATH, dragon.getLocation(), 500, 6, 3, 6, 0.3);
        dragon.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, dragon.getLocation(), 1, 6, 3, 6, 1);
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            if(player.getWorld().equals(dragon.getWorld())) {
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 30, 0.3, 0.3, 0.3, 0.3);
                player.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(320, 1));
                player.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
            }
        }
    }
}
