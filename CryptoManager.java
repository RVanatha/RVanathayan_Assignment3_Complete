import java.util.*;
/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple ìsubstitution cipherî where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */



public class CryptoManager {
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
		
		boolean check = true;
		
		char [] message = plainText.toCharArray();
		
		for(char element : message) {
			if(check && element >= LOWER_RANGE && element <=UPPER_RANGE) 
				check = true;
			else
				check = false;
		}
		return check;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		
		boolean check;
		
		check = isStringInBounds(plainText);
		if (!check)
			return "The selected string is not in bounds, Try again.";
		
		char[] message = plainText.toCharArray();
		
		for(int index = 0; index < message.length; index ++) {	
			
			message[index] += key; 
			
			while(message[index] > UPPER_RANGE) 
				message[index] -= RANGE;
			
		}
		
		String encrypt = String.valueOf(message);
		
		return encrypt;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		
		boolean check;
		
		check = isStringInBounds(plainText);
		if (!check)
			return "The selected string is not in bounds, Try again";
		
		if(plainText.length() > bellasoStr.length()) {
			int i = 0;
			while(plainText.length() > bellasoStr.length()) {
				bellasoStr += bellasoStr.charAt(i);
				i ++;
			}
		}
		
		char[] message = plainText.toCharArray();
		char[] key = bellasoStr.toCharArray();
		
		for(int index = 0; index < message.length; index++) {
			message[index] += key[index];
			
			while(message[index] > UPPER_RANGE) 
				message[index] -= RANGE;
		}
		
		String encrypt = String.valueOf(message);
		
		return encrypt;
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		
		//char[] message = encryptedText.toCharArray();
		int[] message = new int[encryptedText.length()];
		String decryptedText = "";
		
		for(int index = 0; index < encryptedText.length(); index++){
			
			message[index] = (encryptedText.charAt(index) - key);
			
			while(message[index] < LOWER_RANGE)		
				message[index] += RANGE;
			
			decryptedText += Character.toString(message[index]);
		}
		
		return decryptedText;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr){
	
	
		if(encryptedText.length() > bellasoStr.length()) {
			int i = 0;
			while(encryptedText.length() > bellasoStr.length()) {
				bellasoStr += bellasoStr.charAt(i);
				i ++;
			}
		}
		
		int key = bellasoStr.length();
		String decryptedText = "";
		
		for(int index = 0; index < encryptedText.length(); index++) {
			
			char givenChar = bellasoStr.charAt(index % key);
			int decrypt = (encryptedText.charAt(index) - givenChar);
			
			while(decrypt < LOWER_RANGE)
				decrypt += RANGE;
			
			decryptedText += ((char)decrypt);
		}
		
		return decryptedText;	
		
	}
}