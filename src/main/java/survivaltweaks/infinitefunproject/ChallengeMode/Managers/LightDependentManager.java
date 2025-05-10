package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.LightDependent;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class LightDependentManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Light Dependent", new LightDependent());
    }

    @Override
    public String description() {
        return "Your maximum health\ndepends upon light level.";
    }
}
