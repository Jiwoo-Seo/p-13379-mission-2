package com.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {
    public static int run(String s) {
        //괄호 있을때
        int barOpen = s.lastIndexOf('('); // 가장 앞 괄호위치
        if(barOpen>=0){
            int barClose = s.indexOf(')',barOpen);
            String barCal = s.substring(barOpen+1,barClose);
            int barVal = run(barCal);
            // 괄호를 계산한 값으로
            String newVal = s.substring(0,barOpen)+barVal+s.substring(barClose+1);
            // 재귀
            return run(newVal);
        }
        
        //사칙연산 계산
        String[] sBits = s.split(" ");
        List<String> cal = new ArrayList<>(Arrays.asList(sBits));
        for (int i = 1; i < cal.size(); i += 2) {
            String op = cal.get(i);
            // 곱,나눗셈 먼저계산
            if (op.equals("*") || op.equals("/")) {
                int result = op.equals("*") ? Integer.parseInt(cal.get(i - 1)) * Integer.parseInt(cal.get(i + 1)) : Integer.parseInt(cal.get(i - 1)) / Integer.parseInt(cal.get(i + 1));
                cal.set(i - 1, String.valueOf(result));
                cal.remove(i);
                cal.remove(i);
                i -= 2; // 계산하고 앞으로 땡기기
            }

        }
        int answer = Integer.parseInt(cal.get(0));
        for (int i = 1; i < cal.size() - 1; i += 2) {
            String op = cal.get(i);
            if (op.equals("-")) {
                answer -= Integer.parseInt(cal.get(i + 1));
            } else if (op.equals("+")) {
                answer += Integer.parseInt(cal.get(i + 1));
            }
        }
        return answer;
    }
}
