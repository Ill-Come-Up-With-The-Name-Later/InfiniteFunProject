package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class NoLvl implements MetadataValue {

    @Override
    public Object value() {
        return "NoLevel";
    }

    @Override
    public int asInt() {
        return 43;
    }

    @Override
    public float asFloat() {
        return 43;
    }

    @Override
    public double asDouble() {
        return 43;
    }

    @Override
    public long asLong() {
        return 43;
    }

    @Override
    public short asShort() {
        return 43;
    }

    @Override
    public byte asByte() {
        return 43;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public String asString() {
        return "NoLevel";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {
    }
}
