package prodcon.buffer;

import java.util.Date;

public class SaleRecord {
    private Date saleDate;
    private int storeID;
    private int registerNumber;
    private float saleAmount;

    public SaleRecord() {
        saleDate = new Date();
        storeID = 0;
        registerNumber = 0;
        saleAmount = 0;
    }

    public SaleRecord(Date saleDate, int storeID, int registerNumber, float saleAmount) {
        this.saleDate = saleDate;
        this.storeID = storeID;
        this.registerNumber = registerNumber;
        this.saleAmount = saleAmount;
    }
}
