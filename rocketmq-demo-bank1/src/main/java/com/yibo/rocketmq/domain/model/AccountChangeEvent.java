package com.yibo.rocketmq.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 2:25
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountChangeEvent implements Serializable {

    /**
     * 账号
     */
    private String accountNo;
    /**
     * 变动金额
     */
    private double amount;
    /**
     * 事务号
     */
    private String txNo;
}
