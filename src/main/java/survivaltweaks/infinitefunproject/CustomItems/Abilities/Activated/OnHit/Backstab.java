package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Backstab implements AttackAbility {
    @Override
    public void activate(Player player) {}

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @Override
    public void activate(Player player, LivingEntity entity) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(facingSameWay(player, entity) && !entity.isDead() && !entity.hasMetadata("BeenHit")) {
                taxEvade(player, 2, false);

                entity.damage(entity.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 6, player);
                setHasBeenHit(entity, 5);
            }
        }, 1);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Backstabs instant kill.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}
