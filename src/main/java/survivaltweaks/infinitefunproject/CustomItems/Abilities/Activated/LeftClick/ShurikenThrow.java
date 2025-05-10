package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.BackstabMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ShurikenThrow implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        for(int i = 1; i <= 3; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                Arrow arrow = player.launchProjectile(Arrow.class);
                arrow.setVisibleByDefault(false);
                arrow.setVelocity(arrow.getVelocity().normalize().multiply(4.2));
                arrow.setDamage(2.5);
                arrow.setPierceLevel(4);

                arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
                arrow.setMetadata("Backstab", new BackstabMeta());

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(arrow.isDead()) {
                            cancel();
                            return;
                        }

                        if(arrow.isOnGround()) {
                            arrow.remove();
                        }

                        arrow.getLocation().getWorld().spawnParticle(Particle.SMOKE, arrow.getLocation(), 8, 0.1, 0.1, 0.1, 0.02);
                        arrow.getLocation().getWorld().spawnParticle(Particle.ELECTRIC_SPARK, arrow.getLocation(), 8, 0.1, 0.1, 0.1, 0.02);
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
            }, i * 8);
        }
    }

    @Override
    public int getCooldown() {
        return 25;
    }

    @Override
    public String getDescription() {
        return "Throws three piercing shurikens.";
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
