package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.rotateVector;

public class TripleShot implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        for(int i = -1; i <= 1; i++) {
            AbstractArrow arrow;

            if(player.getInventory().contains(Material.SPECTRAL_ARROW)) {
                arrow = player.launchProjectile(SpectralArrow.class);
            } else {
                arrow = player.launchProjectile(Arrow.class);
            }

            arrow.setDamage(12);
            arrow.setPierceLevel(4);

            arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());

            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                arrow.setVelocity(rotateVector(arrow.getVelocity(), Math.toRadians(18 * finalI)));
            }, 1);
        }
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Shoots three arrows at\na time.";
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
