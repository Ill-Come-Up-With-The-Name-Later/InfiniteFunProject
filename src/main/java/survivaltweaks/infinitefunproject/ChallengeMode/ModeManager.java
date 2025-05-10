package survivaltweaks.infinitefunproject.ChallengeMode;

import org.bukkit.entity.Player;

public interface ModeManager {

    void apply(Player player);
    String description();
}
