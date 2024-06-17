package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.IMeMine;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class IMeMineManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("IMeMine", new IMeMine());
    }

    @Override
    public String description() {
        return "You cannot be\nwithin 10 blocks of\nanother player.";
    }
}
