package churn;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class TestDriver 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job j = Job.getInstance(conf,"composite");
		
		Path p = new Path("Probability");
		URI uri = p.toUri();
		j.addCacheFile(uri);
		
		j.setJarByClass(TestDriver.class);
		
		j.setMapperClass(TestMapper.class);
		
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(DoubleWritable.class);
		
		j.setNumReduceTasks(0);
		
		//j.setOutputKeyClass(CStype.class);
		//j.setOutputValueClass(IntWritable.class);
		
		//file location : /user/hadoop/TrainingFile/trainingSet
		
		FileInputFormat.addInputPath(j, new Path("trainingSet"));//folder name and data set name
		FileOutputFormat.setOutputPath(j, new Path("ATrriProbeFile"));
		
		j.waitForCompletion(true);
	}
}


