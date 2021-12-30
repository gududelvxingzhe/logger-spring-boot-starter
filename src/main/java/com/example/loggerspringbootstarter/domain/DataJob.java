package com.example.loggerspringbootstarter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName data_job
 */
@TableName(value ="data_job")
@Data
public class DataJob implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * job_info_id
     */
    @TableField(value = "job_id")
    private Integer job_id;

    /**
     * 
     */
    @TableField(value = "task_id")
    private Integer task_id;

    /**
     * 
     */
    @TableField(value = "case_id")
    private Integer case_id;

    /**
     * 
     */
    @TableField(value = "case_level")
    private Integer case_level;

    /**
     * 
     */
    @TableField(value = "excute_times")
    private Integer excute_times;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}