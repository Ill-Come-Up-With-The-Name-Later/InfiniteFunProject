package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createRay;

public class ExecutionThread implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.CRIT);
                add(Particle.WHITE_SMOKE);
                add(Particle.FIREWORK);
                add(Particle.ELECTRIC_SPARK);
            }
        };

        ArrayList<LivingEntity> entities = createRay(player, 20, false, particles);
        Optional<LivingEntity> target = entities.stream().min(Comparator.comparing(x ->
                player.getLocation().distanceSquared(x.getLocation())));

        if(target.isPresent()) {
            LivingEntity living = target.get();

            Location ahead = player.getLocation().add(player.getLocation().getDirection());
            living.teleport(ahead);

            if(living.getHealth() < living.getAttribute(Attribute.MAX_HEALTH).getBaseValue() / 4) {
                living.damage(2_000_000_000, player);
            } else {
                living.damage(10, player);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Pulls an entity to you, instantly\nkilling it if it is below 25% health.";
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
