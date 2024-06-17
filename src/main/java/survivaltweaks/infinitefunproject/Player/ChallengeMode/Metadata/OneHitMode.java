package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeMode;

public class OneHitMode implements MetadataValue {
    @Override
    public @Nullable Object value() {
        return ChallengeMode.ONE_HIT.getDataString();
    }

    @Override
    public int asInt() {
        return 764;
    }

    @Override
    public float asFloat() {
        return 764;
    }

    @Override
    public double asDouble() {
        return 764;
    }

    @Override
    public long asLong() {
        return 764;
    }

    @Override
    public short asShort() {
        return 764;
    }

    @Override
    public byte asByte() {
        return 31;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public @NotNull String asString() {
        return ChallengeMode.ONE_HIT.getDataString();
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
