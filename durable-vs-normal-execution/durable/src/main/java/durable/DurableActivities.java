package durable;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface DurableActivities {

  public void printVal(int input);

}
