package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Scorch implements Passive {

    int radius = 10;

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, radius);
        drawCircle(player, radius, Particle.FLAME, 90);
        taxEvade(player, 52, false);

        for(Entity entity : entities) {
            entity.setFireTicks(20);
            entity.setVisualFire(true);

            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                living.damage(160, player);
            }

            if(entity instanceof Projectile) {
                return;
            }

            if(entity instanceof Item) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(!entity.isDead()) {
                        entity.remove();
                    }
                }, 15);
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!entity.isDead()) {
                    entity.setVisualFire(false);
                }
            }, 60);
        }
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Burns nearby entities.";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
