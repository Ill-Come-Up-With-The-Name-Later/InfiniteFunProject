package survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class MiniCritsMeta implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "MiniCrits";
    }

    @Override
    public int asInt() {
        return 44;
    }

    @Override
    public float asFloat() {
        return 44;
    }

    @Override
    public double asDouble() {
        return 44;
    }

    @Override
    public long asLong() {
        return 44;
    }

    @Override
    public short asShort() {
        return 44;
    }

    @Override
    public byte asByte() {
        return 44;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "MiniCrits";
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
