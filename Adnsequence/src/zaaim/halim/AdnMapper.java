package zaaim.halim;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AdnMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		char[] lineChars = line.toString().toCharArray();

		for (char c : lineChars) {
            word.set(""+c);
			context.write(word, one);

		}
	}

}
