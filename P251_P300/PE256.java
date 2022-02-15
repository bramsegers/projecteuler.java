package P251_P300;

public class PE256{

    public static void main(String[] args){
        long s=new PE256().solve(200);
        System.out.println(s);
    }

    int solve(int T){
        int N=100000000;
        int[] v=new int[N];
        for(int i=1;i*i<N;i++)
            for(int k=1;;k++){
                int p=k*i+k+2;
                int q=(k+1)*i-k-3;
                if(p>q || i*p>=N) break;
                for(int j=p;j<=q && i*j<N;j++)
                    if((i&j&1)==0) v[i*j]++;
            }
        for(int i=0;;i++)
            if(v[i]==T) return i;
    }

}
