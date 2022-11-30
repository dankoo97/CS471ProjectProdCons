package prodcon.statistics;

import prodcon.buffer.SaleRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    private final HashMap<Integer, Float> monthlySales;
    private final HashMap<Integer, Float> storeWideSales;
    private float totalSales;

    @SafeVarargs
    public Statistics(ArrayList<SaleRecord>... sales) {
        monthlySales = new HashMap<>(12);
        storeWideSales = new HashMap<>();
        for (ArrayList<SaleRecord> sale: sales) {
            for (SaleRecord record : sale) {
                if (record == null) {
                    System.out.println("NULL");
                    continue;
                }
                int month = record.getMonth();
                int store = record.getStoreID();
                float saleAmount = record.getSaleAmount();

                monthlySales.compute(month, (key, value) -> (value == null ? 0 : value) + saleAmount);
                storeWideSales.compute(store, (key, value) -> (value == null ? 0 : value) + saleAmount);

                totalSales += saleAmount;
            }
        }
    }

    @SafeVarargs
    public final void appendStatistics(ArrayList<SaleRecord>... sales) {
        for (ArrayList<SaleRecord> sale: sales) {
            for (SaleRecord record : sale) {
                if (record == null) {
                    System.out.println("NULL");
                    continue;
                }
                int month = record.getMonth();
                int store = record.getStoreID();
                float saleAmount = record.getSaleAmount();

                monthlySales.compute(month, (key, value) -> (value == null ? 0 : value) + saleAmount);
                storeWideSales.compute(store, (key, value) -> (value == null ? 0 : value) + saleAmount);

                totalSales += saleAmount;
            }
        }
    }

    public final void appendStatistics(Statistics... other) {
        for (Statistics stats : other) {
            for (Integer monthly : stats.getMonthlySales().keySet()) {
                this.monthlySales.compute(monthly, (key, value) -> (value == null ? 0 : value) + stats.getMonthlySales().get(monthly));
            }

            for (Integer store : stats.getStoreWideSales().keySet()) {
                this.storeWideSales.compute(store, (key, value) -> (value == null ? 0 : value) + stats.getMonthlySales().get(store));
            }

            this.totalSales += stats.getTotalSales();
        }
    }

    public float getTotalSales() {
        return totalSales;
    }

    public HashMap<Integer, Float> getMonthlySales() {
        return monthlySales;
    }

    public HashMap<Integer, Float> getStoreWideSales() {
        return storeWideSales;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "monthlySales=" + monthlySales +
                ", storeWideSales=" + storeWideSales +
                ", totalSales=" + totalSales +
                '}';
    }

    public String toPrettyString() {
        return "Monthly Sales: " + monthlySales +
                "\nStore-wide Sales: " + storeWideSales +
                "\nTotal Sales: " + totalSales;
    }
}
