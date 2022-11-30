package prodcon;

import prodcon.buffer.Buffer;
import prodcon.buffer.SaleRecord;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    private int storeID;
    private static final Semaphore createItem = new Semaphore(1);
    private static final int[] sleepRange = {5, 40};

    public Producer() {
        storeID = 0;
    }

    public Producer(int storeID){
        this.storeID = storeID;
    }

    public void generateItems() throws InterruptedException {
        while (true) {
            createItem.acquire();
            if (SaleRecord.getTotalNumberOfSales() < SaleRecord.MAX_SALES) {
                SaleRecord saleRecord = SaleRecord.randomSaleRecord(this.storeID);
                Buffer.addSaleRecord(saleRecord);
                createItem.release();
            } else {
                createItem.release();
                break;
            }

            Thread.sleep(ThreadLocalRandom.current().nextInt(sleepRange[0], sleepRange[1]));
        }
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID){ this.storeID = storeID;}

    @Override
    public void run() {
        try {
            generateItems();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
