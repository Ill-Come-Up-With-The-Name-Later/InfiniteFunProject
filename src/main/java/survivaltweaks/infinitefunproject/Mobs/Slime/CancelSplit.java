package survivaltweaks.infinitefunproject.Mobs.Slime;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SlimeSplitEvent;

public class CancelSplit implements Listener {

    @EventHandler
    public void onDeath(SlimeSplitEvent event) {
        event.setCancelled(true);
    }
}
