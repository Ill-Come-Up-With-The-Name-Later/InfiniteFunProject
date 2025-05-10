package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.entity.Monster;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Ability;

public enum OnEntityTargetAbility implements Ability {

    ;

    public final MonsterAbility monsterAbility;

    OnEntityTargetAbility(MonsterAbility ability) {
        monsterAbility = ability;
    }

    public MonsterAbility getMonsterAbility() {
        return monsterAbility;
    }

    public String getAbilityName() {
        return monsterAbility.abilityName();
    }

    public static void addAbility(Monster monster, OnEntityTargetAbility ability) {
        if(!hasAbility(monster, ability)) {
            monster.setMetadata(ability.getAbilityName(), new MobAbilityData(ability.getAbilityName()));
        }
    }

    public static boolean hasAbility(Monster monster, OnEntityTargetAbility ability) {
        return !monster.getMetadata(ability.getAbilityName()).isEmpty();
    }

    public static boolean hasAbility(Monster monster) {
        for(OnEntityTargetAbility ability : OnEntityTargetAbility.values()) {
            if(hasAbility(monster, ability)) {
                return true;
            }
        }

        return false;
    }
}
