package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Optional;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.unusualLine;

public class WeaponUnusual implements Listener {

    @EventHandler
    public void onKill(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(entity.isDead()) {
                if(damager instanceof Player || damager instanceof Projectile) {
                    Player player = null;

                    if(damager instanceof Projectile) {
                        Projectile projectile = (Projectile) damager;

                        if(projectile.getShooter() instanceof Player) {
                            player = (Player) projectile.getShooter();
                        } else {
                            return;
                        }
                    }

                    if(damager instanceof Player) {
                        player = (Player) damager;
                    }

                    if(player == null) {
                        return;
                    }

                    ItemStack item = player.getInventory().getItemInMainHand();

                    if(!item.hasItemMeta()) {
                        return;
                    }

                    if(item.getItemMeta().getLore() == null) {
                        return;
                    }

                    if (!hasUnusual(item)) {
                        return;
                    }

                    int unusualLine = unusualLine(item);

                    if (unusualLine == -1) {
                        return;
                    }

                    String effect = item.getItemMeta().getLore().get(unusualLine).substring(24);
                    Optional<Unusual> unusualOptional = Optional.ofNullable(Unusual.getByName(effect));

                    if(unusualOptional.isPresent()) {
                        Unusual unusual = unusualOptional.get();
                        for(Particle particle : unusual.getParticles()) {
                            Location spawnLoc = entity.getLocation();
                            spawnLoc.setY(spawnLoc.getY() + 0.66);

                            if(particle == Particle.DUST) {
                                if(unusual.getDustOptions() != null) {
                                    player.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.03,
                                            unusual.getDustOptions(), true);
                                    continue;
                                }
                            }

                            player.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.03, null, true);
                        }
                    }
                }
            }
        }, 2);
    }
}
