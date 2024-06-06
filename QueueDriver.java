public class QueueDriver implements QueueDriverInterface {
    private static TestTimes runTime = new TestTimes();

    public static void main(String[] args) {
        QueueDriver queueDriver = new QueueDriver();
        String[] testTypes = {"Enqueue", "Dequeue", "DequeueRandom"};

        for (String testType : testTypes) {
            System.out.println("Test Type = " + testType + "\n");

            // Test Times Section
            printTestTimeHeaders();
            printTestTimes(queueDriver, testTypes);

            System.out.println(); // Separator between test times and memory usage

            // Memory Usage Section
            printMemoryUsageHeaders();
            printMemoryUsage(queueDriver, testTypes);

            System.out.println();  // Blank line between different test types
        }
    }

    private static void printTestTimeHeaders() {
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Run 1", "Run 2", "Run 3", "Run 4", "Run 5", "Run 6", "Run 7", "Run 8", "Run 9", "Run 10", "Average");
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro", "Micro");
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds", "Seconds");
        for (int i = 0; i < 112; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line after printing the dashes
    }

    private static void printTestTimes(QueueDriver queueDriver, String[] testTypes) {
        for (QueueType queueType : QueueType.values()) {
            System.out.println(queueType.name() + "\n");
            Object[] results = queueDriver.runTestCase(queueType, QueueDriverInterface.TestType.valueOf(testTypes[0]), 10); // Assuming the first testType for simplicity
            double averageTime = 0;
            for (int i = 0; i < 10; i++) {
                double time = ((TestTimes) results[results.length - 1]).getTestTimes()[i];
                System.out.printf("%-16s%-12.3f", "Run " + (i + 1), time / 1_000.0);
                averageTime += time;
            }
            averageTime /= 10.0;
            System.out.printf("%-12.3f%n", averageTime / 1_000.0);
            System.out.println();
        }
    }

    private static void printMemoryUsageHeaders() {
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Run 1", "Run 2", "Run 3", "Run 4", "Run 5", "Run 6", "Run 7", "Run 8", "Run 9", "Run 10", "Average");
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo", "Kilo");
        System.out.printf("%-16s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes", "Bytes");
        for (int i = 0; i < 112; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line after printing the dashes
    }

    private static void printMemoryUsage(QueueDriver queueDriver, String[] testTypes) {
        for (QueueType queueType : QueueType.values()) {
            System.out.println(queueType.name() + "\n");
            Object[] results = queueDriver.runTestCase(queueType, QueueDriverInterface.TestType.valueOf(testTypes[0]), 10); // Assuming the first testType for simplicity
            double averageMemory = 0;
            for (int i = 0; i < 10; i++) {
                // Convert memory usage from bytes to kilobytes by dividing by 1024
                double memoryInKiloBytes = ((TestTimes) results[results.length - 1]).getMemoryUsages()[i] / 1024;
                System.out.printf("%-16s%-12.3f", "Run " + (i + 1), memoryInKiloBytes);
                averageMemory += memoryInKiloBytes;
            }
            averageMemory /= 10.0; // Calculate the average memory usage in kilobytes
            System.out.printf("%-12.3f%n", averageMemory); // Print the average memory usage
            System.out.println();
        }
    }



    @Override
    public QueueInterface<String> createQueue(QueueType queueType, TestType testType) {
        switch (queueType) {
            case ArrayBasedQueue:
                ArrayBasedQueue<String> arrayBasedQueue = new ArrayBasedQueue<>();
                if (testType == TestType.Dequeue || testType == TestType.DequeueRandom) {
                    for (int i = 1; i <= 10000; i++) {
                        arrayBasedQueue.enqueue("String " + i);
                    }
                }
                return arrayBasedQueue;
            case LinkedQueue:
                LinkedQueue<String> linkedQueue = new LinkedQueue<>();
                if (testType == TestType.Dequeue || testType == TestType.DequeueRandom) {
                    for (int i = 1; i <= 10000; i++) {
                        linkedQueue.enqueue("String " + i);
                    }
                }
                return linkedQueue;
            default:
                return null;
        }
    }

    @Override
    public Object[] runTestCase(QueueType queueType, TestType queueTestType, int numberOfTimes) {
        runTime.resetTestTimes();
        Object[] results = new Object[numberOfTimes * 2 + 1];

        for (int i = 0; i < numberOfTimes; i++) {
            QueueInterface<String> queue = createQueue(queueType, queueTestType);
            QueueInterface<String> initialQueue = queue.copy();

            long startTime = System.nanoTime();

            switch (queueTestType) {
                case Enqueue:
                    for (int j = 1; j <= 10000; j++) {
                        queue.enqueue("String " + j);
                    }
                    break;
                case Dequeue:
                    while (!queue.isEmpty()) {
                        queue.dequeue(); // Dequeue all elements to empty the queue
                    }
                    break;
                case DequeueRandom:
                    while (!queue.isEmpty()) {
                        queue.dequeue(); // This loop will empty the queue
                    }
                    break;

                default:
                    break;
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            runTime.addTestTime(elapsedTime);

            QueueInterface<String> finalQueue = queue.copy();
            results[i * 2] = initialQueue;
            results[i * 2 + 1] = finalQueue;
        }

        results[results.length - 1] = runTime;
        return results;
    }
}
