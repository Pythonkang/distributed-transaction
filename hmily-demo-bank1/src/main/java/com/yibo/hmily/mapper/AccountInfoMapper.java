package com.yibo.hmily.mapper;

import com.yibo.hmily.domain.entity.AccountInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountInfoMapper extends Mapper<AccountInfo> {

    int subtractAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Long amount);

    int addAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Long amount);

    /**
     * 增加某分支事务try执行记录
     * @param localTradeNo 本地事务编号
     * @return
     */
    int addTry(@Param("txNo") String localTradeNo);

    /**
     * 增加某分支事务Confirm执行记录
     * @param localTradeNo
     * @return
     */
    int addConfirm(@Param("txNo") String localTradeNo);

    /**
     * 增加某分支事务Cancel执行记录
     * @param localTradeNo
     * @return
     */
    int addCancel(@Param("txNo") String localTradeNo);

    /**
     * 查询分支事务try是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    int isExistTry(@Param("txNo") String localTradeNo);

    /**
     * 查询分支事务confirm是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    int isExistConfirm(@Param("txNo") String localTradeNo);

    /**
     * 查询分支事务cancel是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    int isExistCancel(@Param("txNo") String localTradeNo);
}