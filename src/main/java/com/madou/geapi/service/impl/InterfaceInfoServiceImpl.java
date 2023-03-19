package com.madou.geapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.geapi.common.ErrorCode;
import com.madou.geapi.exception.BusinessException;
import com.madou.geapi.mapper.InterfaceInfoMapper;
import com.madou.geapi.service.InterfaceInfoService;
import com.madou.geapicommon.model.entity.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-03-13 20:37:46
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        //创建时参数不为空
        if (add){
            if (StringUtils.isAnyBlank(name)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
            }
            if (StringUtils.isAnyBlank(description)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
            }
            if (StringUtils.isAnyBlank(url)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
            }
            if (StringUtils.isAnyBlank(method)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
            }
            if (userId <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"名称过长");
        }
    }
}




