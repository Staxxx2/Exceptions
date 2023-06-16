// Здесь, если длины входных массивов не равны, мы выбрасываем исключение RuntimeException 
// с сообщением о том, что длины не равны. Точно так же, если какой-либо элемент второго 
// массива равен нулю, мы выбрасываем исключение RuntimeException, указывающее, 
// что деление на ноль не разрешено.

public class Exception1 {

    public static double[] divideArrays(int[] array1, int[] array2) throws RuntimeException {
    if (array1.length != array2.length) {
        throw new RuntimeException("Длины массивов не равны");
    }
    double[] result = new double[array1.length];
    for (int i = 0; i < array1.length; i++) {
        if (array2[i] == 0) {
            throw new RuntimeException("Деление на ноль запрещено!");
        }
        result[i] = (double) array1[i] / array2[i];
    }
    return result;
}



}
