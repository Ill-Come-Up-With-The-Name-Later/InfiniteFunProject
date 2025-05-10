package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class Litigation implements ActivatedAbility {
    double radius = 4.5;

    @Override
    public void activate(Player player) {
        for(int i = 1; i <= 20; i++) {
            int finalI = i;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

                drawCircle(player.getLocation(), radius, Particle.ENCHANTED_HIT, 90);
                drawCircle(player.getLocation(), radius, Particle.WITCH, 90);
                drawCircle(player.getLocation(), radius, Particle.SWEEP_ATTACK, 45);

                for(Entity entity : nearby) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity) entity;

                        if(finalI == 20) {
                            living.damage((6 * finalI) + 15, player);
                        } else {
                            living.damage(6 * finalI, player);
                        }

                        player.setHealth(player.getHealth() + 2);
                    }
                }
            }, i * 6);
        }
    }

    @Override
    public int getCooldown() {
        return 1000;
    }

    @Override
    public String getDescription() {
        return "Swing your hammer around 20 times\ndealing increased damage each\nsubsequent spin and gaining\nhealth for every enemy hit.";
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
