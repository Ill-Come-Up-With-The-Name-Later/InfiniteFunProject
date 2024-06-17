package survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class NoCritsMeta implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "NoCrits";
    }

    @Override
    public int asInt() {
        return 46;
    }

    @Override
    public float asFloat() {
        return 46;
    }

    @Override
    public double asDouble() {
        return 46;
    }

    @Override
    public long asLong() {
        return 46;
    }

    @Override
    public short asShort() {
        return 46;
    }

    @Override
    public byte asByte() {
        return 46;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @NotNull
    @Override
    public String asString() {
        return "NoCrits";
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
