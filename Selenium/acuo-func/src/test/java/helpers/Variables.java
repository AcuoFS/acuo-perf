package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Variables {
    public static String url = null;
    public static String expected_main_URL = null;
    public static String expected_dashboard_URL = null;
    public static String expected_Upload_URL = null;
    public static String expected_Recon_URL = null;
    public static String expected_pledge_URL = null;
    public static String path = null;
    public static String properties_path = null;

    //-------------------------URL-----------------------------------//

// For windows
//    public static String path = System.getProperty("user.dir") + "\\attachment\\";
//    public static String properties_path = System.getProperty("user.dir") + "\\src\\test\\resources\\";

    // For UNIX
//    public static String path = System.getProperty("user.dir") + "/attachment/";
//    public static String properties_path = System.getProperty("user.dir") + "/src/test/resources/";

    public static void getUrl() throws IOException {
        String systemOS = System.getProperty("os.name");
        System.out.println(systemOS);
        String userDir = System.getProperty("user.dir");
        if (systemOS.startsWith("Windows")==true){
            System.out.println("This is Windows OS");
            path = userDir + "\\attachment\\";
            properties_path = userDir + "\\src\\test\\resources\\";
        }else{
            System.out.println("This is Linux OS");
            path = userDir + "/attachment/";
            properties_path = userDir + "/src/test/resources/";
        }

        Properties prop = new Properties();
        InputStream input = new FileInputStream(properties_path + "my.properties");
        prop.load(input);
        url = prop.getProperty("url");
        System.out.println("BaseURL = " + url);
        expected_main_URL = url + "/#/";
        expected_dashboard_URL = url + "/#/dashboard";
        expected_Upload_URL = url + "/#/upload_portfolio";
        expected_Recon_URL = url + "/#/recon";
        expected_pledge_URL = url + "/#/pledge";


    }


    //------------------------Others-------------------------------//
    public static String twoFACode = "123456";
}

