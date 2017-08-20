package churn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class TestMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>
{
	public HashMap<String,double[]> hm= new HashMap<String,double[]>();
	double t_y=1,t_n=1, l_y=0, l_n=0, ev=0, f_y=0, f_n=0;
	
	public void readFile(Path p)
	{
		try{
			BufferedReader br = new BufferedReader(new FileReader(p.toString()));
			String rec = null;
			while(true)
			{
				rec=br.readLine();
				if(rec==null)
					break;
				if(rec.trim().length()==0)
					continue;
				String ent[] = rec.split(",");
				double ar[] = new double[2];
				ar[0]=Double.parseDouble(ent[2]);
				ar[1]=Double.parseDouble(ent[4]);
				if(!ent[0].equals("total"))
					hm.put(ent[0].trim(),ar);
				else
				{
					t_y=Double.parseDouble(ent[2]);
					t_n=Double.parseDouble(ent[4]);
				}
			}
		}
		catch(Exception e){}
	}
	
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Path [] paths=context.getLocalCacheFiles();
		if(paths!=null && paths.length!=0)
		{
			for(Path p:paths)
			{
				readFile(p);
			}
		}
	}
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		
		String line = value.toString();
		String atrs[] = line.split("  ");
		double pr_y=1f,pr_n=1f;
		
		for(int i = 1; i <= 6; i++)
		{
			if(hm.containsKey(atrs[i]))
			{
				pr_y *= hm.get(atrs[i])[0]; //0 - yes
				pr_n *= hm.get(atrs[i])[1]; //1 - no
			}
		}
		
		l_y = pr_y/t_y;
		l_n = pr_n/t_n;
		
		ev = l_y+l_n;  //estimated values p[t]
		
		f_y = l_y/ev; //prob of final yes
		f_n = l_n/ev; //prob of final no
		
		if(f_y>f_n)
			context.write(new Text("yes"), new DoubleWritable(f_y));
		else
			context.write(new Text("no"), new DoubleWritable(f_n));
		
	}
}
