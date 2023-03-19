package com.madou.geapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.madou.geapi.common.ErrorCode;
import com.madou.geapi.exception.BusinessException;
import com.madou.geapi.mapper.InterfaceInfoMapper;
import com.madou.geapicommon.model.entity.InterfaceInfo;
import com.madou.geapicommon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        queryWrapper.eq("method",method);
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectOne(queryWrapper);
        if (interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return interfaceInfo;
    }
}
