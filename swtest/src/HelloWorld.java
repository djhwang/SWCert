import java.util.Scanner;


public class HelloWorld {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        String name;
        for (int i = 0; i < tc; i++) {
            name = sc.next();
            System.out.println("Hello, " + name + "!");
        }

        sc.close();
    }

}
