import java.math.BigInteger;
import java.util.Scanner;

/**
 * Основний клас програми, який обчислює числа Фібоначчі та перевіряє,
 * чи можна їх представити у формі w^3 - 1.
 *
 * @author Дмитро Петрунів
 * @version 1.0
 */
public class Main {
    /**
     * Головний метод, з якого починається виконання програми.
     * Взаємодіє з користувачем та керує логікою обчислень.
     *
     * @param args Аргументи командного рядка (не використовуються в цій програмі).
     */
    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);
        String vibir;
        do {
            System.out.print("Введіть кількість перших чисел Фібоначчі (2 або більше): ");
            int kilkist;
            while (true) {
                if (skaner.hasNextInt()) {
                    kilkist = skaner.nextInt();
                    skaner.nextLine();
                    if (kilkist >= 2) break;
                    System.out.println("Помилка: має бути 2 або більше.");
                } else {
                    System.out.println("Введіть ціле число!");
                    skaner.next();
                }
            }

            ChysloFibo[] masivFibo = new ChysloFibo[kilkist];
            BigInteger[] fib = new BigInteger[kilkist];
            fib[0] = BigInteger.ZERO;
            fib[1] = BigInteger.ONE;
            masivFibo[0] = new ChysloFibo(0, fib[0]);
            masivFibo[1] = new ChysloFibo(1, fib[1]);
            for (int i = 2; i < kilkist; i++) {
                fib[i] = fib[i-1].add(fib[i-2]);
                masivFibo[i] = new ChysloFibo(i, fib[i]);
            }

            System.out.println("\nПерші " + kilkist + " чисел Фібоначчі:");
            for (ChysloFibo ch : masivFibo) {
                System.out.println("F[" + ch.getIndeks() + "] = " + ch.getZnachennya());
            }

            System.out.println("\nЧисла форми w^3 - 1:");
            int kilkistZbigiv = 0;
            for (ChysloFibo ch : masivFibo) {
                if (ch.isKubMinus1()) {
                    BigInteger w = ChysloFibo.kubKor(ch.getZnachennya().add(BigInteger.ONE));
                    System.out.println("F[" + ch.getIndeks() + "] = " + ch.getZnachennya() + " = " + w + "^3 - 1 (w = " + w + ")");
                    kilkistZbigiv++;
                }
            }
            if (kilkistZbigiv == 0) {
                System.out.println("Немає таких серед перших " + kilkist + " чисел.");
            } else {
                System.out.println("\nЗагальна кількість збігів: " + kilkistZbigiv);
            }

            System.out.print("\nПродовжити? (y - так): ");
            vibir = skaner.nextLine().trim().toLowerCase();
            System.out.println();
        } while ("y".equals(vibir));
    }
}