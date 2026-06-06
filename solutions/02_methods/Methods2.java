// Methods2.java
//
// Parameters need types too. Fix the method definition.

public class Methods2 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            callMe(i);
        }
    }

    static void callMe(int num) {
        System.out.println("Call number " + num);
    }
}
