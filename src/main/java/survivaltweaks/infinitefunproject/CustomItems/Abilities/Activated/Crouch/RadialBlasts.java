package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.CrouchAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class RadialBlasts implements CrouchAbility {

    @Override
    public void activate(Player player) {
        player.setVelocity(player.getVelocity().add(new Vector(0, 1.6, 0)));

        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ELECTRIC_SPARK);
                add(Particle.ENCHANTED_HIT);
                add(Particle.HAPPY_VILLAGER);
                add(Particle.CRIT);
            }
        };

        for(int i = 0; i <= 8; i++) {
            int finalI = i;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                player.setRotation(45 * finalI, player.getPitch());

                createDamageRay(player, 20, 300, 1, true, false, true,
                        particles, true);
            }, i * 3);
        }
    }

    @Override
    public int getCooldown() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Launch up, spin, and blast\nattacks in all directions.";
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
