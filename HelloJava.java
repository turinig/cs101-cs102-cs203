


// CS-101: "Computing and Algorithms I"
// CS-102: "Computing and Algorithms II"
// CS-203: "Computing and Algorithms III"
// Prof. Giuseppe Turini
// Kettering University
// 2022-01-27

// Minimalistic code to check the currently used/active Java (JDK) version.
public class HelloJava {

   // ...
   public static void main( String[] args ) {   
      // ...
      System.out.println();
      System.out.println("----- HELLO JAVA -----");
      System.out.println();
      System.out.println("Java (JRE) vendor name:   " + System.getProperty("java.vendor") );
      System.out.println("Java (JRE) version num:   " + System.getProperty("java.version") );
      System.out.println("Java (JRE) install dir:   " + System.getProperty("java.home") );
      System.out.println();
      System.out.println("Operating system name:   " + System.getProperty("os.name") );
      System.out.println("Operating system vers:   " + System.getProperty("os.version") );
      System.out.println("Operating system arch:   " + System.getProperty("os.arch") );
      System.out.println();
      System.out.println("----------------------");
      System.out.println();
   }
   
}


