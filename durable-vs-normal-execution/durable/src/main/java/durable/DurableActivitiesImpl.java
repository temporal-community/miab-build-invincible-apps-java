package durable;

public class DurableActivitiesImpl implements DurableActivities{

  @Override
  public int addOneAndprintVal(int input){
    input += 1;
    System.out.println(input);
    return input;
  }
  
}