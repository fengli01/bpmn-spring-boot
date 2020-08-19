package cn.zunyi001.flowable.test;

import cn.zunyi001.flowable.WebStarter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.GraphicInfo;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testStartProcessInstance1() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("assignee", 111);
        List<Deployment> process1597802069363 = repositoryService.createDeploymentQuery().processDefinitionKey("process1597806183970").list();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process1597802069363", variables);
        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        System.out.println(list);
    }

    /**
     * 完成任务
     */
    @Test
    public void testCompleteTask() {
        String assigne = "1002";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().list().get(0);
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getProcessInstanceId());
        System.out.println(activeActivityIds);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        Map<String, GraphicInfo> locationMap = bpmnModel.getLocationMap();
        System.out.println("");

//        Task task = taskServiceruntimeService.createTaskQuery().taskAssigneeLike(assigne).list().get(0);

//        taskService.complete(task.getId());
    }


}
