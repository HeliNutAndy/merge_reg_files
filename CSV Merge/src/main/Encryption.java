package main;

/*
 * Simple Encryption class so I do not have to display my network password
 */

public class Encryption 
{
    public Encryption() 
    {

    }

    /**
     * Encrypt a string, with a key
     * @param text - The string of text you want to encrypt
     * @param key - the key to encrypt it with.
     * @return - encrypted String
     */
    public String encrypt(String text, int key)
    {
        String endResult = "";
        key = key*7;
        for(char a : text.toCharArray())
        {
            for(int i = 0; i < key; i++)
            {
                if(  !(a >= 123 || a < 31))
                {
                    if(a+1 != 123)
                    {
                        a += 1;
                    }
                    else
                    {
                        a = 32;
                    }
                }
            }
            endResult += a;
        }
        return endResult;
    }

    /**
     * Decrypt a string, with a key
     * @param text - The string of text you want to decrypt
     * @param key - the key to decrypt it with.
     * @return - Decrypted String
     */
    public String decrypt(String cipherText, int key)
    {
        String endResult = "";
            key = key*7;
    for(char a : cipherText.toCharArray())
        {
        for(int i = 0; i < key; i++)
        {
            if(  !(a >= 123 || a < 31))
            {
                if(a-1 != 31)
                {
                    a -= 1;
                }
                else
                {
                    a = 122;
                }
            }
            else
            {
                break;
            }
        }
        endResult += a;
    }
    return endResult;
}

}
