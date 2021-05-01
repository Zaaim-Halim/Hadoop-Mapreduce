package zaaim.halim;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PallindromMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
    private Text pallindromWord = new Text();
    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        String word = itr.nextToken().trim();
        if(word.equals(inversedWord(word)))
        {
        	pallindromWord.set(word);
        	context.write(pallindromWord,new IntWritable(1));
        }
       
      }
    }
	private String inversedWord(String word) {
		String inversedWord = "";
		for(int i = word.length()-1; i >= 0; i--)
		{
			inversedWord = inversedWord + word.charAt(i);
		}
		return inversedWord.trim();
	}	
}
