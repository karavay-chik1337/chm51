public class Function {
    double y0 = 1.2;

    public double f(double x, double y) {
        return x / 8d + y;
        //return Math.cos(2 * x + y) + 1.5 * (x - y);//для численного
    }

    public double CorrectF(double x) {//для тестового
        return 7 * Math.pow(Math.E, x - 0.6) / 5 - x / 8d - 0.125d;
    }
}
