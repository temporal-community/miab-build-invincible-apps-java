package iplocate.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.temporal.spring.boot.WorkflowImpl;
import iplocate.model.WorkflowInput;
import iplocate.model.WorkflowOutput;
import iplocate.activities.IpLocateActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import iplocate.model.GetAddressFromIPWorkflow;

@WorkflowImpl(workers = "iplocate-worker")
public class GetAddressFromIPWorkflowImpl implements GetAddressFromIPWorkflow {

    private static final Logger logger = LoggerFactory.getLogger(GetAddressFromIPWorkflowImpl.class);

    private final ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();

    private final IpLocateActivities activities = Workflow.newActivityStub(IpLocateActivities.class, options);

    @Override
    public WorkflowOutput run(WorkflowInput input) {

        logger.info("Running workflow with input: {}", input);

        String ipAddress = activities.getIp();
        
        if (input.getSeconds() > 0) {
            Workflow.sleep(Duration.ofSeconds(input.getSeconds()));
        }
        
        String location = activities.getLocationInfo(ipAddress);
        
        return new WorkflowOutput(ipAddress, location);
    }
} 