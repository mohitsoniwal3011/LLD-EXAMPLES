package basic_principles;

public class KISS {   // Keep It Simple And Stupid

    public boolean isEvenUnnecessaryComplex(int n ){
        boolean isEven = false;

        if(n % 2 == 0){
            isEven=  true;
        }else{
            isEven = false;
        }
        return isEven;
    }

    public boolean isEvenSimple(int n ){
        return n % 2 == 0;
    }
}
