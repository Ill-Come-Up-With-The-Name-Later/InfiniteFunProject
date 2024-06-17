package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit.pickBonusModifiers;

public class BonusModifiers implements RandomEvent {
    @Override
    public void trigger() {
        WorldModInit.getCountdown()[0] = WorldModInit.getCountdown()[0] + 90;
        pickBonusModifiers();
    }
}
