package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class WindDeflect implements Passive {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 10);

        for(Entity entity : entities) {
            if(entity instanceof Projectile) {
                Projectile projectile = (Projectile) entity;

                if(!projectile.getShooter().equals(player) && !projectile.isOnGround()) {
                    Vector vel = projectile.getLocation().toVector().subtract(player.getBoundingBox().getCenter());

                    WindCharge charge = player.launchProjectile(WindCharge.class);
                    charge.setVelocity(vel);
                    projectile.setVelocity(projectile.getVelocity().multiply(new Random().nextDouble(-0.8, -0.5)).multiply(2));

                    drawCircle(player.getLocation(), 2, Particle.GUST, 90);
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shoots a wind charge at\nincoming projectiles to\ndeflect them.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
