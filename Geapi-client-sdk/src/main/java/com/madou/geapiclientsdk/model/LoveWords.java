package com.madou.geapiclientsdk.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 情话
 * @TableName love_words
 */
@Data
public class LoveWords implements Serializable {

    /**
     * 情话
     */
    private String loveWord;

    private static final long serialVersionUID = 1L;
}
