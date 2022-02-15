package P301_P350;

public class PE310{

    // https://oeis.org/A014586
    public static void main(String[] args){
        new PE310().solve(29);
        new PE310().solve(100000);
    }

    void solve(int N){
        long P=0;
        int max=0;
        int[] seen=new int[N+1];
        int[] nim=new int[N+1];
        for(int j,i=1;i<=N;++i){
            for(j=1;j*j<=i;j++) seen[nim[i-j*j]]=i;
            for(j=0;seen[j]==i;j++){}
            if(j>max) max=j;
            nim[i]=j;
        }
        long[] a=new long[max+1];
        for(int i=0;i<=N;i++) a[nim[i]]++;
        for(int i=0;i<=max;i++)
            for(int j=i;j<=max;j++)
                for(int k=j;k<=max;k++)
                    if((i^j^k)!=0){}
                    else if(i< j && j< k) P+=a[i]* a[j]   * a[k]    /1;
                    else if(i==j && j< k) P+=a[i]*(a[j]+1)* a[k]    /2;
                    else if(i< j && j==k) P+=a[i]* a[j]   *(a[k]+1) /2;
                    else if(i==j && j==k) P+=a[i]*(a[j]+1)*(a[k]+2) /6;
        System.out.format("P(%d)=%d%n",N,P);
    }

}