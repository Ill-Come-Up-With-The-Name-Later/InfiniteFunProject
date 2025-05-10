package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class BlastPulse implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ELECTRIC_SPARK);
                add(Particle.ENCHANTED_HIT);
                add(Particle.HAPPY_VILLAGER);
                add(Particle.CRIT);
            }
        };

        createDamageRay(player, 75, 100, 6, true, false, true,
                particles, true);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shoots a powerful, long\nrange blast that goes\nthrough walls.";
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
