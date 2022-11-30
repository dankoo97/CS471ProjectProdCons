package prodcon;

import prodcon.buffer.Buffer;
import prodcon.buffer.SaleRecord;
import prodcon.statistics.Statistics;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {
    private static int nextConsumerID;
    private final int consumerID;
    private final ArrayList<SaleRecord> saleRecords;
    private final static Semaphore consumeItem = new Semaphore(1);
    private final static int[] sleepRange = {5, 20};

    public Consumer() {
        consumerID = nextConsumerID;
        nextConsumerID++;

        saleRecords = new ArrayList<>();
    }

    public Statistics getStatistics() {
        return new Statistics(saleRecords);
    }

    public ArrayList<SaleRecord> getSaleRecords() {
        return saleRecords;
    }

    public boolean consumeNextItem() throws InterruptedException {
        consumeItem.acquire();
        if(!Buffer.getSaleRecordList().isEmpty()){
            SaleRecord sale = Buffer.pop();
            saleRecords.add(sale);
            consumeItem.release();
            Thread.sleep(ThreadLocalRandom.current().nextInt(sleepRange[0], sleepRange[1]));
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
