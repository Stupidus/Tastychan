/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Victor
 */
public class StringComponent {

    public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;
        hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
}
