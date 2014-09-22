package main;

public class TestEncryption {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String encryptedPass = "";
		String decryptedPass = "";
		
		Encryption encObj = new Encryption();
		encryptedPass = encObj.encrypt("A3cbs2pm", 12);
		decryptedPass = encObj.decrypt(encryptedPass, 12);
		
		System.out.println("Encrypted password: " + encryptedPass);
		System.out.println("Decrypted password: " + decryptedPass);

	}

}
