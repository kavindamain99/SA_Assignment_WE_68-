package doc24paymentproducer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.time.LocalDate;
import java.util.Random;

public class PaymentManagerImpl implements PaymentManager{

	public static final String file = "C:\\Users\\MSI\\Documents\\DataFiles\\payment.txt";
	@Override
	public void newPayment(String payId,String dId, String pId,String desc, double amount) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter( new FileWriter(file,true) );
		
		String payAmount = String.valueOf(amount);
		
		bw.write(payId+","+dId+","+pId+","+desc+","+ payAmount);
		bw.flush();
		bw.newLine();
		bw.close();
	}

	@Override
	public void viewPayment() throws IOException {
		BufferedReader br = new BufferedReader( new FileReader(file) );
		String record;
		
		
    	System.out.println("|	PayID		DoctorID	 PatientID	 Description	 Amount      |");
    	
    	
    	while( ( record = br.readLine() ) != null ) {
			
    		StringTokenizer st = new StringTokenizer(record,",");
    			
    		System.out.println("|	"+st.nextToken()+" 		"+st.nextToken()+" 		 "+st.nextToken()+" 		 "+st.nextToken()+" 		 "+st.nextToken()+"      |");
	
    	}
		
    	
    	
    	br.close();
		
	}

	//@SuppressWarnings({ "resource", "unused" })
	@Override
	public void billPayment(String pId) throws IOException {
		 
		 
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String record;
		 
		while((record = fr.readLine()) != null) {
			
			String[] st = record.split(",");
			String payId = st[0];
			String dId = st[1];
			pId = st[2];
			String desc=st[3];
			double amount = 0;  
			String payAmount = String.valueOf(amount);
			payAmount = st[4];
			
			Random rand = new Random();
			int invoiceID = rand.nextInt(50);
			LocalDate myObj = LocalDate.now();
			
			if(record.contains(pId)) {
				 
				System.out.println(" ----------------------------------------------------------------------------   ");
				System.out.println("|                                 Medical Invoice                            |");
				System.out.println(" ----------------------------------------------------------------------------  \n ");
				System.out.println("Invoice Number: "+invoiceID+ "\t\t\tOrder Date: "+myObj+"\n");
				System.out.println("Patient Code: "+pId+"\n");
				System.out.println("|   Description          Amount     |");
				System.out.println("|   "+desc+"           "+payAmount+"    |\n");
				System.out.println("Payment Method: Cash"+"\t\t\tTotal Amount: "+payAmount+"\n\n");
				
			}else {
				System.out.println("Invalid Patient ID");
			}


		 
		
		fr.close();
		 
	
		
	}
	 	/*
		BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\Dell\\Documents\\DataFiles\\payment.txt"));
		String record;
		
		
		
		while((record = fr.readLine())!=null){
			StringTokenizer s = new StringTokenizer(record,",");
			if(record.contains(pId)) {
				System.out.println(s.nextToken()+" ");
				
		}*/
		
			
	}
	
}
