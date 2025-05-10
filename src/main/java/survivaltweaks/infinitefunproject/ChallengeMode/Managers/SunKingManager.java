package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.SunKing;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class SunKingManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Sun King", new SunKing());
    }

    @Override
    public String description() {
        return "You must always be\nin an area of light\nlevel 9 or brighter.";
    }
}
