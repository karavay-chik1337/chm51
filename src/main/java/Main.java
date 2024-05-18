public class Main {
    public static void main(String[] args) {
        SimpleGUI simpleGUI = new SimpleGUI();
        simpleGUI.setVisible(true);
        double a = 0.6;
        double b = 1;
        double eps = 0.01;
        Function function = new Function();
        TypeMethod typeMethod = new TypeMethod(function);
        System.out.println("Тестовая");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 5; i++) {
            System.out.println(eps + "  " + typeMethod.tes_epxT_euler(a, b, eps) + "  " + typeMethod.tes_epxT_runge(a, b, eps));
            eps *= 0.1;
        }
        eps = 0.01;
        System.out.println("\nЧисленное");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 3; i++) {
            System.out.println(eps + "  " + typeMethod.tes_epxCH_euler(a, b, eps) + "  " + typeMethod.tes_epxCH_runge(a, b, eps));
            eps *= 0.1;
        }
    }
}
