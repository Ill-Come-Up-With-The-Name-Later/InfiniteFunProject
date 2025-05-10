package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.IMeMine;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class IMeMineManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("I Me Mine", new IMeMine());
    }

    @Override
    public String description() {
        return "You cannot be\nwithin 10 blocks of\nanother player.";
    }
}
