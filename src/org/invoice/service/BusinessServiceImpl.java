package org.invoice.service;

import org.invoice.dao.BusinessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 李浩然 on 2017/5/5.
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    public double getIncomeBetweenTwoDate(Date start, Date end) {
        double mainIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_MAIN_INCOME, BusinessDao.TABLE_MAIN_BUS, start, end);
        double otherIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OTHER_INCOME, BusinessDao.TABLE_OTHER_BUS, start, end);
        double outIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_INCOME, BusinessDao.TABLE_OUT_BUS, start, end);
        double income = mainIncome + otherIncome + outIncome;
        return income;
    }

    @Override
    public double getCostBetweenTwoDate(Date start, Date end) {
        double mainCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_MAIN_COST, BusinessDao.TABLE_MAIN_BUS, start, end);
        double otherCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OTHER_COST, BusinessDao.TABLE_OTHER_BUS, start, end);
        double outCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_COST, BusinessDao.TABLE_OUT_BUS, start, end);
        double cost = mainCost + otherCost + outCost;
        return cost;
    }

    @Override
    public double getGrossProfitRateBetweenTwoDate(Date start, Date end) {
        double income = getIncomeBetweenTwoDate(start, end);
        double cost = getCostBetweenTwoDate(start, end);
        double gross_profit = 0;
        if(income != 0) {
            gross_profit = (income - cost) / income;
        }
        return gross_profit;
    }

    @Override
    public double getRetainedProfitBetweenTwoDate(Date start, Date end) {
        double mainIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_MAIN_INCOME, BusinessDao.TABLE_MAIN_BUS, start, end);
        double otherIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OTHER_INCOME, BusinessDao.TABLE_OTHER_BUS, start, end);
        double outIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_INCOME, BusinessDao.TABLE_OUT_BUS, start, end);
        double mainCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_MAIN_COST, BusinessDao.TABLE_MAIN_BUS, start, end);
        double otherCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OTHER_COST, BusinessDao.TABLE_OTHER_BUS, start, end);
        double outCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_COST, BusinessDao.TABLE_OUT_BUS, start, end);
        double taxSum = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_TAX_SUM, BusinessDao.TABLE_MAIN_BUS, start, end);
        double mainAddition = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_MAIN_ADDITION, BusinessDao.TABLE_MAIN_BUS, start, end);
        double otherAddition = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OTHER_ADDITION, BusinessDao.TABLE_OTHER_BUS, start, end);
        double incomeTax = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_INCOME_TAX, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double investIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_INVEST_INCOME, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double subsidy = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_SUBSIDY, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double retainedProfit = mainIncome - mainCost - taxSum - mainAddition
                + otherIncome - otherCost - otherAddition + outIncome - outCost
                - incomeTax + investIncome + subsidy;
        return retainedProfit;
    }

    @Override
    public double getRetainedProfitRateBetweenTwoDate(Date start, Date end) {
        double rate = 0;
        double retainedProfit = getRetainedProfitBetweenTwoDate(start, end);
        double income = getIncomeBetweenTwoDate(start, end);
        if(income != 0) {
            rate = retainedProfit / income;
        }
        return rate;
    }

    @Override
    public double getProfitSumBetweenTwoDate(Date start, Date end) {
        double retainedProfit = getRetainedProfitBetweenTwoDate(start, end);
        double incomeTax = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_INCOME_TAX, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double sum = retainedProfit + incomeTax;
        return sum;
    }

    @Override
    public double getRetainedRaiseRate(Date fStart, Date fEnd, Date bStart, Date bEnd) {
        double rate = 0;
        double fRetainedProfit = getRetainedProfitBetweenTwoDate(fStart, fEnd);
        double bRetainedProfit = getRetainedProfitBetweenTwoDate(bStart, bEnd);
        if (bRetainedProfit != 0) {
            rate = (fRetainedProfit - bRetainedProfit) / bRetainedProfit;
        }
        return rate;
    }

    @Override
    public double getBusProfitBetweenTwoDate(Date start, Date end) {
        double profitSum = getProfitSumBetweenTwoDate(start, end);
        double outIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_INCOME, BusinessDao.TABLE_OUT_BUS, start, end);
        double outCost = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_OUT_COST, BusinessDao.TABLE_OUT_BUS, start, end);
        double investIncome = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_INVEST_INCOME, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double subsidy = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_SUBSIDY, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double profit = profitSum - outIncome + outCost - investIncome - subsidy;
        return profit;
    }

    @Override
    public double getCostUseRateBetweenTwoDate(Date start, Date end) {
        double profitSum = getProfitSumBetweenTwoDate(start, end);
        double costSum = businessDao.getSumBetweenTwoDate(
                BusinessDao.COL_COST_SUM, BusinessDao.TABLE_BASIC_SITUATION, start, end);
        double rate = 0;
        if (costSum != 0) {
            rate = profitSum / costSum;
        }
        return rate;
    }

    @Override
    public double getStableCoefficientBetweenTwoDate(Date start, Date end) {
        double profitSum = getProfitSumBetweenTwoDate(start, end);
        double busProfit = getBusProfitBetweenTwoDate(start, end);
        double coefficient = 0;
        if(profitSum != 0) {
            coefficient = busProfit / profitSum;
        }
        return coefficient;
    }

    @Override
    public double getBusProfitRaiseRate(Date fStart, Date fEnd, Date bStart, Date bEnd) {
        double fBusProfit = getBusProfitBetweenTwoDate(fStart, fEnd);
        double bBusProfit = getBusProfitBetweenTwoDate(bStart, bEnd);
        double rate = 0;
        if (bBusProfit != 0) {
            rate = fBusProfit / bBusProfit;
        }
        return rate;
    }

    @Override
    public double getAverageTotalNetAssetBetweenTwoDate(Date start, Date end) {
        double sProperty = businessDao.getPropertyOnDate(start);
        double eProperty = businessDao.getPropertyOnDate(end);
        double avg = (sProperty + eProperty) / 2;
        return avg;
    }

    @Override
    public double getNetAssetEarningRateBetweenTwoDate(Date start, Date end) {
        double retainedProfit = getRetainedProfitBetweenTwoDate(start, end);
        double avgNetAsset = getAverageTotalNetAssetBetweenTwoDate(start, end);
        double rate = 0;
        if(avgNetAsset != 0) {
            rate = retainedProfit / avgNetAsset;
        }
        return rate;
    }
}
