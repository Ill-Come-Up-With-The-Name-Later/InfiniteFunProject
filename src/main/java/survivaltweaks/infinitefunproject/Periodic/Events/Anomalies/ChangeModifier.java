package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

public class ChangeModifier implements RandomEvent {
    @Override
    public void trigger() {
        WorldModInit.pickModifier();
        WorldModInit.getCountdown()[0] += 120;
    }
}
