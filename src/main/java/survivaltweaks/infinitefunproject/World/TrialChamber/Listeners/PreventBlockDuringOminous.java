package survivaltweaks.infinitefunproject.World.TrialChamber.Listeners;

import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.potion.PotionEffectType;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class PreventBlockDuringOminous implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(player.hasPotionEffect(PotionEffectType.TRIAL_OMEN)) {
            event.setBuild(false);
            event.setCancelled(true);

            drawCircle(block.getLocation(), 1.2, Particle.WITCH, 45);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(player.hasPotionEffect(PotionEffectType.TRIAL_OMEN)) {
            event.setCancelled(true);

            drawCircle(block.getLocation(), 1.2, Particle.WITCH, 45);
        }
    }
}
