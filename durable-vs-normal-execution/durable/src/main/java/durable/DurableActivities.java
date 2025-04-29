package durable;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface DurableActivities {

  public int addOneAndprintVal(int input);

}
