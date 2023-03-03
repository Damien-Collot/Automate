import java.lang.reflect.Array;
import java.util.ArrayList;

public class Automate {

    private String nomFichier;
    private ArrayList<String> alphabet;
    private ArrayList<Etat> listeEtat;

    public Automate(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
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
    }
    
    public boolean appartient(String mot){
        Etat etatActuel = this.getEtatInitial();
        
        return false;
    }
}
