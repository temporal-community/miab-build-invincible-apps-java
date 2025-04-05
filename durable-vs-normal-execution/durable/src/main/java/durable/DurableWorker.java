package durable;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class DurableWorker {

  public static void main(String[] args) {
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);

    Worker worker = factory.newWorker("durable-tasks");

    worker.registerWorkflowImplementationTypes(DurableWorkflowImpl.class);

    worker.registerActivitiesImplementations(new DurableActivitiesImpl());


    factory.start();
  }
}
