package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.giveProjectileShield;

public class FireMembrane implements Passive {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> deflectParticles = new ArrayList<>()
        {
            {
                add(Particle.FLAME);
            }
        };

        giveProjectileShield(player, 40, deflectParticles, new ArrayList<>(), 11);
    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public String getDescription() {
        return "Grants a shield that\nincinerates projectiles.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
