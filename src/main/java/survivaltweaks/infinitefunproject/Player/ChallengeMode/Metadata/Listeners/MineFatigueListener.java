package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MineFatigueListener implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("MineFatigue")) {
            event.setCancelled(true);
        }
    }
}
