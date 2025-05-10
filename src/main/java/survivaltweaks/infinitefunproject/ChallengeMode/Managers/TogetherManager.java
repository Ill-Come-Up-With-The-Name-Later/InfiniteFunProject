package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.ComeTogether;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class TogetherManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Come Together", new ComeTogether());
    }

    @Override
    public String description() {
        return "You must always be\nwithin 25 blocks of a\nliving entity.";
    }
}
