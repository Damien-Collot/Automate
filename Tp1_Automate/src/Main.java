import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var Auto = new Automate("src/Resources/Exemple.json");
        try {
            File file = new File("src/Resources/expressionMaths.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                System.out.println("------------------------------------------------");
                System.out.println("Expression : " + line + " | " + (Auto.appartient(line) ? "Appartient à l'automate" : "N'appartient pas à l'automate"));
            }
            System.out.println("------------------------------------------------");
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
