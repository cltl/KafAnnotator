package eu.kyotoproject.kafannotator.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Oct 21, 2010
 * Time: 12:55:02 PM
 * To change this template use File | Settings | File Templates.
 * This file is part of KafAnnotator.

    KafAnnotator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    KafAnnotator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with KafAnnotator.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Util {
    
    static public String [] fixFields (String[] fields, int MAXTABLECOLUMNS, int ROWSYNSET) {
        ArrayList<String> fieldList = new ArrayList<String>();
        if (fields.length==MAXTABLECOLUMNS-1) {
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                if (i<ROWSYNSET) {
                   fieldList.add(field);
                }
                else if (i==ROWSYNSET) {
                    fieldList.add("");
                    fieldList.add(field);
                }
                else {
                    fieldList.add(field);
                }
            }
        }
        if (fields.length==MAXTABLECOLUMNS-2) {
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                if (i<ROWSYNSET) {
                   fieldList.add(field);
                }
                else if (i==ROWSYNSET) {
                    fieldList.add("");
                    fieldList.add(field);
                }
                else {
                    fieldList.add(field);
                }
            }
            /// add the sentence identifier field
            fieldList.add("");
        }
        String [] newFields = new String[fieldList.size()];
        for (int i = 0; i < fieldList.size(); i++) {
            String s = fieldList.get(i);
            newFields[i] = s;
        }
        return newFields;
    }

    //inputLine = w8646	2007	2007	N		TIME	869	EVENT-SCOPE	870		0		0		0		0	8146	true	595
    static public String [] fixFieldsLayers (String[] fields, int MAXTABLECOLUMNS, int COLUMN, int ADD) {
        ArrayList<String> fieldList = new ArrayList<String>();
        if (fields.length==MAXTABLECOLUMNS-ADD) {
            //// misses the fields for layers beyond COLUMN
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                if (i<COLUMN) {
                   fieldList.add(field);
                }
                else if (i==COLUMN) {
                    fieldList.add(field);
                    for (int j = 0; j < ADD; j++) {
                        fieldList.add("");
                    }
                }
                else {
                    fieldList.add(field);
                }
            }
        }
        String [] newFields = new String[fieldList.size()];
        for (int i = 0; i < fieldList.size(); i++) {
            String s = fieldList.get(i);
            newFields[i] = s;
        }
        return newFields;
    }


        static public ArrayList<String> makeFlatFileList(String inputPath, String filter) {
            ArrayList<String> acceptedFileList = new ArrayList<String>();
            File[] theFileList = null;
            File lF = new File(inputPath);
            if ((lF.canRead()) && lF.isDirectory()) {
                theFileList = lF.listFiles();
                for (int i = 0; i < theFileList.length; i++) {
                    String newFilePath = theFileList[i].getAbsolutePath();
                    if (theFileList[i].isFile()) {
                        if (newFilePath.endsWith(filter)) {
                            acceptedFileList.add(newFilePath);
                        }
                    }
                }
            }
            return acceptedFileList;
        }


        static public ArrayList ReadFileToArrayList(String fileName) {
            ArrayList lineList = new ArrayList();
            if (new File (fileName).exists() ) {
                try {
                    FileInputStream fis = new FileInputStream(fileName);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader in = new BufferedReader(isr);
                    String inputLine = "";
                    long cnt = 0;
                    while (in.ready()&&(inputLine = in.readLine()) != null) {
                        //System.out.println(inputLine);
                        if (inputLine.trim().length()>0) {
                            lineList.add(inputLine);
                            cnt++;
                        }
                    }
//                System.out.println("last inputLine = " + inputLine);
                    System.out.println("read cnt = " + cnt+" lines....");
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return lineList;
        }


        static public int makeInt (String value) {
            String validString = "";
            String valid = "1234567890.,";
            for (int i = 0; i < value.length(); i++) {
                String c = value.substring(i,i+1);
                if (valid.contains(c)) {
                   validString+=c;
                }
            }
            if (validString.isEmpty()) {
                return -1;
            }
            else {
                int i= Integer.parseInt(validString);
                return i;
            }
        }

}
