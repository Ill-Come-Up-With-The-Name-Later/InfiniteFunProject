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
        negativeEffects.add(PotionEffectType.MINING_FATIGUE);
        negativeEffects.add(PotionEffectType.SLOWNESS);
        negativeEffects.add(PotionEffectType.HUNGER);
        negativeEffects.add(PotionEffectType.POISON);
        negativeEffects.add(PotionEffectType.WEAKNESS);
        negativeEffects.add(PotionEffectType.WITHER);
        negativeEffects.add(PotionEffectType.NAUSEA);
        negativeEffects.add(PotionEffectType.DARKNESS);
        negativeEffects.add(PotionEffectType.BAD_OMEN);
        negativeEffects.add(PotionEffectType.LEVITATION);
        negativeEffects.add(PotionEffectType.UNLUCK);
        negativeEffects.add(PotionEffectType.WIND_CHARGED);
        negativeEffects.add(PotionEffectType.OOZING);
        negativeEffects.add(PotionEffectType.INFESTED);
        negativeEffects.add(PotionEffectType.WEAVING);

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
