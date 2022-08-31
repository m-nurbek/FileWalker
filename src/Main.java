import wordmap.FileWalker;
import wordmap.Position;
import wordmap.Occurrences;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            File myoutput = new File("src/myoutput.txt");
            BufferedWriter pw = new BufferedWriter(new FileWriter(myoutput));
            pw.write(FileWalker.getOccurrences("src/test_dir").toString());
            System.out.println(FileWalker.getOccurrences("src/test_dir"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
