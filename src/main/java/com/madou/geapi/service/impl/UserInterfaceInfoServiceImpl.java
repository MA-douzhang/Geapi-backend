package com.madou.geapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.geapi.model.entity.UserInterfaceInfo;
import com.madou.geapi.service.UserInterfaceInfoService;
import com.madou.geapi.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-03-13 20:40:10
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




