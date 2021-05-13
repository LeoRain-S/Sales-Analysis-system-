import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App extends Application {
    
   // Main
   public static void main(String args[]) throws IOException {
      // The results.csv 
      readFileAndExportResult();
      // The bar chart
      launch(args);
   }
   // Author - Eric Chen
   public static void readFileAndExportResult(){
      int[] arr = new int[10];
      int total = 0;
      double[] freq = new double[10];

      try {
          String line;
          BufferedReader reader = new BufferedReader(new FileReader(new File("sales.csv")));
          reader.readLine();
          while ((line = reader.readLine()) != null) {
              // System.out.println(line);
              String num = line.split(",")[1];
              int digit = num.charAt(0) - '0';
              arr[digit]++;
              total++;
          }

          for (int i = 1; i < 10; i++) {
              freq[i] = 100.0 * arr[i] / total;
              // System.out.println(i + ": " + freq[i]);
          }

          if (freq[1] >= 29 && freq[1] <= 32) {
              System.out.println("The data indicates that fraud likely did not occur.");
          }

          BufferedWriter writer = new BufferedWriter(new FileWriter(new File("results.csv")));
          writer.write("digit,frequency");
          writer.newLine();
          for (int i = 1; i < 10; i++) {
              line = i + "," + freq[i];
              writer.write(line);
              writer.newLine();
          }

          reader.close();
          writer.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }     
  } 
}
