package example;

import org.junit.Assert;
import org.junit.Test;

import com.migu.schedule.Schedule;
import com.migu.schedule.constants.ReturnCodeKeys;

public class One {
    String message = "foo";

    public String foo() {
        return message;
    }
    
    private  Schedule  schedule;
    @Test
    public void test001()
    {
    	int test = schedule.init();
    	//test = schedule.registerNode(1);
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