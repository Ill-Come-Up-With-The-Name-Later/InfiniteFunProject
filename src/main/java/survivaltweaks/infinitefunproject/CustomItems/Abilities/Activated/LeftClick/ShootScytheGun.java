package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ShootScytheGun implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        SpectralArrow arrow = player.launchProjectile(SpectralArrow.class);
        arrow.setVisibleByDefault(false);
        arrow.setVelocity(arrow.getVelocity().normalize().multiply(16));
        arrow.setDamage(0.5);
        arrow.setCritical(true);

        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                arrow.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, arrow.getLocation(), 3, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, arrow.getLocation(), 3, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shoots a fast, low damage\nprojectile.";
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
