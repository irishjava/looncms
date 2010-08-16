package com.callaway;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileUtil {

    /**
     * Create a new zipfile, containing all the input files.
     * @param input
     * @return
     */
    public static File create(List<File> input){
        try {
            // Create a buffer for reading the files
            byte[] buf = new byte[1024];
            // Create the ZIP file
            File f = File.createTempFile("report",".zip");
            ZipOutputStream out = null;
            out = new ZipOutputStream(new FileOutputStream(f));
            // Compress the files
            for (File i : input){
                FileInputStream in= new FileInputStream(i);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(i.getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) { out.write(buf, 0, len); }
                // Complete the entry
                out.closeEntry();
                in.close();
                // Complete the ZIP file
                out.close();
            }

            return f;
        } catch (Throwable t) {
            throw new UnsupportedOperationException(t);
        }
    }

}
