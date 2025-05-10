package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class SoberListener implements Listener {

    @EventHandler
    public void onPotionActive(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(player.hasMetadata("Sober")) {
                for(PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }, 2);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(player.hasMetadata("Sober")) {
                for(PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }, 2);
    }
}
