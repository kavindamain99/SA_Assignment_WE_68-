package doc24managerproducer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ManagerServices {
public void addDocter(String id,String name,String Specialist,String dHospital);
public void doctorList() throws IOException;
public void deleteDoctor(String id) throws IOException;



}
