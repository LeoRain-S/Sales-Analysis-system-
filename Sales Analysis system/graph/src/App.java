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
    public void start(Stage stage) {  
        //Defining the axes              
        CategoryAxis xAxis = new CategoryAxis();  
        xAxis.setCategories(FXCollections.<String>
        observableArrayList(Arrays.asList("1             2             3             4              5             6             7             8             9 ")));
        xAxis.setLabel("Digit");
      
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");
     
        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
        barChart.setTitle("Benford's Law Distribution Leading Digit");
        
        //Prepare XYChart.Series objects by setting data       
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        // digit 1
        series1.setName("1 = 31.48148%");
        series1.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 31.48148));
        // digit 2
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("2 = 13.82716%");
        series2.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 " , 13.82716));  
        // digit 3
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("3 = 12.71605%");
        series3.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 12.71605));
        // digit 4
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("4 = 11.04938%");
        series4.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 11.04938));
        // digit 5
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("5 = 9.012346%");
        series5.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 9.012346));
        // digit 6
        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("6 = 6.790123%");
        series6.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 6.790123));
        // digit 7
        XYChart.Series<String, Number> series7 = new XYChart.Series<>();
        series7.setName("7 = 5.679012%");
        series7.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 5.679012));
        // digit 8
        XYChart.Series<String, Number> series8 = new XYChart.Series<>();
        series8.setName("8 = 4.259259%");
        series8.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 4.259259));
        // digit 9
        XYChart.Series<String, Number> series9 = new XYChart.Series<>();
        series9.setName("9 = 5.185185%");
        series9.getData().add(new XYChart.Data<>("1             2             3             4              5             6             7             8             9 ", 5.185185));      
              
        //Setting the data to bar chart       
        barChart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8,series9);
        
        //Creating a Group object 
        Group root = new Group(barChart);
        
        //Creating a scene object
        Scene scene = new Scene(root, 550, 400);

        //Setting title to the Stage
        stage.setTitle("Result");
        
        //Adding scene to the stage
        stage.setScene(scene);
        
        //Displaying the contents of the stage
        stage.show();        
   }
   // Main
   public static void main(String args[]) throws IOException {
      readFileAndExportResult();
      launch(args);
   }
   
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
