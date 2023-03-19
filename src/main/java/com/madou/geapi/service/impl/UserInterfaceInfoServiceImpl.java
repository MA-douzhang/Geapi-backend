package com.madou.geapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.geapi.common.ErrorCode;
import com.madou.geapi.exception.BusinessException;
import com.madou.geapi.mapper.UserInterfaceInfoMapper;
import com.madou.geapi.service.UserInterfaceInfoService;
import com.madou.geapicommon.model.entity.UserInterfaceInfo;
import org.springframework.stereotype.Service;

/**
 * @author MA_dou
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
 * @createDate 2023-03-13 20:40:10
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Long userId = userInterfaceInfo.getUserId();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        //创建时参数不为空
        if (add) {
            if (interfaceInfoId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
            }
            if (userId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
            }
        }
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }


    /**
     * 记录调用接口次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //查询是否第一次调用接口
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId",interfaceInfoId);
        queryWrapper.eq("userId",userId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(queryWrapper);
        if (userInterfaceInfo == null){
            //新用户分配次数 10次，后续可以将改方法设计在数据库默认值中，数据库默认插入值为0
            UserInterfaceInfo newUserInterfaceInfo = new UserInterfaceInfo();
            newUserInterfaceInfo.setInterfaceInfoId(interfaceInfoId);
            newUserInterfaceInfo.setUserId(userId);
            newUserInterfaceInfo.setLeftNum(10);
            return this.save(newUserInterfaceInfo);
        }
        //老用户调用接口
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        //剩余调用次数大于0
        updateWrapper.gt("leftNum",0);
        updateWrapper.setSql("leftNum = leftNum -1,totalNum = totalNum+1");
        return this.update(updateWrapper);
    }


}




