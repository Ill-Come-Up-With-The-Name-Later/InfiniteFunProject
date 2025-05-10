package survivaltweaks.infinitefunproject.ChallengeMode.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.ChallengeMode.ChallengeMode;

public class MineFatigue implements MetadataValue {
    @Override
    public @Nullable Object value() {
        return ChallengeMode.MINE_FATIGUE.getDataString();
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public @NotNull String asString() {
        return ChallengeMode.MINE_FATIGUE.getDataString();
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
