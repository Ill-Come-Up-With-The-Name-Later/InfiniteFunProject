package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class CurseEnemy implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<LivingEntity> nearbyEntities = nearbyLivingEntities(player, 12);
        ArrayList<Enemy> enemies = new ArrayList<>();

        for(LivingEntity entity : nearbyEntities) {
            if(entity instanceof Enemy) {
                enemies.add((Enemy) entity);
            }
        }

        Optional<Enemy> strongest = enemies.stream().max(Comparator.comparing(Damageable::getHealth));

        if(strongest.isPresent()) {
            Enemy target = strongest.get();

            ArrayList<Particle> particles = new ArrayList<>() {
                {
                    add(Particle.TRIAL_OMEN);
                    add(Particle.ENCHANT);
                }
            };

            drawCircle(target, 1.1, particles, 45);
            applyDOT(player, target, target.getAttribute(Attribute.MAX_HEALTH).getBaseValue() / 4, 25, 10);
        }
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getDescription() {
        return "Curses and kills the\nstrongest nearby enemy.";
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
