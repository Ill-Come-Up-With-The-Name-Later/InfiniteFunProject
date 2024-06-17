package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.nearbyLivingEntities;

public class Bloodlust implements AttackAbility {

    int radius = 10;
    int percent = 25;

    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        ArrayList<LivingEntity> entities =  nearbyLivingEntities(player, radius);

        double multiplier = 1 + ((0.01 * percent) * entities.size());
        event.setDamage(event.getDamage() * multiplier);
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Deal " + percent + "% more damage per\nentity within " + radius + " blocks.";
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
