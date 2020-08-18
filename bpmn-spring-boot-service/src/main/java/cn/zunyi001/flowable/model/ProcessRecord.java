package cn.zunyi001.flowable.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Table: tb_process_record
 */
/**
 * 表名：tb_process_record
 */
@Getter
@Setter
@ToString
@Table(name = "tb_process_record")
public class ProcessRecord implements Serializable {
    // 自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 流程ID
    @Column(name = "proc_id")
    private String procId;

    // 流程名称
    private String name;

    // 流程xml
    @Column(name = "xml_str")
    private String xmlStr;

    // svg码
    @Column(name = "svg_str")
    private String svgStr;

    // 状态，0未发布，1已发布
    private Byte status;

    // 描述
    private String note;

    // 删除状态
    @Column(name = "del_flag")
    private Boolean delFlag;

    // 创建时间
    @Column(name = "create_time")
    private Date createTime;

    // 修改时间
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}