import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws FormatException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String input = scan.nextLine();
        String textResult = calc(input);
        System.out.println("Output: \n" + textResult);
    }
    public static String calc (String input) throws FormatException {
        String[] inputArr = input.split(" ");
        if(inputArr.length <3){
            throw new FormatException ("строка не является математической операцией: операнд_пробел_оператор_пробел_операнд");
        }
        if(inputArr.length >3){
            throw new FormatException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String aValue = inputArr[0];
        int a = valueTransformation(aValue);
        char sign = inputArr[1].charAt(0);
        String bValue = inputArr[2];
        int b = valueTransformation(bValue);

        Roman[] romans = Roman.values();
        boolean aIsRoman = romanCheck(aValue, romans);
        boolean bIsRoman = romanCheck(bValue, romans);

        if((aIsRoman && !bIsRoman) || (!aIsRoman && bIsRoman)){
            throw new FormatException("используются одновременно разные системы счисления");
        }

        if(a<1||a>10 || b<1 || b >10){
            throw new FormatException ("Калькулятор должен принимать на вход целые числа от 1 до 10 включительно");
        }
        int intResult =  mathResult(a,b, sign);

        String textResult;
        if(aIsRoman){
            if(intResult<=0){
                throw new FormatException("в римской системе нет отрицательных чисел");
            }else  textResult = romanResultTransformation(intResult);
            } else textResult = String.valueOf(intResult);
        return textResult;
    }
    static int mathResult(int a, int b, char sign) throws FormatException {
        int intResult;
        switch (sign){
            case ('+'):
                intResult = a+b;
                break;
            case ('-'):
                intResult = a-b;
                break;
            case ('*'):
                intResult = a*b;
                break;
            case ('/'):
                intResult = a/b;
                break;
            default:
                throw new FormatException("Неправильный математические оператор");
        }
        return intResult;
    }

    private static int valueTransformation(String stringValue){
        Roman[] romans = Roman.values();
        int intValue=0;
        if(romanCheck(stringValue, romans)){
           // isRoman = true;
            for (int i = 0; i < Roman.values().length; i++) {
                if (stringValue.equals(romans[i].name())){
                  intValue = romans[i].getRomanValue();
                }
            }
        }
        if(stringValue.chars().allMatch( Character::isDigit )){
        //    isNumeric = true;
            intValue = Integer.parseInt(stringValue);
        }
        return intValue;
    }
    private static boolean romanCheck(String text, Roman[] keywords)
    {
        for (Roman keyword : keywords)
        {
            if (text.equals(keyword.name())){
                return true;
            }
        }
        return false;
    }

  //romanDigit  и romanResultTransformation  = реализация честно украдена из stackoverflow
    public static String romanDigit(int n, String one, String five, String ten){
        if(n >= 1)
        {
            if(n == 1)
            {
                return one;
            }
            else if (n == 2)
            {
                return one + one;
            }
            else if (n == 3)
            {
                return one + one + one;
            }
            else if (n==4)
            {
                return one + five;
            }
            else if (n == 5)
            {
                return five;
            }
            else if (n == 6)
            {
                return five + one;
            }
            else if (n == 7)
            {
                return five + one + one;
            }
            else if (n == 8)
            {
                return five + one + one + one;
            }
            else if (n == 9)
            {
                return one + ten;
            }
        }
        return "";
    }

    public static String romanResultTransformation(int result){
        String romanOnes = romanDigit( result%10, "I", "V", "X");
        result /=10;
        String romanTens = romanDigit( result%10, "X", "L", "C");
        return romanTens + romanOnes;
    }
     static class FormatException extends Exception {
        public FormatException(String description){
            super(description);
        }
    }

     enum Roman {
        I(1), II(2), III(3), IV(4), V(5),
        VI(6), VII(7), VIII(8), IX(9), X(10);
        private final int romanValue;

        Roman(int romanValue){
            this.romanValue = romanValue;
        }
        public int getRomanValue(){
            return romanValue;
        }
    }
}
