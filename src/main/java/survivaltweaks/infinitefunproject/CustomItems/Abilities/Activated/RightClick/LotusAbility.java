package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class LotusAbility implements ActivatedAbility {
    int radius = 10;

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);

        arrow.setVelocity(new Vector(0, 0, 0));
        arrow.teleport(player.getLocation().add(0, 1, 0));
        arrow.setVisibleByDefault(false);
        arrow.setGravity(false);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, arrow::remove, 370);

        for(int i = 1; i <= 3; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                drawCircle(arrow.getLocation(), radius, Particle.HAPPY_VILLAGER, 90);

                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(arrow, radius);

                for(Entity entity : entities) {
                    if(entity instanceof Player) {
                        Player other = (Player) entity;

                        other.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 3,
                                false, false, false));
                    }
                }
            }, i * 120);
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.getTicksLived() > 370 || arrow.isOnGround()) {
                    arrow.remove();
                }

                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                arrow.getLocation().getWorld().spawnParticle(Particle.HAPPY_VILLAGER, arrow.getLocation(), 4, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.CHERRY_LEAVES, arrow.getLocation(), 3, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @Override
    public int getCooldown() {
        return 1400;
    }

    @Override
    public String getDescription() {
        return "Spawns a lotus that heals players.";
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
