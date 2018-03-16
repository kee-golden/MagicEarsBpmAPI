package cpm.magicears.bpm;

import com.magicears.bpm.BootApiApplication;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by L on 2018/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApiApplication.class)
public class ActivitiTest {


    //部署流程定义
    @Test
    public void deployProcessDefinition(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();//与流程部署和定义相关的API
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();//得到部署对象
        deploymentBuilder.addClasspathResource("processes/qingjia.bpmn");//加载流程定义的资源文件-一次只能加载一个
        deploymentBuilder.addClasspathResource("processes/qingjia.png");//从classpath类路径加载资源
        deploymentBuilder.name("请假流程");//设置部署的流程的名字
        Deployment deploy = deploymentBuilder.deploy();////Deployment对应数据库的表：

        //输出相关信息:对应数据库的字段
        System.out.println("部署对象的ID："+deploy.getId());
        System.out.println("部署对象的名称："+deploy.getName());
        System.out.println("部署的时间："+deploy.getDeploymentTime());
    }

    //启动流程实例
    @Test
    public void startProcessInstance(){
        //流程定义的key
        String processDefinitionKey ="qingjia";
        //与运行过程中相关的api(流程实例和执行对象相关)--表示正在执行的
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //使用流程定义的key启动实例可以按照流程最新的版本启动-得到流程实例对象
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

        //输出相关信息：对应数据库的字段—对应页面
        System.out.println("流程执行的ID："+processInstance.getId());
        System.out.println("流程实例ID："+processInstance.getProcessInstanceId());//现在值和执行对象ID一样，后面会讲不一样的地方
        System.out.println("流程定义ID："+processInstance.getProcessDefinitionId());
    }

    //查询办理人的个人任务
    @Test
    public void queryMyTask(){
        //任务办理人
        String assignee = "张三";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //执行过程相关的service，用来操作任务节点(userTask节点)
        TaskService taskService = processEngine.getTaskService();
        //得到任务查询对象：用来查询当前任务相关的信息--对应表
        TaskQuery taskQuery = taskService.createTaskQuery();
        //指定任务办理人
        taskQuery.taskAssignee(assignee);

        //列表查询
        List<Task> list = taskQuery.list();

        if(null!=list && list.size()>0){
            //遍历打印
            for (Task task : list) {//任务对象
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务办理人:"+task.getAssignee());
                System.out.println("任务创建时间:"+task.getId());
                System.out.println("任务办理经历的时间："+task.getDueDate());
                System.out.println("流程执行对象ID："+task.getExecutionId());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("流程定义ID："+task.getProcessDefinitionId());
            }
        }
    }
    //完成办理人的个人任务
    @Test
    public void completeTask(){
        //要办理的任务ID
        String taskId = "2504";
        //与正在执行的任务相关的service，操作任务节点(userTask节点)相关。
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //完成任务：通过任务id
        taskService.complete(taskId);


        //输出办理的任务ID
        System.out.println("完成的任务的ID："+taskId);

    }
    //设置流程变量--在任务上设置
    @Test
    public void setProcessVariables(){
        //任务id
        String taskId = "7504";

        //获取任务service
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //1.设置基本类型的流程变量
        //在指定的任务上设置变量
        taskService.setVariable(taskId, "请假天数", 3);
        taskService.setVariable(taskId, "请假日期", new Date());
        taskService.setVariable(taskId, "请假原因", "约会");
        //设置local变量
        taskService.setVariableLocal(taskId, "备注", "要替我保密啊");
        System.out.println("设置流程变量成功！");
    }

    //获取流程变量
    @Test
    public void getProcessVariables(){
        //任务id
        String taskId = "7504";

        //获取任务service
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();

        //第一种方式：获取基本类型的流程变量
        Integer days=(Integer)taskService.getVariable(taskId, "请假天数");
        Date date=(Date)taskService.getVariable(taskId, "请假日期");
        String reason=(String)taskService.getVariable(taskId, "请假原因");
        //获取local变量
        String memo = (String)taskService.getVariableLocal(taskId, "备注");
        //输出
        System.out.println("请假天数："+days);
        System.out.println("请假日期："+date);
        System.out.println("请假原因："+reason);
        System.out.println("备注："+memo);

    }


}