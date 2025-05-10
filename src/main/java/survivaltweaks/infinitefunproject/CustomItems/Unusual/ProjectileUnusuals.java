package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.unusualLine;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.addProjectileTrail;

public class ProjectileUnusuals implements Listener {

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Player) {
           Player player = (Player) projectile.getShooter();

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

                addProjectileTrail(projectile, unusual);
            }
        }
    }
}
