package churn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ChurnProb
{
	public static void main(String[] args) throws IOException
	{
		HashMap<String,Double> hm1 = new HashMap<String,Double>();
		HashMap<String,Double> hm2 = new HashMap<String,Double>();
		BufferedReader br=new BufferedReader(new FileReader("part-r-00000"));
		double yes=0.0,no=0.0;
		while(true)
		{
			String ln = br.readLine();
			if(ln==null)	break;
			String rc[]=ln.split(",");
			String det[]=rc[1].split("\t");
			if(rc[0].equals(det[0]))
			{
				if(rc[0].equals("yes"))
					yes=Double.parseDouble(det[1]);
				else if(rc[0].equals("no"))
					no=Double.parseDouble(det[1]);
				continue;
			}
			else if(rc[0].equals("yes"))
				hm1.put(det[0], new Double(det[1]));
			else if(rc[0].equals("no"))
				hm2.put(det[0], new Double(det[1]));
		}
		for(Map.Entry<String, Double> etr:hm1.entrySet())
			etr.setValue((etr.getValue())/yes);
		for(Map.Entry<String, Double> etr:hm2.entrySet())
			etr.setValue((etr.getValue())/no);
		
		hm1.put("yes", (yes/(yes+no)));
		hm2.put("no", (no/(yes+no)));
		
		PrintWriter pw=new PrintWriter(new FileWriter("Probability"));
		
		for(Map.Entry<String, Double> etr:hm1.entrySet())
		{
			if(!(etr.getKey()).equals("yes"))
				pw.println(etr.getKey()+",Yes,"+etr.getValue()+",No,"+hm2.get(etr.getKey()));
		}
		pw.println("total,Yes,"+hm1.get("yes")+",No,"+hm2.get("no"));		
		pw.close();
		br.close();
	}
}
