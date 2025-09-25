import java.math.BigInteger;

/**
 * Клас ChysloFibo представляє число Фібоначчі
 * з його індексом та значенням. Призначений для
 * інкапсуляції даних.
 *
 * @author Ваш_викладач
 * @version 1.0
 */
public class ChysloFibo {
    /**
     * Індекс числа Фібоначчі (наприклад, F[10]).
     */
    private final int indeks;

    /**
     * Значення числа Фібоначчі, представлене як BigInteger для підтримки великих чисел.
     */
    private final BigInteger znachennya;

    /**
     * Конструктор для створення об'єкта ChysloFibo.
     *
     * @param indeks Індекс числа Фібоначчі.
     * @param znachennya Значення числа Фібоначчі.
     */
    public ChysloFibo(int indeks, BigInteger znachennya) {
        this.indeks = indeks;
        this.znachennya = znachennya;
    }

    /**
     * Повертає індекс числа Фібоначчі.
     *
     * @return Індекс числа Фібоначчі.
     */
    public int getIndeks() {
        return indeks;
    }

    /**
     * Повертає значення числа Фібоначчі.
     *
     * @return Значення числа Фібоначчі.
     */
    public BigInteger getZnachennya() {
        return znachennya;
    }

    /**
     * Перевіряє, чи можна поточне число Фібоначчі представити у формі w^3 - 1.
     *
     * @return true, якщо число відповідає критерію, false - в іншому випадку.
     */
    public boolean isKubMinus1() {
        BigInteger plus1 = znachennya.add(BigInteger.ONE);
        BigInteger w = kubKor(plus1);
        return w.pow(3).equals(plus1);
    }

    /**
     * Обчислює цілочисельний кубічний корінь з числа BigInteger за допомогою
     * алгоритму бінарного пошуку.
     *
     * @param x Число, з якого потрібно витягти корінь.
     * @return Цілочисельний кубічний корінь з x.
     * @throws IllegalArgumentException якщо x є від'ємним.
     */
    public static BigInteger kubKor(BigInteger x) {
        if (x.signum() < 0) throw new IllegalArgumentException("Від'ємне не підтримується");
        if (x.compareTo(BigInteger.ONE) <= 0) return x;
        BigInteger low = BigInteger.ZERO;
        BigInteger high = BigInteger.ONE;
        while (high.pow(3).compareTo(x) < 0) {
            high = high.shiftLeft(1);
        }
        while (high.subtract(low).compareTo(BigInteger.ONE) > 0) {
            BigInteger mid = low.add(high).shiftRight(1);
            BigInteger mid3 = mid.pow(3);
            int cmp = mid3.compareTo(x);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }
}