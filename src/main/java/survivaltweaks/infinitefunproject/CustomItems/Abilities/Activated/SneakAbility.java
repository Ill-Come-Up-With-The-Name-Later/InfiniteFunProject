package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch.GrowGiant;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch.RadialBlasts;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch.SonicBlast;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.Crouch.SuperJump;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum SneakAbility implements Ability {

    GROW_GIANT(new GrowGiant(), "Enlarge", new GrowGiant().getDescription(), new GrowGiant().getCooldown()),
    RADIAL_BLASTS(new RadialBlasts(), "Radial Magic", new RadialBlasts().getDescription(), new RadialBlasts().getCooldown()),
    SONIC_BLAST(new SonicBlast(), "Sonic Blast", new SonicBlast().getDescription(), new SonicBlast().getCooldown()),
    SUPER_JUMP(new SuperJump(), "Super Jump", new SuperJump().getDescription(), new SuperJump().getCooldown()),
    ;

    private final CrouchAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    SneakAbility(CrouchAbility a, String name, String description, float cd) {
        this.ability = a;
        this.abilityName = name;
        this.description = description;
        this.cooldown = cd;
    }

    public CrouchAbility getAbility() {
        return ability;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getDescription() { return description; }

    public float getCooldown() {
        return cooldown;
    }

    public static boolean hasAbility(ItemStack item, SneakAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(Crouch) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(Crouch) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<SneakAbility> getAbilities(ItemStack item) {
        ArrayList<SneakAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (SneakAbility a : SneakAbility.values()) {
                if (SneakAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, SneakAbility ability) {
        if(item == null) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(Crouch) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
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
