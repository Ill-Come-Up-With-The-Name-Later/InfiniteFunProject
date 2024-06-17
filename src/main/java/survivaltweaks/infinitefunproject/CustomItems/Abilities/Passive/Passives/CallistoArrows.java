package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class CallistoArrows implements Passive, Listener {

    @Override
    public void activate(Player player) {}

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shoots seeking, piercing, explosive\narrows.\n\nThe arrows explode after 10\nseconds.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(!(projectile.getShooter() instanceof Player)) {
            return;
        }

        Player player = (Player) projectile.getShooter();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.equals(ItemManager.callisto)) {
            AbstractArrow arrow = (AbstractArrow) projectile;

            arrow.setPierceLevel(10);
            arrow.setMetadata("Explosive", new ExplosiveMeta(3.6f, false, false));
            arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
            arrow.setMetadata("Seeking", new SeekingMetadata(10, 200, InfiniteFunProject.allExceptPlayers()));

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!arrow.isDead()) {
                    arrow.getWorld().createExplosion(arrow.getLocation(),3.6f, false, false, player);
                    arrow.remove();
                }
            }, 199);

            Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
                if(!projectile.isDead() && !projectile.isOnGround()) {
                    projectile.getWorld().spawnParticle(Particle.SPELL_WITCH, projectile.getLocation(),
                            4, 0.2, 0.2, 0.2, 0.05);
                    projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, projectile.getLocation(),
                            4, 0.2, 0.2, 0.2, 0.05);
                    projectile.getWorld().spawnParticle(Particle.CRIT_MAGIC, projectile.getLocation(),
                            4, 0.2, 0.2, 0.2, 0.05);
                }
            }, 1, 1);
        }
    }
}
