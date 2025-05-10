package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class DragonSecondaryAttacks implements Listener {

    /**
     * Dragon spawn and attack loop
     *
     * @param event: Entity spawn event
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) entity;

            if(!dragon.getPhase().equals(EnderDragon.Phase.DYING)) {
                new BukkitRunnable() {
                    int attackNumber = 1;

                    @Override
                    public void run() {
                        if(dragon.isDead() || dragon.getPhase().equals(EnderDragon.Phase.DYING)) {
                            cancel();
                            return;
                        }
                        if(!dragon.isGlowing()) {
                            if(attackNumber == 5) {
                                specialAttack(dragon);
                                attackNumber = 1;
                            } else {
                                pickAttack(dragon);
                                attackNumber++;
                            }
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 550, 550);
            }
        }
    }

    /**
     * Dragon picking attack
     *
     * @param dragon: the attacking dragon
     */
    private void pickAttack(EnderDragon dragon) {
        int attack = new Random().nextInt(0, 7);
        double dragonHpPercent = (dragon.getHealth() / dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 100;

        if(!(dragon.isDead() || dragon.getPhase().equals(EnderDragon.Phase.DYING))) {
            switch (attack) {
                case 0:
                    grab(dragon);
                    break;
                case 1:
                    if (dragonHpPercent <= 20 && new Random().nextInt(0, 2) == 1) {
                        spawnHealers(dragon);
                    } else {
                        grab(dragon);
                    }
                    break;
                case 2:
                    biteAttack(dragon);
                    break;
                case 3:
                    shootFireball(dragon);
                    break;
                case 4:
                    dragonFireEveryone(dragon);
                    break;
                case 5:
                    fireBreath(dragon);
                    break;
                case 6:
                    miteBombs(dragon);
                    break;
            }
        }
    }

    /**
     * Dragon endermite bomb attack
     *
     * @param dragon: the attacking dragon
     */
    private void miteBombs(EnderDragon dragon) {
        Location loc = dragon.getLocation().subtract(0, 7, 0);

        for(Player player : dragon.getWorld().getPlayers()) {
            player.sendMessage(ChatColor.RED + "Watch out for the mites!");
        }

        for(int i = 1; i <= 5; i++) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Endermite endermite = dragon.getWorld().spawn(loc, Endermite.class);
                endermite.setInvulnerable(true);
                endermite.setGlowing(true);

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    endermite.getWorld().createExplosion(endermite.getLocation(), 3, false, false, dragon);
                    endermite.remove();
                }, 60);
            }, i * 15);
        }
    }

    /**
     * Dragon bite attack
     *
     * @param dragon: the attacking dragon
     */
    private void biteAttack(EnderDragon dragon) {
        ArrayList<Player> endPlayers = (ArrayList<Player>) dragon.getWorld().getPlayers();

        Player player = endPlayers.get(new Random().nextInt(0, endPlayers.size()));

        dragon.teleport(player);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Ouch.");
        dragon.setPhase(EnderDragon.Phase.CIRCLING);
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 150, 0, false, false));

        new BukkitRunnable() {
            int bleedNum = 1;
            @Override
            public void run() {
                if(player.isDead() || dragon.isDead() || bleedNum >= 5) {
                    cancel();
                }

                player.damage(5, dragon);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 35, 0.3, 0.3, 0.3, 0.07);
                bleedNum++;
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 30, 30);
    }

    /**
     * Dragon breath fire attack
     *
     * @param dragon: the attacking dragon
     */
    private void fireBreath(EnderDragon dragon) {
        Player player = dragon.getWorld().getPlayers().get(new Random().nextInt(0, dragon.getWorld().getPlayers().size()));
        player.sendMessage(ChatColor.GOLD + "The air around you is heating up...");
        Location playerLoc = player.getLocation();

        dragon.teleport(playerLoc.add(0, 35, 0));
        dragon.setVelocity(new Vector(0, -4.2, 0));

        new BukkitRunnable() {
            @Override
            public void run() {
                if(dragon.isDead()) {
                    cancel();
                    return;
                }

                dragon.getWorld().spawnParticle(Particle.FLAME, dragon.getLocation(), 70, 4.5, 2.7, 4.5, 0.3);

                if(InfiniteFunProject.distanceBetween(playerLoc.toVector(), dragon.getLocation().toVector()) <= 15) {
                    ArrayList<Entity> entities = (ArrayList<Entity>) dragon.getNearbyEntities(15, 15 ,15);
                    dragon.getWorld().spawnParticle(Particle.FLAME, dragon.getLocation(), 70, 4.5, 2.7, 4.5, 0.3);

                    for(Entity entity : entities) {
                        if(entity instanceof LivingEntity) {
                            LivingEntity e = (LivingEntity) entity;
                            e.damage(11, dragon);
                        }
                    }
                    cancel();
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    /**
     * Dragon spawn healing crystals attack
     *
     * @param dragon: the attacking dragon
     */
    private void spawnHealers(EnderDragon dragon) {
        Location portal = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation();

        portal.add(new Vector(0, 4, 0));

        EnderCrystal crystal1 = dragon.getWorld().spawn(portal.clone().add(15, 0, 0), EnderCrystal.class);
        EnderCrystal crystal2 = dragon.getWorld().spawn(portal.clone().subtract(15, 0, 0), EnderCrystal.class);
        EnderCrystal crystal3 = dragon.getWorld().spawn(portal.clone().add(0, 0, 15), EnderCrystal.class);
        EnderCrystal crystal4 = dragon.getWorld().spawn(portal.clone().subtract(0, 0, 15), EnderCrystal.class);

        findAir(crystal1.getLocation());
        findAir(crystal2.getLocation());
        findAir(crystal3.getLocation());
        findAir(crystal4.getLocation());

        crystal1.setShowingBottom(false);
        crystal2.setShowingBottom(false);
        crystal3.setShowingBottom(false);
        crystal4.setShowingBottom(false);

        for(Player player : dragon.getWorld().getPlayers()) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + "The dragon is attempting to heal itself...");
        }
    }

    /**
     * Dragon shoot fireball attack
     *
     * @param dragon: the attacking dragon
     */
    private void shootFireball(EnderDragon dragon) {
        Location portal = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().add(0, 40, 0);
        dragon.teleport(portal);

        Player target = dragon.getWorld().getPlayers().get(new Random().nextInt(0, dragon.getWorld().getPlayers().size()));

        setHeadRotationToFaceEntity(dragon, target);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            dragon.setVelocity(new Vector(0, 0, 0));
            dragon.setAI(false);

            target.sendMessage(ChatColor.RED + "Good luck dodging this.");

            LargeFireball fireball = dragon.getWorld().spawn(dragon.getEyeLocation().subtract(0, 15, 0), LargeFireball.class);

            //faceEntityToAnother(fireball, target);
            fireball.setShooter(dragon);
            fireball.setIsIncendiary(true);
            fireball.setYield(3);

            Vector velocity = target.getLocation().toVector().subtract(fireball.getLocation().toVector());
            fireball.setDirection(velocity);
            fireball.setVelocity(velocity.normalize());

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                dragon.setAI(true);
                dragon.setPhase(EnderDragon.Phase.CIRCLING);
            }, 25);
        }, 20);
    }

    /**
     * Dragon rain fireball attack
     *
     * @param dragon: the attacking dragon
     */
    private void dragonFireEveryone(EnderDragon dragon) {
        Location portal = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().add(0, 40, 0);
        dragon.teleport(portal);

        int i = 0;
        for(Player target : dragon.getWorld().getPlayers()) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                DragonFireball fireball = dragon.getWorld().spawn(dragon.getEyeLocation().subtract(0, 15, 0), DragonFireball.class);

                //faceEntityToAnother(fireball, target);
                fireball.setShooter(dragon);
                fireball.setIsIncendiary(true);
                fireball.setYield(3);

                Vector velocity = target.getLocation().toVector().subtract(fireball.getLocation().toVector());
                fireball.setDirection(velocity);
                fireball.setVelocity(velocity.normalize());

                target.sendMessage(ChatColor.YELLOW + "Fireball inbound!");
            }, i * 3L);
            i++;
        }
    }

    /**
     * Dragon magic grab attack
     *
     * @param dragon: the attacking dragon
     */
    private void grab(EnderDragon dragon) {
        ArrayList<Player> players = (ArrayList<Player>) dragon.getWorld().getPlayers();

        for(Player player : players) {
            Location playerLoc = player.getLocation();

            drawCircle(playerLoc, 2.5, Particle.WITCH, 90);
            drawCircle(playerLoc, 2.7, Particle.DRAGON_BREATH, 90);

            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, playerLoc.add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
            player.getWorld().spawnParticle(Particle.WITCH, playerLoc.add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
            player.getWorld().spawnParticle(Particle.SMOKE, playerLoc.add(0, 0.3, 0), 20, 0.3, 0.1, 0.3, 0.04);
            player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, playerLoc.add(0, 0.3, 0), 20, 0.3, 0.1, 0.3, 0.04);

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Look out below!");
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                Location newLoc = player.getLocation();

                drawCircle(playerLoc, 2.5, Particle.WITCH, 90);
                drawCircle(playerLoc, 2.7, Particle.DRAGON_BREATH, 90);

                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, playerLoc.add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
                player.getWorld().spawnParticle(Particle.WITCH, playerLoc.add(0, 0.3, 0), 20, 0.3, 0.1, 0.3, 0.04);
                player.getWorld().spawnParticle(Particle.WITCH, playerLoc.add(0, 0.3, 0), 20, 0.3, 0.1, 0.3, 0.04);

                if(InfiniteFunProject.distanceBetween(playerLoc.toVector(), newLoc.toVector()) <= 3) {
                    player.teleport(newLoc.subtract(0, 2, 0));
                    player.damage(10, dragon);
                }
            }, 55);
        }
    }

    /**
     * Dragon launch into the air attack
     *
     * @param dragon: the attacking dragon
     */
    private void specialAttack(EnderDragon dragon) {
        Location portal = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().add(0, 40, 0);
        dragon.teleport(portal);

        dragon.setPhase(EnderDragon.Phase.CIRCLING);
        dragon.setVelocity(new Vector(0, -4.2, 0));

        new BukkitRunnable() {
            @Override
            public void run() {
                if(dragon.isDead()) {
                    cancel();
                    return;
                }

                ArrayList<Entity> entities = (ArrayList<Entity>) dragon.getNearbyEntities(35, 10, 35);

                for(Entity e : entities) {
                    if(e instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) e;
                        livingEntity.damage(10, dragon);

                        livingEntity.sendMessage(ChatColor.GREEN + "I hope you brought a parachute... or a water bucket.");

                        Vector direction = livingEntity.getLocation().getDirection();
                        livingEntity.setVelocity(direction.multiply(-1.5).normalize().add(new Vector(0, 2.4, 0)));
                    }
                }
                dragon.setPhase(EnderDragon.Phase.LEAVE_PORTAL);
            }
        }.runTaskLater(InfiniteFunProject.plugin, 14);
    }
}
