package topic01.question10;

public class Son extends Father {
    private String name = "son";

//    public String getName() {
//        return name;
//    }

    public static void main(String[] args) {

        Son son = new Son();
        System.out.println(son.getName());
    }
}
