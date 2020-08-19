package cn.zunyi001.flowable.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Table: tb_approve_task
 */

/**
 * 表名：tb_approve_task
*/
@Getter
@Setter
@ToString
@Table(name = "tb_approve_task")
public class ApproveTask implements Serializable {
    // 自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 任务名称
    @Column(name = "task_name")
    private String taskName;

    // 用户名字
    @Column(name = "user_id")
    private Integer userId;

    // 申请者名字
    @Column(name = "user_name")
    private String userName;

    // 任务ID
    @Column(name = "task_id")
    private String taskId;

    // 状态
    private Byte status;

    // 任务类别
    private Byte type;

    // 审核人ID
    @Column(name = "approve_id")
    private Integer approveId;

    // 审核人
    @Column(name = "approve_name")
    private String approveName;

    // 模块ID
    @Column(name = "module_id")
    private Integer moduleId;

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