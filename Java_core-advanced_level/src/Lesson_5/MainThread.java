package Lesson_5;

import java.util.*;

public class MainThread {
    private static final int size = 10000000;
    private static final int threadNumber = 4;
    private static final int h = size / threadNumber;

    public static void main(String[] args) {
        Result resultSingleThread = singleThread(initArray());
        Result resultMultiThread = multiThread(initArray());

        if (Arrays.equals(resultSingleThread.getResultArray(),resultMultiThread.getResultArray())) {
            System.out.println("Вычисления в один поток завершились за " + resultSingleThread.getExecutionTime() + " сек.");
            System.out.println("Вычисления в " + threadNumber + " потока(ов) завершились за " + resultMultiThread.getExecutionTime() + " сек.");
        }else System.out.println("Упс... что-то пошло не так и массивы не равны :-(");
    }

    private static Result singleThread(float[] originalArray){
        long startTime = System.currentTimeMillis();

        for (int i=0; i<originalArray.length; i++){
            originalArray[i] = (float)(originalArray[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }

        long finishTime = System.currentTimeMillis();
        double executionTime = ((double)finishTime-(double)startTime)/1000.0;

        return new Result(executionTime, originalArray);
    }

    private static Result multiThread(float[] originalArray){
        long startTime = System.currentTimeMillis();

        //двумерный массив для приема вычислений
        float[][] outputArrays = new float[threadNumber][h];

        HashSet<Thread> threadHashSet = new HashSet<>();

        //делю массив, создаю потоки
        for (int i=0, startPos = 0; i<threadNumber; i++, startPos += h){
            float[] array = outputArrays[i]; //массив приемник
            System.arraycopy(originalArray, startPos, array, 0, h);

            int finalStartPos = startPos;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() { calculateArray(array, finalStartPos); }
            });
            threadHashSet.add(t);
        }
        //старт потоков
        Iterator<Thread> iteratorStart = threadHashSet.iterator();
        while (iteratorStart.hasNext()){
            Thread t = iteratorStart.next();
            t.start();
        }
        //join потоков
        Iterator<Thread> iteratorJoin = threadHashSet.iterator();
        try {
            while (iteratorJoin.hasNext()){
                Thread t = iteratorJoin.next();
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //обратная склейка
        for (int i =0, startPos = 0; i<threadNumber; i++, startPos +=h){
            System.arraycopy(outputArrays[i], 0, originalArray, startPos, h);

        }
        long finishTime = System.currentTimeMillis();
        double executionTime = ((double)finishTime-(double)startTime)/1000.0;

        return new Result(executionTime, originalArray);
    }

    private static void calculateArray(float[] array, int startPos){
        for (int i =0; i<array.length; i++, startPos++){
            array[i] = (float)(array[i] * Math.sin(0.2f + startPos / 5.0) * Math.cos(0.2f + startPos / 5.0) * Math.cos(0.4f + startPos / 2.0));
        }
    }

    private static float[] initArray(){
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);

        return array;
    }
}
