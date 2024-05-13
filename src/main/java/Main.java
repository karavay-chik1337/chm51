public class Main {
    public static void main(String[] args) {
        SimpleGUI simpleGUI = new SimpleGUI();
        simpleGUI.setVisible(true);
        double a = 0.6;
        double b = 1;
        int n = 100;
        double eps = 0.01;
        Function function = new Function();
        TypeMethod typeMethod = new TypeMethod(function);
        System.out.println("Тестовая");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 3; i++) {
            System.out.println(eps + "  " + typeMethod.tes_epxT_euler(a, b, eps) + "  " + typeMethod.tes_epxT_runge(a, b, eps));
            eps *= 0.1;
        }
//        for (double i : typeMethod.runge(a, b, n))
//            System.out.println(i + " ");
//        System.out.println(function.CorrectF(b) + " " + typeMethod.runge(a, b, n)[n-1] + " " + typeMethod.euler(a, b, n)[n-1]);
        eps = 0.01;
        System.out.println("\nЧисленное");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 3; i++) {
            System.out.println(eps + "  " + typeMethod.tes_epxCH_euler(a, b, eps) + "  " + typeMethod.tes_epxCH_runge(a, b, eps));
            eps *= 0.1;
        }
    }
}
