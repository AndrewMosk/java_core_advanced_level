package Lesson_2.Exceptions;

class MyArray {
    private static int sum;

    static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        checkSize(array);
        return sumElements(array);
    }

    private static void checkSize(String[][] array) throws MyArraySizeException {
        String MyArraySizeExceptionMsg = "Размерность массива не соответствует требуемой 4х4";
        if (array.length !=4){
            throw new MyArraySizeException(MyArraySizeExceptionMsg);
        }else {
            for (String[] row : array) {
                if (row.length !=4){
                    throw new MyArraySizeException(MyArraySizeExceptionMsg);
                }
            }
        }
    }

    private static int sumElements (String[][] array) throws MyArrayDataException{
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j<array[i].length; j++){
                try {
                    sum = sum + Integer.parseInt(array[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataException("Неверные данные в ячейке: ", i, j);
                }
            }
        }
        return sum;
    }
}