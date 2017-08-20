package churn;

import java.io.*;

public class AppendPr {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br1 = new BufferedReader(new FileReader("finalProb"));
		BufferedReader br2 = new BufferedReader(new FileReader("trainingSet"));
		PrintWriter pw = new PrintWriter(new FileWriter("fop_tr"));
		int cnt=0;
		while(true)
		{
			String l1 = br1.readLine();
			String l2 = br2.readLine();
			if(l1==null || l2==null) break;
			if(l2 != null)
			{
				StringBuilder sb = new StringBuilder(l2);
				sb.append("		  "+l1);
				pw.write(sb.toString()+"\n");
				String ar[]=sb.toString().split("  ");
				if(ar[7].split("\t")[0].endsWith(ar[0]))
					cnt++;
			}
		}
		pw.write("\n"+cnt+" out of 85 records agree with the formulation.\nAccuracy in % : "+((cnt/85.0)*100));
		pw.close();
		br2.close();
		br1.close();
	}

}
