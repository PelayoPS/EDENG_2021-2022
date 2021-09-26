package session1;

import java.io.FileWriter;


import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;


public class TestBench {
	public static final int SLEEP_TIME = 2;
	
	/**
	 * Simple version of test method
	 * @param n
	 */
	public static void test(long n) {
		//executes an algorithm for a given workload 'n'...
		double beforeExec = System.currentTimeMillis();
		Algorithms.linear(n);
		double ExecTime = System.currentTimeMillis()-beforeExec;
		System.out.print(ExecTime + "ms");
	}
	
	/**
	 * does nothing to simulate a high load in each iteracion
	 * @param i
	 */
	public static void doNothing(long i) {
		System.out.print("Doing nothing at iteraion...("+i+")\n");
		long endTime = System.currentTimeMillis() + SLEEP_TIME;
		while(System.currentTimeMillis() < endTime) {
			//does nothing here
		}
	
	}
	
	/**
	 * Developed version of the test method in which the values are printed in a document
	 * @param outputFileName
	 * @param startN
	 * @param endN
	 */
	public static void test(String outputFileName, int startN, int endN) {
		//executes an algorithm for a given workload 'n'...
		List<Long> timesList = new ArrayList<Long>();
		for (int i = startN; i <= endN; i++) {
			long beforeExec = System.currentTimeMillis();
			Algorithms.linear(i);
			timesList.add((System.currentTimeMillis()-beforeExec));			
		}
		writeFile(outputFileName, timesList);
		System.out.print(timesList + "ms");
	}
	
	/**
	 * Developed version of the test method in which the values are printed in a document
	 * In his new version we get to determine the number of samples
	 * @param outputFileName
	 * @param startN
	 * @param endN
	 * @throws Exception 
	 */
	public static void test(String outputFileName, int samples, int startN, int endN,
			String className, String methodName) throws Exception {
		//executes an algorithm for a given workload 'n'...
		List<Long> timesList = new ArrayList<Long>();
		List<Long> meanTimeList = new ArrayList<Long>();
		for (int i = 0; i <= (endN-startN);i++) {
			meanTimeList.add((long) 0);
		}
		for (int i = 0; i < samples; i++) {
			for (int j = startN; j <= endN; j++) {
				long beforeExec = System.currentTimeMillis();
				testAlgorithm(className, methodName, j);//loads the method using recursion
				timesList.add((System.currentTimeMillis()-beforeExec));	
				long aux = meanTimeList.get(j-startN);
				aux += timesList.get(j-startN);
				meanTimeList.set(j-startN,aux);
			}
		}
		for (int i = 0; i < meanTimeList.size(); i++) {
			meanTimeList.set(i,(meanTimeList.get(i) / samples));
		}
		writeFile(outputFileName, meanTimeList);
		System.out.print(meanTimeList + "ms");
	}
	
	/**
	 * private method used to implement reflection
	 *  so the methods can be loaded dynamically
	 * @param className
	 * @param methodName
	 * @param n
	 * @throws Exception
	 */
	private static void testAlgorithm(String className, String methodName, long n)
			throws Exception {
		Class<?> myClass = null;
		Object myObject = null;
		
		//loads the class dynamically using reflection
		myClass = Class.forName(className);
		myObject = myClass.newInstance();
		
		//gets a method instance
		Class<?>[] params = new Class[1];
		params[0] = Long.TYPE;
		
		Method m = myClass.getMethod(methodName, params);
		
		//Calls the method in java using reflection
		m.invoke(myObject, n);
		
		
	}
	
	/**
	 * utility method to write lists in a file
	 * @param fileName
	 * @param data
	 */
	private static void writeFile(String fileName, List<Long> data) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(fileName);
			pw = new PrintWriter(file);
			for (int i = 0; i < data.size(); i++) {
				pw.println(data.get(i));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try {
				 if(file != null) {
					 file.close();
				 }
			 } catch (Exception e1) {
				 e1.printStackTrace();
			 }
		}
	}
	
	
}
