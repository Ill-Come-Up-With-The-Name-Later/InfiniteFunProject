package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

public class SwordSpirit implements AttackAbility {

    @Override
    public void activate(Player player) {}

    @Override
    public void activate(Player player, LivingEntity entity) {
        if(player.hasPotionEffect(PotionEffectType.ABSORPTION)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, player.getPotionEffect(PotionEffectType.ABSORPTION).getDuration(),
                    player.getPotionEffect(PotionEffectType.ABSORPTION).getAmplifier() + 1, false, false, false));
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 40, 1, false, false, false));
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Grants a small absorption shield.";
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
