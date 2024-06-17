package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class RevivedMeta implements MetadataValue {
    @Override
    public Object value() {
        return "Revived";
    }

    @Override
    public int asInt() {
        return 55;
    }

    @Override
    public float asFloat() {
        return 55;
    }

    @Override
    public double asDouble() {
        return 55;
    }

    @Override
    public long asLong() {
        return 55;
    }

    @Override
    public short asShort() {
        return 55;
    }

    @Override
    public byte asByte() {
        return 55;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "Revived";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
