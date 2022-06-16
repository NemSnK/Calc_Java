package org.example;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.Scanner;

public class Main {

    enum RomanNumeral{
        I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"),
        VII("7"), VIII("8"), IX("9"), X("10"), XI("11"), XII("12"),
        XIII("13"), XIV("14"), XV("15"), XVI("16"), XVII("17"),
        XVIII("18"), XIX("19"), XX("20"), XXI("21"), XXII("22"),
        XXIII("23"), XXIV("24"), XXV("25"), XXVI("26"), XXVII("27"),
        XXVIII("28"), XXIX("29"), XXX("30"), XXXI("31"), XXXII("32"),
        XXXIII("33"), XXXIV("34"), XXXV("35"), XXXVI("36"), XXXVII("37"),
        XXXVIII("38"), XXXIX("39"), XL("40"), XLI("41"), XLII("42"),
        XLIII("43"), XLIV("44"), XLV("45"), XLVI("46"), XLVII("47"),
        XLVIII("48"), XLIX("49"), L("50"), LI("51"), LII("52"),
        LIII("53"), LIV("54"), LV("55"), LVI("56"), LVII("57"),
        LVIII("58"), LIX("59"), LX("60"), LXI("61"), LXII("62"),
        LXIII("63"), LXIV("64"), LXV("65"), LXVI("66"), LXVII("67"),
        LXVIII("68"), LXIX("69"), LXX("70"), LXXI("71"), LXXII("72"),
        LXXIII("73"), LXXIV("74"), LXXV("75"), LXXVI("76"), LXXVII("77"),
        LXXVIII("78"), LXXIX("79"), LXXX("80"), LXXXI("81"), LXXXII("82"),
        LXXXIII("83"), LXXXIV("84"), LXXXV("85"), LXXXVI("86"), LXXXVII("87"),
        LXXXVIII("88"), LXXXIX("89"), XC("90"), XCI("91"), XCII("92"),
        XCIII("93"), XCIV("94"), XCV("95"), XCVI("96"), XCVII("97"),
        XCVIII("98"), XCIX("99"), C("100");
        public String translation;
        RomanNumeral(String translation){
            this.translation = translation;
        };
    }

    public static void main(String[] args) {
        System.out.println("Введите выражение:");
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }

    private static String calc(String input) {
        int res = 0;
        int a;
        int b;
        boolean isRoman=false;
        String operator;
        //Проверяем, что строка не пустая
        if(input.length()<3)return "Строка не является математической операцией";
       // Удаляем пробелы
        input=input.replaceAll("\\s","");
        operator=Operator(input);
        //Проверяем соответствие формату выражения
        if(operator==" ")return "Строка должна содержать два операнда и один оператор (+, -, /, *)";
        if(operator.charAt(0)==input.charAt(0))return "Строка должна содержать два операнда и один оператор (+, -, /, *)";
        //if(operator=="+" | operator=="*") {
            String[] exp = input.split("\\"+operator);
        /*}else {
            String[] exp = input.split(operator);
        }*/
        if(exp.length!=2)return "Строка должна содержать два операнда и один оператор (+, -, /, *)";
        try{
            a=Integer.parseInt(exp[0]);
        }catch (NumberFormatException e){
            a=RomToNum(exp[0]);
            if(a==0)return "неверный операнд";
            isRoman=true;
        }
        try{
            b=Integer.parseInt(exp[1]);
            if(isRoman==true)return "используются одновременно разные системы счисления";
        }catch (NumberFormatException e){
            b=RomToNum(exp[1]);
            if(b==0)return "неверный операнд";
            if(isRoman==false)return "используются одновременно разные системы счисления";
        }
        if(a<1|a>10)return "числа должны быть от 1 до 10";
        if(b<1|b>10)return "числа должны быть от 1 до 10";

        switch (operator){
           case "+"->{
               res = a + b;
           }
           case "-"->{
               res = a - b;
               if(res<1&&isRoman==true) return "в римской системе нет отрицательных чисел и нуля";
           }
           case "*"->{
               res = a * b;
           }
           case "/"->{
               if(b==0) return "на ноль делить нельзя";
               res = a / b;
           }
       }
       if(isRoman==true)return NumToRom(res);
       return input=String.valueOf(res);
    }

    private static String Operator(String input){
        String operator=" ";
        int error=0;
        if(input.indexOf("+")!=-1){
            operator="+";
            error++;
        }
        if(input.indexOf("-")!=-1){
            operator="-";
            error++;
        }
        if(input.indexOf("*")!=-1){
            operator="*";
            error++;
        }
        if(input.indexOf("/")!=-1){
            operator="/";
            error++;
        }
        if(error==1)return operator;
        return " ";
    }
    private static int RomToNum(String str){
        RomanNumeral[] rnums= RomanNumeral.values();
        for(RomanNumeral rnum: rnums){
            if (str.equals(rnum.toString())){
                return (rnum.ordinal()+1);

            }
        }
        return 0;
    }
    private static String NumToRom(int res){
        RomanNumeral[] rnums= RomanNumeral.values();
        for(RomanNumeral rnum: rnums){
            if (res == rnum.ordinal()+1){
                return rnum.toString();
            }
        }
        return "";
    }
}
