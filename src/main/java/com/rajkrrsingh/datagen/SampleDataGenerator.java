package com.rajkrrsingh.datagen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

// with 10000000 rows it will produce 434M data
public class SampleDataGenerator{

  private static SecureRandom secureRandom = new SecureRandom();
  
  public static void main(String[] args) throws IOException {
    try {
      String userDir = System.getProperty("user.dir");
      String fullPathToFile = userDir + "/" + "tbl.data";
      File file = new File(fullPathToFile);
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      Random r = new Random();

      String content;
      for (long i = 0; i <= Integer.parseInt(args[0]); i++) {
        int value1 = r.nextInt(10000);
        int value2 = r.nextInt(10000);
        int value3 = r.nextInt(10000);
        int value4 = r.nextInt(10000);


        content = String.valueOf("code"+Math.abs(value1))+","+String.valueOf("description"+Math.abs(value2))+","
                +String.valueOf("value"+Math.abs(value3))+","+String.valueOf("status"+Math.abs(value4));
        //System.out.println(content);
        bw.write(content);
        bw.write("\n");
      }
      bw.close();
      System.out.println("Done. Saved data to " + file.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static String nextSessionId() {
    return new BigInteger(130, secureRandom).toString(32);
  }
}
