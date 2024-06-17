package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Collection;
import java.util.Random;

public class ExplodingAxolotl implements RandomEvent {

    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            Location loc = player.getLocation();
            player.sendMessage(ChatColor.AQUA + "The axolotl will not explode in 5 seconds...");
            Axolotl axolotl = (Axolotl) player.getWorld().spawnEntity(loc, EntityType.AXOLOTL);
            axolotl.setVariant(Axolotl.Variant.BLUE);
            axolotl.setCustomName("Non-Explosive Axolotl");
            axolotl.setCustomNameVisible(true);

            if(new Random().nextInt(0, 150) == 1) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(axolotl.isDead()) {
                            cancel();
                            return;
                        }

                        axolotl.getWorld().createExplosion(axolotl.getLocation(), 2.1f, false, false);
                    }
                }.runTaskLater(InfiniteFunProject.plugin, 120);
            }
        }
    }
}
