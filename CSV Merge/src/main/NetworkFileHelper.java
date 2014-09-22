package main;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;



public class NetworkFileHelper {
	
	private static NtlmPasswordAuthentication authentication;
	
	Logging thisLog;
	
	NetworkFileHelper(Logging log){
		thisLog = log;
	}
	
	public void Connection(String url, String username, String password){
		
		authentication = new NtlmPasswordAuthentication(null, username, password);
		try {
			SmbFile dir = new SmbFile(url, authentication);
			try {
				for (SmbFile f : dir.listFiles())
				{
				    System.out.println(f.getName());
				}
			} catch (SmbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			thisLog.writeToLog("Network Connection Error.");
		}
	}
	
	public boolean checkDirectory(String path) throws Exception{
		
		System.out.println("In checkDirectory()...");
		if(!isExist(path)){
			System.out.println(path + " does not exist");
			return false;
		}
		if(!isDir(path)){
			System.out.println(path + " is not a directory");
			return false;
		}
		return true;
	}
	
	public boolean isExist(String path) throws Exception{
		
		SmbFile sFile = new SmbFile(path, authentication);
		return sFile.exists();
	}


	public boolean isDir(String path) throws Exception{
		
		SmbFile sFile = new SmbFile(path, authentication);
		return sFile.isDirectory();
	}


	public void createDir(String path) throws Exception{
	
		SmbFile sFile = new SmbFile(path, authentication);
		sFile.mkdir();
	}
	
	public SmbFile createFile(String path) throws Exception{
		
		SmbFile sFile = new SmbFile(path, authentication);
		sFile.createNewFile();
		return sFile;
	}

	public static NtlmPasswordAuthentication getAuthentication() {
		return authentication;
	}

	public static void setAuthentication(NtlmPasswordAuthentication authentication) {
		NetworkFileHelper.authentication = authentication;
	}
	
	public void copyFolderTo(String sourceUrl, String destinationUrl) throws MalformedURLException, SmbException{
		 SmbFile sourceFile = new SmbFile(sourceUrl);
		 SmbFile destinationFile = new SmbFile(destinationUrl);
		 sourceFile.copyTo(destinationFile);
	}
	
	public SmbFileOutputStream getOutputStream(SmbFile file) throws SmbException, MalformedURLException, UnknownHostException{
		SmbFileOutputStream stream = new SmbFileOutputStream(file);
		return stream;
	}

}
