package cn.zunyi001.flowable.test;

import cn.zunyi001.flowable.WebStarter;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/25 12:50 上午
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = WebStarter.class)
public class FlowableTest {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcessInstance() {
        String deploymentKey = "process1001";
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentKey(deploymentKey).latest().singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).latestVersion().singleResult();
        runtimeService.startProcessInstanceByKey(processDefinition.getKey());
    }

    /**
     * 完成任务
     */
    @Test
    public void testCompleteTask() {
        String assigne = "zhangsan";
        Task task = taskService.createTaskQuery().taskAssigneeLike(assigne).singleResult();
        taskService.complete(task.getId());
    }
}
