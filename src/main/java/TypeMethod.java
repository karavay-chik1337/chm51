//доделать error
public class TypeMethod {
    Function function;
    double delta_pred, delta_new;
    public static int MAX_ITER = 35000000;
    public TypeMethod(Function function) {
        this.function = function;
    }

    public double[] runge(double a, double b, int n) {
        Section section = new Section(a, b, n);
        double[] x = section.uniformPart();
        double[] y = new double[n + 1];
        y[0] = function.y0;
        for (int i = 1; i <= n; i++) {
            double k1 = section.h * function.f(x[i - 1], y[i - 1]);
            double k2 = section.h * function.f(x[i - 1] + section.h / 3, y[i - 1] + k1 / 3);
            double k3 = section.h * function.f(x[i - 1] + 2 * section.h / 3, y[i - 1] + 2 * k2 / 3);
            y[i] = y[i - 1] + 0.25 * (k1 + 3 * k3);
        }
        return y;
    }

    public double[] euler(double a, double b, int n) {
        Section section = new Section(a, b, n);
        double[] x = section.uniformPart();
        double[] y = new double[n + 1];
        y[0] = function.y0;
        for (int i = 1; i <= n; i++) {
            y[i] = y[i - 1] + section.h * function.f(x[i - 1], y[i - 1]);
        }
        return y;
    }

    public int[] tes_epxT_runge(double a, double b, double eps) {
        boolean flag = true;
        delta_pred = 1;
        double correctY = function.CorrectF(b);
        int i = 2;
        while (delta_pred > eps && i < MAX_ITER) {
            delta_pred = Math.abs(runge(a, b, i)[i - 1] - correctY);
            i *= 2;
            delta_new = Math.abs(runge(a, b, i)[i - 1] - correctY);
            if(delta_new >= delta_pred){
                flag = false;
                break;
            }
        }
        if(!flag)
            return new int[] {i, 1};
        else if(i >= MAX_ITER - 1)
            return new int[] {i, 2};
        else
            return new int[] {i, 0};
    }

    public int[] tes_epxT_euler(double a, double b, double eps) {
        boolean flag = true;
        delta_pred = 1;
        double correctY = function.CorrectF(b);
        int i = 2;
        while (delta_pred > eps && i < MAX_ITER){
            delta_pred = Math.abs(euler(a, b, i)[i - 1] - correctY);
            i *= 2;
            delta_new = Math.abs(euler(a, b, i)[i - 1] - correctY);
            if(delta_new >= delta_pred){
                flag = false;
                break;
            }
        }
        if(!flag)
            return new int[] {i, 1};
        else if(i >= MAX_ITER)
            return new int[] {i, 2};
        else
            return new int[] {i, 0};
    }

    public int[] tes_epxCH_runge(double a, double b, double eps) {
        int i = 2;
        delta_pred = 1;
        boolean flag = true;
        while (delta_pred > eps && i < MAX_ITER){
            delta_pred = (Math.abs(runge(a, b, i)[i - 1] - runge(a, b, 2 * i)[2 * i - 1])) / 7d;
            i *= 2;
            delta_new = (Math.abs(runge(a, b, i)[i - 1] - runge(a, b, 2 * i)[2 * i - 1])) / 7d;
            if(delta_new >= delta_pred){
                flag = false;
                break;
            }
        }
        if(!flag)
            return new int[] {i, 1};
        else if(i >= MAX_ITER)
            return new int[] {i, 2};
        else
            return new int[] {i, 0};
    }

    public int[] tes_epxCH_euler(double a, double b, double eps) {
        int i = 2;
        boolean flag = true;
        delta_pred = 1;
        while (delta_pred > eps && i < MAX_ITER){
            delta_pred = Math.abs(euler(a, b, i)[i - 1] - runge(a, b, 2 * i)[2 * i - 1]);
            i *= 2;
            delta_new = Math.abs(euler(a, b, i)[i - 1] - runge(a, b, 2 * i)[2 * i - 1]);
            if(delta_new >= delta_pred){
                flag = false;
                break;
            }
        }
        if(!flag)
            return new int[] {i, 1};
        else if(i >= MAX_ITER)
            return new int[] {i, 2};
        else
            return new int[] {i, 0};
    }
}
