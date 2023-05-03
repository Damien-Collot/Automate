public class Transition {
    private String symbol;
    private String pop;
    private String push;
    private Etat destination;

    public Transition(String symbol, String pop, String push, Etat destination) {
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

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
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
