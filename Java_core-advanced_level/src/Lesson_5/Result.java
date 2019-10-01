package Lesson_5;

class Result {
    private double executionTime;
    private float[] resultArray;

    Result(double _executionTime, float[] _resultArray) {
        this.executionTime = _executionTime;
        this.resultArray = _resultArray;
    }

    double getExecutionTime() {
        return executionTime;
    }

    float[] getResultArray() {
        return resultArray.clone();
    }
}
