import controller.Parser;
import controller.Validate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        compiler();
        analyzer();
    }

    public static void compiler() throws IOException {
        FileReader fileReader = new FileReader("src/resources/SourceCode.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Validate validate = new Validate();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            boolean result = validate.validateExpression(line);
            if (!result) {
                System.out.println("Invalid source code!");
                return;
            }
        }
        System.out.println("Valid source code!");
    }

    public static void analyzer() throws IOException {
        Parser parser = new Parser();
        String path = "src/resources/Code.txt";

        parser.lexicalAnalyzer(path);
        parser.printTokens();
    }
}