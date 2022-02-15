package P601_P650;

public class PE637 {

    public static void main(String[] args){
        g(100,10,3);      // 3302
        g(10000,10,3);    // 45868794
        g(10000000,10,3); // 49000634845039
    }
    
    static void g(int n,int b1,int b2){
        int[] m1=new int[n+1];
        int[] m2=new int[n+1];
        long g=0;
        for(int i=1;i<=n;i++) 
            g+=(f(i,b1,m1)==f(i,b2,m2))?i:0;
        System.out.format("g(%d,%d,%d)=%d%n",n,b1,b2,g);
    }
    
    static int f(int n,int b,int[] m){
        if(n<b) return 0;
        if(m[n]==0){
            String s=Integer.toString(n,b);
            int len=s.length(), min=1<<30;
            for(int i=(1<<(len-1))-1;i>0;i--){
                int j=i, tsum=0, sum=0;
                for(int k=0;k<len;k++){
                    sum*=b;
                    sum+=s.charAt(k)-'0';
                    if((j&1)==1) {tsum+=sum;sum=0;}
                    j>>=1;
                }
                tsum+=sum;
                min=Math.min(min,f(tsum,b,m));
                if(min==1) break;
            }
            m[n]=1+min;
        }
        return m[n];
    }

}