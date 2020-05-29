import java.util.function.DoubleUnaryOperator;

public class Lab7 {
    private static final double EPSILON = 0.001;
    //N15
    public static final DoubleUnaryOperator equation1 = x -> ctg(x) - x / 4;
    public static final DoubleUnaryOperator equation2 = x -> x * x * x + 0.1 * x * x + 0.4 * x - 1.2;
    //N13
//    public static final DoubleUnaryOperator equation1 = x -> x*Math.log(x) - 1.2;
//    public static final DoubleUnaryOperator equation2 = x -> x * x * x - 0.1 * x * x + 0.4 * x - 1.5;

    public static void main(String args[]) {

        double[] Xs1 = new double[1];
        double[] Xs2 = new double[1];
        double[] Xs3 = new double[1];
        double[] Xs4 = new double[4];


        System.out.printf("Определим промежутки по графикам функций:\n" +
                        "ctg(x)-x/4 = 0                : %f\n" +
                        "x^3 + 0.1x^2 + 0.4x - 1.2 = 0 : %f",
                secantMethod(equation1, 0.85, 0.9, EPSILON),
                secantMethod(equation2, 0.3, 0.5, EPSILON));
    }


    private static double secantMethod(DoubleUnaryOperator operator, double begin, double end, double epsilon) {
        double x = 0;
        while (Math.abs(end - begin) >= epsilon) {
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



