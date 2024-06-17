package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class RocketBarrage implements ActivatedAbility {
    int radius = 7;
    float rocketPower = 5.2f;

    @Override
    public void activate(Player player) {
        for(int i = 1; i < 100; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                drawCircle(player.getLocation(), radius, Particle.CRIT_MAGIC, 90);
                drawCircle(player.getLocation(), radius, Particle.CRIT, 90);
                drawCircle(player.getLocation(), radius, Particle.SPELL_WITCH, 90);
                drawCircle(player.getLocation(), radius, Particle.SMOKE_NORMAL, 90);

                Arrow arrow = player.launchProjectile(Arrow.class);
                arrow.setGravity(false);
                arrow.setVisibleByDefault(false);

                arrow.setMetadata("Explosive", new ExplosiveMeta(rocketPower, false, false));
                arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());

                arrow.teleport(arrow.getLocation().add(new Random().nextInt(-radius, radius), 7, new Random().nextInt(-radius, radius)));
                arrow.setVelocity(new Vector(0, -3, 0));

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(!arrow.isDead()) {
                        arrow.remove();
                    }
                }, 100);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(arrow.isDead()) {
                            cancel();
                            return;
                        }

                        arrow.getLocation().getWorld().spawnParticle(Particle.SMOKE_NORMAL, arrow.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
                        arrow.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, arrow.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
                        arrow.getLocation().getWorld().spawnParticle(Particle.CRIT, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
            }, i * 2);
        }
    }

    @Override
    public int getCooldown() {
        return 800;
    }

    @Override
    public String getDescription() {
        return "Rain down rockets down within a\n" + radius + " block radius.";
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
