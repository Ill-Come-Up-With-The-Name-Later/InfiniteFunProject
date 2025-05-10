package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Grounded implements KillAbility {

    int radius = 8;

    @Override
    public void activate(Player player, LivingEntity entity) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.ELECTRIC_SPARK);
            }
        };

        drawCircle(entity.getLocation(), radius, particles, 90);
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(entity, radius);

        for(Entity ent : entities) {
            if(ent instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) ent;

                if(!living.equals(player)) {
                    living.damage(36, player);
                    stun(living, 30, false);
                    drawExpandingCircle(living, 1, 4, 0.2, 15, particles);
                }
            }
        }
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shocks enemies in a radius\naround a dead enemy.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
