package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class GuidedMissileLaunch implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> missileTrail = new ArrayList<>() {
            {
                add(Particle.SMOKE);
                add(Particle.CRIT);
                add(Particle.FLAME);
                add(Particle.WHITE_SMOKE);
            }
        };

        for(int i = 1; i <= 15; i++) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Arrow missile = player.launchProjectile(Arrow.class);
                missile.setRotation(missile.getYaw(), 90);

                drawExpandingCircle(player, 1.5, 4, 0.5, 7, missileTrail);

                missile.setVelocity(player.getLocation().getDirection().multiply(3.2).setY(3.6));
                missile.setDamage(360);
                missile.setGlowing(true);

                missile.setMetadata("Explosive", new ExplosiveMeta(24f, true, false));
                missile.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
                addProjectileTrail(missile, Unusual.ROCKET_TRAIL);

                ArrayList<LivingEntity> entities = nearbyLivingEntities(player, 100);

                Optional<LivingEntity> target = entities.stream().filter(x -> !x.isDead() && x.hasLineOfSight(missile))
                                .min(Comparator.comparing(x -> x.getLocation().distanceSquared(player.getLocation())));

                if(target.isPresent()) {
                    LivingEntity entity = target.get();

                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        missile.setVelocity(new Vector(0, 0, 0));
                        faceEntityToAnother(missile, entity);

                        Vector direction = missile.getLocation().getDirection();
                        direction.add(new Vector(
                                new Random().nextDouble(-0.25, 0.25),
                                new Random().nextDouble(-0.25, 0.25),
                                new Random().nextDouble(-0.25, 0.25)
                        ));

                        missile.getLocation().setDirection(direction);

                        createRay(missile, (int) (distanceBetween(missile, entity) + 1), false, missileTrail);

                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                            Vector velocity = entity.getBoundingBox().getMin().subtract(missile.getLocation().toVector());
                            missile.setVelocity(velocity);

                            entity.setNoDamageTicks(0);
                        }, 2);
                    }, 8);
                } else {
                    Bukkit.getScheduler().runTaskLater(plugin, () ->
                            missile.setVelocity(new Vector(
                            new Random().nextDouble(-4.5, 4.5),
                            new Random().nextDouble(-0.2, 1),
                            new Random().nextDouble(-4.5, 4.5))), 8);
                }
            }, i * 13);
        }
    }

    @Override
    public int getCooldown() {
        return 1800;
    }

    @Override
    public String getDescription() {
        return "Launches 15 guided missiles\nat nearby enemies.\n\nMissiles destroy blocks.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
