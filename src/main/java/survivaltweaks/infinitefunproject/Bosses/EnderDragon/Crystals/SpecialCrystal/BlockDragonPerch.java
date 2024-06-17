package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal;

import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;

import java.util.ArrayList;

public class BlockDragonPerch implements Listener {

    @EventHandler
    public void onDragonPhaseChange(EnderDragonChangePhaseEvent event) {
        EnderDragon dragon = event.getEntity();
        EnderDragon.Phase newPhase = event.getNewPhase();

        ArrayList<EnderCrystal> entities = (ArrayList<EnderCrystal>) dragon.getWorld().getEntitiesByClass(EnderCrystal.class);

        if((newPhase == EnderDragon.Phase.LAND_ON_PORTAL || newPhase == EnderDragon.Phase.FLY_TO_PORTAL) && !entities.isEmpty()) {
            dragon.setPhase(EnderDragon.Phase.CIRCLING);
        }
    }
}
