package com.madou.geapi.service.impl.inner;

import com.madou.geapicommon.service.InnerUserInterfaceInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InnerUserInterfaceInfoServiceImplTest {

    @Resource
    InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @Test
    void invokeLeftNum() {
        boolean hasLeftNum = innerUserInterfaceInfoService.invokeLeftNum(27L, 1635256920531947521L);
        Assertions.assertFalse(hasLeftNum);
    }
}
