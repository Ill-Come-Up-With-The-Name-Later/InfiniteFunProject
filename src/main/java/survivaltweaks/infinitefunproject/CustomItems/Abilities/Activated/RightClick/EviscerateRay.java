package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RCAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class EviscerateRay implements ActivatedAbility {
    int range = 100;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.CRIT_MAGIC);
                add(Particle.FIREWORKS_SPARK);
                add(Particle.SOUL_FIRE_FLAME);
            }
        };

        HashMap<LivingEntity, Boolean> hitEntities =
                createDamageRay(player, range, 2_000_000_000, 10000, false, true, false, particles,
                        false);

        for(LivingEntity entity : hitEntities.keySet()) {
            if(entity.hasMetadata("ProjShield")) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                        () -> setCooldown(player, RCAbility.EVISCERATION_BEAM, 1), 2);
                break;
            }

            if(hitEntities.get(entity)) {
                drawCircle(entity.getLocation(), 4, Particle.SOUL_FIRE_FLAME, 90);
                drawCircle(entity.getLocation(), 4, Particle.DAMAGE_INDICATOR, 90);
                drawCircle(entity.getLocation(), 4, Particle.CRIT_MAGIC, 90);
                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(entity, 4);

                for(Entity e : entities) {
                    if(e instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity) e;
                        living.damage(30, player);
                    }
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 2400;
    }

    @Override
    public String getDescription() {
        return "Shoots a super high damage laser\nthat instantly kills anything.\n\n" +
                "If your laser hits a shielded enemy,\ndestroy their shield and gain\nanother shot.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
