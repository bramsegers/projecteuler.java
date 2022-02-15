package P601_P650;

public class PE628 {

    /*
        Nice problem! 
        A path is closed iff it has at least 1 main diagonal fully occupied with pawns. 
        After some pen and paper work similar to others, the answer takes O(1) space and O(n) time.
        Code golf fun! :D
    */
    public static void main(String[] args){
        long p=new PE628().pe628(100000000,1008691207);
        System.out.println(p);
    }
    
    long pe628(int n,int m){        
        long i=0,f=1,s=1,p=2;
        while(++i<n){
            f=(f*i)%m;
            s=(s+f)%m;
            p=(p+s)%m;
        }
        p+=(n*f-3*s)%m;
        return (p+m)%m;        
    }

    
}
