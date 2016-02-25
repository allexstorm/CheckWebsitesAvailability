/*
 *    Copyright 2016 Alexander Samtsov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.alexandersamtsov.checkwebsitesavailability;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {



        String packagePath = "/com/alexandersamtsov/checkwebsitesavailability/";
        String line;
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";


        FileReader inputFile = null;
        try {
            inputFile = new FileReader(System.getProperty("user.dir") + "/src" + packagePath + "inputList");
            //inputFile = new FileReader(System.getProperty("user.dir") + packagePath + "inputList"); // use it when make project
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(inputFile);


        FileWriter outputFile = null;
        try {
            outputFile = new FileWriter(System.getProperty("user.dir") + "/src" + packagePath + "resultList");
            //outputFile = new FileWriter(System.getProperty("user.dir") + packagePath + "resultList"); // use it when make project
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bwr = new BufferedWriter(outputFile);



        try {
            while ((line = br.readLine()) != null) {

                try{
                    final URLConnection connection = new URL(line).openConnection();
                    connection.connect();
                    System.out.println(ANSI_GREEN + line + " is available!" + ANSI_RESET);
                    bwr.write(line + " is available!\n");
                } catch(final MalformedURLException e){
                    throw new IllegalStateException("Wrong URL: " + line, e);
                } catch(final IOException e){
                    System.out.println(ANSI_RED + line + " is unavailable!" + ANSI_RESET);
                    bwr.write(line + " is unavailable!\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
            bwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
