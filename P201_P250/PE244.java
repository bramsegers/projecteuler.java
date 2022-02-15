package P201_P250;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PE244 {

    public static void main(String[] args) {
        new PE244().solve(0b001100110011001_0000, 0b101001011010010_0000);
    }

    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> idToBoard = new HashMap<>();
    Map<Integer, Integer> boardToId = new HashMap<>();

    void solve(int source, int destination) {
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 1 << 15; i++) {
            if (Integer.bitCount(i) == 7) {
                for (int j = 0; j < 16; j++) {
                    int board = (i << 4) + j;
                    graph.put(board, getNeighbours(board));
                    idToBoard.put(count, board);
                    boardToId.put(board, count);
                    count++;
                }
            }
        }
        //printGraph();
        System.out.println("source_node:" + boardToId.get(source));
        System.out.println("dest_node  :" + boardToId.get(destination));
        System.out.println("nodes/vertices:" + graph.keySet().size());
        //solve with k shortest paths algoritm
        //k shortest paths algoritm returns only 1 shortest path:
        int[] path = {21632, 21636, 21640, 21785, 21781, 21777, 21824, 21828, 21832, 21836, 22077, 22073, 22069, 22052, 22056, 21977, 23898, 23894, 19863, 19867, 38682, 38686, 73535, 73531, 66138, 66134, 66130, 62099, 62103, 73526, 72725, 72721, 72704};
        for (int p : path) {
            print(idToBoard.get(p));
        }
        String moves = getMoves(path);
        long checkSum = getCheckSum(moves);
        long end = System.currentTimeMillis();
        System.out.format("Checksum:%d%nElapsed:%dms%n", checkSum, end - start);
    }

    String getMoves(int[] path) {
        String moves = "";
        for (int i = 1; i < path.length; i++) {
            int b1 = idToBoard.get(path[i]);
            int b2 = idToBoard.get(path[i - 1]);
            int r = (b1 & 0b11) - (b2 & 0b11);
            int c = ((b1 & 0b1111) >> 2) - ((b2 & 0b1111) >> 2);
            if (r == 0 && c == -1) {
                moves += 'R';
            } else if (r == 0 && c == 1) {
                moves += 'L';
            } else if (r == 1 && c == 0) {
                moves += 'U';
            } else if (r == -1 && c == 0) {
                moves += 'D';
            }
        }
        return moves;
    }

    long getCheckSum(String moves) {
        System.out.println(moves);
        long rv = 0;
        for (char ch : moves.toCharArray()) {
            rv = (rv * 243 + ch) % 100000007;
        }
        return rv;
    }

    void printGraph() {
        Set<String> edges = new TreeSet<>();
        for (int i : graph.keySet()) {
            for (int j : graph.get(i)) {
                edges.add(i + "-" + j);
            }
        }
        //setup .txt format for kshortestpaths algorithm        
        System.out.println(graph.keySet().size());
        System.out.println();
        for (String s : edges) {
            int board1 = Integer.parseInt(s.split("-")[0]);
            int board2 = Integer.parseInt(s.split("-")[1]);
            System.out.println(boardToId.get(board1) + " " + boardToId.get(board2) + " 1");
        }
        System.exit(0);
    }

    void print(int board) {
        String s = "";
        Boolean[][] m = boardToMatrix(board);
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                s += m[r][c] == null ? "." : m[r][c] ? "1" : "0";
            }
            s += "\n";
        }
        System.out.println(s);
    }

    Set<Integer> getNeighbours(int board) {
        Set<Integer> rv = new HashSet<>();
        int re = board & 0b11;
        int ce = (board & 0b1111) >> 2;
        Boolean[][] m = boardToMatrix(board);
        int[] sr = {1, 0, -1, 0};
        int[] sc = {0, 1, 0, -1};
        for (int i = 0; i < 4/*2=undirected graph, 4=directed*/; i++) {
            int r = re + sr[i];
            int c = ce + sc[i];
            if (r >= 0 && c >= 0 && r < 4 && c < 4) {
                rv.add(matrixToBoard(m, re, ce, r, c));
            }
        }
        return rv;
    }

    Boolean[][] boardToMatrix(int board) {
        int re = board & 0b11;
        int ce = (board & 0b1111) >> 2;
        board >>= 4;
        Boolean[][] b = new Boolean[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (!(r == re && c == ce)) {
                    b[r][c] = (board & 1) != 0;
                    board >>= 1;
                }
            }
        }
        return b;
    }

    int matrixToBoard(Boolean[][] m, int re, int ce, int rne, int cne) {
        boolean old = m[rne][cne];
        m[rne][cne] = null;
        m[re][ce] = old;
        int rv = 0;
        int count = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (!(r == rne && c == cne)) {
                    rv += (m[r][c] ? 1 : 0) << (count++);
                }
            }
        }
        rv <<= 2;
        rv += cne;
        rv <<= 2;
        rv += rne;
        m[rne][cne] = old;
        m[re][ce] = null;
        return rv;
    }

}
