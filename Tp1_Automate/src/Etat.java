import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Etat {
    private String name;

    private TypeEtat typeEtat;

    private Map<String, Etat> mapSymboleDestination;


    public Etat(String nom, ArrayList<String> alphabet) {
        this.name = nom;
        this.typeEtat = TypeEtat.Transition;
        createMapSymbol(alphabet);
    }

    public String getName() {
        return name;
    }

    public TypeEtat getTypeEtat() {
        return typeEtat;
    }

    public void setTypeEtat(TypeEtat typeEtat) {
        this.typeEtat = typeEtat;
    }

    private void createMapSymbol(ArrayList<String> alphabet){
        this.mapSymboleDestination = new HashMap<String, Etat>();
        for (String symbol : alphabet){
            this.mapSymboleDestination.put(symbol,null);
        }
    }

    public void addLiaison(String symbole, Etat destination){
        this.mapSymboleDestination.replace(symbole, destination);
    }

    public Etat getDestination(String symbole) {
        return mapSymboleDestination.get(symbole);
    }
}
