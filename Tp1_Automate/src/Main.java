import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var Auto = new Automate("src/Resources/Expressions.json");
        try {
            var mot = "150-24*(72+4)/(12+4)";
            System.out.println("------------------------------------------------");
            System.out.println("Expression : " + mot + " | " + (Auto.appartient(mot) ? "Appartient à l'automate" : "N'appartient pas à l'automate"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
