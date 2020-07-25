package cn.zunyi001.activiti.controller;

import cn.zunyi001.activiti.utils.ImageUtil;
import cn.zunyi001.activiti.vo.ProcessDeploymentVo;
import cn.zunyi001.activiti.vo.result.JsonResult;
import cn.zunyi001.activiti.vo.result.Resp;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/24 2:11 下午
 */
@RestController
@RequestMapping("/act")
@Slf4j
public class ActivitiController {

    @Value("${file.diagram.path}")
    private String diagramPath;

    @Resource
    private RepositoryService repositoryService;

    @PostMapping("/deploy")
    public JsonResult deploy(@RequestBody ProcessDeploymentVo deployment) {
        String xmlFileName = deployment.getResourceName() + ".bpmn20.xml";
        String pngFileName = deployment.getResourceName() + ".png";
        File xmlFile = new File(diagramPath + xmlFileName);
        File pngFile = new File(diagramPath + pngFileName);
        ImageUtil.convertToPng(deployment.getSvg(), pngFile);

        InputStream xmlInputStream = null;
        InputStream pngInputStream = null;
        try {
            FileCopyUtils.copy(deployment.getXml().getBytes(), xmlFile);
            xmlInputStream = new FileInputStream(xmlFile);
            pngInputStream = new FileInputStream(pngFile);
        } catch (IOException e) {
        }
        repositoryService.createDeployment().
                addInputStream(xmlFileName, xmlInputStream)
                .addInputStream(pngFileName, pngInputStream)
                .name(deployment.getProcessName())
                .key(deployment.getProcessKey())
                .deploy();

        return Resp.ok("流程发布成功");
    }

}
