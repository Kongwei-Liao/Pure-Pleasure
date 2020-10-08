package com.example.javase;

public class Display {

    private int deft;
    private String str;

    public int getDeft() {
        return deft;
    }

    public void setDeft(int deft) {
        this.deft = deft;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public static void main(String[] args) {

        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;

        float f = 5.0f;     // 5.0 是 double 型，不会向 float 型做隐式转换
        double d = 6.0;

        char c = '7';
        boolean bool = true | false;

        Display display = new Display();

        String str1 = "com";
        String str2 = new String("com");
        String str3 = ++b + s-- + str1 + --i + l + f + d + c + bool;



    }
}
