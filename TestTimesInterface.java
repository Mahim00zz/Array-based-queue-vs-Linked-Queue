
/**
 * 
 * This interface will be used to organize and manage test times that are measured for specific operations.
 * In addition, memory usage is also captured and maintained every time the user adds a new run time.
 * 
 * The user will utilze <code>System.nanoTime()</code> to measure the test time of an operation.
 * 
 * <p>Before the operation is started, you can obtain the start time:
 * <br>
 * <code>startTime = System.nanoTime()</code>.</p>
 * 
 * <p>After the operation is completed, you can obtain the end time:
 * <br>
 * <code>endTime = System.nanoTime()</code>.</p>
 * 
 * <p>Run Time is then:
 * <br>
 * <code>runTime = endTime - startTime</code>. </p>
 * 
 * @author Sameh A. Fakhouri
 *
 */
public interface TestTimesInterface {

	

	/**
	 * 
	 * Run times are measured in Nano Seconds. This enum will be used to specify what units of time the user
	 * wishes to obtain the run times when calling {@link #getLastTestTime()}, {@link #getTestTimes()}, and
	 * {@link #getAverageTestTime()}.
	 * 
	 * <ol>
	 * <li>Seconds 		- Return run times in seconds.</li>
	 * <li>MilliSeconds	- Return run times in milli-seconds.</li>
	 * <li>MicroSeconds - Return run times in micro-seconds.</li>
	 * <li>NanoSeconds 	- Return run times in nano-seconds. (Default)</li>
	 * </ol>
	 *
	 * See also {@link #getTimeUnits()} and {@link #setTimeUnits(TimeUnits timeUnits)}.
	 *
	 */
	public static enum TimeUnits {Seconds, MilliSeconds, MicroSeconds, NanoSeconds};

	

	/**
	 * 
	 * Memory usage is measured in Bytes. This enum will be used to specify what units of bytes the user
	 * wishes to obtain the memory usage when calling {@link #getLastMemoryUsage()}, {@link #getMemoryUsages()}, and
	 * {@link #getAverageMemoryUsage()}.
	 * 
	 * <ol>
	 * <li>Bytes 	- Return memory usage in bytes. (Default)</li>
	 * <li>KiloBytes	- Return memory usage in kilo-bytes.</li>
	 * <li>MegaBytes	- Return memory usage in mega-bytes.</li>
	 * </ol>
	 *
	 * See also {@link #getMemoryUnits()} and {@link #setMemoryUnits(MemoryUnits memoryUnits)}.
	 *
	 */
	public static enum MemoryUnits {Bytes, KiloBytes, MegaBytes};
	
	
	
	/**
	 * 
	 * This method is used to obtain the current {@link TimeUnits} configured for the <code>RunTime</code>
	 * class. Please note that the default <code>TimeUnits</code> is <code>NanoSeconds</code>
	 * 
	 * @return The currently configured <code>TimeUnits</code>.
	 * 
	 */
	
	public TimeUnits getTimeUnits();
	
	
	
	/**
	 * 
	 * This method is used to configure the desired {@link TimeUnits}.
	 * 
	 * @param timeUnits The desired <code>TimeUnits</code>
	 * 
	 */
	public void setTimeUnits(TimeUnits timeUnits);
	
	
	
	/**
	 * 
	 * This method is used to obtain the current {@link MemoryUnits} configured for the <code>RunTime</code>
	 * class. Please note that the default <code>MemoryUnits</code> is <code>Bytes</code>
	 * 
	 * @return The currently configured <code>MemoryUnits</code>.
	 * 
	 */
	public MemoryUnits getMemoryUnits();
	
	
	
	/**
	 * 
	 * This method is used to configure the desired {@link MemoryUnits}.
	 * 
	 * @param memoryUnits The desired <code>MemoryUnits</code>
	 * 
	 */
	public void setMemoryUnits(MemoryUnits memoryUnits);
	
	
	/**
	 * 
	 * This method is used to retrieve the last test time. If no test time
	 * has been added, the method will return a zero.
	 * 
	 * @return The last test time, converted to the configured {@link TimeUnits},
	 * or 0.
	 *  
	 */
	public double getLastTestTime();
	
	
	
	/**
	 * 
	 * This method is used to retrieve the last memory usage. If no test time
	 * has been added, the method will return a zero.
	 * 
	 * @return The last memory usage, converted to the configured {@link MemoryUnits},
	 * or 0.0.
	 *  
	 */
	public double getLastMemoryUsage();
	
	
	
	/**
	 * 
	 * This method returns an array of <b>double</b> values representing the last 10 test times
	 * converted to the configured {@link TimeUnits}. If less than 10 test times are available, 
	 * the remaining test times should be zero. If more than 10 test times have been added, the array
	 * should contain the last 10 test times. 
	 * 
	 * @return An array of <b>double</b> values representing the last 10 test times.
	 * 
	 */
	public double[] getTestTimes();
	
	
	
	/**
	 * 
	 * This method returns an array of <b>double</b> values representing the last 10 memory usages
	 * converted to the configured {@link MemoryUnits}. 
	 * If less than 10 memory usages are available, the remaining
	 * memory usages should be zero. If more than 10 memory usages have been added, the array
	 * should contain the last 10 memory usages. 
	 * 
	 * @return An array of <b>double</b> values representing the last 10 memory usages.
	 * 
	 */
	public double[] getMemoryUsages();

	
	
	/**
	 * 
	 * This method is used to reset all 10 linear search times to zero.
	 * 
	 */
	public void resetTestTimes();

	
	
	/**
	 * 
	 * This method is used to add a test time and this method also captures the current memory usage and saves it as well.
	 * 
	 * @param runTime a <b>long</b> value representing the test time in nanoseconds.
	 * 
	 */
	public void addTestTime(long runTime);
	
	
	
	/**
	 * 
	 * This method is used to obtain the average test time. The method should average all
	 * the non-zero test times that are available. If no test times are available, the method
	 * returns a zero.
	 * 
	 * @return A <b>double</b> value representing the average of all the non-zero test times, 
	 * converted to the configured {@link TimeUnits} or 0.
	 */
	public double getAverageTestTime();
	
	
	
	/**
	 * 
	 * This method is used to obtain the average memory usage. The method should average all
	 * the non-zero memory usages that are available. If no memory usages are available, the method
	 * returns a zero.
	 * 
	 * @return A <b>double</b> value representing the average of all the non-zero memory usages, 
	 * converted to the configured {@link MemoryUnits}, or 0.0.
	 * 
	 */
	public double getAverageMemoryUsage();
	
}
