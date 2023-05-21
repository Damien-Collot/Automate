import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class Transition {
    private String symbol;
    private ArrayList<String> pop = new ArrayList<>();
    private String push;
    private Etat destination;

    public Transition(String symbol, ArrayList<String> pop, String push, Etat destination) {
        this.symbol = symbol;
        this.pop = pop;
        this.push = push;
        this.destination = destination;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ArrayList<String> getPop() {
        return pop;
    }

    public void setPop(ArrayList<String> pop) {
        this.pop = pop;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public Etat getDestination() {
        return destination;
    }

    public void setDestination(Etat destination) {
        this.destination = destination;
    }

}
