CREATE TABLE tb_approve_task (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    task_name varchar(30) DEFAULT '' COMMENT '任务名称',
    user_id int(11) DEFAULT NULL COMMENT '用户名字',
    user_name varchar(20) DEFAULT NULL COMMENT '申请者名字',
    task_id varchar(30) DEFAULT '' COMMENT '任务ID',
    status tinyint(2) DEFAULT NULL COMMENT '状态',
    type tinyint(2) DEFAULT '0' COMMENT '任务类别',
    approve_id int(11) DEFAULT NULL COMMENT '审核人ID',
    approve_name varchar(30) DEFAULT '' COMMENT '审核人',
    module_id int(2) DEFAULT NULL COMMENT '模块ID',
    del_flag tinyint(1) DEFAULT '0' COMMENT '删除状态',
    create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='审核任务表';

CREATE TABLE tb_leave_record (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    user_id int(11) DEFAULT '0' COMMENT '用户ID',
    user_name varchar(20) DEFAULT NULL COMMENT '用户名字',
    start_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请假开始时间',
    end_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请假结束时间',
    day decimal(8,2) DEFAULT '0.00' COMMENT '请假天数',
    type tinyint(2) DEFAULT '0' COMMENT '请假类别',
    reason varchar(200) DEFAULT '' COMMENT '请假原因',
    status tinyint(2) DEFAULT '0' COMMENT '状态，0暂存，1审核中，2审批通过，3审核不通过',
    del_flag tinyint(1) DEFAULT '0' COMMENT '删除状态',
    create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='请假记录表';

CREATE TABLE tb_process_record (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    proc_id varchar(30) DEFAULT '' COMMENT '流程ID',
    name varchar(30) DEFAULT '' COMMENT '流程名称',
    xml_str text COMMENT '流程xml',
    svg_str text COMMENT 'svg码',
    note varchar(255) DEFAULT '' COMMENT '描述',
    status tinyint(2) DEFAULT '0' COMMENT '状态，0未发布，1已发布',
    del_flag tinyint(1) DEFAULT '0' COMMENT '删除状态',
    create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='流程设计记录表';