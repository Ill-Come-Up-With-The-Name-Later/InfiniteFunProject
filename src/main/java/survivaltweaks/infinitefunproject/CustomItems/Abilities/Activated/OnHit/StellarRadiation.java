package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.applyDOT;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.setFire;

public class StellarRadiation implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        applyDOT(player, entity, 175, 7, 15);
        setFire(entity, 450);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

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
        return "Applies a burning damage\nover time.";
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
