package survivaltweaks.infinitefunproject.Mobs.Bat;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class InfectedMetadata implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "Infected";
    }

    @Override
    public int asInt() {
        return 99;
    }

    @Override
    public float asFloat() {
        return 99;
    }

    @Override
    public double asDouble() {
        return 99;
    }

    @Override
    public long asLong() {
        return 99;
    }

    @Override
    public short asShort() {
        return 99;
    }

    @Override
    public byte asByte() {
        return 99;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Infected";
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
