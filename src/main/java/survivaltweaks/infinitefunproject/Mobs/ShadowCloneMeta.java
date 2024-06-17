package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ShadowCloneMeta implements MetadataValue {
    @Override
    public Object value() {
        return "ShadowClone";
    }

    @Override
    public int asInt() {
        return 35;
    }

    @Override
    public float asFloat() {
        return 35;
    }

    @Override
    public double asDouble() {
        return 35;
    }

    @Override
    public long asLong() {
        return 35;
    }

    @Override
    public short asShort() {
        return 35;
    }

    @Override
    public byte asByte() {
        return 35;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "ShadowClone";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
