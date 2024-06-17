package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SeekingProjectiles implements Listener {

    @EventHandler
    public void onProjectileShot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(!(projectile.getShooter() instanceof LivingEntity)) {
            return;
        }

        LivingEntity shooter = (LivingEntity) projectile.getShooter();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(projectile.hasMetadata("Seeking")) {
                List<MetadataValue> aimingMeta = projectile.getMetadata("Seeking");
                SeekingMetadata meta = (SeekingMetadata) aimingMeta.get(0);

                new BukkitRunnable() {
                    private Entity target;
                    private ArrayList<Entity> targets = new ArrayList<>();

                    @Override
                    public void run() {
                        try {
                            if(target == null || targets.contains(target) || ((LivingEntity)target).getNoDamageTicks() != 0
                                    && (target.getLastDamageCause() == null
                                    || target.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE))) {
                                setTarget();
                            } else if(target.isDead()) {
                                projectile.remove();
                                cancel();
                            } else if(projectile.isDead() || target.isDead()) {
                                cancel();
                                return;
                            }

                            Vector newVector;

                            if(target instanceof EnderDragon) {
                                newVector = target.getBoundingBox().getCenter().subtract(new Vector(0, 2, 0))
                                        .subtract(projectile.getLocation().toVector()).normalize().multiply(2);
                            } else {
                                newVector = target.getBoundingBox().getCenter().subtract(projectile.getLocation().toVector()).normalize().multiply(2);
                            }

                            projectile.setVelocity(newVector);

                            if(projectile.getTicksLived() > meta.getProjLifespan()) {
                                projectile.remove();
                            }

                        } catch (Exception ignored) {
                        }
                    }

                    private void setTarget() {
                        List<Entity> nearbyEntities = projectile.getNearbyEntities(meta.getStrength(), meta.getStrength(), meta.getStrength());

                        if (nearbyEntities.isEmpty()) {
                            cancel();
                        } else {
                            Optional<Entity> optionalEntity;
                            if(!(shooter instanceof Monster)) {
                                optionalEntity = nearbyEntities.stream()
                                        .filter(entity -> !entity.isDead() && !entity.equals(projectile.getShooter()) && !entity.isInvulnerable()
                                                && !(entity.getType() == EntityType.ENDERMAN) && meta.getTargets().contains(entity.getType())
                                                && !targets.contains(entity) && shooter.hasLineOfSight(entity))
                                        .min(Comparator.comparing(entity -> entity.getLocation().distanceSquared(projectile.getLocation())));
                            } else {
                                optionalEntity = nearbyEntities.stream()
                                        .filter(entity -> !entity.isDead() && entity.equals(((Monster) shooter).getTarget())
                                                && !(entity.getType() == EntityType.ENDERMAN)
                                                && !entity.isInvulnerable())
                                        .min(Comparator.comparing(entity -> entity.getLocation().distanceSquared(projectile.getLocation())));
                            }

                            optionalEntity.ifPresent(entity -> target = entity);
                            optionalEntity.ifPresent(entity -> targets.add(entity));
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
            }
        }, 1);
    }
}
