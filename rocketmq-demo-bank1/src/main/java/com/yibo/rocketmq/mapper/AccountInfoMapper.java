package com.yibo.rocketmq.mapper;

import com.yibo.rocketmq.domain.entity.AccountInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountInfoMapper extends Mapper<AccountInfo> {

    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    AccountInfo findByIdAccountNo(@Param("accountNo") String accountNo);

    int isExistTx(String txNo);

    int addTx(String txNo);
}