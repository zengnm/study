package com.share.example.manager;

import com.jd.common.util.SequenceUtil;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by lihuiyan on 2015/6/30.
 */
public class BaseManager {
    protected SequenceUtil sequenceUtil;

    private PlatformTransactionManager lotteryTransactionManager;

    public TransactionTemplate getLotteryDataSourceTransactionManager() {
        return new TransactionTemplate(lotteryTransactionManager);
    }

    public void setLotteryTransactionManager(PlatformTransactionManager lotteryTransactionManager) {
        this.lotteryTransactionManager = lotteryTransactionManager;
    }

    public void setSequenceUtil(SequenceUtil sequenceUtil) {
        this.sequenceUtil = sequenceUtil;
    }
}
