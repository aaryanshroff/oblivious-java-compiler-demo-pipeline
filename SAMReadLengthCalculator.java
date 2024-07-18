import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SAMReadLengthCalculator {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SAMReadLengthCalculator <sam_file>");
            return;
        }

        String samFile = args[0];
        try {
            double averageReadLength = computeAverageReadLength(samFile);
            System.out.printf("Average Read Length: %.2f%n", averageReadLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double computeAverageReadLength(String samFile) throws IOException {
        System.out.println(samFile);
        BufferedReader reader = new BufferedReader(new FileReader(samFile));
        String line;
        long totalReadLength = 0;
        long readCount = 0;

        while ((line = reader.readLine()) != null) {
            // Skip header lines
            if (line.startsWith("@")) {
                continue;
            }

            String[] fields = line.split("\t");
            if (fields.length > 9) {
                String readSequence = fields[9];
                totalReadLength += readSequence.length();
                readCount++;
            }
        }

        reader.close();

        if (readCount == 0) {
            throw new IOException("No reads found in the SAM file.");
        }

        return (double) totalReadLength / readCount;
    }
}