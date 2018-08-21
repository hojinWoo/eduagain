//$ javac -d . Package.java
package kr.or.kosta;

import kr.or.kosta.test.PackageTest;
// import kr.or.kosta.*;

// $ javac -d . Package.java
public class Package{ //namespace
  public static void main(String[] args){
    PackageTest test;
    kr.or.kosta.test.PackageTest test1; //inline import if you don't want write import
    // => reverse compiler(byte code -> source code) in JVM completely.
    // $ javap kr.or.kosta.Package
    // dir -> C:\Program Files\Java\jdk1.8.0_181\bin\javap.exe

    System.out.println("compile in D.O.S");
    // exec -> $ java kr.or.kosta.Package
  }
}
