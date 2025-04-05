package iplocate.workflow;

import iplocate.model.WorkflowInput;
import iplocate.model.WorkflowOutput;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface GetAddressFromIPWorkflow {
    @WorkflowMethod
    WorkflowOutput run(WorkflowInput input);
} 