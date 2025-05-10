package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ProjectileShieldMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Objects;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ProjectileShieldManager {

    public static void init() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    for(LivingEntity entity : world.getEntitiesByClass(LivingEntity.class)) {
                        if(entity.hasMetadata("ProjShield")) {
                            ProjectileShieldMetadata metadata = (ProjectileShieldMetadata) entity.getMetadata("ProjShield").get(0);
                            ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(entity.getLocation(), metadata.getRadius());

                            for(Particle particle : metadata.getAura()) {
                                drawCircle(entity.getLocation(), metadata.getRadius(), particle, 90);
                            }

                            for(Entity e : nearby) {
                                if(e instanceof Projectile && !e.hasMetadata("PierceShield")) {
                                    Projectile projectile = (Projectile) e;

                                    if(!Objects.equals(projectile.getShooter(), entity)) {
                                        projectile.remove();

                                        for(Particle particle : metadata.getDeflectEffect()) {
                                            e.getWorld().spawnParticle(particle, projectile.getLocation(), 6, 0.2, 0.2, 0.2, 0.04);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    public static void destroyShield(LivingEntity entity) {
        ProjectileShieldMetadata meta = (ProjectileShieldMetadata) entity.getMetadata("ProjShield").get(0);

        drawExpandingCircle(entity, meta.getRadius(), 7, 1, 5, meta.getAura());
        entity.removeMetadata("ProjShield", InfiniteFunProject.plugin);
    }
}
