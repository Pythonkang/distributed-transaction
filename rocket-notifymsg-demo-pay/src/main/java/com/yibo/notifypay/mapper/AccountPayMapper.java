package com.yibo.notifypay.mapper;

import com.yibo.notifypay.domain.entity.AccountPay;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountPayMapper extends Mapper<AccountPay> {


    int insertAccountPay(@Param("id") String id, @Param("accountNo") String accountNo, @Param("payAmount") Long pay_amount, @Param("result") String result);


    AccountPay findByIdTxNo(@Param("txNo") String txNo);
}