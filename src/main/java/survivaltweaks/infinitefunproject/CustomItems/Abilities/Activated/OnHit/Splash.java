package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Splash implements AttackAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.WATER_BUBBLE);
                add(Particle.CRIT_MAGIC);
                add(Particle.END_ROD);
                add(Particle.WATER_WAKE);
            }
        };

        if(!player.isInWater()) {
            for(int i = 1; i < 10; i++) {
                int finalI = i;
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    double range = finalI + 0.5;
                    drawCircle(player.getLocation(), range, particles, 90);

                    ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, range);

                    for(Entity entity : entities) {
                        if(entity instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity) entity;

                            if(!living.hasMetadata("BeenHit")) {
                                setHasBeenHit(living, 40);
                                living.damage(18, player);
                            }
                        }
                    }
                }, i * 2);
            }
        }
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    @Override
    public void activate(Player player, LivingEntity entity) {}

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public String getDescription() {
        return "Causes a small wave that deals\ndamage.\n\nOnly works while not in water.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}
