     package Selenovid.Selenovid; 

     import java.io.File;
     import java.io.IOException;
     import java.util.UUID;
     import java.util.logging.Level;
     import java.util.logging.Logger;
     import org.openqa.selenium.OutputType;
     import org.openqa.selenium.TakesScreenshot;
     import org.openqa.selenium.WebDriver;
     import org.openqa.selenium.WebDriverException;
     import org.apache.commons.io.FileUtils;
     
     public class ScreenRecorder {
         private static final Logger LOGGER = Logger.getLogger(ScreenRecorder.class.getName());
         private String outputDir = "screenshots"; 
         public ScreenRecorder() {
             new File(outputDir).mkdirs(); 
         }
         public File captureScreen(WebDriver driver, String testName) {
             if (driver == null) {
                 throw new IllegalArgumentException("WebDriver instance cannot be null.");
             }
             if (!(driver instanceof TakesScreenshot)) {
                 throw new IllegalArgumentException("WebDriver instance does not support taking screenshots.");
             }

             File screenshotFile = null;
             try {
                 // Take screenshot using Selenium's TakesScreenshot
                 TakesScreenshot ts = (TakesScreenshot) driver;
                 File sourceFile = ts.getScreenshotAs(OutputType.FILE);
                 String filename = testName + "_" + UUID.randomUUID() + ".png";
                 screenshotFile = new File(outputDir, filename);
                 FileUtils.copyFile(sourceFile, screenshotFile);
                 LOGGER.log(Level.INFO, "Screenshot saved to: {0}", screenshotFile.getAbsolutePath());
             } catch (WebDriverException | IOException ex) {
                 LOGGER.log(Level.SEVERE, "Error capturing or saving screenshot: {0}", ex.getMessage());
                 ex.printStackTrace();
                 if (screenshotFile != null && screenshotFile.exists()) {
                     screenshotFile.delete();
                 }
                 screenshotFile = null;
             }
             return screenshotFile;
         }
         public void setOutputDir(String dir) {
             if (dir == null || dir.isEmpty()) {
                 throw new IllegalArgumentException("Output directory cannot be null or empty.");
             }
             File dirFile = new File(dir);
             if (!dirFile.exists() && !dirFile.mkdirs()) {
                 throw new IllegalArgumentException("Failed to create output directory: " + dir);
             }
             if (!dirFile.isDirectory()) {
                 throw new IllegalArgumentException("Output path is not a directory: " + dir);
             }
             this.outputDir = dir;
             LOGGER.log(Level.INFO, "Output directory set to: {0}", dir);
         }
         public static void main(String[] args) {
             System.out.println("This is a library class.  It's meant to be used within a testing framework (TestNG or JUnit).");
             System.out.println("See the TestNGScreenRecorder.java or JUnitScreenRecorder.java classes for how to integrate it into your tests.");
         }
     
     }

     
    