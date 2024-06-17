package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;

public class CallistoExplosiveArrow implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        SpectralArrow arrow = player.launchProjectile(SpectralArrow.class, player.getLocation().getDirection().normalize().multiply(3));
        arrow.setVelocity(arrow.getVelocity().multiply(4));
        arrow.setMetadata("Explosive", new ExplosiveMeta(12, false, false));
        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getDescription() {
        return "Shoots a fast, explosive arrow.";
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
