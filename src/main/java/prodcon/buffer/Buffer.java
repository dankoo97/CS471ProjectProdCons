package prodcon.buffer;

import java.util.ArrayList;

public class Buffer {
    private static final ArrayList<SaleRecord> saleRecordList = new ArrayList<>();

    public static void addSaleRecord(SaleRecord saleRecord) {
        saleRecordList.add(saleRecord);
    }

    public static SaleRecord pop() {
        SaleRecord first = saleRecordList.get(0);
        saleRecordList.remove(0);
        return first;
    }

    public static ArrayList<SaleRecord> getSaleRecordList(){ return saleRecordList; }
}
