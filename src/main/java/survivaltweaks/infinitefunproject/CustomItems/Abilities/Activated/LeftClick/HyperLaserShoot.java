package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class HyperLaserShoot implements ActivatedAbility {
    int range = 100;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.FIREWORK);
                add(Particle.SOUL_FIRE_FLAME);
            }
        };

        HashMap<LivingEntity, Boolean> hitEntities =
                createDamageRay(player, range, 40, 0, false, false, false, particles,
                        true);

        for(LivingEntity entity : hitEntities.keySet()) {
            if(hitEntities.get(entity)) {
                drawCircle(entity.getLocation(), 1.3, Particle.SOUL_FIRE_FLAME, 90);
                drawCircle(entity.getLocation(), 1.3, Particle.DAMAGE_INDICATOR, 90);
                drawCircle(entity.getLocation(), 1.3, Particle.ENCHANTED_HIT, 90);

                /*
                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(entity, 4);

                for(Entity e : entities) {
                    if(e instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity) e;
                        living.damage(30, player);
                    }
                }
                 */
            }
        }
    }

    @Override
    public int getCooldown() {
        return 24;
    }

    @Override
    public String getDescription() {
        return "Fires a high damage laser.\nThis laser has a " + range + " block range.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
