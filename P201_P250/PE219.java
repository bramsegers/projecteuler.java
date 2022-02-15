package P201_P250;

import java.util.TreeMap;

public class PE219 {

    public static void main(String[] args) {
        new PE219().solve(6);
        new PE219().solve(1000000000);
    }

    void solve(long size){
        TreeMap<Long,Long> tree=new TreeMap<>();
        tree.put(0L,1L);
        Long f1,f2,f,n,low,cost=0L;
        for(n=1L;n<size;n+=f){
            low=tree.firstKey();
            f=Math.min(tree.get(low),size-n);
            tree.remove(low);
            cost+=f*(low+5);
            f1=tree.get(low+1);
            tree.put(low+1,f1==null?f:f1+f);
            f2=tree.get(low+4);
            tree.put(low+4,f2==null?f:f2+f);
        }
        System.out.format("C(%d)=%d%n",size,cost);
    }

}