package durable;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import org.slf4j.Logger;
import java.time.Duration;

public class DurableWorkflowImpl implements DurableWorkflow {

  public static final Logger logger = Workflow.getLogger(DurableWorkflowImpl.class);

  private final ActivityOptions options =
  ActivityOptions.newBuilder()
      .setStartToCloseTimeout(Duration.ofSeconds(2))
      .build();

private final DurableActivities activities =
  Workflow.newActivityStub(DurableActivities.class, options);

  @Override
  public String run() {
    
    logger.info("*** Counting to 10");
    int x = 0;
    while(x < 10) {

      // math is predicatable/deterministic, so we can do it in the workflow
      x += 1;
      // io is not predictable, so we do it in an activity
      activities.printVal(x);
      Workflow.sleep(Duration.ofSeconds(1));

    }
    logger.info("*** Counted to 10");
    return "Counted to " + x;
  }
}
