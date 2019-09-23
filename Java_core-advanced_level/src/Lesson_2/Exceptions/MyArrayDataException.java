package Lesson_2.Exceptions;

class MyArrayDataException extends NumberFormatException {

    MyArrayDataException(String message, int row, int col) {
        super(message + row + " х " + col);
    }
}