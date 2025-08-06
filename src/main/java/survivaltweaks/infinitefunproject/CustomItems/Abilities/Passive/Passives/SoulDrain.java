package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class SoulDrain implements Passive {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SOUL);
                add(Particle.OMINOUS_SPAWNING);
                add(Particle.ENCHANTED_HIT);
                add(Particle.SMOKE);
            }
        };

        ArrayList<Particle> healParticles = new ArrayList<>() {
            {
                add(Particle.HEART);
                add(Particle.HAPPY_VILLAGER);
            }
        };

        drawExpandingCircle(player, 2, 7, 2, 15, particles);

        for(int i = 1; i <= 7; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 2 + finalI);

                for(Entity entity : entities) {
                    if(entity instanceof Enemy && !entity.hasMetadata("BeenHit")) {
                        Enemy monster = (Enemy) entity;

                        drawCircle(monster, 1.2, Particle.DAMAGE_INDICATOR, 45);

                        monster.damage(monster.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.1, player);
                        faceEntityToAnother(monster, player);
                        ArrayList<LivingEntity> hit = createRay(monster, (int) distanceBetween(monster, player) + 1,
                                false, particles);

                        for(LivingEntity livingEntity : hit) {
                            if(livingEntity instanceof Player) {
                                Player p = (Player) livingEntity;

                                if(p.equals(player)) {
                                    player.setHealth(Math.min(player.getHealth() +
                                                    monster.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.025,
                                            player.getAttribute(Attribute.MAX_HEALTH).getBaseValue()));

                                    drawCircle(player, 1.2, healParticles, 45);
                                }
                            }
                        }

                        setHasBeenHit(monster, 60);
                    }
                }
            }, i * 15);
        }
    }

    @Override
    public int getCooldown() {
        return 240;
    }

    @Override
    public String getDescription() {
        return "Drains nearby monsters'\nsouls.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
