package com.madou.geapiinterface.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 情话
 * @TableName love_words
 */

@TableName(value ="love_words")
@Data
public class LoveWords implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 情话
     */
    private String loveWord;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
