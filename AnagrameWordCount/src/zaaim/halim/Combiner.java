package zaaim.halim;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;

public class Combiner extends org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text> {
    // pour supprimer les duplicants
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Set<Text> uniques = new HashSet<Text>();
		for (Text value : values) {
			if (uniques.add(value)) {
				context.write(key, value);
			}
		}
	}
}