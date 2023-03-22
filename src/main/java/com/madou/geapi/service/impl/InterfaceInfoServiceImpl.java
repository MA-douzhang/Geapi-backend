package com.madou.geapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.geapi.common.ErrorCode;
import com.madou.geapi.exception.BusinessException;
import com.madou.geapi.mapper.InterfaceInfoMapper;
import com.madou.geapi.model.vo.InterfaceInfoVO;
import com.madou.geapi.service.InterfaceInfoService;
import com.madou.geapi.service.UserInterfaceInfoService;
import com.madou.geapicommon.model.entity.InterfaceInfo;
import com.madou.geapicommon.model.entity.User;
import com.madou.geapicommon.model.entity.UserInterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author MA_dou
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-03-13 20:37:46
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Resource
    UserInterfaceInfoService userInterfaceInfoService;

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

    @Override
    public InterfaceInfoVO getInterfaceInfoById(long id, User loginUser) {
        InterfaceInfo interfaceInfo = this.getById(id);
        if (interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"接口不存在");
        }
        InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
        BeanUtils.copyProperties(interfaceInfo,interfaceInfoVO);
        //查询该用户剩余调用接口次数
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId",id);
        queryWrapper.eq("userId",loginUser.getId());
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(queryWrapper);
        if (userInterfaceInfo != null){
            interfaceInfoVO.setLeftNum(userInterfaceInfo.getLeftNum());
            interfaceInfoVO.setTotalNum(userInterfaceInfo.getTotalNum());
        }
        return interfaceInfoVO;
    }
}




