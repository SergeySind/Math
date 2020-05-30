//x = 1,83 + 0,003n;
public class Lab5 {
    private static double EPSILON = 0.0001;

    public static void main(String[] args) {
        double x0 = 1.83;
        int r = 6;
        double[] x = new double[]{1.5, 1.55, 1.6, 1.65, 1.7, 1.75, 1.8, 1.85, 1.9, 1.95, 2, 2.05, 2.1};
        double[] y = new double[]{15.132, 17.422, 20.393, 23.994, 28.160, 32.812, 37.857, 43.189, 48.689, 54.225, 59.653, 64.817, 69.550};

        double[] y_1 = new double[6];
        double[] y__1 = new double[6];

        double[] y_2 = new double[5];
        double[] y__2 = new double[5];
        double[] y_3 = new double[5];
        double[] y__3 = new double[5];

        for (int i = r; i > 0; i--) {
            y_1[i - 1] = y[i] - y[i - 1];
            y__1[i - 1] = y[i] - y[i + 1];
        }
        for (int i = r; i > 1; i--) {
            y_2[i - 2] = y_1[i - 1] - y_1[i - 2];
            y__2[i - 2] = y__1[i - 1] - y__1[i - 2];
        }
        for (int i = r; i > 2; i--) {
            y_3[i - 3] = y_2[i - 2] - y_2[i - 3];
            y__3[i - 3] = y__2[i - 2] - y__2[i - 3];
        }

        for (int i = 1; i < 31; i++) {
            double x1 = x0 + i * 0.003;
            double dx1, x2;
            dx1 = x1 % 0.1;
            int index = 0, index2 = 0;

            if (dx1 < 0.025) {
                x2 = x1 - dx1;
            } else if (dx1 > 0.075) {
                x2 = x1 - dx1 + 0.1;
            } else {
                x2 = x1 - dx1 + 0.05;
            }

            for (int j = 0; j < x.length; j++) {
                if (x2 - x[j] < EPSILON) {
                    index = j;
                    index2 = (index >= r) ? index - r : index;
                    break;
                }
            }
            if (index2 > 4) {
                index2 = 4;
            }

            double t = (x1 - x2) / 0.05;
            double gauss_1 = (index < r) ? Gauss_1(y[index], y_1[index2], y_2[index2 - 1], y_3[index2 - 1], t) : Gauss_1(y[index], y__1[index2], y__2[index2 + 1], y__3[index2 + 1], t);
            double gauss_2 = (index < r) ? Gauss_2(y[index], y_1[index2 - 1], y_2[index2 - 1], y_3[index2 - 2], t) : Gauss_2(y[index], y__1[index2 + 1], y__2[index2 + 1], y__3[index2 + 2], t);
            double bessel = (index < r) ? Bessel(y[index], y[index + 1], y_1[index2], y_2[index2 - 1], y_2[index2], y_3[index2 - 2], t) : Bessel(y[index], y[index - 1], y__1[index2], y__2[index2], y__2[index2 + 1], y__3[index2 + 1], t);
            double stirling = (index < r) ? Stirling(y[index], y_1[index2], y_1[index2 - 1], y_2[index2 - 1], y_3[index2 - 1], y_3[index2 - 2], t) : Stirling(y[index], y__1[index2], y__1[index2 + 1], y__2[index2 + 1], y__3[index2 + 1], y__3[index2 + 2], t);
            System.out.printf("x = %5.3f\n" +
                    "Первый метод гаусса: у= %f\n" +
                    "Второй метод гаусса: у= %f\n" +
                    "Метод Бесселя: у= %f\n" +
                    "Метод Стирлинга: у= %f\n", x1, gauss_1, gauss_2, bessel, stirling);
        }
    }

    private static double Gauss_1(double y1, double y2, double y3, double y4, double t) {
        return y1 + t * y2 + t * (t - 1) / 2 * y3 + (t + 1) * (t - 1) * t / 6 * y4;
    }

    private static double Gauss_2(double y1, double y2, double y3, double y4, double t) {
        return y1 + t * y2 + t * (t + 1) / 2 * y3 + (t + 1) * (t - 1) * t / 6 * y4;
    }

    private static double Bessel(double y1, double y2, double y3, double y4, double y5, double y6, double t) {
        return (y1 + y2) / 2 + (t - 0.5) * y3 + (t * (t - 1) * (y4 + y5)) / 4 + (t - 0.5) * t * (t - 1) * y6 / 6;
    }

    private static double Stirling(double y1, double y2, double y3, double y4, double y5, double y6, double t) {
        return y1 + t * (y2 + y3) / 2 + t / 2 * y4 + t * (t * t - 1) / 6 * (y5 + y6) / 2;
    }

}
