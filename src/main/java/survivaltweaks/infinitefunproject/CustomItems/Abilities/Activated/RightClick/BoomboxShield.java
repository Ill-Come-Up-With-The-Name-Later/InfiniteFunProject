package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.giveProjectileShield;

public class BoomboxShield implements ActivatedAbility {
    int radius = 4;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> deflect = new ArrayList<>() {
            {
                add(Particle.NOTE);
                add(Particle.ENCHANTED_HIT);
            }
        };

        ArrayList<Particle> aura = new ArrayList<>() {
            {
                add(Particle.NOTE);
                add(Particle.ENCHANTED_HIT);
                add(Particle.CRIT);
            }
        };

        giveProjectileShield(player, 300, deflect, aura, radius);
    }

    @Override
    public int getCooldown() {
        return 1000;
    }

    @Override
    public String getDescription() {
        return "Creates a sound barrier so\npowerful it can destroy projectiles.";
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
