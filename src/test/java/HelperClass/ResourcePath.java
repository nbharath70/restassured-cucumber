package HelperClass;

import java.io.File;

public class ResourcePath {
    public static final String USER_DIR=System.getProperty("user.dir")+ File.separator;
    public static final String Environment_Properties=USER_DIR+"\\src\\test\\resources\\environment.properties";
    public static final String DATABASE_PROPERTIES=USER_DIR+"\\src\\test\\resources\\database.properties";
    public static final String VERIFICATION_PROPERTIES=USER_DIR+"\\src\\test\\resources\\verification.properties";
}
