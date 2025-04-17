package iplocate.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface IpLocateActivities {
    String getIp();
    String getLocationInfo(String ip);
} 