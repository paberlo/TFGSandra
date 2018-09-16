package com.example.sandra.proyecto0509;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class Archivo
{
    private static final String TAG = "Archivo";

    public static final String LOCAL = "Aplicacion";

    public static final String LOCAL_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;

    /**
     * Recording file directory
     */
    public static final String REC_PATH = LOCAL_PATH + LOCAL + File.separator;



    /**
     * Automatically create the relevant directory on the SD card
     */
    static {
        File dirRootFile = new File(LOCAL_PATH);
        if (!dirRootFile.exists()) {
            dirRootFile.mkdirs();
        }
        File recFile = new File(REC_PATH);
        if (!recFile.exists()) {
            recFile.mkdirs();
        }
    }

    private Archivo() {
    }

    /**
     * To determine whether there is storage space *
     *
     * @return
     */
    public static boolean isExitSDCard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    private static boolean hasFile(String fileName) {
        File f = createFile(fileName);
        return null != f && f.exists();
    }

    public static File createFile(String fileName) {

        File myCaptureFile = new File(REC_PATH + fileName);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }
        try {
            myCaptureFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCaptureFile;
    }
}
