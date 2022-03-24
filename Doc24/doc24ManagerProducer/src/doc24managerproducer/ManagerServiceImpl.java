package doc24managerproducer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ManagerServiceImpl implements ManagerServices {

	//file handeling
	//public static String fileName = "C:\\Users\\MSI\\Documents\\DataFiles\\doctors.txt";
	public static final String fileName = "C:\\Users\\MSI\\Documents\\DataFiles\\doctors.txt";
	public static final String tempFile ="C:\\Users\\MSI\\Documents\\DataFiles\\doctor_temp.txt";

	//constructor fields
	public ManagerServiceImpl() {
		
		// Using file pointer creating the file.
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				} else {
					System.out.println("File already exists.");
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			
		}

	}

	//insert a new doctor
	@Override
	public void addDocter(String id, String name, String specialist,String hospital) {

		try {
			
			
			BufferedWriter bw = new BufferedWriter( new FileWriter(fileName,true) );
    		Scanner strInput = new Scanner(System.in);
    		bw.write(id+","+name+","+specialist+","+hospital);
    		bw.flush();
    		bw.newLine();
    		bw.close();	
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//View Existing doctors
	@Override
	public void doctorList() throws IOException {
		// Opening file in reading and write mode.

		String name = "";
		String specialist = "";

		try {
			BufferedReader br = new BufferedReader( new FileReader(fileName) );
			String record;
			
			System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    	System.out.println("|	ID		Name 				Specialty		Hospital |");
	    	System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    	
	    	while( ( record = br.readLine() ) != null ) {
    			
	    		StringTokenizer st = new StringTokenizer(record,",");
	    			
	    		System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+"      ");
		
	    	}
			
	    	
	    	System.out.println(" ------------------------------------------------------------------------------------------------------- ");
	    	br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// System.out.println("Friend Name: " + name + "\n" + "Contact Number: " +
		// specialist + "\n");

	}

	//Delete specific doctor by id
	@Override
	public void deleteDoctor(String id) throws IOException {
		String record;
		File tempDB = new File(tempFile);
		File db = new File(fileName);
		
		BufferedReader br = new BufferedReader( new FileReader( db ) );
		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
		
		while( ( record = br.readLine() ) != null ) {
			
			
			if( record.contains(id) ) 
				continue;

			bw.write(record);
			bw.flush();
			bw.newLine();
			
			
		}
		System.out.println("Record Deleted Successfully!");
		
		br.close();
		bw.close();
		
		db.delete();
		
		tempDB.renameTo(db);
		
	}

	

}
