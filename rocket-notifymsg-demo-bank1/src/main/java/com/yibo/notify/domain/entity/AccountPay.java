package com.yibo.notify.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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