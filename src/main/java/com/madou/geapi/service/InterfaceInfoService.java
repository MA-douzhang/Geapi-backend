package com.madou.geapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.geapi.model.vo.InterfaceInfoVO;
import com.madou.geapicommon.model.entity.InterfaceInfo;
import com.madou.geapicommon.model.entity.User;

/**
* @author MA_dou
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-03-13 20:37:46
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {


    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 根据接口id返回接口封装信息（个人剩余调用次数）
     *
     * @param id
     * @param loginUser
     * @return
     */
    InterfaceInfoVO getInterfaceInfoById(long id, User loginUser);
}
