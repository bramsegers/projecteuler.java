package P101_P150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import util.Util;

/* 
 * It is possible to optimise a network by removing some edges and still ensure 
 * that all points on the network remain connected. Using P107.txt, a network 
 * with forty vertices, and given in matrix form, find the maximum saving which 
 * can be achieved by removing redundant edges whilst ensuring that the network 
 * remains connected.
 * 
 * Info: http://en.wikipedia.org/wiki/Kruskal%27s_algorithm
 */
public class P107 {

    private static List<Set<Integer>> vertices = new ArrayList<>();
    private static List<int[]> edges = new ArrayList<>();

    public static long solve(List<String> list) {
        int weight = 0;
        for (int i = 0; i < list.size(); i++) {
            Set<Integer> set = new TreeSet<>();
            set.add(i);
            vertices.add(set);
            String[] row = list.get(i).split(",");
            for (int j = 0; j < i; j++) {
                if (!row[j].equals("-") && !row[j].equals("0")) {
                    int w = Integer.parseInt(row[j]);
                    edges.add(new int[]{w, j, i});
                    weight += w;
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int cmp0 = o1[0] - o2[0];
                int cmp1 = o1[1] - o2[1];
                int cmp2 = o1[2] - o2[2];
                return cmp0 != 0 ? cmp0 : cmp1 != 0 ? cmp1 : cmp2;
            }
        });

        int i = 0;
        while (vertices.size() > 1) {
            int[] edge = edges.get(i++);
            Set<Integer> set1 = getVertex(edge[1]);
            Set<Integer> set2 = getVertex(edge[2]);
            if (set1 != set2) {
                set1.addAll(set2);
                vertices.remove(set2);
                weight -= edge[0];
            }
        }

        return weight;
    }

    private static Set<Integer> getVertex(int i) {
        for (Set<Integer> vertex : vertices) {
            if (vertex.contains(i)) {
                return vertex;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P107.txt");
        System.out.println(P107.solve(list));
    }
}
