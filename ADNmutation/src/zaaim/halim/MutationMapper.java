package zaaim.halim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MutationMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private IntWritable position = new IntWritable();
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().trim();
		
		List<Integer> indices = null;
		
		if (lookforSequenceOfChar(line, 'A', 3).size() != 0) {
            indices = lookforSequenceOfChar(line, 'A', 3);
            for(Integer i : indices)
            {
            	word.set("AAA");
    			position.set(i);
    			context.write(word, position);
            }
		}
		if (lookforSequenceOfChar(line, 'T', 3).size() != 0) {
            indices = lookforSequenceOfChar(line, 'T', 3);
            for(Integer i : indices)
            {
            	word.set("TTT");
    			position.set(i);
    			context.write(word, position);
            }
		}
		if (lookforSequenceOfChar(line, 'C', 4).size() != 0) {
            indices = lookforSequenceOfChar(line, 'C', 4);
            for(Integer i : indices)
            {
            	word.set("CCCC");
    			position.set(i);
    			context.write(word, position);
            }
		}
		if (lookforSequenceOfChar(line, 'G', 4).size() != 0) {
            indices = lookforSequenceOfChar(line, 'G', 4);
            for(Integer i : indices)
            {
            	word.set("GGGG");
    			position.set(i);
    			context.write(word, position);
            }
		}
	}
	
	private List<Integer> lookforSequenceOfChar(String line , char c , int size)
	{
		List<Integer> indices = new ArrayList<Integer>();
		char [] seq = new char[size];
		Arrays.fill(seq, c);
		String sequence = new String(seq);
		int index = 0;
		String lineTemp = new String(line); // to insure the immutability
		while(lineTemp.contains(sequence))
		{
			index = lineTemp.indexOf(sequence);
			char [] lineChars = lineTemp.toString().toCharArray();
			for(int i = index ; i<index+size;i++)
			{
				lineChars[i] = '_';
				
			}
			lineTemp = new String(lineChars);
			indices.add(index+1);
		}
		return indices;
	}

}
