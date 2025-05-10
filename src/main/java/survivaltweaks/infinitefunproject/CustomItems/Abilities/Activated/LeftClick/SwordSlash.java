package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class SwordSlash implements ActivatedAbility {
    int range = 4;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.SWEEP_ATTACK);
            }
        };

        createDamageRay(player, range, 17, 10000, true, false, false, particles,
                false);
    }

    @Override
    public int getCooldown() {
        return 18;
    }

    @Override
    public String getDescription() {
        return "Slash your sword forward, dealing\ndamage to entities in its path.";
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
