package survivaltweaks.infinitefunproject.Mobs.EnderDragon;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;
import static survivaltweaks.infinitefunproject.World.Infection.InfectionManager.grantImmunity;

public class GiveDragonHealth implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity.getType().equals(EntityType.ENDER_DRAGON)) {
            EnderDragon dragon = (EnderDragon) entity;
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            grantImmunity(dragon);

            dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(280 + (players.size() * 60) + (getLevel() * 6));
            dragon.setHealth(dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(dragon.getWorld().getEntitiesByClass(EnderCrystal.class).size() <= 1) {
                    dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 1.33);
                    dragon.setHealth(dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                }
            }, 20);

            dragon.setGlowing(false);
            dragon.getBossBar().setColor(BarColor.PINK);
            dragon.getBossBar().setVisible(true);

            for(Player player : dragon.getWorld().getPlayers()){
                dragon.getBossBar().getPlayers().add(player);
            }
        }
    }
}
