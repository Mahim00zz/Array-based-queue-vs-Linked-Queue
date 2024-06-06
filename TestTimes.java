
public class TestTimes implements TestTimesInterface {

    private double[] TestTimes = new double[10];
    private double[] memoryUsages = new double[10];
    private int index = 0;

    private TimeUnits timeUnits;
    private MemoryUnits memoryUnits;

    @Override
    public TimeUnits getTimeUnits() {

        return timeUnits;
    }

    @Override
    public void setTimeUnits(TimeUnits timeUnits) {

    
        this.timeUnits = timeUnits;

    }

    @Override
    public MemoryUnits getMemoryUnits() {

        return memoryUnits;
    }

    @Override
    public void setMemoryUnits(MemoryUnits memoryUnits) {

        this.memoryUnits = memoryUnits;
    }

    @Override
    public double getLastTestTime() {

        if (index != 0) {
            if (timeUnits == TimeUnits.Seconds)
                return TestTimes[index-1]/(1000*1000*1000);
            else if (timeUnits == TimeUnits.MilliSeconds)
                return TestTimes[index-1]/(1000*1000);
            else if (timeUnits == TimeUnits.MicroSeconds)
                return TestTimes[index-1]/(1000);
            else
                return TestTimes[index-1];
        } else {
            return 0.0;
        }
    }

    @Override
    public double getLastMemoryUsage() {

        if (index != 0) {

            if (memoryUnits == MemoryUnits.KiloBytes)
                return memoryUsages[index-1]/1024;
            else if (memoryUnits == MemoryUnits.MegaBytes)
                return memoryUsages[index-1]/(1024*1024);
            else
                return memoryUsages[index-1];

        } else {
            return 0.0;
        }
    }

    @Override
    public double[] getTestTimes() {

        if (timeUnits == TimeUnits.Seconds) {
            double[] temp = new double[10];
            for (int i=0; i<10; i++) {
                temp[i] = TestTimes[i]/(1000*1000*1000);
            }

            return temp;
        }
        else if (timeUnits == TimeUnits.MilliSeconds) {
            double[] temp = new double[10];
            for (int i=0; i<10; i++) {
                temp[i] = TestTimes[i]/(1000*1000);
            }

            return temp;
        }
        else if (timeUnits == TimeUnits.MicroSeconds) {
            double[] temp = new double[10];
            for (int i=0; i<10; i++) {
                temp[i] = TestTimes[i]/(1000);
            }

            return temp;
        }
        else
            return TestTimes;
    }

    @Override
    public double[] getMemoryUsages() {

        if (memoryUnits == MemoryUnits.KiloBytes)
        {
            double[] temp = new double[10];
            for (int i=0; i<10; i++)
                temp[i] = memoryUsages[i]/1024;

            return temp;
        }
        else if (memoryUnits == MemoryUnits.MegaBytes)
        {
            double[] temp = new double[10];
            for (int i=0; i<10; i++)
                temp[i] = memoryUsages[i]/(1024*1024);

            return temp;
        }
        else
            return memoryUsages;
    }

    @Override
    public void resetTestTimes() {

        TestTimes = new double[10];
        memoryUsages = new double[10];
        index = 0;
    }

    @Override
    public void addTestTime(long testTime) {

        if (index < TestTimes.length) {
            TestTimes[index] = testTime;
            memoryUsages[index] = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            index++;
        } else {
            for ( int i = 0 ; i < (TestTimes.length - 1) ; i++ ) {
                TestTimes[i] = TestTimes[i+1];
                memoryUsages[i] = memoryUsages[i+1];
            }
            memoryUsages[9] = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            TestTimes[9] = testTime;
        }
    }

    @Override
    public double getAverageTestTime() {

        double sum = 0;
        for ( int i = 0 ; i < index; i++) {
            sum = sum + TestTimes[i];
        }

        double averageRunTime = sum / (double) index;

        if (timeUnits == TimeUnits.Seconds)
            return averageRunTime/(1000*1000*1000);
        else if (timeUnits == TimeUnits.MilliSeconds)
            return averageRunTime/(1000*1000);
        else if (timeUnits == TimeUnits.MicroSeconds)
            return averageRunTime/(1000);
        else
            return averageRunTime;
    }

    @Override
    public double getAverageMemoryUsage() {

        double sum = 0;
        for ( int i = 0 ; i < index; i++) {
            sum = sum + memoryUsages[i];
        }

        double averageMemoryUsage =  sum / (double) index;
        if (memoryUnits == MemoryUnits.KiloBytes)
            return averageMemoryUsage/1024;
        else if (memoryUnits == MemoryUnits.MegaBytes)
            return averageMemoryUsage/(1024*1024);
        else
            return averageMemoryUsage;

    }

}