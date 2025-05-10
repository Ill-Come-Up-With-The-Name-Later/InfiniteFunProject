package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.stun;

public class ElectricPulse implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ELECTRIC_SPARK);
                add(Particle.ENCHANTED_HIT);
            }
        };

        HashMap<LivingEntity, Boolean> hitEnemies = createDamageRay(player, 30, 65, 7, true, false, false,
                particles, false);

        for(LivingEntity entity : hitEnemies.keySet()) {
            stun(entity, 40, false);
            player.getWorld().strikeLightningEffect(entity.getLocation());
        }
    }

    @Override
    public int getCooldown() {
        return 220;
    }

    @Override
    public String getDescription() {
        return "Shoots off a pulse that stuns\nenemies and does damage.";
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
