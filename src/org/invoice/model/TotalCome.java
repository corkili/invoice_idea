package org.invoice.model;

import java.text.SimpleDateFormat;

/**
 * Created by 李浩然 on 2017/6/10.
 */
public class TotalCome implements Comparable<TotalCome> {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
    private String date;
    private double incomes;
    private double outcomes;

    public TotalCome(String date, double incomes, double outcomes) {
        this.date = date;
        this.incomes = incomes;
        this.outcomes = outcomes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(double incomes) {
        this.incomes = incomes;
    }

    public double getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(double outcomes) {
        this.outcomes = outcomes;
    }

    @Override
    public int compareTo(TotalCome o) {
        return date.compareTo(o.getDate());
    }
}
