import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Automate {

    private ArrayList<String> alphabet;
    private ArrayList<Etat> listeEtat = new ArrayList<>();

    public Automate(String nomFichier) {
        JSONParser parser = new JSONParser();
        try {
            // Parse le fichier et stocke le json dans file
            JSONObject file = (JSONObject) parser.parse(new FileReader(nomFichier));
            this.alphabet = (ArrayList<String>) file.get("Alphabet");

            // Création de la liste d'Etat et initialisation de la map pour changer le type des Etats
            ArrayList<String> listEtat =  (ArrayList<String>) file.get("Etats");
            HashMap<String, Etat> mapEtat = new HashMap<>();
            for (String nom : listEtat){
                var Etat = new Etat(nom, alphabet);
                listeEtat.add(Etat);
                mapEtat.put(nom, Etat);
            }

            String etatInitial = (String) file.get("EtatInitial");
            mapEtat.get(etatInitial).setTypeEtat(TypeEtat.Initial);

            ArrayList<String> etatFinaux = (ArrayList<String>) file.get("EtatsFinaux");

            for (String nom : etatFinaux){
                mapEtat.get(nom).setTypeEtat(TypeEtat.Final);
            }

            JSONArray transition = (JSONArray) file.get("Transitions");

            for (int i = 0; i < transition.size(); i++) {
                JSONObject jsonobject = (JSONObject) transition.get(i);
                mapEtat.get(jsonobject.get("ori")).addLiaison((String) jsonobject.get("symbol"), mapEtat.get(jsonobject.get("dest")));
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    public ArrayList<Etat> getListeEtat() {
        return listeEtat;
    }

    public void setListeEtat(ArrayList<Etat> listeEtat) {
        this.listeEtat = listeEtat;
    }
    
    public Etat getEtatInitial(){
        for (Etat value : this.listeEtat) {
            if (value.getTypeEtat() == TypeEtat.Initial){
                return value;
            }
        }
        return null;
    }
    
    public boolean appartient(String mot){
        Etat etatActuel = this.getEtatInitial();
        String[] str = mot.split("");
        for (String val: str) {
            Etat destination = etatActuel.getDestination(val);
            if (destination == null){
                return false;
            }
            etatActuel = destination;
        }
        return etatActuel.getTypeEtat() == TypeEtat.Final;
    }
}
