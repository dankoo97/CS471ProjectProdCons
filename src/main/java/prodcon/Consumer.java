package prodcon;

import prodcon.buffer.Buffer;
import prodcon.buffer.SaleRecord;
import prodcon.statistics.Statistics;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
    private static int nextConsumerID;
    private final int consumerID;
    private final ArrayList<SaleRecord> saleRecordHashSet;
    private final static Semaphore consumeItem = new Semaphore(1);

    public Consumer() {
        consumerID = nextConsumerID;
        nextConsumerID++;

        saleRecordHashSet = new ArrayList<>();
    }

    public void addSalesRecord(SaleRecord sale) {
        saleRecordHashSet.add(sale);
    }

    public Statistics getStatistics() {
        return new Statistics(saleRecordHashSet);
    }

    public ArrayList<SaleRecord> getSaleRecordHashSet() {
        return saleRecordHashSet;
    }

    public boolean consumeNextItem() throws InterruptedException {
        consumeItem.acquire();
        if(!Buffer.getSaleRecordList().isEmpty()){
            SaleRecord sale = Buffer.pop();
            addSalesRecord(sale);
            consumeItem.release();
            return true;
        }
        consumeItem.release();
        return false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                boolean allCreated = SaleRecord.getTotalNumberOfSales() >= SaleRecord.MAX_SALES;
                boolean consumed = consumeNextItem();
                if (allCreated && !consumed) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
