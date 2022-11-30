package prodcon;

import prodcon.buffer.Buffer;
import prodcon.buffer.SaleRecord;

import java.util.ArrayList;

public class ProdConDriver {
    public static void main(String[] args) throws InterruptedException {
        int p = Integer.parseInt(args[0]);
        int c = Integer.parseInt(args[1]);
        Buffer.setMaxBuffer(Integer.parseInt(args[2]));

        ArrayList<Producer> producers = new ArrayList<>(p);
        ArrayList<Consumer> consumers = new ArrayList<>(c);

        for (int i = 0; i < p; i++) {
            producers.add(new Producer(i+1));
            producers.get(i).start();
        }

        for (int i = 0; i < c; i++) {
            consumers.add(new Consumer());
            consumers.get(i).start();
        }

        for (Producer producer : producers) {
            producer.join();
        }

        int sum = 0;
        for (Consumer consumer : consumers) {
            consumer.join();
            sum += consumer.getSaleRecords().size();
            System.out.println(consumer.getStatistics());
        }

        System.out.println(sum);
        System.out.println(SaleRecord.getTotalNumberOfSales());

    }
}
