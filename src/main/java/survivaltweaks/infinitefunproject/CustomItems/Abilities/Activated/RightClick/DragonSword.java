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
        ArrayList<EntityType> ignore = new ArrayList<>();
        ignore.add(EntityType.ITEM_FRAME);
        ignore.add(EntityType.DROPPED_ITEM);
        ignore.add(EntityType.GLOW_ITEM_FRAME);
        ignore.add(EntityType.FIREBALL);
        ignore.add(EntityType.SMALL_FIREBALL);
        ignore.add(EntityType.DRAGON_FIREBALL);
        ignore.add(EntityType.FIREWORK);
        ignore.add(EntityType.FISHING_HOOK);
        ignore.add(EntityType.ARMOR_STAND);
        ignore.add(EntityType.TEXT_DISPLAY);
        ignore.add(EntityType.ARROW);
        ignore.add(EntityType.SPECTRAL_ARROW);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.EGG);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.ENDER_CRYSTAL);
        ignore.add(EntityType.SHULKER_BULLET);
        ignore.add(EntityType.PLAYER);
        ignore.add(EntityType.WITHER);
        ignore.add(EntityType.ENDER_DRAGON);
        ignore.add(EntityType.AREA_EFFECT_CLOUD);
        ignore.add(EntityType.LEASH_HITCH);

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 12);

        drawCircle(player.getLocation(), 12, Particle.DRAGON_BREATH, 180);
        drawCircle(player.getLocation(), 11, Particle.SPELL_WITCH, 180);

        for (Entity entity : entities) {
            if (ignore.contains(entity.getType())) {
                continue;
            }
            for (int i = 1; i <= 5; i++) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if (entity instanceof LivingEntity) {
                        LivingEntity entity1 = (LivingEntity) entity;
                        entity1.damage(entity1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 6, player);
                        drawCircle(entity1.getLocation(), 1.2, Particle.DRAGON_BREATH, 90);
                        drawCircle(entity1.getLocation(), 1.2, Particle.SPELL_WITCH, 90);
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
