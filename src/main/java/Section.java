public class Section {
    double a;
    double b;
    int n;
    double h;

    //int partition;
    public Section(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
        h = (b - a) / (double) (n);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

//    public double[] separation(double[] xValues) {
//        double[] xValuesNew = new double[xValues.length - 1];
//        for (int i = 0; i < xValuesNew.length; i++) {
//            xValuesNew[i] = (xValues[i + 1] + xValues[i]) / 2;
//        }
//        return xValuesNew;
//    }

    public double[] uniformPart() {
        double[] x = new double[n + 1];
        for (int i = 0; i <= n; i++)//равномерное разбиение
            x[i] = a + (h * i);
        return x;
    }
}