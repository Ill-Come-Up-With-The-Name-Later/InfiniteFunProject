package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class CalamitySlice implements ActivatedAbility {
    int range = 5;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SWEEP_ATTACK);
                add(Particle.ENCHANTED_HIT);
                add(Particle.ENCHANTED_HIT);
            }
        };

        for(int i = 8; i <= 18; i += 2) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> createDamageRay(player, range, finalI, 1000, true, false, false, particles,
                            false), i);
        }
    }

    @Override
    public int getCooldown() {
        return 25;
    }

    @Override
    public String getDescription() {
        return "Slice your katana forward five\ntimes.";
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
