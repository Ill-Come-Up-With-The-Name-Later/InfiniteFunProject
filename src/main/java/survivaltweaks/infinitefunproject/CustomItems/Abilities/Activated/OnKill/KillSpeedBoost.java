package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;

public class KillSpeedBoost implements KillAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {

    }

    @Override
    public void activate(Player player) {
        if(player.hasPotionEffect(PotionEffectType.SPEED)) {
            int amplifier = player.getPotionEffect(PotionEffectType.SPEED).getAmplifier();

            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, amplifier + 1, false, false, true));
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0, false, false, true));
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Grants the player Speed. If the\nplayer has Speed, the level will\nincrease.";
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
