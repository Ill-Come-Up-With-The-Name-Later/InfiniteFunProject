package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class ShootRevolver implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SMOKE);
            }
        };

        createDamageRay(player, 45, 30f, 10, false, false, false,
                particles, true);
    }

    @Override
    public int getCooldown() {
        return 30;
    }

    @Override
    public String getDescription() {
        return "Fires a single, high damage\nbullet.";
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
