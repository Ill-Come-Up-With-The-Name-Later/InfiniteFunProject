package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DragonSword implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 12);

        drawCircle(player.getLocation(), 12, Particle.DRAGON_BREATH, 180);
        drawCircle(player.getLocation(), 11, Particle.WITCH, 180);

        for(Entity entity : entities) {
            for(int i = 1; i <= 5; i++) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(entity instanceof LivingEntity) {
                        LivingEntity entity1 = (LivingEntity) entity;
                        entity1.damage(entity1.getAttribute(Attribute.MAX_HEALTH).getBaseValue() / 6, player);

                        drawCircle(entity1.getLocation(), 1.2, Particle.DRAGON_BREATH, 90);
                        drawCircle(entity1.getLocation(), 1.2, Particle.WITCH, 90);
                    }
                }, i * 20);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 220;
    }

    @Override
    public String getDescription() {
        return "Deals 16.66% of enemies' health as\ndamage 5 times.";
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
