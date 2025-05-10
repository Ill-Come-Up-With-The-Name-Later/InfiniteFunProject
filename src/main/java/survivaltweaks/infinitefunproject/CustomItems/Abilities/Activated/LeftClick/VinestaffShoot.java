package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.addProjectileTrail;

public class VinestaffShoot implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVisibleByDefault(false);
        arrow.setVelocity(arrow.getVelocity().normalize().multiply(10));
        arrow.setDamage(1);

        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                addProjectileTrail(arrow, Particle.CHERRY_LEAVES);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Shoots fast, low damage petals.";
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
