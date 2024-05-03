public class Euler {
    Function function;

    public Euler(Function function) {
        this.function = function;
    }

    public double euler_calc(double x, double y, double h) {
        return y + h * function.f(x, y);
    }

    public double[] euler(double a, double b, int n) {
        Section section = new Section(a, b, n);
        double f;
        double[] x = section.uniformPart();
        double[] y = new double[n];
        y[0] = function.y0;
        for (int i = 0; i < n - 1; i++) {
            y[i + 1] = euler_calc(x[i], y[i], section.h);
            f = function.fTochnoe(x[i]);
        }
        return y;
    }

    public int tes_epxT(double a, double b, double eps) {
        double yToch = function.fTochnoe(b);
        int i = 2;
        while (Math.abs(euler(a, b, i)[i - 1] - yToch) > eps)
            i += 1;
        return i;
    }

    public int tes_epxCH(double a, double b, double eps) {
        int i = 2;
        while ((Math.abs(euler(a, b, i)[i - 1] - euler(a, b, 2 * i)[2 * i - 1])) / 8d > eps)
            i += 1;
        return i;
    }
}
