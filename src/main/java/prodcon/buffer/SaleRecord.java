package prodcon.buffer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class SaleRecord {
    private static int totalNumberOfSales;
    private int month;
    private int day;
    private int storeID;
    private int registerNumber;
    private float saleAmount;

    public static final int[] daysInMonth2016 = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public SaleRecord() {
        month = 1;
        day = 1;
        storeID = 0;
        registerNumber = 0;
        saleAmount = 0;

        totalNumberOfSales++;
    }

    public SaleRecord(int month, int day, int storeID, int registerNumber, float saleAmount) {
        this.month = month;
        this.day = day;
        this.storeID = storeID;
        this.registerNumber = registerNumber;
        this.saleAmount = saleAmount;

        totalNumberOfSales++;
    }

    public static SaleRecord randomSaleRecord(int storeID) {
        int month = ThreadLocalRandom.current().nextInt(12) + 1;
        int day = ThreadLocalRandom.current().nextInt(daysInMonth2016[month - 1]) + 1;
        int register = ThreadLocalRandom.current().nextInt(1, 6);
        float saleTotal = ThreadLocalRandom.current().nextFloat(0.50F, 999.99F);

        return new SaleRecord(month, day, storeID, register, saleTotal);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public float getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
    }

    public static int getTotalNumberOfSales() {
        return totalNumberOfSales;
    }

    @Override
    public String toString() {
        return "SaleRecord{" +
                "year=" + 2016 +
                ", month=" + month +
                ", day=" + day +
                ", storeID=" + storeID +
                ", registerNumber=" + registerNumber +
                ", saleAmount=" + saleAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleRecord that = (SaleRecord) o;
        return month == that.month && day == that.day && storeID == that.storeID && registerNumber == that.registerNumber && Float.compare(that.saleAmount, saleAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day, storeID, registerNumber, saleAmount);
    }
}
