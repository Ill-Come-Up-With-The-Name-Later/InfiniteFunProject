package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.doCrit;

public class DamageFromVelocity implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        double speed = player.getPotionEffect(PotionEffectType.SPEED) == null ? 0
                : player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() * 20;
        event.setDamage(speed);

        if(speed >= 120) {
            doCrit(event);
        }

        //player.sendMessage("Speed: " + speed);
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
        return "Damage increases with Speed\neffect level.\n\nSpeed VII gives guaranteed\nCrits.";
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
