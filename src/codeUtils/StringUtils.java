/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author root
 */
public class StringUtils {
    public  StringBuffer inputStreamToString(InputStream inputStream) throws IOException{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();

        String str;
        while ((str = bufferedReader.readLine()) != null) {

            sb.append(str+"\n");
        }
        return sb;
    }
}
