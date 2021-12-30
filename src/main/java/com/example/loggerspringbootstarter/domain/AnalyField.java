package com.example.loggerspringbootstarter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName analy_field
 */
@TableName(value ="analy_field")
@Accessors(chain = true)
@Data
@EqualsAndHashCode
public class AnalyField implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String srcSelectField;

    /**
     * 
     */
    private String srcRule;

    /**
     * 
     */
    private Boolean srcKey;

    /**
     * 
     */
    private String tarSelectField;

    /**
     * 
     */
    private String tarRule;

    /**
     * 
     */
    private Boolean tarKey;

    /**
     * 
     */
    private Integer mappingConfId;

    /**
     * 
     */
    private Integer testCaseId;

    /**
     * 
     */
    private String editUser;

    /**
     * 
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date editTime;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 
     */
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}