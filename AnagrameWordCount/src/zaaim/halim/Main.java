package zaaim.halim;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Main {

	public static void main(String[] args) throws IOException,InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"AnagramwordCount");
		job.setJarByClass(Main.class);
		
		 job.setOutputKeyClass(Text.class);
		 job.setOutputValueClass(Text.class);

		 job.setMapOutputKeyClass(Text.class);
		 job.setMapOutputValueClass(Text.class);
		
		job.setMapperClass(AnagramMapper.class);
		job.setReducerClass(AnagramReducer.class);
		 job.setCombinerClass(Combiner.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);

	}

}