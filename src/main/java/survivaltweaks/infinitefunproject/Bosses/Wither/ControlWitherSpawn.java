package survivaltweaks.infinitefunproject.Bosses.Wither;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.distanceBetween;

public class ControlWitherSpawn implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(entity.hasMetadata("WitherSpawn") && entity instanceof Monster) {
                Collection<Wither> withers = entity.getWorld().getEntitiesByClass(Wither.class);

                Wither wither = withers.toArray(new Wither[]{})[0];

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(entity.isDead() || wither.isDead()) {
                            cancel();
                            return;
                        }

                        ((Monster) entity).setTarget(wither.getTarget());

                        if(distanceBetween(entity.getLocation().toVector(), wither.getLocation().toVector()) >= 20
                                && entity.getWorld().equals(wither.getWorld())) {
                            entity.teleport(wither.getLocation());
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 1, 2);
            }
        }, 2);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damaged instanceof LivingEntity) {
            if(damager instanceof Wither || damager.hasMetadata("WitherSpawn") || event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if(damaged.hasMetadata("WitherSpawn")) {
                    event.setDamage(0);
                    event.setCancelled(true);
                    if(damaged instanceof Monster) {
                        ((Monster) damaged).setTarget(null);
                    }
                }
            }
        }
    }
}
