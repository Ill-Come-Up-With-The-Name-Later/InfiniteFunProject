package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class BadArmListener implements Listener {

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if(event.getEntity().getShooter() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity().getShooter();

            if(entity.hasMetadata("Bad Arm")) {
                event.setCancelled(true);
            }
        }
    }
}
