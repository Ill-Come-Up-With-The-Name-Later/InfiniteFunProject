package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DecrepitScroll implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVisibleByDefault(false);
        arrow.setVelocity(arrow.getVelocity().multiply(2));
        arrow.setPierceLevel(3);
        arrow.setDamage(15);
        arrow.setMetadata("Seeking", new SeekingMetadata(8, 380, InfiniteFunProject.hostiles()));
        arrow.addCustomEffect(PotionEffectType.SLOWNESS.createEffect(200, 5), true);

        drawCircle(player.getLocation(), 1.4, Particle.SMOKE, 90);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(arrow.isDead() || isCancelled()) {
                    cancel();
                    return;
                }
                arrow.getWorld().spawnParticle(Particle.SMOKE, arrow.getLocation(), 7, 0.02, 0.02, 0.02, 0.07);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(arrow.isDead() || isCancelled()) {
                    cancel();
                    return;
                }

                arrow.getWorld().createExplosion(arrow.getLocation(), 8f, false, false, player);
                arrow.remove();
            }
        }.runTaskLater(InfiniteFunProject.plugin, 100);
    }

    @Override
    public int getCooldown() {
        return 180;
    }

    @Override
    public String getDescription() {
        return "Shoots off a high damage,\nseeking, piercing projectile\nto attack your foes.\n\nExplodes after 5 seconds.";
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
