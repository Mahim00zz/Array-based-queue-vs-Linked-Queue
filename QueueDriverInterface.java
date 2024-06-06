
public interface QueueDriverInterface {

	
	
	/**
	 * 
	 * The <code>QueueType</code> enum has the following values:
	 * <ol>
	 * <li>ArrayBasedQueue</li>
	 * <li>LinkedQueue</li>
	 * </ol>
	 *
	 */
	public static enum QueueType {ArrayBasedQueue, LinkedQueue};

	
	
	/**
	 * 
	 * The <code>QueueTestType</code> enum has the following values:
	 * <ol>
	 * <li>Enqueue</li>
	 * <li>Dequeue</li>
	 * <li>DequeueRandom</li>
	 * </ol>
	 *
	 */
	public static enum TestType {Enqueue, Dequeue, DequeueRandom};

	
	
	/**
	 * 
	 * This method is used to create a new queue of the specified <b>queueType</b>. Depending on the 
	 * specified <b>TestType</b>, this method may need to initialize the queue with the required elements.
	 * All the elements in your queues will be instances of the String class.
	 * <br>
	 * <dl>
	 * <dt>
	 * Enqueue
	 * </dt>
	 * <dd>
	 * For this test case, this method will return an empty queue.
	 * </dd>
	 * <dt>
	 * Dequeue
	 * </dt>
	 * <dd>
	 * For this test case, this method will return a queue containing 10,000 String objects representing the strings: 
	 * "String 1", "String 2", ... , "String 10000". 
	 * </dd>
	 * <dt>
	 * DequeueRandom
	 * </dt>
	 * <dd>
	 * For this test case,  this method will return a queue containing 10,000 String objects representing the strings: 
	 * "String 1", "String 2", ... , "String 10000".
	 * </dd>
	 * </dl>
	 * 
	 * @param queueType		This parameter specifies the type of queue to create. See the enum {@link QueueType}.
	 * 
	 * @param testType	This parameter specifies the type of test that the queue is being created for. See the enum
	 * {@link TestType}.
	 * 
	 * @return				The method will return the created queue as a QueueInterface&lt;String&gt;.
	 * 
	 */
	public QueueInterface<String> createQueue(QueueType queueType, TestType testType);

	
	
	/**
	 *
	 * <p>
	 * This method is called to run a particular <b>testType</b> on a queue of type <b>queueType</b> for the specified 
	 * <b>numberOfTimes</b>. The test time and memory usage for each test is saved in the TestTimes class. 
	 * </p>
	 * 
	 * <p>
	 * This method repeats the following <code>numberOfTimes</code>:
	 * <ul>
	 * <li>
	 * Obtain and initialize a queue of type <b>queueType</b>
	 * </li>
	 * <li>
	 * Make a copy of the initialized queue
	 * </li>
	 * <li>
	 * Capture <b>startTime</b>
	 * </li>
	 * <li>
	 * Run the specified <b>testType</b> 
	 * </li>
	 * <li>
	 * Capture <b>endTime</b>
	 * </li>
	 * <li>
	 * Calculate <b>testTime</b> and add it to your TestTimes class 
	 * </li>
	 * <li>
	 * Make a copy of queue after <b>testType</b> is completed
	 * </li>
	 * </ul>
	 * </p>
	 *
	 * @param 	queueType		The type of queue needed for the test case. See the enum {@link QueueType}.
	 * 
	 * @param 	queueTestType	The type of test case being run. See the enum {@link TestType}.
	 * 
	 * @param 	numberOfTimes 	This parameter specifies the number of times to run the specified test.
	 * 
	 * @return The method must return an array of <b>Object</b> containing the following:
	 * <ul>
	 * <li>
	 * array[0] - Must contain a copy of the initialized queue for the first test.
	 * </li>
	 * <li>
	 * array[1] - Must contain a copy of the queue after the first test.
	 * </li>
	 * <li>
	 * array[2] - Must contain a copy of the initialized queue for the second test.
	 * </li>
	 * <li>
	 * array[3] - Must contain a copy of the queue after the second test.
	 * </li>
	 * <li>
	 * ...
	 * </li>
	 * <li>
	 * array[numberOfTimes*2-2] - Must contain a copy of the initialized queue before for the last test.
	 * </li>
	 * <li>
	 * array[numberOfTimes*2-1] - Must contain a copy of the queue after the last test.
	 * </li>
	 * <li>
	 * array[numberOfTimes*2] - Must contain the TestTimes class that was used to save the measured test times and memory
	 * usages for the <b>testType</b> performed.
	 * </li>
	 * </ul> 
	 * 
	 */
	public Object[] runTestCase(QueueType queueType, TestType queueTestType, int numberOfTimes); 
}
