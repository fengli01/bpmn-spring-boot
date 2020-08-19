package cn.zunyi001.flowable.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Table: tb_leave_record
 */

/**
 * 表名：tb_leave_record
*/
@Getter
@Setter
@ToString
@Table(name = "tb_leave_record")
public class LeaveRecord implements Serializable {
    // 自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 用户ID
    @Column(name = "user_id")
    private Integer userId;

    // 用户名字
    @Column(name = "user_name")
    private String userName;

    // 请假开始时间
    @Column(name = "start_time")
    private Date startTime;

    // 请假结束时间
    @Column(name = "end_time")
    private Date endTime;

    // 请假天数
    private BigDecimal day;

    // 请假类别
    private Byte type;

    // 请假原因
    private String reason;

    // 状态，0暂存，1审核中，2审批通过，3审核不通过
    private Byte status;

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