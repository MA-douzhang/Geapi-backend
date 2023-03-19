package com.madou.geapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.geapicommon.model.entity.InterfaceInfo;
import com.madou.geapicommon.model.entity.User;
import com.madou.geapicommon.model.entity.UserInterfaceInfo;

/**
* @author MA_dou
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-03-13 20:40:10
*/
public interface InnerUserService{

    /**
     * 从数据库中查询已经分配的密钥（accessKey，secretKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);

}
