package P201_P250;

import java.util.HashSet;
import java.util.Set;
import util.Frac;

public class PE228 {

    public static void main(String[] args) {
        new PE228().solve(3,4);
        new PE228().solve(1864,1909);
    }

    void solve(int a,int b){
        Set<Frac> set=new HashSet<>();
        for(int k=a;k<=b;k++)
            for(int j=0;j<k;j++)
                set.add(new Frac(j,k));
        System.out.println(set.size());
    }

}
