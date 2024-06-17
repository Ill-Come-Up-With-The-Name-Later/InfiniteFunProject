package survivaltweaks.infinitefunproject.CustomItems.Metadata;

import org.bukkit.Particle;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class ProjectileShieldMetadata implements MetadataValue {
    public ArrayList<Particle> deflectEffect;
    public ArrayList<Particle> aura;
    public int radius;

    public ProjectileShieldMetadata(ArrayList<Particle> deflectEffect, ArrayList<Particle> aura, int radius) {
        this.deflectEffect = deflectEffect;
        this.aura = aura;
        this.radius = radius;
    }

    @Nullable
    @Override
    public Object value() {
        return "ProjShield";
    }

    @Override
    public int asInt() {
        return 79;
    }

    @Override
    public float asFloat() {
        return 79;
    }

    @Override
    public double asDouble() {
        return 79;
    }

    @Override
    public long asLong() {
        return 79;
    }

    @Override
    public short asShort() {
        return 79;
    }

    @Override
    public byte asByte() {
        return 79;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "ProjShield";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }

    public int getRadius() {
        return radius;
    }

    public ArrayList<Particle> getDeflectEffect() {
        return deflectEffect;
    }

    public ArrayList<Particle> getAura() {
        return aura;
    }
}
