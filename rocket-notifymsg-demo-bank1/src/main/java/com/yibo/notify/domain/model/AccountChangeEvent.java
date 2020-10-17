package com.yibo.notify.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountChangeEvent implements Serializable {


    /**
     * 账号
     */
    private String accountNo;
    /**
     * 变动金额
     */
    private Long amount;
    /**
     * 事务号
     */
    private String txNo;

}
