package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class DirectHitRocket implements ActivatedAbility {

    float rocketPower = 2.5f;

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(17);
        arrow.setVelocity(arrow.getVelocity().normalize().multiply(5));

        arrow.setGravity(false);
        arrow.setVisibleByDefault(false);

        arrow.setMetadata("Explosive", new ExplosiveMeta(rocketPower, false, false));
        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(!arrow.isDead()) {
                arrow.remove();
            }
        }, 180);

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                arrow.getLocation().getWorld().spawnParticle(Particle.SMOKE, arrow.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Fires a fast, explosive\nprojectile.";
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
