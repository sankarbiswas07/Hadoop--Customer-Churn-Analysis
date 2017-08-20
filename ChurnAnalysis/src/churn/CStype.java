package churn;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class CStype implements WritableComparable<CStype>
{
	private Text attribute=new Text();
	private Text churn=new Text();

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		attribute.readFields(in);
		churn.readFields(in);
		
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		attribute.write(out);
		churn.write(out);
	}

	public int compareTo(CStype o) {
		// TODO Auto-generated method stub
		int c=attribute.compareTo(o.attribute);
		if(c==0)
		{
			c=churn.compareTo(o.churn);
		}
		
		return c;
	}
	
	@Override
	public int hashCode()
	{
		int a=attribute.hashCode();
		int c=churn.hashCode();
		int hc=a*31+c; //a+c , a*c
		return hc;
	}

	@Override
	public String toString() 
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		//sb.append("[");
		sb.append(attribute.toString()+","+churn.toString());
		//sb.append("]");
		String r=sb.toString();
		return r;
	}


	public void set(String attri, String chur) 
	{
		attribute.set(attri);
		churn.set(chur);
		
	}
	
	public void set(Text attri, Text chur) 
	{
		attribute.set(attri);
		churn.set(chur);
		
	}
	
}