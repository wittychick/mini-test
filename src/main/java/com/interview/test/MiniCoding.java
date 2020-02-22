package com.interview.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MiniCoding {

    private static final Map<String, String> SOURCE = new HashMap<String, String>();

    static {
        SOURCE.clear();
        SOURCE.put("0","");
        SOURCE.put("1","");
        SOURCE.put("2","ABC");
        SOURCE.put("3","DEF");
        SOURCE.put("4","GHI");
        SOURCE.put("5","JKL");
        SOURCE.put("6","MNO");
        SOURCE.put("7","PQRS");
        SOURCE.put("8","TUV");
        SOURCE.put("9","WXYZ");
    }

    public static String getLetters(Integer[] num) {
        List<Integer> integers = Arrays.asList(num);
        Collections.reverse(integers);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.addAll(integers);
        List<String> buffer = new CopyOnWriteArrayList<String>();
        int length = num.length;
        StringBuilder result = new StringBuilder();
        for (String s : deep(queue,buffer)){
            if (s.length() != length){
                continue;
            }
            result.append(s.toLowerCase()).append(" ");
        }
        return result.toString();
    }



    private static List<String> deep(Queue<Integer> queue,List<String> buffer){
        if (queue.isEmpty()) {
            return buffer;
        }
        Integer f = queue.poll();
        if (f == null || f > 99 || f < 0) {
            throw new RuntimeException("error nums");
        }
        String current = "";
        char[] chars;
        if (f < 10) {
            current = SOURCE.get(f.toString());
        } else {
            // split the num
            chars = f.toString().toCharArray();
            int length = chars.length;
            for (int i = 0;i < length;i ++) {
                current = SOURCE.get(String.valueOf(chars[i])) + current;
            }
        }
        chars = current.toCharArray();
        boolean flag = buffer.isEmpty();
        for (int i = 0;i < chars.length;i ++) {
            char c = chars[i];
            if (flag) {
                buffer.add(String.valueOf(c));
                continue;
            }
            for (String s: buffer) {
                buffer.add(c + s);
            }
        }
        return deep(queue,buffer);
    }






}
