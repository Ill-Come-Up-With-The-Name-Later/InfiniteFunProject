package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;

public class ThrowGrenade implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        Snowball grenade = player.launchProjectile(Snowball.class);
        grenade.setMetadata("Explosive", new ExplosiveMeta(2, false, false));
    }

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "Tosses a grenade.";
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
