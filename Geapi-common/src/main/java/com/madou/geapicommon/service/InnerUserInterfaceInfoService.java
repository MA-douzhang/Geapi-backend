package com.madou.geapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.geapicommon.model.entity.UserInterfaceInfo;

/**
* @author MA_dou
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-03-13 20:40:10
*/
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口后增加数据库中的调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
