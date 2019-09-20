package Lesson_2.Exceptions;

class MyArray {

    static int sumArray(int[][] array) throws MyArraySizeException, MyArrayDataException {
        checkSize(array);
        checkElements(array);
        return countSum(array);
    }

    private static void checkSize(int[][] array) throws MyArraySizeException {
        String MyArraySizeExceptionMsg = "Размерность массива не соответствует требуемой 4х4";
        if (array.length !=4){
            throw new MyArraySizeException(MyArraySizeExceptionMsg);
        }else {
            for (int[] row : array) {
                if (row.length !=4){
                    throw new MyArraySizeException(MyArraySizeExceptionMsg);
                }
            }
        }
    }

    private static void checkElements (int[][] array) throws MyArrayDataException{
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j<array[i].length; j++){
                try {
                    int i1 = new Integer(array[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataException("Неверные данные в ячейке: ", i, j);
                }
            }
        }
    }

    private static int countSum(int[][] array){
        int sum = 0;




        return sum;
    }
}
