package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class NoArmorListener implements Listener {

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("NoArmor")) {
            player.getInventory().setArmorContents(new ItemStack[] {});
        }
    }
}
