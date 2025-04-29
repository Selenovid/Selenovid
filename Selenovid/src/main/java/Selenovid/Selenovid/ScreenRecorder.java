package Selenovid.Selenovid;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ScreenRecorder {
	private Path tempDir;


		public ScreenRecorder() {
		    // Removed temporary directory creation logic
		    System.out.println("Saving screenshots to the source code folder.");
		}

		private File captureScreen() {
		    File screenshotFile = null;
		    try {
		        Robot robot = new Robot();
		        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		        String filename = "screenshot_" + UUID.randomUUID() + ".png";
		        String currentDir = System.getProperty("user.dir");
		        screenshotFile = new File(currentDir, filename);
		        ImageIO.write(screenFullImage, "png", screenshotFile);
		        System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());

		    } catch (AWTException | IOException ex) {
		        System.err.println("Error capturing or saving screenshot: " + ex.getMessage());
		        if (screenshotFile != null && screenshotFile.exists()) {
		            screenshotFile.delete(); // Clean up if saving failed
		        }
		        screenshotFile = null; // Indicate failure
		    }
		    return screenshotFile;
		}

		// Placeholder for startRecording and stopRecording methods
		public void startRecording(String outputFilePath) {
		    System.out.println("startRecording() method called (not yet implemented)");
		}

		public void stopRecording1() {
		    System.out.println("stopRecording() method called (not yet implemented)");
		}

		// Example main method for testing
		public static void main(String[] args) throws InterruptedException {
		    ScreenRecorder recorder = new ScreenRecorder();
		    System.out.println("Capturing a screenshot...");
		    File capturedFile = recorder.captureScreen();
		    if (capturedFile != null) {
		        System.out.println("Screenshot captured successfully.");
		    } else {
		        System.out.println("Screenshot capture failed.");
		    }
		}
}
