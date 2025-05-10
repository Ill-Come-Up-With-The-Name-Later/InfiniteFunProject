package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class Flare implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>()
        {
            {
                add(Particle.FLAME);
                add(Particle.SMALL_FLAME);
                add(Particle.SMOKE);
                add(Particle.ENCHANTED_HIT);
            }
        };

        createDamageRay(player, 50, 250, 9999, true, true, false,  particles, false);
    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Unleashes a blast of heat\nto incinerate foes.";
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
