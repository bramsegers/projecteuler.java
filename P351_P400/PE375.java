package P351_P400;

public class PE375 {

    // 7435327983715286168
    // elapsed: 24min 15sec
    public static void main(String[] args){
        new PE375().solve(2000000000);
    }
   
    void solve(int n){
        long s0=290797, m=50515093;
        long s1=(s0*s0)%m, s=s1;
        long[] S=new long[10000000];
        S[0]=s1;
        int M;
        for(M=1;M<n&&(s=(s*s)%m)!=s1;M++) S[M]=s;
        long sum=0;
        for(int i=0;i<n;i++){
            long min=S[i%M],t=0,v=0;
            for(int j=i-1;j>=0 && S[j%M]>=min;j--) t++;
            for(int j=i+1;j<n  && S[j%M]> min;j++) v++;
            sum+=min*(v+1)*(t+1);
        }
        System.out.println(sum);
    }

}