package prodcon;

import prodcon.buffer.Buffer;
import prodcon.statistics.Statistics;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;



public class ProdConDriver {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();

        int p = Integer.parseInt(args[0]);
        int c = Integer.parseInt(args[1]);
        Buffer.setMaxBuffer(Integer.parseInt(args[2]));

        ArrayList<Producer> producers = new ArrayList<>(p);
        ArrayList<Consumer> consumers = new ArrayList<>(c);

        /**
         * Loops p times, generating p producers
         */
        for (int i = 0; i < p; i++) {
            producers.add(new Producer(i+1));
            producers.get(i).start();
        }

        /**
         * Loops c times, generating c consumers
         */
        for (int i = 0; i < c; i++) {
            consumers.add(new Consumer());
            consumers.get(i).start();
        }

        /**
         * Listening for producer threads to finish execution
         */
        for (Producer producer : producers) {
            producer.join();
        }

        Statistics globalStats = new Statistics();

        /**
         * Listens for consumer threads to finish execution
         * then prints consumer statistics
         */
        for (Consumer consumer : consumers) {
            consumer.join();
            Statistics consumerStats = consumer.getStatistics();
            globalStats.appendStatistics(consumerStats);
            System.out.println("Consumer ID: " + consumer.getConsumerID());
            System.out.println(consumerStats.toPrettyString());
            System.out.println();
        }

        System.out.println("All Producers: ");
        System.out.println(globalStats.toPrettyString());
        System.out.println(Duration.between(start, Instant.now()).toMillis() + " Milliseconds");
        System.out.println();
    }
}
