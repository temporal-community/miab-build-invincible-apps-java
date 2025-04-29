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

      /* We could do the math in the loop, but to show off
         the WebUI, we do it in the Activity. However, since
         IO is not predictable, we should do it in an activity */
      x = activities.addOneAndprintVal(x);
      Workflow.sleep(Duration.ofSeconds(1));

    }
    logger.info("*** Counted to 10");
    return "Counted to " + x;
  }
}
