

package churn;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PAtrriMapper extends Mapper<LongWritable,Text,CStype,IntWritable>
{
	private CStype keyout=new CStype();
	private IntWritable valueout=new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		String rec = value.toString();//hadoop doesnt undrstand text so string	
		String  f[] = rec.split("  ");
		for(int i=0;i<f.length;i++)
		{
			//if(f[i].trim().length()!=0)
				keyout.set(f[0],f[i]);
				context.write(keyout,valueout);
		}
		
	}
}
