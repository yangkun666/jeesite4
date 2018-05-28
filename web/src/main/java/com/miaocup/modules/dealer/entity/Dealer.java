package com.miaocup.modules.dealer.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Dealer {

    /**
     * 经销商编码
     */
    private String companyCode;

    /**
     * 经销商名称
     */
    private String companyName;

    /**
     * 经销商收益
     */
    private BigDecimal amount;

    /**
     * 上级回扣
     */
    private BigDecimal brokerage;

    /**
     * 账单开始日期
     */
    private Date startDate;

    /**
     * 账单结束日期
     */
    private Date endDate;

}
