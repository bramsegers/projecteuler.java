package P651_P700;

public class PE653{

    // Answer won't change if we replace all marbles with points and decrease the tube length by 20(N-j)+10
    // Then ignore collisions, find exit times, sort them, and use the jth biggest one as the answer
    public static void main(String[] args){
        new PE653().solve(5000,3,2);
        new PE653().solve(10000,11,6);
        new PE653().solve(100000,101,51);
        new PE653().solve(1000000000,1000001,500001);
    }

    void solve(long L,int N,int j){
        long p=0,r=6563116;
        long[] a=new long[N];
        for(int i=0;i<N;i++){
            p+=(r%1000)+1;
            r=(r*r)%32745673;
            a[i]=L-20*(N-j)-10;
            a[i]+=(r>10000000)?p:-p;
        }
        java.util.Arrays.sort(a);
        String out="d(%d,%d,%d)=%d%n";
        System.out.format(out,L,N,j,a[j-1]);
    }

}
