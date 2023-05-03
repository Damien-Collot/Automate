import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Etat {
    private String name;
    private TypeEtat typeEtat;
    private Map<String, Transition> mapSymboleTransition;

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

    private void createMapSymbol(ArrayList<String> alphabet) {
        this.mapSymboleTransition = new HashMap<String, Transition>();
        for (String symbol : alphabet) {
            this.mapSymboleTransition.put(symbol, null);
        }
    }

    public void addLiaison(String symbole, String pop, String push, Etat destination) {
        Transition transition = new Transition(symbole, pop, push, destination);
        this.mapSymboleTransition.replace(symbole, transition);
    }


    public Transition getTransition(String symbole) {
        return mapSymboleTransition.get(symbole);
    }
}
