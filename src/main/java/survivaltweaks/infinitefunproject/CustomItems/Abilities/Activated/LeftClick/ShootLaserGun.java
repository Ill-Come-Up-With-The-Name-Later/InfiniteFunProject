package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createSpreadDamageRay;

public class ShootLaserGun implements ActivatedAbility {
    int range = 60;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.FIREWORK);
                add(Particle.WITCH);
            }
        };

        createSpreadDamageRay(player, 0.15, range, 20, 10, false, false, false,
                particles, true);
    }

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Shoots a fast, piercing laser.";
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
