package iplocate.model;


public class WorkflowInput {
    private String name;
    private int seconds = 0;

    public WorkflowInput() {};

    public WorkflowInput(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
} 