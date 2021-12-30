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
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class SysRole implements Serializable {
    /**
     * 瑙掕壊id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 瑙掕壊鍚嶇О
     */
    private String roleName;

    /**
     * 瑙掕壊瑾槑
     */
    private String roledesc;

    /**
     * 鍒涘缓鏃ユ湡
     */
    private Date createtime;

    /**
     * 鏇存柊鏃ユ湡
     */
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}