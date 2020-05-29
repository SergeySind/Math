import java.util.function.DoubleFunction;

public class Lab6 {
    private static final double EPSILON = 0.000001;
    private static final DoubleFunction<Double> function1 = x -> 2 * Math.atan(x) - 3 * x + 2;
    private static final DoubleFunction<Double> function2 = x -> 2 * Math.pow(x, 4) + 8 * Math.pow(x, 3) + 8 * Math.pow(x, 2) - 1;
    private static final DoubleFunction<Double> function3 = x -> Math.log(x + 2) / Math.log(2) * (x - 1) - 1;
    private static final DoubleFunction<Double> function4 = x -> Math.sin(x - 0.5) - x + 0.8;

    //    N13
    public static final DoubleUnaryOperator function1 = x -> Math.pow(3, x) + 2 * x - 5;
    public static final DoubleUnaryOperator function2 = x -> x * x * x * x - 4 * x * x * x - 8 * x * x + 1;
    public static final DoubleUnaryOperator function3 = x -> x * x - 3 + Math.pow(0.5, x);
    public static final DoubleUnaryOperator function4 = x -> (x - 2) * (x - 2) * Math.log10(x + 11) - 1;

//    N15
//    public static final DoubleUnaryOperator function1 = x -> Math.pow(3, x - 1) - 4 - x;
//    public static final DoubleUnaryOperator function2 = x -> 2 * x * x * x - 9 * x * x - 60 * x + 1;
//    public static final DoubleUnaryOperator function3 = x -> (x - 3) * (x - 3) * lohHalf(x - 2) + 1;
//    public static final DoubleUnaryOperator function4 = x -> 5 * Math.sin(x) - x + 1;
//    public static double lohHalf(double n) {
//        return Math.log(n) / Math.log(0.5);
//    }

    public static void main(String[] args) {
        double[] Xs1 = {-0.577350269189626, 0.577350269189626};
        double[] Xs2 = {0, -1, -2};
        double[] Xs3 = {-1, 1};
        double[] Xs4 = {0.5, 6.783};


        System.out.println("2arctg(x)-3x+2");
        for (int i = 0; i < 100; i++) {
            if (function1.apply(Xs1[0]) > 0) {
                System.out.println(bisectionPrototype(function1, Xs1[0] - 1, Xs1[0], EPSILON));
                break;
            } else if (function1.apply(Xs1[0]) < function1.apply(Xs1[0] - 1)) {
                Xs1[0] = Xs1[0] - 1;
            }

        }
        Xs1[0] = 0.914;
        for (int i = 0; i < 100; i++) {
            if (function1.apply(Xs1[0]) > 0) {
                System.out.println(bisectionPrototype(function1, Xs1[0] - 1, Xs1[0], EPSILON));
                break;
            } else if (function1.apply(Xs1[0]) < function1.apply(Xs1[0] + 1)) {
                Xs1[0] = Xs1[0] + 1;
            }
        }

        System.out.println("2x^4+8x^3+8x^2-1");
        for (int i = 0; i < 100; i++) {
            if (function1.apply(Xs2[0]) > 0) {
                System.out.println(bisectionPrototype(function2, Xs2[0] - 1, Xs2[0], EPSILON));
                break;
            } else if (function2.apply(Xs2[0]) < function2.apply(Xs2[0] - 1)) {
                Xs2[0] = Xs2[0] - 1;
            }
        }
        Xs2[0] = 0;
        for (int i = 0; i < 100; i++) {
            if (function2.apply(Xs2[0]) > 0) {
                System.out.println(bisectionPrototype(function2, Xs2[0] - 1, Xs2[0], EPSILON));
                break;
            } else if (function2.apply(Xs2[0]) < function2.apply(Xs2[0] + 1)) {
                Xs2[0] = Xs2[0] + 1;
            }
        }
        System.out.println(
                "Определим промежутки по графикам функций\n" +
                        "[log2(x+2)](x-1)\n" +
                        bisectionPrototype(function3, -1.5, 0, EPSILON) + "\n" +
                        bisectionPrototype(function3, 0, 1.5, EPSILON) + "\n" +
                        "sin(x-0.5)-x+0.8\n" + bisectionPrototype(function3, -3, -2, EPSILON) + "\n" +
                        bisectionPrototype(function4, -1, 1, EPSILON) + "\n" +
                        bisectionPrototype(function4, 2, 3, EPSILON));
    }

    private static double bisectionPrototype(DoubleFunction<Double> function, double lowerBound, double upperBound, double precision) {
        double media = 0;
        while ((upperBound - lowerBound) > precision) {
            media = (lowerBound + upperBound) / 2;
            if (function.apply(media) == 0) break;
            if (function.apply(media) * function.apply(lowerBound) < 0) upperBound = media;
            else lowerBound = media;
        }
        return media;
    }
}
