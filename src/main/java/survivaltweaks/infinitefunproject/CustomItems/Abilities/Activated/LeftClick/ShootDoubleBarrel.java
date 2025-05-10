package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createSpreadDamageRay;

public class ShootDoubleBarrel implements ActivatedAbility {

    int pellets = 20;
    double variance = 0.5;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SMOKE);
            }
        };

        for(int i = 0; i < pellets; i++) {
            createSpreadDamageRay(player, variance, 120, 7f, 10, false, false, false,
                    particles, true);
        }
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Fires off " + pellets + " pellets with random\nspread.";
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
