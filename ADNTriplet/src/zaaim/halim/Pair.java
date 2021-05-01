package zaaim.halim;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

// a custom data type
public class Pair implements Writable {
	private IntWritable position;
	private IntWritable count;

	public Pair() {
		position = new IntWritable(0);
		count = new IntWritable(0);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		position.readFields(in);
		count.readFields(in);

	}

	@Override
	public void write(DataOutput out) throws IOException {
		position.write(out);
		count.write(out);
	}
     // Getters and Setters 
	public IntWritable getPosition() {
		return position;
	}

	public void setPosition(IntWritable position) {
		this.position = position;
	}

	public IntWritable getCount() {
		return count;
	}

	public void setCount(IntWritable count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Pair [position=" + position + ", count=" + count + "]";
	}

}
