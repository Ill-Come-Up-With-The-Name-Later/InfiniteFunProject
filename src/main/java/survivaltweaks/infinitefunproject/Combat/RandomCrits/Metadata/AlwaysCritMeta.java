package survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class AlwaysCritMeta implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "AllCrits";
    }

    @Override
    public int asInt() {
        return 45;
    }

    @Override
    public float asFloat() {
        return 45;
    }

    @Override
    public double asDouble() {
        return 45;
    }

    @Override
    public long asLong() {
        return 45;
    }

    @Override
    public short asShort() {
        return 45;
    }

    @Override
    public byte asByte() {
        return 45;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "AllCrits";
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
