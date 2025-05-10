package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createRay;

public class ImpregnationShot implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.WHITE_SMOKE);
            }
        };

        ArrayList<LivingEntity> hit = createRay(player, 25, false, particles);

        for(LivingEntity entity : hit) {
            if(entity instanceof Ageable) {
                entity.getWorld().spawnParticle(Particle.WHITE_SMOKE, entity.getLocation(), 9, 0.2, 0.2, 0.2, 0.03);
                entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation(), 7, 0.2, 0.2, 0.2, 0.03);

                Ageable baby = (Ageable) entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
                baby.setBaby();
            }
        }
    }

    @Override
    public int getCooldown() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "Creatures hit with this reproduce\nasexually and create babies.";
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
