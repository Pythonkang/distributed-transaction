package com.yibo.eureka.seata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "account_info")
public class AccountInfo {
    @Id
    private Long id;

    /**
     * 户主姓名
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 银行卡号
     */
    @Column(name = "account_no")
    private String accountNo;

    /**
     * 帐户密码
     */
    @Column(name = "account_password")
    private String accountPassword;

    /**
     * 帐户余额
     */
    @Column(name = "account_balance")
    private Long accountBalance;
}