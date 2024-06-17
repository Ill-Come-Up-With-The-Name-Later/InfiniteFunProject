package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ShurikenStab implements ActivatedAbility {
    int range = 4;

    @Override
    public void activate(Player player) {
        player.setVelocity(player.getLocation().getDirection().multiply(3).setY(0.05));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            ArrayList<LivingEntity> entities = createRay(player, range, false, new ArrayList<>());

            Optional<LivingEntity> nearest = entities.stream().min(Comparator.comparing(
                    e -> e.getLocation().distanceSquared(player.getLocation())));
            nearest.ifPresent(entity -> {
                if(facingSameWay(player, entity)) {
                    faceEntityToAnother(player, entity);
                    player.swingMainHand();
                    entity.damage(30, player);

                    entity.getWorld().spawnParticle(Particle.CRIT, entity.getLocation().add(0, 0.7, 0),
                            10, 0.1, 0.2, 0.1, 0.03);
                    entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, entity.getLocation().add(0, 0.7, 0),
                            10, 0.1, 0.2, 0.1, 0.03);
                    drawCircle(entity.getLocation().add(0, 0.7, 0), 2, Particle.FALLING_LAVA, 90);
                } else {
                    entity.damage(20, player);
                }
            });
        }, 15);
    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Charge forward and stab the\nnearest enemy in range.";
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
