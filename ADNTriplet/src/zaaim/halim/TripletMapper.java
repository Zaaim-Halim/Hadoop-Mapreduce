package zaaim.halim;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TripletMapper extends Mapper<LongWritable, Text, Text, Pair> {
	private IntWritable position = new IntWritable();
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().trim();
		int i = 0;
		for (i = 0; i < line.length() - 3; i++) {
			String triplet = line.substring(i, i + 3);
			String newLine = null;
			if (i > 0) {
				newLine = line.substring(i + 1, line.length());
			} else
				newLine = line.substring(i + 1, line.length());
			int j = 0;
			for (j = 0; j < newLine.length() - 3; j++) {
				if (line.substring(j, j + 2).equals(triplet)) {

					position.set(i + 1);
					word.set(triplet);
					Pair pair = new Pair();
					pair.setCount(one);
					pair.setPosition(position);
					context.write(word, pair);
				}

			}
			/* the remaining sequence from the newLine */
			position.set(j + 1);
			word.set(line.substring(j, newLine.length()));
			Pair pair = new Pair();
			pair.setCount(one);
			pair.setPosition(position);
			context.write(word, pair);

		}
		/* the remaining triplet from the line */
		position.set(i + 1);
		word.set(line.substring(i, line.length()));
		Pair pair = new Pair();
		pair.setCount(one);
		pair.setPosition(position);
		context.write(word, pair);
	}

}
