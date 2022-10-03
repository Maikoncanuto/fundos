import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {

    private Item[] items;
    private int capacity;

    public Knapsack(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity * 100;
    }

    public void display() {
        if (items != null && items.length > 0) {
            System.out.println("Knapsack problem");
            System.out.println("Capacity : " + capacity);
            System.out.println("Items :");

            for (Item item : items) {
                System.out.println("- " + item.str());
            }
        }
    }

    public Solution solve() {
        int NB_ITEMS = items.length;
        int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

        for (int i = 0; i <= capacity; i++)
            matrix[0][i] = 0;

        for (int i = 1; i <= NB_ITEMS; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (items[i - 1].weight > j)
                    matrix[i][j] = matrix[i - 1][j];
                else
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - items[i - 1].weight] + items[i - 1].value);
            }
        }

        int res = matrix[NB_ITEMS][capacity];
        int w = capacity;
        List<Item> itemsSolution = new ArrayList<>();

        for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                itemsSolution.add(items[i - 1]);
                res -= items[i - 1].value;
                w -= items[i - 1].weight;
            }
        }

        return new Solution(itemsSolution, matrix[NB_ITEMS][capacity]);
    }

    public static void main(String[] args) {

        Item[] items = {
                // TICKET - DIVIDENDO - VALOR
                new Item("A35", 106.15, 0.61),
                new Item("A36", 106.00, 1.05),
                new Item("A37", 104.46, 1.20),
                new Item("A38", 103.96, 1.20),
                new Item("A39", 103.52, 1.25),
                new Item("A1", 259.57, 1.60),
                new Item("A2", 233.49, 2.11),
                new Item("A153", 58.06, 0.45),
                new Item("A70", 36.20, 0.70),
                new Item("A179", 7.72, 0.08),
                new Item("A178", 7.58, 0.08),
                new Item("A177", 7.54, 0.06),
        };

        Arrays.sort(items);

        Knapsack knapsack = new Knapsack(items, 1200);
        knapsack.display();

        Solution solution = knapsack.solve();
        solution.display();
    }
}
