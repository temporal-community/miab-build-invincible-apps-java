package durable;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface DurableActivities {

  public int addOne(int input);

}
