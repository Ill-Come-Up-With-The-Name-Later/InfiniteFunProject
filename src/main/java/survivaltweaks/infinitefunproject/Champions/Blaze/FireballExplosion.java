package survivaltweaks.infinitefunproject.Champions.Blaze;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttackedAbility;
import survivaltweaks.infinitefunproject.MonsterAbilities.OnMobAttacksAbility;

public class FireballExplosion implements Listener {

    @EventHandler
    public void projectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Blaze) {
            Blaze blaze = (Blaze) projectile.getShooter();

            if(blaze.hasMetadata("Champion")) {
                projectile.getWorld().createExplosion(projectile.getLocation(), 1.3f, true, false, blaze);
            }
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Blaze) {
            Monster monster = (Monster) spawned;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(monster.hasMetadata("Champion")) {
                    OnMobAttacksAbility.addAbility(monster, OnMobAttacksAbility.SCORCH_TARGET);
                    OnMobAttackedAbility.addAbility(monster, OnMobAttackedAbility.IGNITE_ATTACKER);
                }
            }, 2);
        }
    }
}
