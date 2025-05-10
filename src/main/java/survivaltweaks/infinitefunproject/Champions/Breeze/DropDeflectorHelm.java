package survivaltweaks.infinitefunproject.Champions.Breeze;

import org.bukkit.Bukkit;
import org.bukkit.entity.Breeze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttacksAbility;

public class DropDeflectorHelm implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Breeze) {
            Monster monster = (Monster) spawned;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(monster.hasMetadata("Champion")) {
                    OnMobAttacksAbility.addAbility(monster, OnMobAttacksAbility.WIND_CHARGE_TARGET);
                }
            }, 2);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity instanceof Breeze && entity.hasMetadata("Champion")) {
            event.getDrops().add(ItemManager.deflectorHelmet);
        }
    }
}
