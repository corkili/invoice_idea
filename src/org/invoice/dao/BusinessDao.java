package org.invoice.dao;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by 李浩然 on 2017/5/5.
 */
public interface BusinessDao {
    public static final String COL_DATE = "date";

    public static final String TABLE_MAIN_BUS = "table_main_bus";
    public static final String COL_MAIN_INCOME = "main_income";
    public static final String COL_MAIN_COST = "main_cost";
    public static final String COL_TAX_SUM = "tax_sum";
    public static final String COL_MAIN_ADDITION = "main_addition";

    public static final String TABLE_OTHER_BUS = "table_other_bus";
    public static final String COL_OTHER_INCOME = "other_income";
    public static final String COL_OTHER_COST = "other_cost";
    public static final String COL_OTHER_ADDITION = "other_addition";

    public static final String TABLE_OUT_BUS = "table_out_bus";
    public static final String COL_OUT_INCOME = "out_income";
    public static final String COL_OUT_COST = "out_cost";

    public static final String TABLE_BASIC_SITUATION = "table_basic_situation";
    public static final String COL_INVEST_INCOME = "invest_income";
    public static final String COL_SUBSIDY = "subsidy";
    public static final String COL_PROFIT_SUM = "profit_sum";
    public static final String COL_INCOME_TAX = "income_tax";
    public static final String COL_GROSS_PROFIT = "gross_profit";
    public static final String COL_RETAINED_PROFIT = "retained_profit";
    public static final String COL_COST_SUM = "cost_sum";
    public static final String COL_PROPERTY = "property";

    public double getSumBetweenTwoDate(String col, String table, Date start, Date end);
    public double getPropertyOnDate(Date date);
}
