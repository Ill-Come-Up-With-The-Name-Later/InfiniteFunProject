package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

public class Vigor implements Passive {

    @Override
    public void activate(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 5, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 5, 2, false, false, false));
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Gives powerful buffs.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
