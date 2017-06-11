package org.invoice.model;

import java.util.List;

/**
 * Created by 李浩然 on 2017/6/11.
 */
public class ProductCome implements Comparable<ProductCome> {
    private String date;
    private List<String> names;
    private List<Double> amounts;

    public ProductCome(String date, List<String> names, List<Double> amounts) {
        this.date = date;
        this.names = names;
        this.amounts = amounts;
    }

    public String getDate() {
        return date;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }

    @Override
    public int compareTo(ProductCome o) {
        return date.compareTo(o.getDate());
    }

}
