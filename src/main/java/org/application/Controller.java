package org.application;

import java.io.*;
import java.util.Scanner;

public class Controller {
//    private String fileName = "programs.txt";
//
//    public Controller() {
//
//    }
//
//    public void createProgram(String pTitle, String pDescription, int ext_ID, String cTitle, String cOccupation, String cPerson) throws IOException {
//        Credits credit1 = new Credits(cTitle, cOccupation, cPerson);
//        Program program1 = new Program(pTitle, pDescription, ext_ID, credit1);
//
//        /*PrintWriter outputStream = null;
//        outputStream = new PrintWriter(fileName);
//        outputStream.println(program1);
//        outputStream.close();
//        System.out.println("Saved object in text file (hopefully...)");
//         */
//
//        File p = new File(fileName);
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p, true));
//        out.writeObject(program1);
//        out.close();
//
//
//        /*if(!exist) {
//               p  = new File(fileName);
//        }
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p));
//        out.writeObject(program1);
//        out.close();
//        System.out.println("Saved object in text file (hopefully...)");
//
//         */
//    }
//
//    public void testToSeeIfTextfileWorks() throws IOException, ClassNotFoundException {
//        /*Scanner inputStream = null;
//        inputStream = new Scanner(new File(fileName));
//        while(inputStream.hasNextLine()) {
//            String line = inputStream.nextLine();
//            System.out.println(line);
//        }
//        inputStream.close();
//
//         */
//
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
//        Program program1 = (Program) in.readObject();
//        System.out.println("Title is " + program1.getTitle());
//        in.close();
//
//
//    }
}
