package com.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
    private final String [] characters = {"h","J","M","N"};
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		String line = value.toString();
		String [] words = line.split(" ");
		int itr = 0 ;
		while(itr < words.length) {
			for(String character : characters)
			{
				if(words[itr].trim().startsWith(character)) {
					word.set(words[itr].trim().substring(0, 1).toUpperCase());
					context.write(word, one);
				}
			}
		   itr++;
			
		}

	}

}
