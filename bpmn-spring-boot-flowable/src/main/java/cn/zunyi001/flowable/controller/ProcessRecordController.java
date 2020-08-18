package cn.zunyi001.flowable.controller;

import cn.zunyi001.flowable.model.ProcessRecord;
import cn.zunyi001.flowable.service.ProcessRecordService;
import cn.zunyi001.flowable.utils.ImageUtil;
import cn.zunyi001.flowable.vo.JsonResult;
import cn.zunyi001.flowable.vo.QueryResult;
import cn.zunyi001.flowable.vo.Resp;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 10:18 上午
 */
@RestController
@RequestMapping("/process")
public class ProcessRecordController {

    @Value("${file.diagram.path}")
    private String diagramPath;

    @Autowired
    private ProcessRecordService processRecordService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @PostMapping("/buffer")
    public JsonResult<Integer> buffer(@RequestBody ProcessRecord processRecord) {
        int count = processRecordService.save(processRecord);
        return Resp.ok(count);
    }

    /**
     * 删除流程定义
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/remove")
    public JsonResult<Integer> remove(@PathVariable("id") Integer id) {
        int count = processRecordService.remove(id);
        return Resp.ok(count);
    }

    /**
     * 流程发布
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/deploy")
    public JsonResult deploy(@PathVariable("id") Integer id) {
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setId(id);
        processRecord.setDelFlag(false);
        ProcessRecord processRecordDB = processRecordService.selectOne(processRecord);

        String xmlFileName = processRecordDB.getProcId() + ".bpmn20.xml";
        String pngFileName = processRecordDB.getProcId() + ".png";
        File xmlFile = new File(diagramPath + xmlFileName);
        File pngFile = new File(diagramPath + pngFileName);
        ImageUtil.convertToPng(processRecordDB.getSvgStr(), pngFile);

        InputStream xmlInputStream = null;
        InputStream pngInputStream = null;
        try {
            FileCopyUtils.copy(processRecordDB.getXmlStr().getBytes(), xmlFile);
            xmlInputStream = new FileInputStream(xmlFile);
            pngInputStream = new FileInputStream(pngFile);
        } catch (IOException e) {
        }
        repositoryService.createDeployment().
                addInputStream(xmlFileName, xmlInputStream)
                .addInputStream(pngFileName, pngInputStream)
                .name(processRecordDB.getName())
                .key(processRecordDB.getProcId())
                .deploy();
        processRecord.setStatus((byte) 1);
        int modify = processRecordService.modify(processRecord);
        return Resp.ok(modify);
    }

    @GetMapping("/list")
    public JsonResult<QueryResult<ProcessRecord>> pageQuery(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        QueryResult<ProcessRecord> queryResult = processRecordService.pageQuery(page, pageSize);
        return Resp.ok(queryResult);
    }

    @GetMapping("/inst/list")
    public JsonResult<List<ProcessInstance>> definitionQuery() {
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
        return Resp.ok(processInstances);
    }
}
