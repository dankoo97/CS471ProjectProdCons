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

    /**
     * Creates a new consumer and increments consumerID
     */
    public Consumer() {
        consumerID = nextConsumerID;
        nextConsumerID++;

        saleRecords = new ArrayList<>();
    }

    /**
     * @return Returns the statistics for the consumer
     */
    public Statistics getStatistics() {
        return new Statistics(saleRecords);
    }

    public ArrayList<SaleRecord> getSaleRecords() {
        return saleRecords;
    }

    /**
     * Grabs an item from the buffer it one exists and consumes it. Sleeps after successfully consuming an item
     * @return Boolean of whether the function could consume an item
     * @throws InterruptedException Require for semaphore
     */
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

    public int getConsumerID() {
        return consumerID;
    }

    /**
     * The thread run keeps trying to consume items until all have been created and there are no more to consume
     */
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
