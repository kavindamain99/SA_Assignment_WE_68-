# SA_Assignment_WE_68

Doc24 Online Channeling

Doc24 is an online channeling platform specially implemented for covid-19 pandemic situation. There are four roles in the platform namely Doctor manager, Doctor, patient, and payment handler.
The doctor manager can view the list of doctors currently available in the platform, add a new doctor with relevant details, search doctors with their IDs and delete an existing doctor. 
The doctors can view the relevant appointments providing their ID. Then, the doctor can select an appointment and write prescription for that appointment. Also, the doctor can view all the prescription that the doctor has written. 
The patient can select a field (ex: - Cardiologist, Neurologist) and view the doctors that are available for the relevant field. Then, the patient can select a doctor providing the doctor ID. Then, an appointment is created if there are time slots available. And receipt is created displaying the appointment details.
The payment handler can add new payment by providing necessary details of a appointment. Then, the payment handler can view all the created payments. And the payment handler can print a bill of the payment with necessary details. 
We used java file handling to save data into relevant files and to manipulate data in files.

OSGI 
OSGI (Open-Source Gateway Initiative) is a modularization model of JAVA. Equinox is a sub-project of eclipse that provide implementations of the OSGI framework specification. Modules help program to separate functionality of the program so that every module content all the things necessary the execute the function. Therefore, by using OSGI framework for the platform, we are able to create a modular program which can be used for many channeling platforms.

	How to Install and run the project <br>
Eclipse > File > import 
1.	Create an eclipse OSGI Run Configuration.
2.	Add required bundles and eclipse console plugins.
3.	Copy and Paste dataFiles in your selected location
4.	Update Data file locations according to your Memory Location
5.	Run Producer and Consumers Seperately

<h2>Contributors</h2>
  @kavindamain99<br>
  @MulithaBM<br>
  @Madhawa-Tharindu<br>
  @ishalini<br>
