package zaaim.halim;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MutationReducer extends Reducer<Text, IntWritable, Text, Text> {
	private Text pos = new Text();
	private Text output = new Text();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int count = 0;
		StringBuilder positions = new StringBuilder();
		for (IntWritable val : values) {

			positions.append(val.get());
			positions.append(",");
			count++;
		}
		if (positions.length() > 1)
			positions.setLength(positions.length() - 1); // to delete "," from the end of the string
		output.set(key.toString() + "\t" + count);
		pos.set(positions.toString());
		context.write(output, pos);
	}
}
