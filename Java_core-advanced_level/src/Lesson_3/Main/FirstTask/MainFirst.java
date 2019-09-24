package Lesson_3.Main.FirstTask;

import java.util.HashMap;
import java.util.Map;

public class MainFirst {
    public static void main(String[] args) {
        String[] stringsArray = {"field", "south", "figure", "garage", "south", "field", "birth", "calm", "under", "bell", "yard", "vital", "south", "break", "break", "change"};
        Map<String, Integer> hm = new HashMap<>();

        for (String string: stringsArray) {
            Integer current = hm.get(string);
            hm.put(string,current == null ? 1: current + 1);
        }
        System.out.println(hm);
    }
}
