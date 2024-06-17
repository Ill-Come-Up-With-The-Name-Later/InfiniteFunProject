package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Triggered;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggerAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DoubleJumpAbility implements TriggerAbility {
    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), 1.5, Particle.WHITE_SMOKE, 90);
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Jumps in mid air.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
