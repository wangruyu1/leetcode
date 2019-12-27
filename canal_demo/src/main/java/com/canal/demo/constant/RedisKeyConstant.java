package com.canal.demo.constant;

public interface RedisKeyConstant {
    /**
     * boss静态特征key
     */
    String BOSS_STATIC_FEATURE = "boss:%s:static:feature";
    /**
     * 公司维度的hashkey
     */
    String COMPANY_FEATURE = "company:%s:feature";

}
