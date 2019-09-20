package Lesson_2.Exceptions;

public class MainExceptions {
    public static void main(String[] args) {
        int [][] twoDimArray = {{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {2,9,3,0}};

        try {
            System.out.println("Сумма элементов массива равна: " + MyArray.sumArray(twoDimArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}
