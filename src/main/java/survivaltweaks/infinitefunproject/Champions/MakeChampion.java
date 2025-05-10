package survivaltweaks.infinitefunproject.Champions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import survivaltweaks.infinitefunproject.Mobs.OnSpawn;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.Random;

import static survivaltweaks.infinitefunproject.Champions.ChampionInit.makeChampion;

public class MakeChampion implements Listener {

    int baseChance = 300;

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(OnSpawn.getLevel(entity) < 12) {
            return;
        }

        if(entity instanceof Monster) {
            if(WorldModInit.modifierActive(WorldModifier.ULTIMATE_POWER)
            || WorldModInit.modifierActive(WorldModifier.ANOMALOUS)) {
                if(new Random().nextInt(0, baseChance / 2) == 1) {
                    makeChampion((Monster) entity);
                }
            } else {
                if(new Random().nextInt(0, baseChance) == 1) {
                    makeChampion((Monster) entity);
                }
            }
        }
    }
}
