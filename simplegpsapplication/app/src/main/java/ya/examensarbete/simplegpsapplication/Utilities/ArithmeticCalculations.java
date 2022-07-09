package ya.examensarbete.simplegpsapplication.Utilities;

public class ArithmeticCalculations {


    public double roundToFourDigits(double num1){
        num1 = Math.round(num1 * Math.pow(10, 4))
                / Math.pow(10, 4);

        return num1;
    }


}
