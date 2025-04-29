package iplocate.model;


public class WorkflowOutput {
    private String ipAddr;
    private String location;

    public WorkflowOutput() {};

    public WorkflowOutput(String ipAddr, String location) {
        this.ipAddr = ipAddr;
        this.location = location;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
} 