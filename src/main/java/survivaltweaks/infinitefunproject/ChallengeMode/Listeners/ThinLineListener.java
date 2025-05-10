package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ThinLineListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof Player) {
            Player player = (Player) damaged;

            if(player.hasMetadata("Thin Line") && !player.isDead()) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(player.getHealth() < 10) {
                        player.setNoDamageTicks(0);

                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                () -> player.damage(Integer.MAX_VALUE, DamageSource.builder(DamageType.MAGIC).build()), 2);
                    }
                }, 2);
            }
        }
    }
}
