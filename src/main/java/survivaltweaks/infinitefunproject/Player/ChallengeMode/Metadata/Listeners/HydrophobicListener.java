package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HydrophobicListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Hydrophobic") && player.isInWaterOrRainOrBubbleColumn()) {
            player.damage(Integer.MAX_VALUE, DamageSource.builder(DamageType.DROWN).build());
        }
    }
}
