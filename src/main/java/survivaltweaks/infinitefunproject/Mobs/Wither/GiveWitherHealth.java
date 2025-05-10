package survivaltweaks.infinitefunproject.Mobs.Wither;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;
import static survivaltweaks.infinitefunproject.World.Infection.InfectionManager.grantImmunity;

public class GiveWitherHealth implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity.getType().equals(EntityType.WITHER)) {
            Wither wither = (Wither) entity;
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            grantImmunity(wither);

            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(335 + (players.size() * 60) + (getLevel() * 5));
            wither.setHealth(wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        }
    }
}
