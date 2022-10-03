import java.util.Comparator;

public class Item implements Comparable<Item> {

    public String name;
    public int value;
    public int weight;

    public Item(String name, Double weight, Double value) {
        this.value = ((int) (value * 100));
        this.name = name;
        this.weight = ((int) (weight * 100));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public String str() {
        return name + " [Rendimento = " + Double.parseDouble(String.valueOf(value))/ 100 + ", Preco = " + Double.parseDouble(String.valueOf(weight))/ 100 + "]";
    }

    @Override
    public int compareTo(Item o) {
        return Comparator
                .comparing(Item::getValue)
                .thenComparing(Item::getWeight)
                .compare(this, o);
    }
}