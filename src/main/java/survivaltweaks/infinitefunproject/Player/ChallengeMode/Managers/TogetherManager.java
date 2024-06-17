package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.ComeTogether;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class TogetherManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("ComeTogether", new ComeTogether());
    }

    @Override
    public String description() {
        return "You must always be\nwithin 25 blocks of a\nliving entity.";
    }
}
