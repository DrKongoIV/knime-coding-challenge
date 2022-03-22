package de.sixhours.knimecc;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.lang.Thread;

/**
 * Main class.
 * 
 * @author KNIME GmbH
 */
public class Main { 

	public static void main(String[] args) throws Exception {
		// Input parameters
		String inputFile, inputType, outputFile = null;
		String[] operations;
		int numThreads;
		
		// Parse command line arguments
		// Very rudimentary, in real-world scenario I would use apache cli or java-getopt or similar
		// This assumes that the arguments are provided exactly as specified and in that particular order
		inputFile = args[1];
		inputType = args[3];
		operations = args[5].split(",");
		numThreads = Integer.parseInt(args[7]);
		
		// Save the standard output for later, because we might change System.out
		PrintStream stdout = System.out;
		if (args.length > 8) { // Only if "--output" is provided, we try to read the filename
			outputFile = args[9];
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile))));
		}
		
		LineHandler h = new LineHandler(inputType, operations);
		
		if (numThreads > 1) {
			multithreaded(inputFile, numThreads, h);
		} else {
			// default to sequential solution using a stream
			// this stream solution could also be parallelized
			try (Stream<String> lines = Files.lines(Paths.get(inputFile))) {
				lines.map(h::handle).forEachOrdered(System.out::println); // System.out might be a file
			}
		}
		
		System.setOut(stdout);
		
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
	}

	/**
	 * Consume and process the input file.
	 * @param inputFile             A newline-separated file of input values
	 * @param numThreads            How many worker threads shall be summoned
	 * @param lineHandler           The {@link LineHandler} instance to use
	 * @throws IOException          In case reading / writing fails
	 * @throws InterruptedException In case a thread gets interrupted
	 */
	private static void multithreaded(String inputFile, int numThreads, LineHandler lineHandler) throws IOException, InterruptedException {
		// We have to read the whole file anyway to count the number of lines, so there's no point in in parallelizing this
		String[] lines = Files.lines(Paths.get(inputFile)).toArray(String[]::new);
		
		// This is the lazy solution. Alternatively, one could define a lock on the Statistics instance and update from within the worker threads
		for (String line : lines) Statistics.getInstance().updateStatisticsWithLine(line);
		lineHandler.disableCounting();
		
		List<Thread> threads = new LinkedList<>();
		for (int i = 0; i < numThreads; i++) {
			final int threadIndex = i;
			Thread t = new Thread(() -> {
				// The thread will operate directly on the array with all the lines
				// I don't know if this is indeed thread-safe -- it looks like it.
				int lower = threadIndex * lines.length / numThreads;
				int upper = (threadIndex+1 == numThreads) ? lines.length : (threadIndex+1) * lines.length / numThreads;
				for (int ix = lower; ix < upper; ix++) {
					lines[ix] = lineHandler.handle(lines[ix]);
				}
			});
			threads.add(t);
			t.start();
		}
		// Join with the worker threads (order doesn't matter)
		for (Thread t : threads) {
			t.join();
		}
		for (String line : lines) {
			System.out.println(line);
		}
	}

}
