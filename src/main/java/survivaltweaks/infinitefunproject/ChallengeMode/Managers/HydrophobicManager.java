package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.Hydrophobic;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class HydrophobicManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Hydrophobic", new Hydrophobic());
    }

    @Override
    public String description() {
        return "You cannot go into\nwater.";
    }
}
