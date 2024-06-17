package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;

public class EasterEgg implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        Egg egg = player.launchProjectile(Egg.class);
        egg.setMetadata("Explosive", new ExplosiveMeta( 3f, false, false));
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Throws an explosive egg.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
