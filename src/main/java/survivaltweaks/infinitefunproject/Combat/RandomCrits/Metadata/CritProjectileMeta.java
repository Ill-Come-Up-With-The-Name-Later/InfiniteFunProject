package survivaltweaks.infinitefunproject.Combat.RandomCrits.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

@Deprecated
public class CritProjectileMeta implements MetadataValue {

    @Override
    public Object value() {
        return "EntityCrit";
    }

    @Override
    public int asInt() {
        return 17;
    }

    @Override
    public float asFloat() {
        return 17;
    }

    @Override
    public double asDouble() {
        return 17;
    }

    @Override
    public long asLong() {
        return 17;
    }

    @Override
    public short asShort() {
        return 17;
    }

    @Override
    public byte asByte() {
        return 17;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "EntityCrit";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
