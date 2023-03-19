package com.madou.geapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.geapicommon.model.entity.InterfaceInfo;

/**
* @author MA_dou
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-03-13 20:37:46
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {


    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);


}
