package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.CrouchAbility;

import java.util.Collections;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class SonicBlast implements CrouchAbility {

    @Override
    public void activate(Player player) {
        int volume = new Random().nextInt(3, 6);

        createDamageRay(player, 10 * volume, 40, 999, true, true, true,
                Collections.singletonList(Particle.SONIC_BOOM), true);

        player.getWorld().playSound(player.getLocation(), Sound.values()[new Random().nextInt(0, Sound.values().length)],
                volume, (float) new Random().nextDouble(1, 2.4));
    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public String getDescription() {
        return "Emits a very loud sound.";
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
