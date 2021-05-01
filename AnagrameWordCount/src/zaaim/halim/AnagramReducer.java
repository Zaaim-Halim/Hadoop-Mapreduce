package zaaim.halim;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnagramReducer extends Reducer<Text, Text, IntWritable, Text> {
	private IntWritable count = new IntWritable();
	private Text outputValue = new Text();

	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Set<Text> uniques = new HashSet<Text>();
		int size = 0;
		StringBuilder builder = new StringBuilder();
		for (Text value : values) {
			if (uniques.add(value)) {
				size++;
				builder.append(value.toString());
				builder.append(",");
			}
		}
		builder.setLength(builder.length() - 1); //to delete "," from the end of the string 

		if (size > 1) {
			count.set(size);
			outputValue.set(builder.toString());
			context.write(count, outputValue);
		}
	}

}
