package Lesson_5;

import java.util.Arrays;

public class MainThread {
    static final int size = 10000000;
    static final int threadNumber = 4;
    static final int h = size / threadNumber;

    public static void main(String[] args) {
        float[] originalArray = initArray();
        System.out.println("Вычисления в один поток завершились за " +  singleThread(originalArray));

        System.out.println("Вычисления в несколько потоков завершились за " +  multiThread(originalArray));
    }

    private static double singleThread(float[] originalArray){
        long startTime = System.currentTimeMillis();

        for (int i=0; i<originalArray.length; i++){
            originalArray[i] = (float)(originalArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long finishTime = System.currentTimeMillis();
        double executionTime = ((double)finishTime-(double)startTime)/1000.0;

        return executionTime;
    }

    private static double multiThread1(float[] originalArray){
        //коллеккция с ключом. массив таких коллекций?
        long startTime = System.currentTimeMillis();

        float[] array1 = new float[h];
        float[] array2 = new float[h];
        System.arraycopy(originalArray, 0, array1, 0, h);
        System.arraycopy(originalArray, h, array2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculateArray(array1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculateArray(array2);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(array1, 0, originalArray, 0, h);
        System.arraycopy(array1, 0, originalArray, h, h);

        long finishTime = System.currentTimeMillis();
        double executionTime = ((double)finishTime-(double)startTime)/1000.0;

        return executionTime;
    }




    private static double multiThread(float[] originalArray){
        long startTime = System.currentTimeMillis();

        float[] array1 = new float[h];
        float[] array2 = new float[h];
        System.arraycopy(originalArray, 0, array1, 0, h);
        System.arraycopy(originalArray, h, array2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculateArray(array1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculateArray(array2);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(array1, 0, originalArray, 0, h);
        System.arraycopy(array1, 0, originalArray, h, h);

        long finishTime = System.currentTimeMillis();
        double executionTime = ((double)finishTime-(double)startTime)/1000.0;

        return executionTime;
    }

    private static float[] calculateArray(float[] array){
        for (int i=0; i<array.length; i++){
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return array;
    }

    private static float[] initArray(){
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        return arr;
    }
}
