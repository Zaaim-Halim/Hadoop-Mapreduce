package zaaim.halim;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCountJava {
	private static Map<String , Integer> wordCount  = new HashMap<String, Integer>();
	public void WordCount(String path,String separator) throws IOException
	{
		FileReader reader = new FileReader(path);	
		@SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(reader);
	    String line ;   
        Map<Map<String ,Integer> , Integer> mapOfLines = new HashMap<>();
        int lineIndex = 1 ;
	    while((line = buffer.readLine()) != null)
	    {
	    	Map<String , Integer> lineMap = new HashMap<>();
            String [] words  = line.split(separator);
	    	for(String word:words)
	    	{
	    	    if(lineMap.containsKey(word))
	    	    	lineMap.replace(word, lineMap.get(word)+1);
	    	    else	
	    		   lineMap.put(word.trim(), 1);
	    	}
	    	mapOfLines.put(lineMap, lineIndex);
	    	lineIndex++;
	    }
	    procces(mapOfLines);
	}
   
	private void procces(Map<Map<String, Integer>, Integer> mapOfLines) {
		for (Map.Entry<Map<String, Integer>, Integer> entry : mapOfLines.entrySet()) {
			for(Map.Entry<String, Integer> entryLine : entry.getKey().entrySet())
			{
				if(wordCount.containsKey(entryLine.getKey())) {
				   Integer oldValue = wordCount.get(entryLine.getKey());
				   wordCount.replace(entryLine.getKey(), entryLine.getValue()+oldValue);
				}
				else	
				   wordCount.put(entryLine.getKey().trim(),entryLine.getValue());
			}	
	    }
	}

	public static void main(String[] args) {
		WordCountJava test = new WordCountJava();
		try {
			test.WordCount("C:\\big-data\\test.txt", " ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
	        System.out.println(entry.getKey() + " : " + entry.getValue());
	    }
	}
}
