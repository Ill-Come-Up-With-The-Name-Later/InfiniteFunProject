package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

public class Tea implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<PotionEffectType> negativeEffects = new ArrayList<>();
        negativeEffects.add(PotionEffectType.BLINDNESS);
        negativeEffects.add(PotionEffectType.SLOW_DIGGING);
        negativeEffects.add(PotionEffectType.SLOW);
        negativeEffects.add(PotionEffectType.HUNGER);
        negativeEffects.add(PotionEffectType.POISON);
        negativeEffects.add(PotionEffectType.WEAKNESS);
        negativeEffects.add(PotionEffectType.WITHER);
        negativeEffects.add(PotionEffectType.CONFUSION);
        negativeEffects.add(PotionEffectType.DARKNESS);
        negativeEffects.add(PotionEffectType.BAD_OMEN);
        negativeEffects.add(PotionEffectType.LEVITATION);
        negativeEffects.add(PotionEffectType.UNLUCK);

        for(PotionEffect effect : player.getActivePotionEffects()) {
            if(negativeEffects.contains(effect.getType())) {
                player.removePotionEffect(effect.getType());
            }
        }
    }

    @Override
    public int getCooldown() {
        return 540;
    }

    @Override
    public String getDescription() {
        return "Clears all negative effects.";
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
