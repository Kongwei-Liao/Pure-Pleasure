# Lambda表达式

---

Lambda 表达式是 JDK8 的一个新特性，可以取代大部分的匿名内部类，写出更优雅的 Java 代码，尤其在集合的遍历和其他集合操作中，可以极大地优化代码结构。

lambda表达式代码简洁在哪里？？（针对只有一个抽象方法的接口）
1. 既然在形参中写清楚了是什么接口，编译器实际上已经知道了接口名称，没有必要再重新写一遍。
2. 如果接口中只有一个方法，那么要实现的必然是这个方法，那么方法名称编译器也知道，没有必要再重新写一遍。
3. 既然方法的参数和返回值类型在接口中都定义过了，那么编译器依然知道，没有必要再重新写一遍。

lambda表达式是一个可传递的代码块，可以在以后执行一次或多次（将代码像数据一样进行传递，这段代码块会在将来的某个时间调用）。

举例：比如定制比较器完成排序，想要按照字符串长度而不是默认的字典顺序对其进行排序，向sort方法中传递一个定制比较器LengthComparator对象

```java

import java.util.Comparator;
class LengthComparator implements Comparator<String> {

    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}

// ...
Arrays.sort(strings,new LengthComparator());
```
compare方法并不是立即调用。实际上，数组完成排序之前，sort方法会一直调用compare方法，只要元素的顺序不正确就会重新排列元素。
将比较元素所需的代码段放到sort方法中，这段代码将与其余的排序逻辑集成（可能并不打算重新实现其余这一部分逻辑）

---

## lambda表达式的语法格式

基础的语法形式：() -> {}，其中()描述参数列表，-> lambda运算符，读作goes to，而 {} 用来描述方法体。

还有其他简写的形式，在以下示例代码中体现：

```java
//无参无返回
NoReturnNoParam noReturnNoParam = () -> {
    System.out.println("NoReturnNoParam");
};
noReturnNoParam.method();

//一个参数无返回
NoReturnOneParam noReturnOneParam = (int a) -> {
    System.out.println("NoReturnOneParam param:" + a);
};
noReturnOneParam.method(6);

//多个参数无返回
NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
    System.out.println("NoReturnMultiParam param:" + "{" + a +"," + + b +"}");
};
noReturnMultiParam.method(6, 8);

//无参有返回值
ReturnNoParam returnNoParam = () -> {
    System.out.print("ReturnNoParam");
    return 1;
};

int res = returnNoParam.method();
System.out.println("return:" + res);

//一个参数有返回值
ReturnOneParam returnOneParam = (int a) -> {
    System.out.println("ReturnOneParam param:" + a);
    return 1;
};

int res2 = returnOneParam.method(6);
System.out.println("return:" + res2);

//多个参数有返回值
ReturnMultiParam returnMultiParam = (int a, int b) -> {
    System.out.println("ReturnMultiParam param:" + "{" + a + "," + b +"}");
    return 1;
};

int res3 = returnMultiParam.method(6, 8);
System.out.println("return:" + res3);
```

```java
//1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
NoReturnMultiParam lambda1 = (a, b) -> {
    System.out.println("简化参数类型");
};
lambda1.method(1, 2);

//2.简化参数小括号，如果只有一个参数则可以省略参数小括号
NoReturnOneParam lambda2 = a -> {
    System.out.println("简化参数小括号");
};
lambda2.method(1);

//3.简化方法体大括号，如果方法条只有一条语句，则可以省略方法体大括号
NoReturnNoParam lambda3 = () -> System.out.println("简化方法体大括号");
lambda3.method();

//4.如果方法体只有一条语句，并且是 return 语句，则可以省略方法体大括号
ReturnOneParam lambda4 = a -> a + 3;
System.out.println(lambda4.method(5));

//5.方法引用
// ReturnMultiParam lambda5 = (a, b) -> a + b;
ReturnMultiParam lambda5 = Integer::sum;
System.out.println(lambda5.method(1, 1));

```

---

## 函数式接口

### 对接口的要求

虽然使用 Lambda 表达式可以对某些接口进行简单的实现，但并不是所有的接口都可以使用 Lambda 表达式来实现。Lambda 规定接口中只能有一个需要被实现的方
法，不是规定接口中只能有一个方法。

问题：
1. 为什么函数式接口必须要又一个抽象方法？
2. 不是接口中所有的方法都是抽象的吗？？

>实际上，接口完全有可能重新声明Object类的方法，如toString或clone，这些声明会让方法不再是抽象的。（JavaAPI中一些接口会在、重新声明Object的方法
>来附加javadoc注释。Comparator API就是这样的一个例子。）此外Java 8后，可以在接口中声明非抽象方法（默认方法）

JDK提供了大量的内置函数式接口供我们使用，使得 Lambda 表达式的运用更加方便、高效。    java.util.function包下

jdk 8中有另一个新特性：default， 被 default 修饰的方法会有默认实现，不是必须被实现的方法，所以不影响 Lambda 表达式的使用。

### @FunctionalInterface
修饰函数式接口的，要求接口中的抽象方法只有一个。 这个注解往往会和 lambda 表达式一起出现。

---

## 方法引用

例如，表达式System.out::println是一个方法的引用（Method Reference），其指示编译器生成一个函数式接口的实例，覆盖这个接口中的抽象方法来调用给定
的方法。

类似于Lambda表达式，方法的引用并不是一个对象。不过，为一个类型为函数式接口的变量赋值时会生成一个对象。

>有时候我们不是必须要自己重：写某个匿名内部类的方法，我们可以可以利用 lambda表达式的接口快速指向一个已经被实现的方法。

### 语法

>方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象​  

上面的System.out就是一个PrintStream类的实例，该类中有10个重载的println方法。编译器会根据上下文确定使用哪一个println方法。

## 构造器引用

构造器应用与方法引用类似，只不过方法名为new。比如Person::new是Person构造器的一个引用。引用的是哪一具体的构造器，还是取决于上下文。

一般我们需要声明接口，该接口作为对象的生成器，通过：

>类名::new 的方式来实例化对象，然后调用方法返回对象。


## lambda表达式常见的其他用法

### lambda 表达式创建线程

我们以往都是通过创建 Thread 对象，然后通过匿名内部类重写 run() 方法，一提到匿名内部类我们就应该想到可以使用 lambda 表达式来简化线程的创建过程。

### 遍历集合

我们可以调用集合的 public void forEach(Consumer<? super E> action) 方法，通过 lambda 表达式的方式遍历集合中的元素。以下是 Consumer 接
口的方法以及遍历集合的操作。Consumer 接口是 jdk 为我们提供的一个函数式接口。

```java
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
        //....
    }
```

### 删除集合中的某个元素

我们通过public boolean removeIf(Predicate<? super E> filter)方法来删除集合中的某个元素，Predicate 也是 jdk 为我们提供的一个函数式接口，
可以简化程序的编写。

集合内元素的排序

在以前我们若要为集合内的元素排序，就必须调用 sort 方法，传入比较器匿名内部类重写 compare 方法，我们现在可以使用 lambda 表达式来简化代码。



## 变量作用域     （Lambda 表达式中的闭包问题）

有时候我们可能希望能够在lambda表达式中访问外围方法或类中的变量。

lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。

```java
public static void repeatMessage(String text, int delay) {
    ActionListener listener = event -> {
        System.out.println(text);
        Toolkit.getDefaultToolkit().beep();
    };
    new Timer(delay, listener).start();
}
```
>这样的一个调用：repreatMessage("hello", listener); // prints hello every 1000 millisecond

>这个lambda表达式中的变量text不是在lambda表达式中定义的，考虑其中的一个不明显的问题：
lambda表达式的代码可能在repeatMessage调用返回很久之后才运行，而那时这个参数变量已经不存在了，此时在使用text可能产生许多问题。
>

lambda表达式的三个部分：
>1. 一个代码块
>2. 参数
>3. 自由变量的值，这里指的是非参数而且不在lambda表达式代码块中定义的变量

>自由变量的值在lambda表达式内部或者是外部的改变都是不合法的。
>规则：lambda表达式中捕获的变量必须实际上是**事实最终变量（Effectivelly final）**
>事实最终变量：就是这个变量初始化之后便不会在为其重新赋值。


