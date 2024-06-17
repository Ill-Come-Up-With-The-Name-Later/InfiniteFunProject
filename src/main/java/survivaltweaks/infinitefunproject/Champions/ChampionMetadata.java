package survivaltweaks.infinitefunproject.Champions;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ChampionMetadata implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "Champion";
    }

    @Override
    public int asInt() {
        return 11;
    }

    @Override
    public float asFloat() {
        return 11;
    }

    @Override
    public double asDouble() {
        return 11;
    }

    @Override
    public long asLong() {
        return 11;
    }

    @Override
    public short asShort() {
        return 11;
    }

    @Override
    public byte asByte() {
        return 11;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Champion";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
