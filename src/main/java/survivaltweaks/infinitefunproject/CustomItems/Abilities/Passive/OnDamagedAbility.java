package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged.FireThorns;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged.RetaliationRush;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged.SoulHarvest;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum OnDamagedAbility implements Ability {

    RETALIATION_RUSH(new RetaliationRush(), "Retaliatory Rush", new RetaliationRush().getDescription(),
            new RetaliationRush().getCooldown()),
    SOUL_HARVEST(new SoulHarvest(), "Soul Harvest", new SoulHarvest().getDescription(), new SoulHarvest().getCooldown()),
    FLAMING_THORN(new FireThorns(), "Flaming Thorns", new FireThorns().getDescription(), new FireThorns().getCooldown()),
    ;

    private final OnAttackedAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    OnDamagedAbility(OnAttackedAbility a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public OnAttackedAbility getAbility() {
        return ability;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public static boolean hasAbility(ItemStack item, OnDamagedAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(On Damaged) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(On Damaged) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<OnDamagedAbility> getAbilities(ItemStack item) {
        ArrayList<OnDamagedAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for(OnDamagedAbility a : OnDamagedAbility.values()) {
                if(OnDamagedAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, OnDamagedAbility ability) {
        if(item == null) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(On Damaged) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
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
