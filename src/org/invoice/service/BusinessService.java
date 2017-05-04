package org.invoice.service;

import java.util.Date;

/**
 * Created by 李浩然 on 2017/5/5.
 */
public interface BusinessService {
    /**
     * 计算收入
     * @param start
     * @param end
     * @return
     */
    public double getIncomeBetweenTwoDate(Date start, Date end);

    /**
     * 计算成本
     * @param start
     * @param end
     * @return
     */
    public double getCostBetweenTwoDate(Date start, Date end);

    /**
     * 计算毛利率
     * @param start
     * @param end
     * @return
     */
    public double getGrossProfitRateBetweenTwoDate(Date start, Date end);

    /**
     * 计算净利润
     * @param start
     * @param end
     * @return
     */
    public double getRetainedProfitBetweenTwoDate(Date start, Date end);

    /**
     * 计算净利率
     * @param start
     * @param end
     * @return
     */
    public double getRetainedProfitRateBetweenTwoDate(Date start, Date end);

    /**
     * 计算利润总额
     * @param start
     * @param end
     * @return
     */
    public double getProfitSumBetweenTwoDate(Date start, Date end);

    /**
     * 计算净利润增值率函数
     * @param fStart
     * @param fEnd
     * @param bStart
     * @param bEnd
     * @return
     */
    public double getRetainedRaiseRate(Date fStart, Date fEnd, Date bStart, Date bEnd);

    /**
     * 计算营业利润
     * @param start
     * @param end
     * @return
     */
    public double getBusProfitBetweenTwoDate(Date start, Date end);

    /**
     * 计算成本费用利用率
     * @param start
     * @param end
     * @return
     */
    public double getCostUseRateBetweenTwoDate(Date start, Date end);

    /**
     * 计算稳定系数
     * @param start
     * @param end
     * @return
     */
    public double getStableCoefficientBetweenTwoDate(Date start, Date end);

    /**
     * 计算营业利润增长率
     * @param fStart
     * @param fEnd
     * @param bStart
     * @param bEnd
     * @return
     */
    public double getBusProfitRaiseRate(Date fStart, Date fEnd, Date bStart, Date bEnd);

    /**
     * 计算平均净资产总额
     * @param start
     * @param end
     * @return
     */
    public double getAverageTotalNetAssetBetweenTwoDate(Date start, Date end);

    /**
     * 计算净资产收益率
     * @param start
     * @param end
     * @return
     */
    public double getNetAssetEarningRateBetweenTwoDate(Date start, Date end);
}
