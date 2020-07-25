package cn.zunyi001.activiti.test;

import cn.zunyi001.activiti.ActivitiStarter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @author: fengli
 * @date: 2020/7/25 7:02 下午
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = ActivitiStarter.class)
public class ActivitiTest {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Test
    public void testDeploy() {
        repositoryService.createDeployment()
                .addClasspathResource("test.bpmn")
                .addClasspathResource("test.png")
                .name("测试")
                .deploy();
    }

    @Test
    public void testStartProcessInstance() {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentKey("s1111").singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        runtimeService.startProcessInstanceById(processDefinition.getId());
    }

    @Test
    public void testTaskComplete() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("wangwu").list();
        taskService.complete(list.get(0).getId());
    }

    @Test
    public void testHisTask() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        System.out.println(list);
    }
}
