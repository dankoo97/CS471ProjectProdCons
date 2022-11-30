package prodcon.buffer;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {
    private static final ArrayList<SaleRecord> saleRecordList = new ArrayList<>();
    private static int b;
    private static Semaphore empty, full, mutex;

    public static void addSaleRecord(SaleRecord saleRecord) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        saleRecordList.add(saleRecord);
        mutex.release();
        full.release();
    }

    public static SaleRecord pop() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        SaleRecord first = saleRecordList.get(0);
        saleRecordList.remove(0);
        mutex.release();
        empty.release();
        return first;
    }

    public static ArrayList<SaleRecord> getSaleRecordList(){
        return saleRecordList;
    }

    public static void setB(int maxBuffer){
        b = maxBuffer;
        empty = new Semaphore(b);
        full = new Semaphore(0);
        mutex = new Semaphore(b);
    }
}
