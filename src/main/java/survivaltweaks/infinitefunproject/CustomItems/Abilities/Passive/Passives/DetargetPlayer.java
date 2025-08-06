package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DetargetPlayer implements Passive {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 12);

        for(Entity entity : entities) {
            if(!(entity instanceof Enemy || entity instanceof Projectile)) {
                continue;
            }

            Mob enemy = null;

            if(entity instanceof Projectile) {
                Projectile projectile = (Projectile) entity;

                if(projectile.getShooter() instanceof Enemy) {
                    enemy = (Mob) projectile.getShooter();
                }
            }

            if(entity instanceof Enemy) {
                enemy = (Mob) entity;
            }

            if(enemy == null) {
                continue;
            }

            if(enemy.getTarget() == null) {
                continue;
            }

            if(enemy.getTarget().equals(player)) {
                ArrayList<Entity> targets = (ArrayList<Entity>)
                        circularNearbyEntities(enemy, enemy.getAttribute(Attribute.FOLLOW_RANGE).getBaseValue());

                Mob finalEnemy = enemy;
                Mob finalEnemy2 = enemy;
                Optional<Entity> target = targets.stream().filter(x -> x instanceof Enemy && !x.equals(player)
                                && !x.equals(finalEnemy))
                        .min(Comparator.comparing(x -> x.getLocation().distanceSquared(finalEnemy2.getLocation())));

                Mob finalEnemy1 = enemy;
                target.ifPresent(value -> finalEnemy1.setTarget((LivingEntity) value));

                if(!target.isPresent()) {
                    enemy.setTarget(null);
                }

                drawCircle(entity, 1.2, Particle.WITCH, 90);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Enemies that come near lose\ninterest.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
