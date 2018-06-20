package example;

import static org.junit.Assert.*;
import org.junit.Test;

import com.migu.schedule.Schedule;

public class OneTest {
  @Test
  public void testFoo() throws Exception {
    One one = new One();
    //Test foo
    assertEquals("foo", one.foo());
  }

  
  private  Schedule  schedule;
  @Test
  public void test001()
  {
  	int test = schedule.init();
  	test = schedule.registerNode(1);
  	//test = schedule.addTask(1, 10);
  	//test = schedule.deleteTask(7);
  	System.out.println(test);
  	//Assert.assertEquals(ReturnCodeKeys.E002, test);
  	
  }
  /*
  @Test
  public void test002()
  {
  	int test = schedule.init();
  	、、test = schedule.registerNode(1);
  	System.out.println(test);

  	//test = schedule.addTask(1, 10);
  	//test = schedule.deleteTask(7);
  	//Assert.assertEquals(ReturnCodeKeys.E002, test);
  	
  }*/
}