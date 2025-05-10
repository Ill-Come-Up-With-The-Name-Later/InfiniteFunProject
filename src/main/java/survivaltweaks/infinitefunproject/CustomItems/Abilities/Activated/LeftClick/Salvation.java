package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class Salvation implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SMOKE);
                add(Particle.WITCH);
                add(Particle.ENCHANTED_HIT);
            }
        };

        giveCrits(player, 4, false);
        createDamageRay(player, 25, 100, 5, false, false, false, particles, false);
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Fires a piercing beam.";
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
