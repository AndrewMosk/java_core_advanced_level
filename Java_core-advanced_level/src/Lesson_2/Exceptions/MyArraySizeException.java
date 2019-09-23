package Lesson_2.Exceptions;

class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    MyArraySizeException(String message) {
        super(message);
    }
}