package iplocate;

import iplocate.model.WorkflowInput;
import iplocate.model.WorkflowOutput;
import iplocate.model.Constants;
import iplocate.workflow.GetAddressFromIPWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class IpLocateController {

    @Autowired 
    WorkflowClient workflowClient;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/demo-options")
    public String demoOptions() {
        return "demo";
    }

    @PostMapping("/greet")
    public String greet(@RequestParam String name, @RequestParam(required = false) Integer sleep_duration, Model model) {
        WorkflowInput input = new WorkflowInput(name, sleep_duration != null ? sleep_duration : 0);
        
        String workflowId = "greeting-workflow-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
        
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(workflowId)
                .setTaskQueue(Constants.TASK_QUEUE_NAME)
                .build();

        GetAddressFromIPWorkflow workflow = workflowClient.newWorkflowStub(GetAddressFromIPWorkflow.class, options);

        WorkflowOutput result = workflow.run(input);
        
        String greeting = String.format("Hello, %s!<br> Your IP Address is <code>%s</code>.<br> You are in %s",
                name, result.getIpAddr(), result.getLocation());
        
        model.addAttribute("greeting", greeting);
        return "greeting :: greeting";
    }
} 