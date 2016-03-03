package com.share.example.manager;

import com.share.common.util.SequenceUtil;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21.
 */
public class BaseManager {
    protected SequenceUtil sequenceUtil;

    private PlatformTransactionManager transactionManager;

    public TransactionTemplate getExampleDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setSequenceUtil(SequenceUtil sequenceUtil) {
        this.sequenceUtil = sequenceUtil;
    }
}
