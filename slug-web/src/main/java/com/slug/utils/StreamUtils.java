package com.slug.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public class StreamUtils {


    /**
     * copy inputStream to outputStream
     *
     * @param is
     * @param os
     */
    public static void copyStream(InputStream is, OutputStream os) {

        try {

            int length;
            byte[] buffer = new byte[4 * 1024];
            while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
//                throw new RuntimeException(e);
                //todo add log error when release resource
            }
        }

    }


    /**
     * read String from stream
     *
     * @param is
     * @return
     */
    public static String getString(InputStream is) {

        StringBuilder sb = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
