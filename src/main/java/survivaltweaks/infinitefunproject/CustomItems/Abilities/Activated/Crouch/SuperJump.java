package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.CrouchAbility;

public class SuperJump implements CrouchAbility {

    @Override
    public void activate(Player player) {
        player.setVelocity(player.getVelocity().multiply(player.getVelocity().lengthSquared()).add(new Vector(0, 1, 0)));
    }

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "Jumps high into the air.";
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
