package survivaltweaks.infinitefunproject.Bosses.Wither.Attacks;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Bosses.Wither.WitherSpawn;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.faceEntityToAnother;
import static survivaltweaks.infinitefunproject.Bosses.Wither.InitWither.setWitherSpawn;

public class WitherSecondaryAttacks implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Wither) {
            Wither wither = (Wither) entity;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (wither.isDead()) {
                        cancel();
                        return;
                    }
                    if(wither.hasAI()) {
                        pickAttack(wither);
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 500, 500);
        }
    }

    private void pickAttack(Wither wither) {
        int attack = new Random().nextInt(0, 4);

        if(!wither.isDead()) {
            switch (attack) {
                case 0:
                    rallyUndead(wither);
                    break;
                case 1:
                    spawnGhasts(wither);
                    break;
                case 2:
                    superSkullBarrage(wither);
                    break;
                case 3:
                    lifesteal(wither);
                    break;
            }
        }
    }

    private void superSkullBarrage(Wither wither) {
        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(20, 17, 20);

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                for(int i = 1; i <= 5; i++) {
                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        WitherSkull skull = wither.getWorld().spawn(wither.getEyeLocation(), WitherSkull.class);

                        //faceEntityToAnother(skull, entity);
                        skull.setShooter(wither);
                        skull.setCharged(true);

                        Vector velocity =  entity.getLocation().toVector().subtract(skull.getLocation().toVector());
                        skull.setDirection(velocity);
                        skull.setVelocity(velocity.normalize());
                    }, 6 * i);
                }
            }
        }
    }

    private void spawnGhasts(Wither wither) {
        for(int i = 0; i < 3; i++) {
            Ghast ghast = wither.getWorld().spawn(wither.getLocation(), Ghast.class);
            ghast.setTarget(wither.getTarget());
            setWitherSpawn(ghast);
        }
    }

    private void rallyUndead(Wither wither) {
        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(20, 15, 20);

        drawCircle(wither.getLocation(), 5, Particle.SMOKE_NORMAL, 90);
        drawCircle(wither.getLocation(), 5, Particle.SOUL, 90);

        for(Entity entity : entities) {
            if(entity instanceof Zombie || entity instanceof Skeleton) {
                drawCircle(entity.getLocation(), 4, Particle.SMOKE_NORMAL, 90);
                entity.getWorld().spawnParticle(Particle.SPELL_WITCH, entity.getLocation().add(0, 0.3, 0), 50, 0.3, 0.1, 0.3, 0.04);
                entity.getWorld().spawnParticle(Particle.SMOKE_NORMAL, entity.getLocation().add(0, 0.3, 0), 40, 0.3, 0.1, 0.3, 0.04);
                ((Monster) entity).setTarget(wither.getTarget());
            }
        }
    }

    private void lifesteal(Wither wither) {
        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(8, 8, 8);
        wither.getWorld().spawnParticle(Particle.HEART, wither.getEyeLocation(), 8, 0.3, 0.3, 0.3, 0.1);
        drawCircle(wither.getLocation(), 4, Particle.VILLAGER_HAPPY, 90);

        int i = 0;
        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                i++;
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    drawCircle(entity.getLocation(), 2, Particle.SMOKE_NORMAL, 180);
                    ((LivingEntity) entity).damage(5, wither);
                    entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, entity.getLocation(), 8, 0.3, 0.3, 0.3, 0.1);
                    wither.setHealth(Math.min(wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), wither.getHealth() + 3));
                }, 4L * i);
            }
        }
    }
}
