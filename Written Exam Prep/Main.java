import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16);

        // 17 January 2019 MIE

       /* 2.(3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)eliminates all the numbers which are not multiple of 4;
        b)transform the result into a list*/

        List<Integer> result = numbers.stream().filter(n -> (n % 4 == 0)).collect(Collectors.toList());
        //System.out.println(result);

        /*
        1)  Compare static vs non-static methods in Java.

        Static methods are shared by all class instances.
        A static method cannot use those fields or methods which are not static. It can use or call only the static members.
        A static method can be called without an instance or object of the class it belongs to.
        A static method cannot be overridden.
        -compile time, on ram
        -belongs to class

        Non-static methods can access any static method and static variable, but they have to be called using an
        instance/object of the class.
        -can override,happens on runtime,not on ram
        -belong to OBJECT of class

         */

        /*
        3) What is a Java thread.

        Threads are the units that are scheduled by the system for executing on the processor.
        A thread is a particular execution path of a process.
        Threads share the process's resources, including memory and open files.
        This makes for efficient, but potentially problematic communication.

        are called lightweight processes
        -creating a new thread requires fewer resources than creating a new process.
        -threads exist within a process —every process has at least one.
    -threads share the process's resources, including memory and open files. This makes for efficient, but potentially problematic, communication
    -a thread is a particular execution path of a process.
    -one allows multiple threads to read and write the same memory (no process can directly access the memory of another process).
    -when one thread modifies a process resource, the change is immediately visible to sibling threads.


         */


        // Erasmus 2020

        /*
        2.(3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)eliminates all the numbers which are not multiple of  4;
        b)transform each remaining number into its succesor (eg. 4 is transformed into 5);
        c)compute the sum modulo 2 of the remaining numbers (eg. (9 +5) mod 2=0)
        d)transform the result into a list
        */

        //List <Integer> result2=numbers.stream().filter(n->(n%4==0)).map(n->n+1).reduce(0,(s,n)->(s+n)%2) // Will return a number... integer... not an OPTIONAL number!!

        List<Integer> result2 = numbers.stream()
                .filter(n -> (n % 4 == 0))
                .map(n -> n + 1)
                .reduce((number1,
                         number2) -> (number1 + number2) %
                        2).stream().collect(Collectors.toList());

        int resulttt=numbers.stream()
                .filter(n -> (n % 4 == 0))
                .map(n -> n + 1)
                .reduce((number1,
                         number2) -> (number1 + number2) %
                        2).get();
        //System.out.println(resulttt);
        //System.out.println(result2);

        //1.(3p). Compare static vs non-static methods in Java. Solved above


        /*
        3.(3p). What is a Semaphore in Java.
        A Semaphore is a synchronization method that has two uses:
        guards a critical section against the entry of more than N threads at a time,
        where N is the number of permits the semaphore was initialized with and sends signals between two threads.
        So the semaphore controls access to a shared resource through the use of a counter.
        If the counter is greater than 0, the access is allowed.

        In both binary and non-binary semaphores,
        when a thread releases the resource,
         the semaphore's value is incremented to indicate that the resource is available again.

         A binary semaphore is a type of semaphore that can only take on two values: 0 or 1.
          When the value of a binary semaphore is 0, it indicates that the shared resource it controls
          is not currently available, and any thread attempting to acquire the resource will be blocked until
          the semaphore's value becomes 1.
          When the value of a binary semaphore is 1, it indicates that the shared resource is available, and
          a thread can acquire the resource by decrementing the semaphore's value to 0.

        In contrast, a non-binary semaphore can take on any positive integer value greater than or equal to 0. When the value of a non-binary semaphore is greater than 0, it indicates that the shared resource is available and a thread can acquire the resource by decrementing the semaphore's value by 1. However, if multiple threads attempt to acquire the resource at the same time, the semaphore's value will be decremented multiple times, potentially leading to a negative value if the semaphore's value is not incremented by other threads releasing the resource.
        When the value of non-binary semaphore is 0, it indicates that the shared resource is not currently available, and any thread attempting to acquire the resource will be blocked until the semaphore's value becomes greater than 0.
          */


        //****2020 24 Ianuarie

        //1 (1p). Compare interfaces vs. abstract classes in Java.

        /*
        The interface is a blueprint that can be used to implement a class.
        It does not contain any concrete methods (methods that have code).
        All the methods are public and abstract and a class that implements an interface should implement all the methods
         of the interface.
         You can't create an instance of an interface.

         Abstract classes should have at least one abstract method (without a body) and can have multiple concrete methods.
         All the classes that inherit the abstract class should implement the abstract methods.
         Abstract classes cannot be instantiated.
         The interface can't contain data fields, but the abstract class can.
         !!!A class can implement multiple interfaces, but inherits just one abstract class!!!.

         Abstract Class = 1  abstract method at minimum =-> abstract method =no code generated,it will be done by the subclass
         In general, if you want to define a common interface for unrelated classes to implement,
          you should use an interface.
          If you want to provide a common implementation for subclasses to inherit from and build upon,
          you should use an abstract class.
         */

        /*
        2 (3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)eliminates all the numbers which are not multiple of  3 or  multiple of 7;
        b)transform each remaining number into its predecessor (eg. 3 is transformed into 2);
        c)compute the sum modulo 5 of the remaining numbers (eg. (2 +4) mod 5=1)*/

        //System.out.println(numbers.stream().filter(n->(n%3==0) || (n%7==0)).map(n->n-1).reduce((n1,n2)->(n1+n2)%5).get());

        /*
        4 (2p). What is an Atomic Variable in Java.

        -better performance than locks

        An atomic variable is an int, long, boolean or object reference that can be automatically updated,
        ensuring synchronization of the threads in the program.
        It is better to use an atomic variable over a lock in case only a single mutable variable needs to be
        changed concurrently.

        An example of an atomic variable in Java is the AtomicInteger class, which is a wrapper class for the int primitive type.
         It provides atomic operations, such as getAndIncrement() and compareAndSet(), that can be used to safely access
         and modify the variable from multiple threads without the need for explicit synchronization.
         */


        /*
        3 (3p). Given the following four classes in Java:
        class A implements D{...}   class B extends A implements D {...}
        class C extends A implements D {...}   interface D {...}

        class Amain{

            D  method1(ArrayList<...> list) {  if list.isEmpty() return null;
                                   else return list.get(1);}

            void method2(ArrayList<...>  list, C elem) {  list.add(elem);}

            void method3(C elem){
                ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());listA.add(new A());
                ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());listB.add(new B());
                ArrayList<C> listC = new ArrayList<C>(); listC.add(new C()); listC.add(new C());
                this.method1(listA); this.method1(listB); this.method1(listC);
                this.method2(listA,elem); this.method2(listB,elem); this.method2(listC,elem);
            }
        }
        Compute the most specific signatures for the class Amain methods (method1 and method2)
        such that the entire program is correct.
                If it is not possible to find the types justify your answer.
                Discuss line by line the correctness of the above program.

        Answer:
        The most specific signatures for the class Amain methods (method1 and method2) such that the entire program is correct would be:

        D method1(ArrayList<? extends D> list) {  if list.isEmpty() return null;

                                   else return list.get(1);}    //  READ OPERATION <=D

        ArrayList<? extends D> you can pass in ArrayList objects that contain objects of type D, A, B, or C. This allows for greater reusability of the method1 method, as it can handle more types of input.

        void method2(ArrayList<? super C>  list, C elem) {  list.add(elem);} // Write operation >=C

        object that contain C,B,A,D !!!

        --nah
        Why: In this program, A, B, and C classes all implement the interface D,
        so they can be considered subtypes of D and can be stored in an ArrayList<D>.
        This is why the most specific signature for method1 is D method1(ArrayList<D> list),
        so that it can take any ArrayList containing objects of type D or its subtypes.

        The method2 takes two arguments, an ArrayList<D> and a C object.
        The C class implements the interface D, so it can be considered a subtype of D, so it can be stored in an ArrayList<D>.
         This is why the most specific signature for method2 is void method2(ArrayList<D> list, C elem), so that it can take any ArrayList containing objects of type D or its subtypes and a C object.

         Regarding the correctness of the program, the following lines are correct:

         ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());listA.add(new A());
         ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());listB.add(new B());
         ArrayList<C> listC = new ArrayList<C>(); listC.add(new C()); listC.add(new C());

         this.method1(listA); this.method1(listB); this.method1(listC);
         this.method2(listA,elem); this.method2(listB,elem); this.method2(listC,elem);

         These lines create `ArrayList` objects of type `A`, `B`, and `C` respectively and
         add two objects of the corresponding class to each list.
         This is correct because `A`, `B`, and `C` are all subtypes of `D` and can be stored in an `ArrayList<D>`.

    */

        //** 2020 23 ianuarie
        /*

        1 (1p). Compare overriding vs. overloading in Java.
        Overriding is when the method signature is the same in the superclass and the child class.
        The method call is determined at the runtime based on the object type.
        Overloading is when two or more methods in the same class have the same name but different parameters.
        The method call is determined at the compile time.



        2 (3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)keep only the numbers which are multiple of  5 or multiple of 2;
        b)transform each remaining number into a string, that consists of a prefix "N", the number and the suffix "R"
        (eg. 5 is transformed into "N5R")
        c)concatenate all the strings
        */

        String result4 = numbers.stream().filter(n -> (n % 5 == 0) || (n % 2 == 0)).map(n -> String.format("N%dR", n)).reduce("", (s1, s2) -> s1 + s2);
        //String result5=numbers.stream().filter(n -> n%3 ==0 || n%2 == 0).map(n -> "A"+(n+1)+"B").reduce((s1,s2) -> s1+s2); // returns an Optional -> use .get()
        String result5 = numbers.stream().filter(n -> n % 5 == 0 || n % 2 == 0).map(n -> "N" + n + "R").reduce((s1, s2) -> s1 + s2).get();

        //System.out.println(result4);
        //System.out.println(result5);

        /*

        4 (2p). What is a CountDownLatch in Java.
        A CountDown Latch is a kind of synchronizer which allows one thread to wait for one or more threads
        before starting processing.
        It is initialized with a given count, which is decremented by calls to the countDown() method.
        Threads waiting for this count to reach zero can call one of the await() methods.
        Calling await() blocks the thread until the count reaches 0.

        -can block a thread until other threads have completed a given task
        wait for all threads to count down to 0; counter cannot be reset

         3 (3p). Given the following four classes in Java:

        class A {...}   class B extends A {...}   class C extends B {...}

        class Amain{

        A  method1(ArrayList<...> list) {  if list.isEmpty() return null;
                                   else return list.get(1);}

        void method2(ArrayList<...>  list, C elem) {  list.add(elem);}

        void method3(C elem){
            ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());
            ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());
            ArrayList<C> listC = new ArrayList<C>(); listC.add(new C());
            this.method1(listA); this.method1(listB); this.method1(listC);
            this.method2(listA,elem); this.method2(listB,elem); this.method2(listC,elem);
        }
    }
        Compute the most specific signatures for the class Amain methods (method1 and method2) such that the entire program is correct.
        If it is not possible to find the types justify your answer.
        Discuss line by line the correctness of the above program.

        The most specific signatures for the class Amain methods (method1 and method2) such that the entire program is correct would be:

                               <A>
        A method1(ArrayList<? extends A> list) {  if list.isEmpty() return null;

                                   else return list.get(1);} // READ OPERATION -> upperbound -> extend .. <=A

        void method2(ArrayList<A>  list, C elem) {  list.add(elem);} // WRITE OPERATION -> lower bound-> super ... >=C
                    ArrayList<? super C>
                     the ArrayList<A> only knows that it contains elements of type A, and it doesn't know that they are C type objects. So when you try to access the specific methods of class C, the compiler will not find it in class A and it will not allow you to access it.

         The method1 accepts an ArrayList of any type that is a subtype of A, so it can accept ArrayList<A>, ArrayList<B>, and ArrayList<C>.
        The method2 accepts an ArrayList of any type that is a supertype of C, so it can accept ArrayList<C>, ArrayList<B> and ArrayList<A>.
        By using wildcards, they're allowing the methods to accept a broader range of types, giving more flexibility and preventing compilation errors.

         */


        //*** 22 ianuarie 2020
        /*
        1 (1p). Compare static vs non-static methods in Java.  // SOLVED ABOVE




        2 (3p). Given the following collection

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)eliminates all the numbers which are not multiple of  4;
        b)transform each remaining number into its succesor (eg. 4 is transformed into 5);
        c)compute the sum modulo 2 of the remaining numbers (eg. (9 +5) mod 2=0)
         */

        //System.out.println(numbers.stream().filter(n->(n%4==0)).map(n->n+1).reduce((n1,n2)->(n1+n2)%2).get());


        /*
        4 (2p). What is a Semaphore in Java. // SOLVED ABOVE


        3 (3p). Given the following four classes in Java:

        class A {...}   class B extends A {...}   class C extends A {...}

        class Amain{
        A  method1(ArrayList<...> list) {  if list.isEmpty() return null;
                                   else return list.get(1);}   // READ... <=A



        void method2(ArrayList<...>  list) {  list.add(null);}  // WRITES NULL!!
        void method3(){
            ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());
            ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());
            ArrayList<C> listC = new ArrayList<C>(); listC.add(new C());
            this.method1(listA); this.method1(listB); this.method1(listC);
            this.method2(listA); this.method2(listB); this.method2(listC);

            }
    }

           Compute the most specific signatures for the class Amain methods (method1 and method2)
            such that the entire program is correct.
            If it is not possible to find the types justify your answer.
            Discuss line by line the correctness of the above program.


            A method1(ArrayList<? extends A> list) {  if list.isEmpty() return null; <=A, read op...
                                   else return list.get(1);}

            //This way the method can accept any type of ArrayList that extends A, such as ArrayList<B> and ArrayList<C>.

            SAfe: void method2(ArrayList<? super C>  list) {  list.add(null);}      >=C
            More specific: void method2(ArrayList<?>  list) {  list.add(null);}  // it writes null
                                                aka T



         */

        //***20 ianuarie 2020
        /*
       1. Checked vs unchecked exceptions in Java. (1p)
        Checked exceptions in Java are the exceptions that are checked at compile time,
        while the unchecked exceptions are not checked at compile time.
        The checked exceptions need to be declared in the signature of the method that throws it
        by using the throws keyword or to be caught using a try-catch block, in order to be handled.
        In general, checked exceptions represent errors outside the control of the program.
         Unchecked exceptions usually represent logic errors and, unlike checked exceptions, don't have to be declared or caught.
         */

        /*
        2 (3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a) keep only the numbers which are multiple of 3 or multiple of 2;
        b) transform each remaining number into a string, that consists of a prefix "A", the successor of the number and the suffix "B"
        (eg. 6 is transformed into "A7B")
        c) concatenate all the strings

        SOLVED ABOVE...

         */
        //4. What is a cyclic barrier?

        /*A Cyclic Barrier is a synchronization mechanism that can synchronize threads progressing through an algorithm.
        All threads must wait at it until all of them reach it and no thread continue can continue until that.
         Once all the threads are waiting at the Cyclic Barrier, all threads are released and can continue running.

         synchronizer that allows a set of threads to wait for each other once the execution has reached a
        certain point; takes a single integer that denotes the number of threads that need to call the await()
        method on the barrier instance to signify reaching the common execution point
        */

        /*
        3 (3p). Given the following four classes in
        Java:
        class A {...}
        class B extends A {...}
        class C extends A {...}
        class Amain{
                ... method1(... list) { return list.get(1)
                void method2 (... list, A el) { list.add(el); }
                void method3 (A elem) {
                    ArrayList<A> listA=new ArrayList<>(); listA.add(new A()); listA.add(new A());
                    ArrayList<B> listB= new ArrayList<B>(); listB.add(new B()); listB.add(new B());
                    ArrayList<C> listC= new ArrayList<C>(); listC.add(new C()); listC.add(new C());
                    this.method1 (listA); this.method1 (listB); this.method1 (listC);
                    this.method2 (listA, elem);this.method2 (listB,elem);this.method2 (listC,elem);
                }
            }

    Compute the most specific signatures for the class Amain methods (method1 and method2)
    such that the entire program is correct. If it is not possible to find the types justify your answer.
    Discuss line by line the correctness of the above program.

    A method1(ArrayList<? extends A> list) { return list.get(1)} // Read operation, <=A
    void method2 (ArrayList<? super C> list, A el) { list.add(el); } // Write operation >=C




    */



        //*** 2019 25 ianuarie - solved already
        /*
        1.(3p). Compare overriding vs. overloading in Java.

        2.(3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a)keep only the numbers which are multiple of  5 or  multiple of 2;
        b)transform each remaining number into a string, that consists of a prefix "N", the number and the suffix "R"
        (eg. 5 is transformed into "N5R")
        c)concatenate all the strings

        3.(3p). What is a CountDownLatch in Java.*/

        /*
        ***23 January 2019

        1. (3p). Compare static vs non-static methods in Java. // SOLVED ABOVE
        2. (3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a) eliminates all the numbers which are not multiple of 4;
        b) transform each remaining number into its succesor (eg. 4 is transformed into 5);
        c) compute the sum modulo 2 of the remaining numbers (eg. (9 +5) mod 2=0)
        d) transform the result into a list*/

        List<Integer> result_list=numbers.stream().filter(n->(n%4==0)).map(n->n+1).reduce((n1,n2)->(n1+n2)%2).stream().collect(Collectors.toList());
        //System.out.println(result_list);

        /*22 January 2019  // ALL SOLVED

        1. (3p). Compare checked vs. unchecked exceptions in Java.

        2. (3p). Given the following collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Using Java functional style (Java streams),
        please write a program that is doing the following operations in the following order:
        a) keep only the numbers which are multiple of 3 or multiple of 2;
        b) transform each remaining number into a string, that consists of a prefix "A", the successor of the number and the suffix "B"
        (eg. 6 is transformed into "A7B")
        c) concatenate all the strings

        3.(3p). What is a Cyclic Barrier in Java.
         */



        /** -> Other theoretical Questions... Not included in tests **/

        /**
         4. Reference Type vs Primitive type

         All the basic types in Java (int, boolean, char, short, float, long, double) are primitive types.
         A primitive type always has a value, but reference type can be null, denoting the absence of a value.
         -always has a value, takes less memory, created on the stack

         Reference types are used to point objects (String, Thread, File, Objects, Enums, Arrays etc.).
         Reference types are handles to the object which is created in the heap memory.
         -can be null, when compared with == the address is compared, not the value; created
         on the heap, where the garbage collector manages everything, are instantiable;

         -both are used for storing and manipulating data & it can be assigned to variables



         12. How can we create a generic array and why can't it be created directly?

         To create a generic array we can use the following code:
         T[] elem = (T[]) new Object[dim],
         Array.newInstance or ArrayList instead of array.
         We can't create it directly, like T[] elem = new T[dim]
         because Java's arrays contain, at runtime, information about its component type,
         so it must be known when creating the array.

         //dc
         However, with generic types, the type information is not available at runtime.
         Generics are a feature of the Java compiler and are used to provide type safety at compile-time. W
         hen a generic type is used, the compiler erases the generic type information and replaces it
         with its bounding type (e.g., Object) at runtime.
         Therefore, Java doesn't know the actual type of the array and can't store type
         information about it.
         That's why we can't create a generic array directly and have to use the above-mentioned alternatives.

         13. Define wildcards

         13) Wildcards <?> are used to denote any type/unknown type.
         When used, the elements can be considered to be of type Object.
         When used to declare an instance, the instance elements cannot be read or written,
         the only allowed operations are to read Object and to write null.
         They can have bounds: upper bound by extends (we can read elements of the type given)
         and lower bound by super (we can write elements of the type given).

         14. What is a buffer, buffer operation, stream?

         14) A buffer is a linear, finite sequence of elements of a specific primitive type.
         A buffer has capacity, limit and position.
         Java has a BufferedReader and a Buffered Writer, which use a buffer to keep data which is going to be
         read/written from/to a stream.
         A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.
         stream: continuous flow of data, stored in contiguous memory locations in the buffer; intermediate
         to main memory and cpu

         //They are used to perform functional-style operations on streams of elements, such as filter, map, reduce, and collect. The elements of a stream are only visited once during the life of a stream, this is known as being "eagerly evaluated" and not stored in memory. A new stream can be obtained from an existing one by applying intermediate operations such as filter and map, and a terminal operation such as forEach or reduce can be used to produce a result or side-effect

         better 14)
         A buffer is a temporary storage area where data is stored before it is sent to its final destination.
         A buffer operation is an action performed on a buffer, such as writing data to it or reading data from it.
         A stream is a flow of data, such as a flow of bytes,
         that is used to transfer data between different parts of a program or between different programs.
         In Java, the java.io package provides classes for working with streams,
         such as InputStream and OutputStream, as well as classes for working with buffers,
         such as BufferedReader and BufferedWriter.
         These classes allow you to read and write data in a more efficient way by buffering the data before it is sent to its final destination.


         15. Processes vs threads

         15) A program in execution is often referred as a process and
         a thread is a subset(part) of the process.
         A process consists of multiple threads.
         A process has its own address space.

         A thread uses the process's address space and
         share it with the other threads of that process.
         Threads can communicate with each other directly, but processes use inter-process communication methods.
         New threads are easily created, but new processes require duplication of the parent process.
         Threads have control over the other threads of the same process.
         A process does not have control over the sibling process, it has control over its child processes only.

         16. Multithreading in Java

         16) A multi-threaded program is one that can have multiple flows of control when executed.
         A thread is a single sequential flow of control within a program.
         Multithreading is used for maximum utilization of the CPU.
         Threads can be created by using two mechanisms :
         1. Extending the Thread class
         2. Implementing the Runnable Interface

         //
         Multithreading is a Java feature that allows concurrent execution of two or more parts of a program
         for maximum utilization of CPU. Each part of such program is called a thread. So, threads are lightweight
         processes within a process.
         //

         17. What are the possible states of a thread?

         17) The possible states for a thread are:
         new(created & has not yet started) Thread t = new Thread();,
         runnable(thread executing in the JVM), t.start(); started but not running yet
            running: A thread is in the running state when the JVM is executing its run() method.
         blocked(a thread waiting for a monitor lock [to enter a synchronized block or method]), synchronized(lock) { // thread is blocked until lock is available }
         waiting(thread waiting indefinitely for another thread to perform a particular action),//t.wait, t.join(); // thread is waiting for t to complete
         timed waiting(a thread waiting for another thread to perform an action for up to a specified waiting time) //Thread.sleep(1000); // thread is timed waiting for 1 second
         and terminated(thread that has exited).// it has completed execution of its run() method.

         18. Methods for thread synchronization.

         18) There are two types of thread synchronization:
         -mutual exclusive(synchronized method, synchronized block, static synchronization) and
         -cooperation(inter-thread communication).
         Synchronization is built around an internal entity known as the lock or monitor.
         Every object has a lock associated with it.
         A thread that needs access to an object's fields has to acquire the object's lock before
         and then release the lock when it's done with them.
         Synchronized method is used to lock an object for any shared resource.
         Some synchronization mechanisms are: semaphore, count down latch, cyclic barrier, lock (read write lock)
         and atomic variables.

         19. What is a monitor model?

         19) A monitor is a collection of code (called the critical section) associated with an object (called the lock).
         At any time only one thread at most can have its execution point located in the critical section
         associated with the lock (mutual exclusion).

         20. What is event-driven programming?

         20) Event-driven programming is a programming paradigm in which the flow of the program is determined
         by events such as
         user operations, sensor outputs or message passing from other programs or threads.
         It is the dominant paradigm used in GUIS and other applications that are centered on performing certain
         actions in response to user input.

         21. ExecutorService

         21) The executor service interface
         represents an asynchronous execution mechanism which is capable of executing tasks in the background.
         It is very similar to a thread pool.
         -threads who are free execute those tasks

         ExecutorService present in the java.util.concurrent package is a thread pool implementation.
         concurrent.ExecutorService interface represents an asynchronous execution mechanism which is
         capable of executing tasks in the background.

         ExecutorService executorService =Executors.newFixedThreadPool(10);
         //10 threads for executing tasks are created

         executorService.execute(new Runnable() {
         public void run() {
         System.out.println("Asynchronous task");
         }
         });
         executorService.shutdown();


         22. Blocking Queue.

         22) A blocking queue is a queue that additionally supports operations that wait for the queue
         to become non-empty when retrieving an element and wait for space to become available in the queue
         when storing an element.

         Blocking queues are commonly used in multi-threaded environments to coordinate the flow of data between threads.
         For example, a producer thread may add elements to a blocking queue,
         while a consumer thread may remove elements from the same queue.
         This allows for easy communication and coordination between threads without the need for explicit locks or other
         synchronization mechanisms.

         BlockingQueue does not accept a null value.
         If we try to enqueue the null item, then it throws NullPointerException.

         --
         For example, the ArrayBlockingQueue class provides a fixed-size queue that stores elements in an array. The LinkedBlockingQueue class provides a dynamic-size queue that stores elements in a linked list. The PriorityBlockingQueue class provides a queue that stores elements in a prioritized order.



         23. Concurrent Collections

         23) A concurrent collection is a hash map that offers better concurrency than the HashTable from Java does.
         It does not lock the map while you are reading from it and it does not lock
         the entire map when writing to it.
         It only locks the part of the map that is being written to, internally.

         contain methods through which we can get a synchronized version of non-synchronized objects;
         don’t have to take care of thread safety; can use multiple threads on object

         24. ForkJoinPool

         24) A ForkJoinPool is similar to the ExecutorService, but it has a
         difference: it implements the work-stealing strategy
         (i.e. every time a running thread has to wait for some result);
         the thread removes the current task from the work queue and executes some other task ready to run.
         This way the current thread is not blocked and can be used to
         execute other tasks.
         Once the result for the originally suspended task has been computed the task gets executed again
         and the join() method returns the result.

         Fork Join Pool
         a call of fork() will start an asynchronous execution of the task,
         a call of join() will wait until the task has finished and retrieve its result.
         makes it easy for tasks to split their work up into smaller tasks(divide and conquer approach)
         which are then submitted to the ForkJoinPool too


         **/

    }
}