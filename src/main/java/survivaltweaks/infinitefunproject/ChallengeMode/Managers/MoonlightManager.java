package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.MrMoonlight;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class MoonlightManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Mr. Moonlight", new MrMoonlight());
    }

    @Override
    public String description() {
        return "You must always be\nin an area of light\nlevel 6 or dimmer.";
    }
}
