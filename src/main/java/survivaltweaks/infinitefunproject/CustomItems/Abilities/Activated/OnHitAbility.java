package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit.*;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public enum OnHitAbility implements Ability {
    LOOMING_MISFORTUNE(new LoomingMisfortune(), "Looming Misfortune", new LoomingMisfortune().getDescription(), new LoomingMisfortune().getCooldown()),
    SWORD_SPIRIT(new SwordSpirit(), "Spirit of the Sword", new SwordSpirit().getDescription(), new SwordSpirit().getCooldown()),
    SOUL_STEALER(new SoulStealer(), "Soul Stealer", new SoulStealer().getDescription(), new SoulStealer().getCooldown()),
    LOCKED_LOADED(new LockedLoaded(), "Locked and Loaded", new LockedLoaded().getDescription(), new LockedLoaded().getCooldown()),
    BACKSTAB(new Backstab(), "Backstab", new Backstab().getDescription(), new Backstab().getCooldown()),
    IMPALE_ABILITY(new ImpalingAbility(), "Impaling", new ImpalingAbility().getDescription(), new ImpalingAbility().getCooldown()),
    SPLASH(new Splash(), "Splash", new Splash().getDescription(), new Splash().getCooldown()),
    DIVORCE(new Divorce(), "Divorce", new Divorce().getDescription(), new Divorce().getCooldown()),
    BLOODLUST(new Bloodlust(), "Bloodlust", new Bloodlust().getDescription(), new Bloodlust().getCooldown()),
    DRACONIAN_RAGE(new DraconicRage(), "Draconian Rage", new DraconicRage().getDescription(), new DraconicRage().getCooldown()),
    ;

    private final AttackAbility ability;
    private final String abilityName;
    private final String description;
    private final float cooldown;

    OnHitAbility(AttackAbility a, String name, String description, float cd) {
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

    public AttackAbility getAbility() {
        return ability;
    }

    public static boolean hasAbility(ItemStack item, OnHitAbility ability) {
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
            if(s.contains(ChatColor.YELLOW + "(On Hit) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName())) {
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
            if(s.contains(ChatColor.YELLOW + "(On Hit) Ability" + ChatColor.GRAY + ": ")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<OnHitAbility> getAbilities(ItemStack item) {
        ArrayList<OnHitAbility> abilities = new ArrayList<>();
        if(hasAbility(item)) {
            for (OnHitAbility a : OnHitAbility.values()) {
                if (OnHitAbility.hasAbility(item, a)) {
                    abilities.add(a);
                }
            }
        }
        return abilities;
    }

    public static void addAbility(ItemStack item, OnHitAbility ability) {
        if(item == null) {
            return;
        }

        if(!item.hasItemMeta()) {
            return;
        }

        ArrayList<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            lore.addAll(meta.getLore());
        }

        lore.add("");
        lore.add(ChatColor.YELLOW + "(On Hit) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + ability.getAbilityName());
        String[] description = ability.getDescription().split("\n");

        for(String s : description) {
            lore.add(ChatColor.GRAY + s);
        }

        if(ability.getCooldown() > 0) {
            lore.add("");
            lore.add(color("&7- &eCooldown&7: &b" + String.format("%.2f", ability.getCooldown() / 20) + " Second(s)"));
        }

        if(!ability.getAbility().cooldownModifiable() && ability.getCooldown() > 0) {
            lore.add(color("&7- &cNon-Reducible Cooldown"));
        }
        if(ability.getAbility().oneTimeUse()) {
            lore.add(color("&7- &cOne Time Use"));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
