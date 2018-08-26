import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class wrapper {
    public static void main(String[] args) throws IOException {
        if(args.length == 1 && !args[0].contains("txt")){
            FileInputStream is = new FileInputStream(new File(args[0]));
            System.setIn(is);
            WordFinder.main(new String[0]);
        }else if(args.length == 2) {
            FileInputStream is = new FileInputStream(new File(args[1]));
            System.setIn(is);
            WordFinder.main(args);
        }else {
            WordFinder.main(args);
        }
    }
}