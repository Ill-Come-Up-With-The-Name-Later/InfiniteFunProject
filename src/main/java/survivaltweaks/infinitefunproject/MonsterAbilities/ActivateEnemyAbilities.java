package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class ActivateEnemyAbilities implements Listener {

    @EventHandler
    public void activateOnAttackedAbilities(EntityDamageByEntityEvent event) {
        Entity attacked = event.getEntity();
        Entity attacker = event.getDamager();

        if(attacked instanceof Monster) {
            Monster monster =  (Monster) attacked;

            for(OnMobAttackedAbility ability : OnMobAttackedAbility.values()) {
                if(OnMobAttackedAbility.hasAbility(monster, ability)) {
                    ability.getMonsterAbility().activate(monster);

                    if(attacker instanceof LivingEntity) {
                        ability.getMonsterAbility().activate(monster, (LivingEntity) attacker);
                    }

                    ability.getMonsterAbility().activate(event);
                }
            }
        }
    }

    @EventHandler
    public void activateOnAttackAbilities(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity attacked = event.getEntity();

        if(attacker instanceof Monster) {
            Monster monster = (Monster) attacker;

            for(OnMobAttacksAbility ability : OnMobAttacksAbility.values()) {
                if(OnMobAttacksAbility.hasAbility(monster, ability)) {
                    ability.getMonsterAbility().activate(monster);

                    if(attacked instanceof LivingEntity) {
                        ability.getMonsterAbility().activate(monster, (LivingEntity) attacked);
                    }

                    ability.getMonsterAbility().activate(event);
                }
            }
        }
    }

    @EventHandler
    public void activateOnAttackAbilitiesFromProjectiles(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity attacked = event.getEntity();

        if(attacker instanceof Projectile) {
            Projectile projectile = (Projectile) attacker;

            if(projectile.getShooter() instanceof Monster) {
                Monster monster = (Monster) projectile.getShooter();

                for(OnMobAttacksAbility ability : OnMobAttacksAbility.values()) {
                    if(OnMobAttacksAbility.hasAbility(monster, ability)) {
                        ability.getMonsterAbility().activate(monster);

                        if(attacked instanceof LivingEntity) {
                            ability.getMonsterAbility().activate(monster, (LivingEntity) attacked);
                        }

                        ability.getMonsterAbility().activate(event);
                    }
                }
            }
        }
    }

    @EventHandler
    public void activateTargetAbilities(EntityTargetEvent event) {
        Entity entity = event.getEntity();
        Entity target = event.getTarget();

        if(!(target instanceof LivingEntity)) {
            return;
        }

        if(!(entity instanceof Monster)) {
            return;
        }

        Monster monster = (Monster) entity;
        LivingEntity living = (LivingEntity) target;

        if(OnEntityTargetAbility.hasAbility(monster)) {
            for(OnEntityTargetAbility ability : OnEntityTargetAbility.values()) {
                if(OnEntityTargetAbility.hasAbility(monster, ability)) {
                    ability.getMonsterAbility().activate(monster, living);
                    ability.getMonsterAbility().activate(monster);
                }
            }
        }
    }
}
