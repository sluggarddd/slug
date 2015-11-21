package com.slug.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhw
 * @version 0.1  15/11/21
 */
public class TransactionProxy {

    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);


    /**
     * use to mark if this thread dealing with transaction,default is false
     */
    private static final ThreadLocal<Boolean> flagContainer = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

}
