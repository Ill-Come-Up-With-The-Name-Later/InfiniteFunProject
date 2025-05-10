package survivaltweaks.infinitefunproject.Champions.Spider;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttackedAbility;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttacksAbility;

public class WebPlayers implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Spider) {
            Monster monster = (Monster) spawned;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
               if(spawned.hasMetadata("Champion")) {
                   OnMobAttacksAbility.addAbility(monster, OnMobAttacksAbility.WEB_TARGET);
                   OnMobAttackedAbility.addAbility(monster, OnMobAttackedAbility.WEB_ATTACKER);
               }
            }, 2);
        }
    }
}
