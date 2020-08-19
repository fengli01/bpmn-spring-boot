package cn.zunyi001.flowable.controller;

import cn.zunyi001.flowable.model.ApproveTask;
import cn.zunyi001.flowable.model.LeaveRecord;
import cn.zunyi001.flowable.service.ApproveTaskService;
import cn.zunyi001.flowable.service.LeaveRecordService;
import cn.zunyi001.flowable.utils.flow.ImageService;
import cn.zunyi001.flowable.vo.JsonResult;
import cn.zunyi001.flowable.vo.QueryResult;
import cn.zunyi001.flowable.vo.Resp;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.GraphicInfo;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/19 9:47 上午
 */
@RestController
@RequestMapping("/leave")
public class LeaveRecordController {

    @Value("${file.diagram.path}")
    private String diagramPath;

    @Autowired
    private LeaveRecordService leaveRecordService;

    @Autowired
    private ApproveTaskService approveTaskService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private ImageService imageService;

    private String processDefinitionId = "process1597828420116";

    /**
     * 请假申请
     *
     * @param leaveRecord
     * @return
     */
    @PostMapping("/apply")
    public JsonResult apply(@RequestBody LeaveRecord leaveRecord) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("assignee", leaveRecord.getUserId());
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionId).singleResult();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey(), variables);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        ApproveTask approveTask = new ApproveTask();
        approveTask.setApproveId(Integer.parseInt(task.getAssignee()));
        approveTask.setModuleId(1);
        approveTask.setStatus((byte) 2);
        approveTask.setTaskName(processDefinition.getName());
        approveTask.setUserId(leaveRecord.getUserId());
        approveTask.setUserName(leaveRecord.getUserName());
        approveTask.setTaskId(task.getId());
        approveTask.setApproveName(task.getName());
        approveTask.setType((byte) 1);
        approveTaskService.save(approveTask);
        taskService.complete(task.getId());
        leaveRecord.setStatus((byte) 1);
        leaveRecordService.save(leaveRecord);
        task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        approveTask = new ApproveTask();
        approveTask.setApproveId(Integer.parseInt(task.getAssignee()));
        approveTask.setModuleId(1);
        approveTask.setStatus((byte) 1);
        approveTask.setTaskName(processDefinition.getName());
        approveTask.setUserId(leaveRecord.getUserId());
        approveTask.setUserName(leaveRecord.getUserName());
        approveTask.setTaskId(task.getId());
        approveTask.setApproveName(task.getName());
        approveTask.setType((byte) 1);
        approveTaskService.save(approveTask);
        return Resp.ok(1);
    }

    @GetMapping("/procimage")
    public void getProcessImage(HttpServletResponse response) throws Exception{
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionId).list().get(0);
        byte[] bytes = imageService.generateImageByProcInstId(pi.getId());
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(bytes,outputStream);
        System.out.println(new String(bytes));
    }

    @GetMapping("/{userId}/list")
    public JsonResult<QueryResult<LeaveRecord>> list(@PathVariable("userId") Integer userId, Integer page, Integer pageSize) {
        LeaveRecord leaveRecord = new LeaveRecord();
        leaveRecord.setUserId(userId);
        QueryResult<LeaveRecord> queryResult = leaveRecordService.pageQuery(page, pageSize, leaveRecord);
        return Resp.ok(queryResult);
    }
}
