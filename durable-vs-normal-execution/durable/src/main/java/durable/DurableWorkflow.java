package durable;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DurableWorkflow {

  @WorkflowMethod
  String run();

}
