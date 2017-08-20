package churn;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PAtrriDriver 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job j = Job.getInstance(conf,"composite");
		
		j.setJarByClass(PAtrriDriver.class);
		
		j.setMapperClass(PAtrriMapper.class);
		j.setReducerClass(PAtrriReducer.class);
		
		j.setOutputKeyClass(CStype.class);
		j.setOutputValueClass(IntWritable.class);
		
		//j.setOutputKeyClass(CStype.class);
		//j.setOutputValueClass(IntWritable.class);
		
		//file location : /user/hadoop/TrainingFile/trainingSet
		
		FileInputFormat.addInputPath(j, new Path("smp_data/trainingSet"));//folder name and data set name
		FileOutputFormat.setOutputPath(j, new Path("ATrriProbeFile1"));
		
		j.waitForCompletion(true);
	}
}
