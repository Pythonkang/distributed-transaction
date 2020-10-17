package com.yibo.notify.mapper;

import com.yibo.notify.domain.entity.AccountInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountInfoMapper extends Mapper<AccountInfo> {

    //修改账户金额
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Long amount);

    //查询幂等记录，用于幂等控制
    int isExistTx(String txNo);

    //添加事务记录，用于幂等控制
    int addTx(String txNo);
}