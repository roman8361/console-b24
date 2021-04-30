package ru.kravchenko.sb.utils;

import java.io.*;

public class Utils {

    public static String readFile(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        return readFile(reader);
    }

    private static String readFile(BufferedReader reader) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        int lastLsIndex = stringBuilder.lastIndexOf(ls);
        stringBuilder.delete(lastLsIndex, lastLsIndex + ls.length());

        return stringBuilder.toString();
    }

    public static String readFileFromResource(String resource) throws IOException {
        return Utils.readFile(Utils.class.getClassLoader().getResourceAsStream(resource));
    }

    public static String decoder(String s) {
        int i=0, len=s.length();
        char c;
        StringBuffer sb = new StringBuffer(len);
        while (i < len) {
            c = s.charAt(i++);
            if (c == '\\') {
                if (i < len) {
                    c = s.charAt(i++);
                    if (c == 'u') {
                        // TODO: check that 4 more chars exist and are all hex digits
                        c = (char) Integer.parseInt(s.substring(i, i+4), 16);
                        i += 4;
                    } // add other cases here as desired...
                }
            } // fall through: \ escapes itself, quotes any character but u
            sb.append(c);
        }
        return sb.toString();
    }

}
