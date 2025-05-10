package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Triggered.Crystallize;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Triggered.DoubleJumpAbility;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum TriggeredAbility implements Ability {

    CRYSTALLIZE(new Crystallize(), "Crystallize", new Crystallize().getDescription(), new Crystallize().getCooldown()),
    DOUBLE_JUMP(new DoubleJumpAbility(), "Double Jump", new DoubleJumpAbility().getDescription(), new DoubleJumpAbility().getCooldown()),
    ;

    private final TriggerAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    TriggeredAbility(TriggerAbility a, String name, String description, float cd) {
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

    public TriggerAbility getAbility() {
        return ability;
    }

    public static boolean hasAbility(ItemStack item, TriggeredAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(Triggered) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(Triggered) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<TriggeredAbility> getAbilities(ItemStack item) {
        ArrayList<TriggeredAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (TriggeredAbility a : TriggeredAbility.values()) {
                if (TriggeredAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, TriggeredAbility ability) {
        if(item == null) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(Triggered) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
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

        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void triggerAbility(Player player, TriggeredAbility ability) {
        ability.getAbility().activate(player);

        switch (WorldModInit.getActiveModifier()) {
            case INSTANT_COOLDOWNS:
                if(ability.getAbility().cooldownModifiable()) {
                    if (ability.getCooldown() > 20) {
                        setCooldown(player, ability, 20);
                    } else {
                        setCooldown(player, ability, ability.getAbility().getCooldown() / 2);
                    }
                } else {
                    setCooldown(player, ability, ability.getAbility().getCooldown());
                }
                break;
            case TRIPLE_COOLDOWNS:
                setCooldown(player, ability, ability.getAbility().getCooldown() * 3);
                break;
            case ANOMALOUS:
                if(ability.getAbility().cooldownModifiable()) {
                    setCooldown(player, ability, ability.getAbility().getCooldown() / 4);
                } else {
                    setCooldown(player, ability, ability.getAbility().getCooldown());
                }
                break;
            default:
                setCooldown(player, ability, ability.getAbility().getCooldown());
                break;
        }
    }
}
