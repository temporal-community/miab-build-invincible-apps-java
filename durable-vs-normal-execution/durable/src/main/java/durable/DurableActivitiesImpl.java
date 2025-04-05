package durable;

public class DurableActivitiesImpl implements DurableActivities{

  @Override
  public int addOne(int input){
    return input + 1;
  }
  
}