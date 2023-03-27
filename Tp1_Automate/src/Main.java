import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var Auto = new Automate("Tp1_Automate/Distributeur.json");
        try {
            File file = new File("Tp1_Automate/testDistributeur.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                System.out.println("Test du mot : " + line + " | " + Auto.appartient(line));
                System.out.println("----------------");
            }
            fr.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}