package prodcon.statistics;

import prodcon.buffer.SaleRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    private final HashMap<Integer, Float> monthlySales;
    private final HashMap<Integer, Float> storeWideSales;
    private float totalSales;

    /**
     * Creates a statistics object to keep track of statistics as outlined in project description
     * @param sales a list of sales
     */
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

    /**
     * Appends more sales to the statistics
     * @param sales The sales to add
     */
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

    /**
     * Appends the statistics from other statistics to this
     * @param other An iterable of other statistics objects
     */
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

    /**
     * Slightly more readable stats
     * @return A string
     */
    public String toPrettyString() {
        return "Monthly Sales: " + monthlySales +
                "\nStore-wide Sales: " + storeWideSales +
                "\nTotal Sales: " + totalSales;
    }
}
