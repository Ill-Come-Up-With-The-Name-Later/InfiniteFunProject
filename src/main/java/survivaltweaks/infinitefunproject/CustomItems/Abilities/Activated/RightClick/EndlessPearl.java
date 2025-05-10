package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

public class EndlessPearl implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        EnderPearl pearl = player.launchProjectile(EnderPearl.class);
        pearl.setGlowing(true);
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Throws an Ender Pearl.";
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
