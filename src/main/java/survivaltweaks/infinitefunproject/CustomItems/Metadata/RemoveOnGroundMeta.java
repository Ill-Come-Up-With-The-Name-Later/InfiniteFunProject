package survivaltweaks.infinitefunproject.CustomItems.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class RemoveOnGroundMeta implements MetadataValue {
    @Nullable
    @Override
    public Object value() {
        return "RemoveOnGround";
    }

    @Override
    public int asInt() {
        return 49;
    }

    @Override
    public float asFloat() {
        return 49;
    }

    @Override
    public double asDouble() {
        return 49;
    }

    @Override
    public long asLong() {
        return 49;
    }

    @Override
    public short asShort() {
        return 49;
    }

    @Override
    public byte asByte() {
        return 49;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "RemoveOnGround";
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
