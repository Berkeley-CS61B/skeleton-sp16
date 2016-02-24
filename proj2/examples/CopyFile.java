import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This program copies all of the text in one file to a new file. The first command line argument
 * should be the name of the file to copy from, and the second command line argument should be the
 * name of the file to copy to.
 */
public class CopyFile {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Expected usage: CopyFile <source filename> <destination filename>");
            System.exit(1);
        }
        String inputFilename = args[0];
        String outputFilename = args[1];

        try {
            File inputFile = new File(inputFilename);
            // Check to make sure that the input file exists!
            if (!inputFile.exists()) {
                System.out.println("Unable to copy because file with name " + inputFilename
                    + " does not exist");
                return;
            }
            FileReader reader = new FileReader(inputFile);
            // It's good practice to read files using a buffered reader.  A buffered reader reads
            // big chunks of the file from the disk, and then buffers them in memory.  Otherwise,
            // if you read one character at a time from the file using FileReader, each character
            // read causes a separate read from disk.  You'll learn more about this if you take more
            // CS classes, but for now, take our word for it!
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Create a FileWriter to write to outputFilename. FileWriter will overwrite any data
            // already in outputFilename.
            FileWriter writer = new FileWriter(outputFilename);

            int intRead = -1;
            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            while ((intRead = bufferedReader.read()) != -1) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = (char) intRead;
                writer.write(charRead);
            }

            System.out.println("Successfully copied file " + inputFilename + " to "
                    + outputFilename);

            // Close the reader and writer.
            bufferedReader.close();
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
    }

}
