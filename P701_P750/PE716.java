package P701_P750;

import static util.Util.modPow;

public class PE716 {

    public static void main(String[] args) {
        System.out.println(C(10000,20000,1000000007));
    }

    static long C(long h,long w,long m){
        long c=9*modPow(2,w+h,m)%m;
        c+=(w*h-4*h-5)*modPow(2,w+1,m)%m;
        c+=(w*h-4*w-5)*modPow(2,h+1,m)%m;
        c+=2*(w*h+5*w+5*h+5)%m;
        return c%m;
    }
    
}
