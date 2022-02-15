package P651_P700;

public class PE662{

    public static void main(String[] args){
        new PE662().F(3,4);
        new PE662().F(10,10);
        new PE662().F(10000,10000);
    }

    int[][] mem,mov;
    int     len,mod=1000000007;

    void F(int W,int H){
        mem=new int[W+1][H+1];
        mov=new int[100][2];
        for(int w=0;w<=W;w++)
            for(int h=0;h<=H;h++){
                int n2=w*w+h*h;
                int n=isqrt(n2);
                if(n*n==n2){
                    int f2=5*n2+4;
                    int g2=5*n2-4;
                    int f=isqrt(f2);
                    int g=isqrt(g2);
                    if(f*f==f2||g*g==g2)
                        mov[len++]=new int[]{w,h};
                }
            }
        System.out.format("F(%d,%d)=%d%n",W,H,DP(W,H));
    }    

    int DP(int w,int h){
        if(w<0||h<0)    return 0;
        if(w+h==0)      return 1;
        if(w>h)         return DP(h,w);
        if(mem[w][h]>0) return mem[w][h];
        int f=0;
        for(int i=1;i<len;i++)
            f=(f+DP(w-mov[i][0],h-mov[i][1]))%mod;
        return mem[w][h]=f;
    }

    int isqrt(int n) {return (int)Math.sqrt(n);}

}
