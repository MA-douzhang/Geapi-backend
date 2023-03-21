package com.madou.geapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.madou.geapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-03-13 20:40:10
* @Entity com.madou.geapi.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    /**
     * 被调用最多的接口TOP5
     * @param limit
     * @return
     */
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

    /**
     * 最活跃的TOP5用户
     * @param limit
     * @return
     */
    List<UserInterfaceInfo> listTopUserInvokeInterfaceInfo(int limit);

}




