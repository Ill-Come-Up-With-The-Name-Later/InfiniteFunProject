package survivaltweaks.infinitefunproject.World.Infection;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ImmuneMetadata implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "Immune";
    }

    @Override
    public int asInt() {
        return 78;
    }

    @Override
    public float asFloat() {
        return 78;
    }

    @Override
    public double asDouble() {
        return 78;
    }

    @Override
    public long asLong() {
        return 78;
    }

    @Override
    public short asShort() {
        return 78;
    }

    @Override
    public byte asByte() {
        return 78;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Immune";
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
