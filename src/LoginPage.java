
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class LoginPage {
	 
	static String Rollno;
    static String Password;
	boolean state = false;

	Logout l1 = new Logout();

	static String logintime;
	static String logouttime;
	private boolean state2;
	private boolean state90;
	static  boolean studentOptions_boolean;
	static boolean state87;
	 static  boolean TeacherOptions_boolean;
 static boolean studentregistration_boolean_for_options = true;
	static boolean teacherregistration_boolean_for_options = true;
 static boolean student_registration_boolean;
	static String File_path;
	CheckinActivity c1 = new CheckinActivity();

	 static boolean Rollnum_txtfile_notcontains;
	public void login() throws IOException {
		Scanner s2 = new Scanner(System.in);
// Data structure no1 Array List
		List ROllnum_password_list = new ArrayList();  // adding Rollno add passwords
		List Rollnum_txtfile = new ArrayList();   // adding rollnum text files recoveryfile
		FileReader W23 = new FileReader("Recoveryfile");
		//Reading RecoveryFiles
		BufferedReader w15 = new BufferedReader(W23);
		String line; // Reading line by line and storing in String line
		 FileInputStream file = new FileInputStream(File_path); // Reading  File where h= path of excel file
		 XSSFWorkbook workbook = new XSSFWorkbook(file);  // opens workbook
		 XSSFSheet sheet = workbook.getSheetAt(0); // opens sheet  At sheet 0 means sheet1
		 int rows = sheet.getLastRowNum();     // Counts rows in excel file
			for(int r =1;r<=rows;r++) {
				 XSSFRow row = sheet.getRow(r);
				 XSSFCell cell1 =  row.getCell(0);  // Reads cell at 0 coloumn 0
				 XSSFCell cell2 =  row.getCell(1);// Reads cell at 0 coloumn 1
				 switch(cell1.getCellType()){  // getcelltype means takes cell and tells which type of cell is like string,integer or boolean
				 case STRING:ROllnum_password_list.add(cell1.getStringCellValue());break;
				 case NUMERIC:int d = (int) cell1.getNumericCellValue();ROllnum_password_list.add(d);break;  // adding in the list
				 case BOOLEAN:ROllnum_password_list.add(cell1.getBooleanCellValue());break;
				 }
				 switch(cell2.getCellType()){
				 case STRING:ROllnum_password_list.add(cell2.getStringCellValue());break;
				 case NUMERIC:int d = (int) cell2.getNumericCellValue();ROllnum_password_list.add(d);break;
				 case BOOLEAN:ROllnum_password_list.add(cell2.getBooleanCellValue());break;
				 }
				 	}
			System.out.println(ConsoleColors.CYAN_BOLD+"                           ENTER THE ROLLNO   >>>"+"\u001B[0m");
	         Rollno = s2.nextLine();
			while((line=w15.readLine())!=null) {
				//array where size is fixed to only 2
				String[] array = line.split(",", 2); // splitting the line by "," this symbol and storing in array only 2 elements are splitted one will be in array index 0 and index 1
				Rollnum_txtfile.add(array[0]); //storing value in array index 0 in list
			}
			 if((Rollnum_txtfile.contains(Rollno)) || Rollnum_txtfile_notcontains) {
				 if(ROllnum_password_list.contains(Rollno)){
			            System.out.println(ConsoleColors.CYAN_BOLD+"                         ENTER THE PASSWORD  >>>           "+"\u001B[0m");
			             Password = s2.nextLine();
			            if(ROllnum_password_list.contains(Password)){
							System.out.println(ConsoleColors.RED_BOLD_BRIGHT+ConsoleColors.BANANA_YELLOW_BACKGROUND+"                                                          ^^^   LOGIN SUCCESSFUL ^^^"+"\u001B[0m");

							state= true;
						logintime = c1.loginActivity(); // login time date year is stored

			            }
			            else {
							System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT+ConsoleColors.BANANA_YELLOW_BACKGROUND+"               ||  WRONG PASSWORD  -  TRY AGAIN ||"+"\u001B[0m");
							state = false;
							RegistrationPage registrationpage_object = new RegistrationPage();
							if (registrationpage_object.ForgotPassword_option){
								while (true) {
									System.out.println("1.FORGOTPASSWORD ");
									System.out.println("2.EXIT");
									System.out.println("3.TRYAGAINLOGIN");
									String choice = s2.nextLine();
									if (choice.equals("1")) {
										ForgotPassword forgotpassword_object = new ForgotPassword();
										forgotpassword_object.forgotpassword();
										break;
									}
									if (choice.equals("2")) {
										l1.logout();
										studentOptions_boolean = false;
										TeacherOptions_boolean = false;
										break;
									}

									if (choice.equals("3")) {
										if (!studentOptions_boolean) {
											TeacherLogin();
										} else {
											StudentLogin();
										}

									}
									if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
										System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+ConsoleColors.BANANA_YELLOW_BACKGROUND+"                                                         ##  INPUT CORRECT OPTION  ##"+"\u001B[0m");
									}
								}
						}
			            }
			            
			        }
			        else{
			            System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT+ConsoleColors.RED_BOLD+"                                                                                    %%%    YOUR ENTRY IS WRONG  -  TRY AGAIN   %%%          "+"\u001B[0m");
state =  false;
studentOptions_boolean = false;
TeacherOptions_boolean = false;
			        }
			        file.close();
				 if((studentOptions_boolean && studentregistration_boolean_for_options)) {
					 StudentOptions();
				 }
				 if((TeacherOptions_boolean && teacherregistration_boolean_for_options )) {
					 TeacherOptions();}


			         }
				
			        else{
				 System.out.println(ConsoleColors.PURPLE_BOLD+"                                                                        ***  PLEASE REGISTER  ***"+"\u001B[0m");
				 System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD+"                                                                          ---  REGISTRATION ---"+"\u001B[0m");



						Rollnum_txtfile_notcontains = true;
						RegistrationPage we = new RegistrationPage();
						if(student_registration_boolean){
							we.StudentRegistration();
						}
						else{
							we.TeacherRegistration();
						}

					}


			}
       void StudentLogin() throws IOException {
    	  File_path = "Book1.xlsx";
		   studentOptions_boolean = true;
		   student_registration_boolean = true;
		   TeacherOptions_boolean =false;
    	  login();
	}
       void TeacherLogin() throws IOException {
    	   state90 = true;
    	   TeacherOptions_boolean =true;
		   studentOptions_boolean =false;
     	  File_path = "TeacherDetails.xlsx";
     	  login();
 	}
      public void StudentOptions() throws IOException {
    	  Scanner scan1 = new Scanner(System.in);
    	  while(true) {
    	  System.out.println("------------------------------------------------ ");
    		System.out.println("1.CHECK MARKS");
    		System.out.println("2.CHECK ATTENDENCE");
    		System.out.println("3.CHECK FEES");
    		System.out.println("4.PROFILE");
			System.out.println("5.ANNOUNCEMENTS");
			  System.out.println("6.PAYMENT");
    		System.out.println("7.LOGOUT");
    		System.out.println("------------------------------------------------ ");
    		System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.RED_BOLD_BRIGHT+"                                                               |||  ENTER THE OPTION |||"+"\u001B[0m");
    		String option = scan1.nextLine();
    		if(option.equals("1")) {
    			CheckMarks c1 = new CheckMarks();
    			c1.checkMarks();
    		}
    		else if(option.equals("2")) {
    			CheckAttendence c2 = new CheckAttendence();
    			c2.checkattendence();
    		}
    		else if(option.equals("3")) {
	CheckFees c3 = new CheckFees();
	c3.checkFees();
}
    		else if(option.equals("4")) {
    			profile p = new profile();
				p.state89 = true;
    			p.profile();
    		}
			else if(option.equals("5")) {
				announcements a1 = new announcements();
				a1.Announcements();
			}
			else if(option.equals("6")){
				Payment.main(null);

			}

    		else if(option.equals("7")) {
				logouttime = c1.loginActivity();
				c1.loginActivity();
				l1.logout();
    			    break;
    		}
    		else {
    			System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.CYAN_BOLD_BRIGHT+"                                                                                             ??? PLEASE ENTER THE CORRECT OPTION ???"+"\u001B[0m" );
    		}}

       }
      public void TeacherOptions() throws IOException {
    	  Scanner s26 = new Scanner(System.in);
		  Scanner b = new Scanner(System.in);
    	  while(true) {
    	  System.out.println("------------------------------------------------ ");
    		System.out.println("1.GIVE ATTENDENCE");
    		System.out.println("2.GIVE MARKS");
    		System.out.println("3.ANNOUNCEMENTS");
    		System.out.println("4.PROFILE");
			  System.out.println("5.UPDATE STUDENT");
			  System.out.println("6.SEARCH_STUDENT_DETAILS_BY_ROLLNO");
			  System.out.println("7.CHECK LOGIN ACTIVITY");
			  System.out.println("8.CheckMarks");
    		System.out.println("9.LOGOUT");

    		System.out.println("------------------------------------------------ ");

			System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.RED_BOLD_BRIGHT+"                                                                |||  ENTER THE OPTION |||"+"\u001B[0m");

			String w2 = b.nextLine();
    		if(w2.equals("1")) {
    			GiveAttendence g1 = new GiveAttendence();
				System.out.println("------------------------------");
				System.out.println("1.AtttendenceByRollno");
				System.out.println("2.ClassAttendence");
				System.out.println("------------------------------");
				System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.CYAN_BOLD_BRIGHT+"                                                                                    %%% ENTER THE CORRECT OPTION %%%"+"\u001B[0m");
				String option = s26.nextLine();
				if(option.equals("1")){
					g1.giveAttendenceByRollNo();
				}
				else if(option.equals("2")){
g1.ClassAttendence();
				}
				else{
					System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.PURPLE_BOLD+"                                                                                                            *** PLEASE ENTER THE CORRECT ROLLNUM ***"+"\u001B[0m");
				}
    		}
    		else if(w2.equals("2")) {
    			GiveMarks gm = new GiveMarks();
    			gm.givemarks();
    			
    		}
    		else if(w2.equals("3")) {
	announcements a2 = new announcements();
	a2.GiveAnnouncements();
}
    		else if(w2.equals("4")) {
    			TeacherProfile object = new TeacherProfile();
				object.teacherprofile();
    		}
			else if(w2.equals("5")) {
UpdateStudent u1 = new UpdateStudent();
u1.updatestudent();
			}
			else if(w2.equals("6")) {
SearchStudentDetails s1 = new SearchStudentDetails();
s1.searchstudentdetails();
			}
			else if(w2.equals("7")) {

				c1.LoginActivity();
				state87 = true;

			}
			else if(w2.equals("8")) {

				Marks m1 = new Marks();
				m1.checkmarks();

			}
    		else if(w2.equals("9")) {
    			l1.logout();
    			    break;
    		}
    		else {
    			System.out.println(ConsoleColors.BANANA_YELLOW_BACKGROUND+ConsoleColors.RED_BOLD_BRIGHT+"                                                                                                              ??? PLEASE ENTER THE CORRECT OPTION ???" +"\u001B[0m");
    		}}


       }

}

