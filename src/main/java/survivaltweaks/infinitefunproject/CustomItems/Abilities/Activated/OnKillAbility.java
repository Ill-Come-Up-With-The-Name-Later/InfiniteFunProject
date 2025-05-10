package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill.*;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum OnKillAbility implements Ability  {

    HEALTH_DRAIN(new HealthDrain(), "Blood Drain", new HealthDrain().getDescription(), new HealthDrain().getCooldown()),
    BREAD_SLICE(new BreadSlice(), "Bread Slice", new BreadSlice().getDescription(), new BreadSlice().getCooldown()),
    CORPSE_SHOT(new ExplosiveArrowFling(), "Volley From Death", new ExplosiveArrowFling().getDescription(),
            new ExplosiveArrowFling().getCooldown()),
    GROUNDED(new Grounded(), "Grounded Charge", new Grounded().getDescription(), new Grounded().getCooldown()),
    EXPLOSIVE_KILL(new ExplosiveKill(), "Corpse Detonation", new ExplosiveKill().getDescription(), new ExplosiveKill().getCooldown()),
    KILL_SPEED_BOOST(new KillSpeedBoost(), "Quick Escape", new KillSpeedBoost().getDescription(), new KillSpeedBoost().getCooldown()),
    ;

    private final KillAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    OnKillAbility(KillAbility a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public KillAbility getAbility() {
        return ability;
    }

    public static boolean hasAbility(ItemStack item, OnKillAbility ability) {
        if(item == null) {
            return false;
        }

        if(!item.hasItemMeta()) {
            return false;
        }

        if(item.getItemMeta() == null) {
            return false;
        }

        if(!item.getItemMeta().hasLore()) {
            return false;
        }

        ArrayList<String> lore = (ArrayList<String>) item.getItemMeta().getLore();

        for(String s : lore) {
            if(s.contains(ChatColor.YELLOW + "(On Kill) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAbility(ItemStack item) {
        if(item == null) {
            return false;
        }

        if(!item.hasItemMeta()) {
            return false;
        }

        if(item.getItemMeta() == null) {
            return false;
        }

        if(!item.getItemMeta().hasLore()) {
            return false;
        }

        ArrayList<String> lore = (ArrayList<String>) item.getItemMeta().getLore();

        for(String s : lore) {
            if(s.contains(ChatColor.YELLOW + "(On Kill) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<OnKillAbility> getAbilities(ItemStack item) {
        ArrayList<OnKillAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (OnKillAbility a : OnKillAbility.values()) {
                if (OnKillAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, OnKillAbility ability) {
        if(item == null) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(On Kill) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
        String[] description = ability.getDescription().split("\n");

        for(String s : description) {
            lore.add(ChatColor.GRAY + s);
        }

        if(ability.getCooldown() > 0) {
            lore.add("");
            lore.add(color("&7- &eCooldown&7: &b" + String.format("%.2f", ability.getCooldown() / 20) + " Second(s)"));
        }

        if(!ability.getAbility().cooldownModifiable() && ability.getCooldown() > 0) {
            lore.add(color("&7- &cNot affected by modifier."));
        }
        if(ability.getAbility().oneTimeUse()) {
            lore.add(color("&7- &cOne Time Use"));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
