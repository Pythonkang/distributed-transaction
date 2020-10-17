package com.yibo.seata.service;

import com.yibo.seata.apiservice.Bank2Client;
import com.yibo.seata.mapper.AccountInfoMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/9 0:02
 * @Description:
 */

public interface AccountService {

    public void updateAccountBalance(String accountNo, Long amount);
}
