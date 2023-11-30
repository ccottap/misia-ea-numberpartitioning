package es.uma.lcc.caesium.problem.numberpartitioning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;


/**
 * Number Partitioning problem
 * @author ccottap
 * @version 1.0
 */
public class NumberPartitioning {
	/**
	 * value of the numbers
	 */
	private long[] values;
	/**
	 * size of the instance
	 */
	private int num;
	/**
	 * class-wide RNG
	 */
	protected static Random rng = new Random(1);
	
	/**
	 * Sets the seed for the RNG
	 * @param seed the seed for the RNG
	 */
	public static void setSeed (long seed) {
		rng.setSeed(seed);
	}
	
	/**
	 * Creates a random instance given the bin size and the number of objects
	 * @param binSize bin size
	 * @param numObjects the number of objects
	 */
	public NumberPartitioning(int num, int digits){
		setNum(num);
		randomize(digits);
	}
	
	/**
	 * Returns the number of values
	 * @return the number of values
	 */
	public int getNum() {
		return num;
	}


	/**
	 * Sets the size of the instance (number of values)
	 * @param num the number of values
	 */
	private void setNum(int num) {
		this.num = num;
		values = new long[num];
	}
	
	/**
	 * Gets a value given its index
	 * @param i the index of the value
	 * @return the i-th value
	 */
	public long getValue (int i) {
		assert (i>=0) && (i < num);
		return values[i];
	}
	
	/**
	 * Randomizes the values of numbers
	 * @param digits number of digits of each value
	 */
	private void randomize(int digits) {
		for (int i=0; i<num; i++) {
			long v = rng.nextLong(9) + 1;
			for (int j=1; j<digits; j++)
				v = v * 10 + rng.nextLong(10);
			values[i] = v;
		}
	}

	/**
	 * Creates an instance from a file
	 * @param filename file with the instance data
	 * @throws FileNotFoundException if the file cannot be opened
	 */
	public NumberPartitioning(String filename) throws FileNotFoundException {
		readFromfile(filename);
	}

	/**
	 * Reads an instance from a file
	 * @param filename the name of the file
	 * @throws FileNotFoundException if the file cannot be opened
	 */
	public void readFromfile(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filename));
		setNum(reader.nextInt());
		for (int i=0; i<num; i++) {
			values[i] = reader.nextLong();
		}
		reader.close();		
	}
	
	/**
	 * Saves the data to a file
	 * @param filename name of the file
	 * @throws FileNotFoundException if the file cannot be created
	 */
	public void saveToFile (String filename) throws FileNotFoundException {
		PrintWriter file = new PrintWriter(filename);
		file.println(num);
		for (int i=0; i<num; i++) {
			file.println(values[i]);
		}
		file.close();
	}
	
	
	/**
	 * Returns the difference in weight (positive or negative) of
	 * a certain partition with respect to the complementay partition.
	 * @param partition a collection of indices
	 * @return the sum of values in the partition minus the sum of values not in the partition
	 */
	public long balance (Collection<Integer> partition) {
		long balance = 0;
		
		for (int i=0; i<num; i++)
			if (partition.contains(i))
				balance += values[i];
			else
				balance -= values[i];
		
		return balance;
	}

	@Override
	public String toString() {
		int num = values.length;
		String str = "numbers: " + num + "\nvalues:\n";
		for (int i=0; i<num; i++) {
			str += (i%10 == 0) ? "\n" : "\t";
			str += values[i];
		}
		str += "\n";
		return str;
	}
	
}
