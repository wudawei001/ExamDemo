package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.Consumption;

import java.util.ArrayList;
import java.util.List;


/*
*类名和方法不能修改
 */
public class Schedule {
	
//任务列表
private static	List<TaskInfo>  taskInfoList  = new ArrayList<TaskInfo>();

//总的资源

private static List<Consumption>   totalConsumption = new ArrayList<Consumption>();

    public int init() {
    	//清理所以的节点
        clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
    	
    	if(nodeId<=0)
    	{
    		return ReturnCodeKeys.E004;
    	}
    	else
    	{
    		if(register(nodeId)==0)
    		{
    			 return ReturnCodeKeys.E003;
    		}
    		else if(register(nodeId)>0)
    		{
    			return ReturnCodeKeys.E005;
    		}
    	}
        return ReturnCodeKeys.E000;
    }
   

    public int unregisterNode(int nodeId) {

    	if(nodeId<0)
    	{
    		 return ReturnCodeKeys.E004;
    	}
    	else
    	{
    		if(null != taskInfoList && taskInfoList.size()>0)
        	{
        		 for(int i= 0 ; i<taskInfoList.size();i++ )
        	      {
    	    		if(nodeId == taskInfoList.get(i).getNodeId())
    	      		{
    	    			taskInfoList.remove(i);
    	    			return ReturnCodeKeys.E006;
    	      		}
        	      }
        	}
    	}
        return ReturnCodeKeys.E007;
    }


    public int addTask(int taskId, int consumption) {
    	if(taskId<=0)
    	{
    		return ReturnCodeKeys.E009;
    	}
    	//是否没有添加
    	boolean noAdd = true;
    	if(null != taskInfoList && taskInfoList.size()>0)
    	{
    		for(TaskInfo task: taskInfoList)
    		{
    			if(task.getTaskId() == taskId)
    			{
    				noAdd = false;
    			}
    		}
    	}
    	else
    	{
    		noAdd = false;
    	}
    	if(noAdd)
    	{
    		TaskInfo taskInfo = new TaskInfo();
      		 int nodeId = maxNode();
      		 taskInfo.setNodeId(nodeId);
      		 taskInfo.setTaskId(taskId);
      		 //资源累积统计
      		 Consumption consumptionBean = new  Consumption();
      		consumptionBean.setConsumption(consumption);
      		totalConsumption.add(consumptionBean);
      		 
      		 taskInfoList.add(taskInfo);
      		 return ReturnCodeKeys.E008;
    	}
    	else
    	{
        	return ReturnCodeKeys.E010;
    	}
    	
    }


    public int deleteTask(int taskId) {
    	
    	if(taskId<=0)
    	{
    		 return ReturnCodeKeys.E009;
    	}
    	if(taskInfoList!= null && taskInfoList.size()>0)
    	{
    		for(int i=0; i<taskInfoList.size(); i++)
    		{
    		  if(taskId == taskInfoList.get(i).getTaskId())
    		  {
    			  taskInfoList.remove(i);
    			  return ReturnCodeKeys.E011;
    		  }
    		  
    		}
    	}
    	
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {
    	if(threshold<=0)
    	{
    		 return ReturnCodeKeys.E002;
    	}
    	for(int i= 0 ; i < totalConsumption.size() ; i ++ )
    			
    	{
    		for(int j = i+1; j < totalConsumption.size() ; )
    		{
    			if(totalConsumption.get(i).getConsumption() - totalConsumption.get(j).getConsumption()<threshold)
    			{
    				 return ReturnCodeKeys.E013;
    			}
    			else
    			{
    		        return ReturnCodeKeys.E014;
    			}
    		}
    	}
        return ReturnCodeKeys.E014;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
       if(tasks==null)
       {
    	   return ReturnCodeKeys.E016;
       }
       
       for(TaskInfo taskList: taskInfoList)
       {
    	   for(TaskInfo task: tasks)
           {
    		   if(task.getTaskId() ==taskList.getTaskId() )
    		   {
    			   return ReturnCodeKeys.E015; 
    		   }
           } 
       }
        return ReturnCodeKeys.E015;
    }
    /**
     * 清除所以节点
     */
    public void clear()
    {
    	taskInfoList.clear();
    }
    
	
    /**
     * 注册节点,并且返回已经注册的node节点数
     */
    public int register(int nodeId)
    {
      TaskInfo taskInfo = new TaskInfo();
      int registerNum = 0;
      if(null != taskInfoList && taskInfoList.size()>0)
      {
    	  for(TaskInfo task:taskInfoList)
      	{
    		  if(task != null )
    		  {
	      		if(nodeId == task.getNodeId())
	      		{
	      			++registerNum;
	      			break;
	      		}
	      		else
	      		{
	      			taskInfo.setNodeId(nodeId);
	      			taskInfoList.add(taskInfo);
	      			registerNum = 0;
	      			return registerNum;
	      		}
      	     }
      	}
      }
      else
      {
			taskInfo.setNodeId(nodeId);
			//taskInfo.setTaskId(nodeId);
			taskInfoList.add(taskInfo);  
      }
      
    		return registerNum;
    	
    }
    
    /**
     * 返回最大的节点+1
     * @param nodeId
     * @return
     */
	public int maxNode() {
		int max = 0;
		if (null != taskInfoList && taskInfoList.size() > 0) {

			for (int i = 0; i < taskInfoList.size(); i++) {
				max = taskInfoList.get(i).getNodeId();
				for (int j = i + 1; j < taskInfoList.size(); j++) {
					if (taskInfoList.get(j).getNodeId() > max) {
						max = taskInfoList.get(j).getNodeId();
					}
				}
			}
		}
		return max + 1;
	}
    
	

}
