package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ScytheSwing implements ActivatedAbility {

    int radius = 5;

    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), 4, Particle.SWEEP_ATTACK, 90);
        drawCircle(player.getLocation(), 3, Particle.ENCHANTED_HIT, 180);

        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        for(Entity entity : nearby) {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                if(distanceBetween(player.getLocation().toVector(), living.getLocation().toVector()) <= radius - 1) {
                    living.damage(20, player);
                } else {
                    living.damage(Math.max(15 + (living.getHealth() * 0.1), 20), player);
                    living.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, living.getLocation(), 15, 0.2, 0.2, 0.2, 0.06);
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Swing your scythe, dealing\n20 damage to enemies\nand extra if the tip of\nyour blade hits an enemy.";
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
