import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String input = scan.nextLine();
        String[] inputArr = input.split(" ");
        String aValue = inputArr[0];
        int a = Integer.parseInt(aValue);
        char sign = inputArr[1].charAt(0);
        String bValue = inputArr[2];
        int b = Integer.parseInt(bValue);
        if (a>0&&a<=10 && b>0 && b<=10){
            int result =  mathResult(a,b, sign);
            System.out.println("Output: \n" + result);
        } else System.out.println("Wrong numbers");
    }
    static int mathResult(int a, int b, char sign){
        int result=0;
        switch (sign){
            case ('+'):
                result = a+b;
                break;
            case ('-'):
                result = a-b;
                break;
            case ('*'):
                result = a*b;
                break;
            case ('/'):
                result = a/b;
                break;
            default:

        }
        return result;
    }

}
