package P701_P750;

import java.util.Queue;
import java.util.PriorityQueue;

public class PE718 {

    class Node implements Comparable<Node>{
        int type,reach,count;
        Node(int t,int r,int c){type=t;reach=r;count=c;}
        public int compareTo(Node n){return count-n.count;}
    }

    void solve(int p,int mod){
        long sum=0;
        int x=(int)Math.pow(17,p);
        int y=(int)Math.pow(23,p);
        int z=(int)Math.pow(19,p);
        boolean[] reachable=new boolean[x];
        Queue<Node> Q=new PriorityQueue<>();
        Q.add(new Node(1,(x+y+z)%x,(x+y+z)/x));
        for(int reached=0;reached<x;){
            Node N=Q.poll();
            int t=N.type;
            int r=N.reach;
            int c=N.count;
            if(!reachable[r]){
                reachable[r]=true;
                reached++;
                sum+=(long)c*r;
                sum+=(long)c*(c-1)/2*x;
                sum%=mod;
                int r0=(r+z)%x;
                int r1=(r+y)%x;
                int c0=c+(z/x)+(r+(z%x))/x;
                int c1=c+(y/x)+(r+(y%x))/x;
                Q.add(new Node(0,r0,c0));
                if(t==1) Q.add(new Node(1,r1,c1));
            }
        }
        System.out.format("G(%d) mod %d = %d%n",p,mod,sum);
    }
    
    public static void main(String[] args) {
        new PE718().solve(1,1000000007);
        new PE718().solve(2,1000000007);
        new PE718().solve(6,1000000007);
    }

}