package com.yibo.eureka.seata.mapper;

import com.yibo.eureka.seata.entity.AccountInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountInfoMapper extends Mapper<AccountInfo> {

    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Long amount);
}