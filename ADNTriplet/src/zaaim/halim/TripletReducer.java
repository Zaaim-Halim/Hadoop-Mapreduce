package zaaim.halim;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TripletReducer extends Reducer<Text, Pair, Text, IntWritable> {

	private IntWritable count = new IntWritable();
	private Text output = new Text();

	public void reduce(Text key, Iterable<Pair> pairs, Context context) throws IOException, InterruptedException {
		int sum = 0;
		StringBuilder positions = new StringBuilder();
		
			for (Pair val : pairs) {
				sum = sum + val.getCount().get();
				positions.append(val.getPosition().get());
				positions.append(",");

			}
			if (positions.length() > 1)
				positions.setLength(positions.length() - 1); // to delete "," from the end of the string
			output.set(key.toString() + "\t" + positions.toString());
			count.set(sum);
			context.write(output, count);
	}

}
