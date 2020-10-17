package com.yibo.notifypay.domain.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "account_pay")
public class AccountPay {
    @Id
    private String id;

    /**
     * 账号
     */
    @Column(name = "account_no")
    private String accountNo;

    /**
     * 充值余额
     */
    @Column(name = "pay_amount")
    private Long payAmount;

    /**
     * 充值结果:success，fail
     */
    private String result;
}