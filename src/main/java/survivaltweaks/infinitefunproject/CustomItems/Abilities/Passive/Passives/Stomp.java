package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class Stomp implements Passive {

    @Override
    public void activate(Player player) {
        double radius = 3 + (2 * player.getAttribute(Attribute.GENERIC_SCALE).getBaseValue());
        drawCircle(player, radius, Particle.LARGE_SMOKE, 90);

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;

                living.damage(player.getAttribute(Attribute.GENERIC_SCALE).getBaseValue() * 30, player);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 45;
    }

    @Override
    public String getDescription() {
        return "Stomps nearby mobs.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
