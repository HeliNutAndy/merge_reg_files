package main;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class Salesforce {
	
	public static String csvSourceFolder = "C:/Projects/workspace/Combine_CSV_Files/Requests/";	
	public static String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws IOException {
		
		getUsRegData();
	}	
	
	public static void getUsRegData() throws IOException{
		
		Date dateFileStamp = new java.util.Date();
		dateFileStamp.setDate(dateFileStamp.getDate());
		DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");  		
		CSVReader reader = null;
		FileReader sourceFileReader = null;
        
		//number of days to go back 1 = yesterday duh...
		int numDays = 1;
			
		Date dateStamp = new java.util.Date();
		dateStamp.setDate(dateStamp.getDate()- numDays);
		DateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");  
			
		//String sourceFile = csvSourceFolder + "Registration." + myformat.format(dateStamp) + ".csv";
		String sourceFile = "C:/Projects/workspace/Combine_CSV_Files/Requests/Registration.2014-01-24.csv";

		try{
			sourceFileReader = new FileReader(sourceFile);
			//Skip the header...
			reader = new CSVReader(sourceFileReader, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
			}
		catch (IOException e) {
			// File does not exist. Keep on moving....
			e.printStackTrace();
			}
		
		String userData = "";
        String email = "";
        String fName = "";
        String lName = "";
        String company = "";
        String street = "";        
        String city = "";
        String state = "";
        String zip = "";
        String country = "";        
        String phone = "";
			
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			userData = "encoding=UTF-8";
			userData = userData + "&oid=00DC00000016ubQ";
			userData = userData + "&url=www.harvardapparatus.com";
			
            userData = userData + "&email=";
            email = nextLine[0];
            userData = userData + email;
            
            userData = userData + "&first_name=";
            fName = nextLine[3];
            userData = userData + fName;
            
            userData = userData + "&last_name=";
            lName = nextLine[4];
            userData = userData + lName;
            
            userData = userData + "&company=";
            company = nextLine[7];
            userData = userData + company;
            
            userData = userData + "&street=";
            street = nextLine[8];
            userData = userData + street;     
            
            userData = userData + "&city=";
            city = nextLine[11];
            userData = userData + city;
            
            userData = userData + "&state=";
            state = nextLine[12];
            userData = userData + state;
            
            userData = userData + "&zip=";
            zip = nextLine[13];
            userData = userData + zip;
            
            userData = userData + "&country=";
            country = nextLine[14];
            userData = userData + country; 
                        
            userData = userData + "&phone=";
            phone = nextLine[15];
            userData = userData + phone;
            
            try {
				sendPost(userData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    System.out.println(userData);
		   }

	}
	
	// HTTP POST request
	public static void sendPost(String urlParameters) throws Exception {
 
		//String url = "https://172.16.11.149:8111/PHP_Testing/SalesForce.php";
		String url = "https://www.salesforce.com/servlet/servlet.WebToLead";
		URL obj = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		System.out.println(response.toString());
 
	}

}
