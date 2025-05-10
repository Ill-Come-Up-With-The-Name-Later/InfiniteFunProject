package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

/**
 * Identifier for a monster ability
 */
public class MobAbilityData implements MetadataValue {

    public String abilityName;

    public MobAbilityData(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityName() {
        return abilityName;
    }

    @Override
    public @Nullable Object value() {
        return abilityName;
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public @NotNull String asString() {
        return abilityName;
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
