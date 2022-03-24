package doc24managersearchproducer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ManagerSearchServiceImpl implements ManagerSearchServices {
	public static final String fileName = "C:\\Users\\MSI\\Documents\\DataFiles\\doctors.txt";
	//search doctor by id
	@Override
	public void searchDoctor(String id) throws IOException {
		
		String record;
		BufferedReader br = new BufferedReader( new FileReader(fileName) );
		System.out.println("\t\t\t Search Doctor Record\n");
		
		System.out.println(" ----------------------------------------------------------------------------------------");
		System.out.println(" ----------------------------------------------------------------------------------------");
    	
		boolean validate = false;
		//create a display bar
    	while( ( record = br.readLine() ) != null ) {
			
    		StringTokenizer st = new StringTokenizer(record,",");
    		if( record.contains(id) ) {	
    		System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+" |      ");
    		validate = true;
    		}
    		
    	}
    	if(validate==false) {
    		System.out.println("\t\t No Record Found! please enter Valid Name or ID ! ");
    	}
    	System.out.println(" ----------------------------------------------------------------------------------------");
    	br.close();
	}
	
}
