import java.util.function.DoubleUnaryOperator;

public class Lab7 {
    private static final double EPSILON = 0.001;
    //N15
    public static final DoubleUnaryOperator equation1 = x -> ctg(x) - x / 4;
    public static final DoubleUnaryOperator equation3 = x -> 2*Math.cos(x)/(Math.sin(x)*Math.sin(x)*Math.sin(x));
    public static final DoubleUnaryOperator equation2 = x -> x * x * x + 0.1 * x * x + 0.4 * x - 1.2;
    //N13
//    public static final DoubleUnaryOperator equation1 = x -> x*Math.log(x) - 1.2;
//    public static final DoubleUnaryOperator equation2 = x -> x * x * x - 0.1 * x * x + 0.4 * x - 1.5;

    public static void main(String[] args) {
        System.out.printf("Определим промежутки по графикам функций:\n" +
                        "%s:\t%f\n",// +
//                        "%s:\t%f\n",
                "ctg(x)-x/4 = 0",
                secantMethod(equation1, 1.2, 1.4, EPSILON));//,
//                "x^3 + 0.1x^2 + 0.4x - 1.2 = 0 ",
//                secantMethod(equation2, 0.8, 1, EPSILON));
//        double a = 1.2;
//        double xn = 1.331592463384973;
//        double a_xn =  xn -a;
////        double b = 1.4;
//        double fxn = equation3.applyAsDouble(xn);
//        double fa = equation3.applyAsDouble(a);
//
//        System.out.println(a - fxn/(fxn-fa)*a_xn);

    }


    private static double secantMethod(DoubleUnaryOperator operator, double begin, double end, double epsilon) {
        double x = 0;
        while (Math.abs(end - begin) >= epsilon) {
            System.out.println(x);
            x = begin + (operator.applyAsDouble(end) * (end - begin) / (operator.applyAsDouble(end) - operator.applyAsDouble(begin)));
            if (operator.applyAsDouble(begin) * operator.applyAsDouble(x) < 0) {
                end = x;
            } else if (operator.applyAsDouble(end) * operator.applyAsDouble(x) < 0) {
                begin = x;
            } else return x;
        }
        return x;

    }

    private static double ctg(double x) {
        return 1.0 / Math.tan(x);
    }
}



