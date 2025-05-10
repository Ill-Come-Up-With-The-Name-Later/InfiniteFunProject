package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.RandomDamage;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class RandomDamageManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Lucky Numbers", new RandomDamage());
    }

    @Override
    public String description() {
        return "Damage tax is reduced by\n50%, but your damage is\nmultiplied by a random\namount.";
    }
}
