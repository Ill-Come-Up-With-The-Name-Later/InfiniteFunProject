package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class SoulSplicer implements ActivatedAbility {
    double radius = 4.5;

    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), radius, Particle.SWEEP_ATTACK, 90);
        drawCircle(player.getLocation(), radius, Particle.ENCHANTED_HIT, 90);

        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        double totalHealth = 0;
        for(Entity entity : nearby) {
            if(entity instanceof LivingEntity) {
                totalHealth += ((LivingEntity) entity).getHealth();
            }
        }

        double average = totalHealth / nearby.size();
        average += 35;

        for(Entity entity : nearby) {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                living.setHealth(Math.min(average, living.getHealth()));
                drawCircle(living.getLocation(), 1.3, Particle.DAMAGE_INDICATOR, 90);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getDescription() {
        return "Swing your katana around and set\nthe health of nearby entities\nto their average health plus 35.";
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
