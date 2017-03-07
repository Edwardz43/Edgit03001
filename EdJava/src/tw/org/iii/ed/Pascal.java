package tw.org.iii.ed;

import static java.lang.System.out;
import java.util.*;

public class Pascal {
    private List<List<Integer>> rows = new ArrayList<>();
    //???
    
    Pascal(int height) {
        for(int r = 0; r < height; r++) {
            rows.add(createRow(r));
        }
    }
    
    int combi(int r, int n) {
        return rows.get(r).get(n);
    }
    
    private List<Integer> createRow(int r){
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int n = 1; n <= r; n++) {
            row.add(row.get(n - 1) * (r - n + 1) / n);
        }
        return row;
    }
    
    public static void main(String[] args) {
        final int HEIGHT = 10;
        Pascal p = new Pascal(HEIGHT);
        for(int r = 0; r < HEIGHT; r++) {
            out.printf(String.format("%%%ds", (HEIGHT - r) * 3), "");//空行
            for(int n = 0; n <= r; n++) {
                out.printf("%5d", p.combi(r, n));
            }
            out.println();
        }
    }
}
