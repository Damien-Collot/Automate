import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Automate {

    private ArrayList<String> alphabet;
    private ArrayList<Etat> listeEtat = new ArrayList<>();

    private LinkedList<String> pile;

    public Automate(String nomFichier) {
        JSONParser parser = new JSONParser();
        try {
            // Parse le fichier et stocke le json dans file
            JSONObject file = (JSONObject) parser.parse(new FileReader(nomFichier));
            this.alphabet = (ArrayList<String>) file.get("Alphabet");

            this.pile = new LinkedList<>();
            this.pile.add("Z");

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
                mapEtat.get(jsonobject.get("ori")).addLiaison(
                        (String) jsonobject.get("symbol"),
                        (String) jsonobject.get("pop"),
                        (String) jsonobject.get("push"),
                        mapEtat.get(jsonobject.get("dest"))
                );
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

    public boolean appartient(String mot) {
        Etat etatActuel = this.getEtatInitial();
        String[] str = mot.split("");

        for (String val : str) {
            Transition transition = etatActuel.getTransition(val);

            if (!alphabet.contains(val)) {
                System.out.println("Symbole : " + val + " pas présent dans l'alphabet");
                return false;
            }

            if (transition == null) {
                System.out.println("Symbole provoquant l'erreur : " + val);
                return false;
            }

            var depile = pile.getLast();
            if (pile.isEmpty() || !depile.equals(transition.getPop())) {
                System.out.println("Erreur lors du dépilement : " + val);
                return false;
            } else {
                pile.removeLast();
                for (int i=transition.getPush().length()-1;i>=0;i--){
                    pile.add(String.valueOf(transition.getPush().charAt(i)));
                }
                etatActuel = transition.getDestination();
            }
        }
        return etatActuel.getTypeEtat() == TypeEtat.Final && pile.size() == 1 && pile.getFirst().equals("Z");
    }
}
