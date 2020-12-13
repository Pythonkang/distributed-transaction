package com.yibo.eureka.seata.service;

/**
 * @Author: huangyibo
 * @Date: 2020/10/9 0:02
 * @Description:
 */

public interface AccountService {

    public void updateAccountBalance(String accountNo, Long amount);
}
