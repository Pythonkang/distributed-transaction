package com.yibo.rocketmq.domain.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "account_info")
public class AccountInfo {
    @Id
    private Long id;

    /**
     * 户
主姓名
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 银行
卡号
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