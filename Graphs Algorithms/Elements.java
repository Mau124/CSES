import javax.lang.model.util.ElementScanner6;

public class Elements implements Comparable<Elements> {
    public int weight;
    public int row;
    public int col;

    public Elements(int weight, int row, int col) {
        this.weight = weight;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Elements el) {
        return this.weight - el.weight;
    }
}
