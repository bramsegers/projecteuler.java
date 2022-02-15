package P601_P650;

import java.util.Arrays;

public class PE612 {

    public static void main(String[] args) {
        new PE612().brute(100);
        new PE612().solve();
    }

    int N = 18;
    int MOD = 1000267129;
    int[] len = new int[1024];
    long[] fac = new long[N + 1];
    long[][][] count1 = new long[2][N + 1][1024];  
    long[][][][] count2 = new long[2][N + 1][1024][1024];

    boolean bitset(int key, int pos) {return ((key>>pos)&1)==1;}
    int     setbit(int key, int pos) {return key|(1<<pos);}
    int   clearbit(int key, int pos) {return key&~(1<<pos);}

    void solve() { 
        for (int i = 0; i < 1024; i++)
            len[i]=Integer.bitCount(i);        
        for (int i = 0; i <= N; i++) 
            fac[i] = i < 2 ? 1 : i * fac[i - 1];
        count1(N, 0, 0, 0);
        long c = 0;
        for (int kp = 1; kp < 1024; kp++) {
            for (int kq = 1; kq < 1024; kq++) {
                for (int dp = 1; dp <= N && (kp & kq) > 0; dp++) {
                    if (count1[0][dp][kp] == 0) continue;                    
                    c += count2(1, dp, kp, kq);
                    c %= MOD;
                    for (int dq = dp + 1; dq <= N; dq++) {
                        c += count1[0][dp][kp] * count1[0][dq][kq];
                        c %= MOD;
                    }
                }
            }
        }
        System.out.format("P(%d) mod %d = %d%n",N,MOD,c);        
    }

    void count1(int left, int i, int len, int key, int... val) {
        if (i == 10) {
            long v = val1(len, key, val);
            long v2 = !bitset(key,0)? 0: val2(len, key, val);            
            count1[0][len][key] += v-v2;
            count1[1][len][key] += v;
            count1[0][len][key] %= MOD;
            count1[1][len][key] %= MOD;
            return;
        }
        count1(left, i + 1, len, key, val);
        for (int j = 1; j <= left; j++) {
            int[] val2 = Arrays.copyOf(val, val.length + 1);
            val2[val.length] = j;
            count1(left - j, i + 1, len + j, setbit(key,i), val2);
        }
    }

    long val1(int len, int key, int... val) {
        long v1 = fac[len];
        for (int j = 0, i = 0; i < 10; i++) {
            if (bitset(key,i)) v1/= fac[val[j++]];
        }
        return v1 % MOD;
    }
    
    long val2(int len, int key, int... val) {
        long v2 = fac[len - 1] / fac[val[0] - 1];
        for (int j = 1, i = 1; i < 10; i++) {
            if (bitset(key,i)) v2/= fac[val[j++]];
        }
        return v2 % MOD;
    }

    // #numbers p < q where #digits p = #digits q = d 
    // and unique digits p = kp and unique digits q = kq
    long count2(int first, int d, int kp, int kq) {
        if(d<len[kp] || d<len[kq]) return 0;        
        if(count2[first][d][kp][kq]>0) return count2[first][d][kp][kq];        
        long c=0;
        for (int i = first; i < 10; i++) {
            if(!bitset(kp,i)) continue;
            int cp = clearbit(kp,i);                    
            if(bitset(kq,i)){
                    int cq = clearbit(kq,i);
                    c += count2(0, d-1, kp, kq); 
                    c += count2(0, d-1, cp, kq); 
                    c += count2(0, d-1, kp, cq); 
                    c += count2(0, d-1, cp, cq);
                    c %= MOD;
            }
            for (int j = i+1; j < 10; j++) {
                if(bitset(kq,j)){
                    int cq = clearbit(kq,j);
                    c += count1[1][d-1][kp] * count1[1][d-1][kq]; 
                    c += count1[1][d-1][cp] * count1[1][d-1][kq]; 
                    c += count1[1][d-1][kp] * count1[1][d-1][cq]; 
                    c += count1[1][d-1][cp] * count1[1][d-1][cq];
                    c %= MOD;
                }
            }            
        }
        count2[first][d][kp][kq] = c;
        return c;
    } 

    private void brute(int N) {
        long c = 0;
        for (int p = 1; p < N; p++) {
            for (int q = p + 1; q < N; q++) {
                if (valid("" + p, "" + q)) c++;
            }
        }
        System.out.println(c);
    }

    boolean valid(String p, String q) {
        for (int i = 0; i < p.length(); i++) {
            if (q.contains(p.substring(i, i + 1))) 
                return true;            
        }
        return false;
    }

}
