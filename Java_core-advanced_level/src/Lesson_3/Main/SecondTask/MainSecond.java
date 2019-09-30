package Lesson_3.Main.SecondTask;

public class MainSecond {
    public static void main(String[] args) {
        PhoneDictionary phoneDictionary = new PhoneDictionary(PhoneDictionary.initDefaultPhoneDictionary());

        try {
            phoneDictionary.add("Tom", "1234567","2225551");
            phoneDictionary.add("Jerry");
        }catch (MyNumberFormatException e){
            e.printStackTrace();
        }


        String[] checkArray = {"Tom","Michael","Jerry","Alan"};
        for (String name: checkArray) {
            System.out.println(phoneDictionary.get(name));
        }
    }
}
