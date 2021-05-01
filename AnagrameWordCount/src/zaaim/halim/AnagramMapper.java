package zaaim.halim;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AnagramMapper extends Mapper<LongWritable, Text, Text, Text>{
    private Text word = new Text();
    private Text sortedText = new Text();
    private Text originalText = new Text();
    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        char[] wordChars = word.toString().toCharArray();
        Arrays.sort(wordChars);
        String sortedWord = new String(wordChars);
        sortedText.set(sortedWord);
        originalText.set(word);
        context.write(sortedText,originalText);
      }
    }	
}
