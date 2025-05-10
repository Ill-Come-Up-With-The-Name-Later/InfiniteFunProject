package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

public class MagicExplosive implements KillAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player) {
        player.getWorld().createExplosion(player, 4, false, false);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getDescription() {
        return "Enemies blow up when killed.";
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
