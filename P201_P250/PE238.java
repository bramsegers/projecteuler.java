package P201_P250;

public class PE238 {

    public static void main(String[] args){
        solve((long)2e15);
    }
    
    static int    s0 = 14025256;
    static int   mod = 20300713;
    static int[] dig = new int[20000000];

    static void solve(long n){
        int i,j,k,r,t,m=0,q=0,s=s0;
        while(m==0||s!=s0){
            m+=(int)Math.log10(s)+1;
            for(t=s,i=1;t>0;i++,t/=10)
                q+=dig[m-i]=t%10;
            s=(int)((1L*s*s)%mod);
        }        
        long c,d=0,p=0;
        boolean[] b=new boolean[q+1];
        for(j=0;d<n;j++)
            for(r=0,i=0;i<m;i++){
                if((k=i+j)>=m) k-=m;
                if((r+=dig[k])>0&&!b[r]){
                    d+=c=1+(n-r)/q;
                    p+=c*(j+1);
                    b[r]=true;
                }
            }
        System.out.format("P(%d)=%d%n",n,p);
    }

}
