package htmlReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlReadingTest {
    public static void main(String[] args) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/page-templates/order-procedure-template.html"));
            String buffer = "";
            while ((buffer = bufferedReader.readLine()) != null) {
                contentBuilder.append(buffer);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(contentBuilder.toString());
    }
}
