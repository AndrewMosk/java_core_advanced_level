package Lesson_2.Exceptions;

class MyArrayDataException extends Exception {
    private int row;
    private int col;

    MyArrayDataException(String message, int _row, int _col) {
        super(message + _row + " х " + _col);
        this.row = _row;
        this.col = _col;
    }
}
