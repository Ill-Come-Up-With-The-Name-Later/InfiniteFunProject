package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class SlingShot implements ActivatedAbility {
    int range = 24;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.ELECTRIC_SPARK);
            }
        };

        createDamageRay(player, range, 20, 0, false, false, false, particles,
                false);
    }

    @Override
    public int getCooldown() {
        return 18;
    }

    @Override
    public String getDescription() {
        return "Fire a fast shot from the\nslingshot.";
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
