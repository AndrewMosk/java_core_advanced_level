package Lesson_3.Main.SecondTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class PhoneDictionary {
    private static int phoneNumberLength = 7;
    private HashMap<String, ArrayList<String>> phoneNumbers;

    PhoneDictionary(HashMap<String, ArrayList<String>> _phoneNumbers) {
        this.phoneNumbers = _phoneNumbers;
    }

    HashMap<String, ArrayList<String>> get(String name) {
        HashMap<String, ArrayList<String>> person = new HashMap<>();

        ArrayList phonesArrayList = phoneNumbers.getOrDefault(name, new ArrayList<>());
        if (phonesArrayList.size() == 0) name = "Имя " + name + " в справочнике не найдено.";

        person.put(name, phonesArrayList);
        return person;
    }

    void add(String name, String ... phonesArray) throws MyNumberFormatException {
        //проверяю номер телефона на наличие недопустимых символов
        checkPhonesArray(name, phonesArray);
        ArrayList<String> phonesArrayList = new ArrayList<>(Arrays.asList(phonesArray));
        /*чтобы отличить существующего пользователя без номера телефона от ненайденного (когда в get() подается пользователь, не заведенный в справочник)
        пользователю без номера (пришедшим сюда с пустым массивов номеров) в список номеров добавляю один элемент: "-" */
        if (phonesArrayList.size()==0) phonesArrayList.add("-");

        phoneNumbers.put(name,phonesArrayList);
    }

    private void checkPhonesArray(String name, String[] phonesArray) throws MyNumberFormatException{
        for (String phone: phonesArray) {
            try{
                int a = Integer.parseInt(phone);
            }catch (NumberFormatException e){
                throw new MyNumberFormatException(name + " в номере телефона: " + phone + " содержит недопустимые символы");
            }
        }
    }

    static HashMap<String, ArrayList<String>> initDefaultPhoneDictionary(){
        String[] namesArray = {"John", "Joseph", "Michael", "Robert"};
        HashMap<String, ArrayList<String>> defaultPhoneDictionary = new HashMap<>();

        for (String name: namesArray) {
            //случайным образом задаю количество телефонных номеров у пользователя
            int count = (int) (1 + Math.random() * 3);
            //генерирую номер телефона
            ArrayList<String> phonesArrayList = generateNumber(count);
            defaultPhoneDictionary.put(name,phonesArrayList);
        }
        return defaultPhoneDictionary;
    }

    private static  ArrayList<String> generateNumber(int length){
        ArrayList<String> randomPhoneNumberArrayList = new ArrayList<String>();

        for (int i = 0; i<length; i++) {
            String phone = "";
            for (int j = 0; j < phoneNumberLength; j++) {
                String number = String.valueOf((int) (Math.random() * 9));
                phone += number;
            }
            randomPhoneNumberArrayList.add(phone);
        }
        return randomPhoneNumberArrayList;
    }
}
