import java.util.function.DoubleUnaryOperator;

public class Lab6 {
    private static final double EPSILON = 0.001;
    private static final String equantion1 = "3^(x-1)-4-x=0";
    private static final String equantion2 = "2*x^3-9*x^2-60*x+1=0";
    private static final String equantion3 = "(x-3)^2*log0.5(x-5)= -1";
    private static final String equantion4 = "5*sin(x)=x-1";

    private static final DoubleUnaryOperator function1 = x -> Math.pow(3, x - 1) - 4 - x;
    private static final DoubleUnaryOperator function2 = x -> 2 * x * x * x - 9 * x * x - 60 * x + 1;
    private static final DoubleUnaryOperator function3 = x -> (x - 3) * (x - 3) * logHalf(x - 2) + 1;
    private static final DoubleUnaryOperator function4 = x -> 5 * Math.sin(x) - x + 1;

    public static double logHalf(double n) {
        return Math.log(n) / Math.log(0.5);
    }

    public static void main(String[] args) {
        double[][] equation1_roots_range = {{-5, -3}, {2, 3}};
        double[][] equation2_roots_range = {{-4, -3}, {0, 1}, {8, 9}};
        double[][] equation3_roots_range = {{}, {}}; //todo
        double[][] equation4_roots_range = {{-.8*Math.PI, -.6*Math.PI}, {-.2*Math.PI, 0},{.8*Math.PI, Math.PI}};
        System.out.printf("Calculating roots for equation %s:\n", equantion1);
        for (int i = 0; i < equation1_roots_range.length; i++) {
            System.out.printf("x%d = %.4f\n",i+1, bisectionPrototype(function1, equation1_roots_range[i], EPSILON));
        }
        System.out.printf("Calculating roots for equation %s:\n", equantion2);
        for (int i = 0; i < equation2_roots_range.length; i++) {
            System.out.printf("x%d = %.4f\n",i+1, bisectionPrototype(function2, equation2_roots_range[i], EPSILON));
        }
        System.out.printf("Calculating roots for equation %s:\n", equantion3);
        for (int i = 0; i < equation3_roots_range.length; i++) {
            System.out.printf("x%d = %.4f\n",i+1, bisectionPrototype(function3, equation3_roots_range[i], EPSILON));
        }
        System.out.printf("Calculating roots for equation %s:\n", equantion4);
        for (int i = 0; i < equation4_roots_range.length; i++) {
            System.out.printf("x%d = %.4f\n",i+1, bisectionPrototype(function4, equation4_roots_range[i], EPSILON));
        }
    }

    private static double bisectionPrototype(DoubleUnaryOperator function, final double[] bounds, double precision) {
        if (bounds.length != 2) throw new ArrayIndexOutOfBoundsException();
        double upperBound = bounds[1];
        double lowerBound = bounds[0];
        double media = 0;
        while ((upperBound - lowerBound) > precision) {
            media = (lowerBound + upperBound) / 2;
            if (function.applyAsDouble(media) == 0) break;
            if (function.applyAsDouble(media) * function.applyAsDouble(lowerBound) < 0) upperBound = media;
            else lowerBound = media;
        }
        return media;
    }

    private static void rootsPrinting(){}
}
