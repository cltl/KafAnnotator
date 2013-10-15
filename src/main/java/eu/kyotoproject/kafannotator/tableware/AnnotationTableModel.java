package eu.kyotoproject.kafannotator.tableware;

import eu.kyotoproject.kafannotator.util.Util;
import eu.kyotoproject.kafannotator.objects.WordTag;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 4:54:03 PM
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
public class AnnotationTableModel extends AbstractTableModel {
          public final static int TAGLEVELS = 8;
          public final static int ROWID = 0;
          public final static int ROWWORDTOKEN = 1;
          public final static int ROWWORDTYPE = 2;
          public final static int ROWPOS = 3;
          public final static int ROWSYNSET = 4;
          public final static int ROWTAG1 = 5;
          public final static int ROWTAGID1 = 6;
          public final static int ROWTAG2 = 7;
          public final static int ROWTAGID2 = 8;
          public final static int ROWTAG3 = 9;
          public final static int ROWTAGID3 = 10;
          public final static int ROWTAG4 = 11;
          public final static int ROWTAGID4 = 12;
          public final static int ROWTAG5 = 13;
          public final static int ROWTAGID5 = 14;
          public final static int ROWTAG6 = 15;
          public final static int ROWTAGID6 = 16;
          public final static int ROWTAG7 = 17;
          public final static int ROWTAGID7 = 18;
          public final static int ROWTAG8 = 19;
          public final static int ROWTAGID8 = 20;
          public final static int ROWORDER = 21;
          public final static int ROWSTATUS = 22;
          public final static int ROWSENTENCE = 23;
          public final static int[] TAGROWS = {ROWTAG1, ROWTAG2, ROWTAG3, ROWTAG4, ROWTAG5, ROWTAG6, ROWTAG7, ROWTAG8};
          final String[] columnNames = {"Id","Word token","Word type","Pos","Label", "Tag1","Tag1 id","Tag2","Tag2 id","Tag3","Tag3 id"
                  ,"Tag4","Tag4 id","Tag5","Tag5 id","Tag6","Tag6 id", "Tag7","Tag7 id", "Tag8","Tag8 id"
                  , "Order", "Status", "Sentence"};
          private final static String TAGSEPARATOR = "\t";
          public int MAXTABLECOLUMNS = 24;

          public int MAXTABLEROWS = 10000;
          public int nTABLEROWS = 0;
          public Object[][] data;

    public ArrayList<Integer> tagIdList;

    public AnnotationTableModel () {
             data = new Object[MAXTABLEROWS][MAXTABLECOLUMNS];
             initTable(MAXTABLEROWS);
             tagIdList = new ArrayList<Integer>();
    }

      public AnnotationTableModel (ArrayList<WordTag> taggedList) {
          data = null;
          int nList = taggedList.size();
          data = new Object[nList][MAXTABLECOLUMNS];
          tagIdList = new ArrayList<Integer>();
          initTable(nList);
          for (int i = 0; i < taggedList.size(); i++) {
              WordTag wordTag = taggedList.get(i);
              addNewRow(wordTag, false);
           }
      }

     public int addAnnotations (String inputFile) {
         String error = "";
         String inputLine = "";
         int nList = 0;
         try {
           FileInputStream fis = new FileInputStream(inputFile);
           //InputStreamReader isr = new InputStreamReader(fis, System.getProperty("file.encoding"));
           InputStreamReader isr = new InputStreamReader(fis);
           BufferedReader in = new BufferedReader(isr);
           while ((inputLine = in.readLine()) != null) {
                String [] fields = inputLine.split(TAGSEPARATOR);
                if (fields.length<MAXTABLECOLUMNS) {
                    fields = Util.fixFieldsLayers(fields, MAXTABLECOLUMNS, ROWTAGID6, 4);
                    //fields = Util.fixFields(fields, MAXTABLECOLUMNS, ROWSYNSET);
                }
              //  System.out.println("fields.length = " + fields.length);
                if (fields.length==MAXTABLECOLUMNS) {
                       nList++;
                       boolean done = true;
                       String id   = fields[ROWID];
                       String synset = fields[ROWSYNSET];
                       String tag1  = fields[ROWTAG1];
                       Integer tagId1 = 0;
                       try {
                            tagId1 = Integer.parseInt(fields[ROWTAGID1]);
                       } catch (NumberFormatException e) {
                           // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }
                       if ((tagId1.intValue()>0) && (!tagIdList.contains(tagId1))) {
                           tagIdList.add(tagId1);
                       }
                       String tag2  = fields[ROWTAG2];
                       Integer tagId2 = 0;
                        try {
                            tagId2 = Integer.parseInt(fields[ROWTAGID2]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId2.intValue()>0) && (!tagIdList.contains(tagId2))) {
                           tagIdList.add(tagId2);
                       }
                       String tag3  = fields[ROWTAG3];
                       Integer tagId3 = 0;
                        try {
                            tagId3 = Integer.parseInt(fields[ROWTAGID3]);
                        } catch (NumberFormatException e) {
                           // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId3.intValue()>0) && (!tagIdList.contains(tagId3))) {
                           tagIdList.add(tagId3);
                       }
                       String tag4  = fields[ROWTAG4];
                       Integer tagId4 = 0;
                        try {
                            tagId4 = Integer.parseInt(fields[ROWTAGID4]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId4.intValue()>0) && (!tagIdList.contains(tagId4))) {
                           tagIdList.add(tagId4);
                       }
                       String tag5  = fields[ROWTAG5];
                       Integer tagId5 = 0;
                        try {
                            tagId5 = Integer.parseInt(fields[ROWTAGID5]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId5.intValue()>0) && (!tagIdList.contains(tagId5))) {
                           tagIdList.add(tagId5);
                       }
                       String tag6  = fields[ROWTAG6];
                       Integer tagId6 = 0;
                        try {
                            tagId6 = Integer.parseInt(fields[ROWTAGID6]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId6.intValue()>0) && (!tagIdList.contains(tagId6))) {
                           tagIdList.add(tagId6);
                       }
                       String tag7  = fields[ROWTAG7];
                       Integer tagId7 = 0;
                        try {
                            tagId7 = Integer.parseInt(fields[ROWTAGID7]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId7.intValue()>0) && (!tagIdList.contains(tagId7))) {
                           tagIdList.add(tagId7);
                       }
                       String tag8  = fields[ROWTAG8];
                       Integer tagId8 = 0;
                        try {
                            tagId8 = Integer.parseInt(fields[ROWTAGID8]);
                        } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId8.intValue()>0) && (!tagIdList.contains(tagId8))) {
                           tagIdList.add(tagId8);
                       }
                       if (fields[ROWSTATUS].equals("true")) {
                           done = true;
                       }
                       else {
                           done = false;
                       }
                       String sentence = fields[ROWSENTENCE];
                       int numRows = nTABLEROWS;
                       for (int i=0; i < numRows; i++) {
                           String tokenId = (String) data[i][0];
                           if (tokenId.equals(id)) {
                               if (synset.length()>0) {
                                    this.setValueAt(synset, i, ROWSYNSET);
                               }
                               this.setValueAt(tag1, i, ROWTAG1);
                               this.setValueAt(tagId1, i, ROWTAGID1);
                               this.setValueAt(tag2, i, ROWTAG2);
                               this.setValueAt(tagId2, i, ROWTAGID2);
                               this.setValueAt(tag3, i, ROWTAG3);
                               this.setValueAt(tagId3, i, ROWTAGID3);
                               this.setValueAt(tag4, i, ROWTAG4);
                               this.setValueAt(tagId4, i, ROWTAGID4);
                               this.setValueAt(tag5, i, ROWTAG5);
                               this.setValueAt(tagId5, i, ROWTAGID5);
                               this.setValueAt(tag6, i, ROWTAG6);
                               this.setValueAt(tagId6, i, ROWTAGID6);
                               this.setValueAt(tag7, i, ROWTAG7);
                               this.setValueAt(tagId7, i, ROWTAGID7);
                               this.setValueAt(tag8, i, ROWTAG8);
                               this.setValueAt(tagId8, i, ROWTAGID8);
                               this.setValueAt(done, i, ROWSTATUS);
                               break;
                           }
                       }
                }
                else {
                    System.out.println("Number of input fields different from table columns size");
                    System.out.println("inputLine = " + inputLine);
                    System.out.println("fields.length = " + fields.length);
                }
          }
          in.close();
         }
         catch (Exception eee) {
               error += "\nException --"+eee.getMessage();
               System.out.println(error);
               System.out.println("Line:"+nList);
               System.out.println(inputLine);
         }

         return nList;
     }
     public int addJustAnnotations (String inputFile) {
         String error = "";
         String inputLine = "";
         int nList = 0;
         try {
           FileInputStream fis = new FileInputStream(inputFile);
           //InputStreamReader isr = new InputStreamReader(fis, System.getProperty("file.encoding"));
           InputStreamReader isr = new InputStreamReader(fis);
           BufferedReader in = new BufferedReader(isr);
           while ((inputLine = in.readLine()) != null) {
                String [] fields = inputLine.split(TAGSEPARATOR);
                if (fields.length<MAXTABLECOLUMNS) {
                    fields = Util.fixFieldsLayers(fields, MAXTABLECOLUMNS, ROWTAGID6, 4);
                   // fields = Util.fixFields(fields, MAXTABLECOLUMNS, ROWSYNSET);
                }
               // System.out.println("fields.length = " + fields.length);
                if (fields.length==MAXTABLECOLUMNS) {
                       boolean done = true;
                       String id   = fields[ROWID];
                       String wordToken = fields[ROWWORDTOKEN];
                       String wordType = fields [ROWWORDTYPE];
                       String pos = fields[ROWPOS];
                       String synset = fields[ROWSYNSET];
                       String tag1  = fields[ROWTAG1];
                       Integer tagId1 = -1;
                       try {
                         tagId1 = Integer.parseInt(fields[ROWTAGID1]);
                       } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }
                       if ((tagId1.intValue()>0) && (!tagIdList.contains(tagId1))) {
                           tagIdList.add(tagId1);
                       }
                       String tag2  = fields[ROWTAG2];
                       Integer tagId2 = -1;
                       try {
                            tagId2 = Integer.parseInt(fields[ROWTAGID2]);
                       } catch (NumberFormatException e) {
                         //   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }

                       if ((tagId2.intValue()>0) && (!tagIdList.contains(tagId2))) {
                           tagIdList.add(tagId2);
                       }
                       String tag3  = fields[ROWTAG3];
                       Integer tagId3 = -1;
                       try {
                            tagId3 = Integer.parseInt(fields[ROWTAGID3]);
                       } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }
                       if ((tagId3.intValue()>0) && (!tagIdList.contains(tagId3))) {
                           tagIdList.add(tagId3);
                       }
                       String tag4  = fields[ROWTAG4];
                       Integer tagId4 = -1;
                       try {
                            tagId4 = Integer.parseInt(fields[ROWTAGID4]);
                       } catch (NumberFormatException e) {
                          //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }
                       if ((tagId4.intValue()>0) && (!tagIdList.contains(tagId4))) {
                           tagIdList.add(tagId4);
                       }
                       String tag5  = fields[ROWTAG5];
                       Integer tagId5 = -1;
                       try {
                            tagId5 = Integer.parseInt(fields[ROWTAGID5]);
                       } catch (NumberFormatException e) {
                         //   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }
                       if ((tagId5.intValue()>0) && (!tagIdList.contains(tagId5))) {
                           tagIdList.add(tagId5);
                       }
                       String tag6  = fields[ROWTAG6];
                       Integer tagId6=0;
                        try {
                            tagId6 = Integer.parseInt(fields[ROWTAGID6]);
                        } catch (NumberFormatException e) {
                           // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId6.intValue()>0) && (!tagIdList.contains(tagId6))) {
                           tagIdList.add(tagId6);
                       }
                       String tag7  = fields[ROWTAG7];
                       Integer tagId7=0;
                        try {
                            tagId7 = Integer.parseInt(fields[ROWTAGID7]);
                        } catch (NumberFormatException e) {
                           // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId7.intValue()>0) && (!tagIdList.contains(tagId7))) {
                           tagIdList.add(tagId7);
                       }
                       String tag8  = fields[ROWTAG8];
                       Integer tagId8=0;
                        try {
                            tagId8 = Integer.parseInt(fields[ROWTAGID8]);
                        } catch (NumberFormatException e) {
                           // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if ((tagId8.intValue()>0) && (!tagIdList.contains(tagId8))) {
                           tagIdList.add(tagId8);
                       }
                       if (fields[ROWSTATUS].equals("true")) {
                           done = true;
                       }
                       else {
                           done = false;
                       }
                       Integer rowOrder = new Integer(-1);
                        try {
                            rowOrder = Integer.parseInt(fields[ROWORDER]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        String sentence = fields[ROWSENTENCE];

                        this.setValueAt(id, nList, ROWID);
                        this.setValueAt(wordToken, nList, ROWWORDTOKEN);
                        this.setValueAt(wordType, nList, ROWWORDTYPE);
                        this.setValueAt(pos, nList, ROWPOS);
                        this.setValueAt(synset, nList, ROWSYNSET);
                        this.setValueAt(tag1, nList, ROWTAG1);
                        this.setValueAt(tagId1, nList, ROWTAGID1);
                        this.setValueAt(tag2, nList, ROWTAG2);
                        this.setValueAt(tagId2, nList, ROWTAGID2);
                        this.setValueAt(tag3, nList, ROWTAG3);
                        this.setValueAt(tagId3, nList, ROWTAGID3);
                        this.setValueAt(tag4, nList, ROWTAG4);
                        this.setValueAt(tagId4, nList, ROWTAGID4);
                        this.setValueAt(tag5, nList, ROWTAG5);
                        this.setValueAt(tagId5, nList, ROWTAGID5);
                        this.setValueAt(tag6, nList, ROWTAG6);
                        this.setValueAt(tagId6, nList, ROWTAGID6);
                        this.setValueAt(tag7, nList, ROWTAG7);
                        this.setValueAt(tagId7, nList, ROWTAGID7);
                        this.setValueAt(tag8, nList, ROWTAG8);
                        this.setValueAt(tagId8, nList, ROWTAGID8);
                        this.setValueAt(done, nList, ROWSTATUS);
                        this.setValueAt(rowOrder, nList, ROWORDER);
                        this.setValueAt(sentence, nList, ROWSENTENCE);
                    nList++;

                }
                else {
                    System.out.println("inputLine = " + inputLine);
                    System.out.println("fields.length = " + fields.length);
                }
          }
          in.close();
         }
         catch (Exception eee) {
               error += "\nException --"+eee.getMessage();
               System.out.println(error);
               System.out.println("Line:"+nList);
               System.out.println(inputLine);
         }
     this.nTABLEROWS = nList;
         return nList;
     }

     public AnnotationTableModel (int nClasses) {
           MAXTABLEROWS = nClasses;
           data = new Object[MAXTABLEROWS][MAXTABLECOLUMNS];
           initTable(MAXTABLEROWS);
      }


      public void addNewRow(WordTag tag, boolean status
                               ) {
               if (tag.getTagId1()>0) {
                   if (!tagIdList.contains(tag.getTagId1())) {
                       tagIdList.add(tag.getTagId1());
                   }
               }
               int nRow = nTABLEROWS;
               setValueAt(new String (tag.getTokenId()), nRow, ROWID);
               setValueAt(new String (tag.getWordToken()), nRow, ROWWORDTOKEN);
               setValueAt(new String (tag.getWordType()), nRow, ROWWORDTYPE);
               setValueAt(new String (tag.getPos()), nRow, ROWPOS);
               setValueAt(new String (tag.getWordSynset()), nRow, ROWSYNSET);
               setValueAt(new String (tag.getTag1()), nRow, ROWTAG1);
               setValueAt(new Integer (tag.getTagId1()), nRow, ROWTAGID1);
               setValueAt(new String (tag.getTag2()), nRow, ROWTAG2);
               setValueAt(new Integer (tag.getTagId2()), nRow, ROWTAGID2);
               setValueAt(new String (tag.getTag3()), nRow, ROWTAG3);
               setValueAt(new Integer (tag.getTagId3()), nRow, ROWTAGID3);
               setValueAt(new String (tag.getTag4()), nRow, ROWTAG4);
               setValueAt(new Integer (tag.getTagId4()), nRow, ROWTAGID4);
               setValueAt(new String (tag.getTag5()), nRow, ROWTAG5);
               setValueAt(new Integer (tag.getTagId5()), nRow, ROWTAGID5);
               setValueAt(new String (tag.getTag6()), nRow, ROWTAG6);
               setValueAt(new Integer (tag.getTagId6()), nRow, ROWTAGID6);
               setValueAt(new String (tag.getTag7()), nRow, ROWTAG7);
               setValueAt(new Integer (tag.getTagId7()), nRow, ROWTAGID7);
               setValueAt(new String (tag.getTag8()), nRow, ROWTAG8);
               setValueAt(new Integer (tag.getTagId8()), nRow, ROWTAGID8);
               setValueAt(new Integer (tag.getOrder()), nRow, ROWORDER);
               setValueAt(new Boolean (status), nRow, ROWSTATUS);
               setValueAt(new Integer (tag.getSentenceId()), nRow, ROWSENTENCE);
               nTABLEROWS++;
      }


      public void initTable (int nRows)
          {  for (int k=0; k<nRows; k++) {
                  setValueAt(new String (""), k, ROWID);
                  setValueAt(new String (""), k, ROWWORDTOKEN);
                  setValueAt(new String (""), k, ROWWORDTYPE);
                  setValueAt(new String (""), k, ROWPOS);
                  setValueAt(new String (""), k, ROWSYNSET);
                  setValueAt(new String (""), k, ROWTAG1);
                  setValueAt(new Integer (0), k, ROWTAGID1);
                  setValueAt(new String (""), k, ROWTAG2);
                  setValueAt(new Integer (0), k, ROWTAGID2);
                  setValueAt(new String (""), k, ROWTAG3);
                  setValueAt(new Integer (0), k, ROWTAGID3);
                  setValueAt(new String (""), k, ROWTAG4);
                  setValueAt(new Integer (0), k, ROWTAGID4);
                  setValueAt(new String (""), k, ROWTAG5);
                  setValueAt(new Integer (0), k, ROWTAGID5);
                  setValueAt(new String (""), k, ROWTAG6);
                  setValueAt(new Integer (0), k, ROWTAGID6);
                  setValueAt(new String (""), k, ROWTAG7);
                  setValueAt(new Integer (0), k, ROWTAGID7);
                  setValueAt(new String (""), k, ROWTAG8);
                  setValueAt(new Integer (0), k, ROWTAGID8);
                  setValueAt(new Integer (0), k, ROWORDER);
                  setValueAt(new Boolean (false), k, ROWSTATUS);
                  setValueAt(new Integer (0), k, ROWSENTENCE);
               }
          }

            public int getColumnCount() {
                return columnNames.length;
            }

            public int getRowCount() {
                return data.length;
            }

            public String getColumnName(int col) {
                return columnNames[col];
            }

            public Object getValueAt(int row, int col) {
                return data[row][col];
            }

            /*
             * JTable uses this method to determine the default renderer/
             * editor for each cell.  If we didn't implement this method,
             * then the last column would contain text ("true"/"false"),
             * rather than a check box.
             */
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            /*
             * Don't need to implement this method unless your table's
             * editable.
             */
            public boolean isCellEditable(int row, int col) {
                    if (col ==ROWID)
                    {  return false;}
                    else if (col == ROWWORDTOKEN)
                    {  return false;}
                    else if (col == ROWWORDTYPE)
                    {  return false;}
                    else if (col == ROWPOS)
                    {  return false;}
                    else if (col == ROWSYNSET)
                    {  return false;}
                    else if (col == ROWTAG1)
                    {  return false;}
                    else if (col == ROWTAGID1)
                    {  return false;}
                    else if (col == ROWTAG2)
                    {  return false;}
                    else if (col == ROWTAGID2)
                    {  return false;}
                    else if (col == ROWTAG3)
                    {  return false;}
                    else if (col == ROWTAGID3)
                    {  return false;}
                    else if (col == ROWTAG4)
                    {  return false;}
                    else if (col == ROWTAGID4)
                    {  return false;}
                    else if (col == ROWTAG5)
                    {  return false;}
                    else if (col == ROWTAGID5)
                    {  return false;}
                    else if (col == ROWTAG6)
                    {  return false;}
                    else if (col == ROWTAGID6)
                    {  return false;}
                    else if (col == ROWTAG7)
                    {  return false;}
                    else if (col == ROWTAGID7)
                    {  return false;}
                    else if (col == ROWTAG8)
                    {  return false;}
                    else if (col == ROWTAGID8)
                    {  return false;}
                    else if (col ==ROWORDER)
                    {  return false;}
                    else if (col ==ROWSTATUS)
                    {  return false;}
                    else if (col ==ROWSENTENCE)
                    {  return false;}
                    else
                    {   return false;
                    }
            }

           public void setValueAt(Object aValue, int row, int col) {
               data[row][col] = aValue;
               if (col == ROWSYNSET) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG1) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG2) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG3) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG4) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG5) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG6) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG7) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               if (col == ROWTAG8) {
                    data[row][ROWSTATUS] = new Boolean(true);
               }
               fireTableCellUpdated(row, col);
            }

           public Integer generateTagId () {

               Integer tagId = 1;
               if (tagIdList.size()==0) {
                   tagIdList.add(tagId);
                   return tagId;
               }
               else {
                   tagId = tagIdList.get(tagIdList.size()-1);
                   tagId = tagId.intValue()+1;
                   while (tagIdList.contains(tagId)) {
                       tagId = tagId.intValue()+1;
                   }
                   tagIdList.add(tagId);
                   return tagId;
               }
           }

           public void printDebugData() {
                int numRows = nTABLEROWS; //getRowCount();
                int numCols = getColumnCount();

                for (int i=0; i < nTABLEROWS; i++) {
          //      for (int i=0; i < numRows; i++) {
                    System.out.print("    row " + i + ":");
                    for (int j=0; j < numCols; j++) {
                        System.out.print("  " + data[i][j]);
                    }
                    System.out.println();
                }
                System.out.println("--------------------------");
            }

            public boolean hasTagValue (int row) {
                if (((String) this.getValueAt(row, ROWTAG1)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG2)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG3)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG4)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG4)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG6)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG7)).isEmpty() &&
                    ((String) this.getValueAt(row, ROWTAG8)).isEmpty()){
                    return false;
                }
                else {
                    return true;
                }
            }
            public void writeTableToTagFile(String outputFile) {
                    try {
                      File theFile = new File (outputFile);
                      if (theFile.exists()) {
                          String outputPath = outputFile+".bu";
                          File input = new File(outputFile);
                         input.renameTo(new File(outputPath));

                      }
                      FileOutputStream fos = new FileOutputStream(outputFile);
                      int numRows = nTABLEROWS; //getRowCount();
                      int numCols = getColumnCount();
                      String str = "";
                      for (int i=0; i < numRows; i++) {

                        if ((this.getValueAt(i, ROWSTATUS).equals(new Boolean(true))) ||
                            (hasTagValue(i))) {
                            for (int j=0; j < numCols; j++) {
                                if (j==0) {
                                    str = data[i][j].toString();
                                }
                                else {
                                    str +=TAGSEPARATOR + data[i][j].toString();;
                                }
                            }
                            str += "\n";
                            fos.write(str.getBytes(), 0, str.length());
                            str = "";
                        }
                      }
                      fos.close();
                    }
                    catch (Exception e){ e.printStackTrace();}
            }

            public void writeTableToTrainFile(String outputFile) {
                    try {
                      File theFile = new File (outputFile);
                      if (theFile.exists()) {
                          String outputPath = outputFile+".bu";
                          File input = new File(outputFile);
                         input.renameTo(new File(outputPath));

                      }
                      FileOutputStream fos = new FileOutputStream(outputFile);
                      int numRows = nTABLEROWS; //getRowCount();
                      String str = "";
                      String word = "";
                      boolean splitWord = false;
                      for (int i=0; i < numRows; i++) {
                              if (data[i][ROWTAG1].equals("SW")) {
                                  splitWord = true;
                              }
                              else {
                                  splitWord = false;
                              }
                              for (int j=0; j < MAXTABLECOLUMNS; j++) {
                                    if (j== ROWWORDTOKEN) {
                                        word = data[i][j].toString();
                                        if ((splitWord) && (word.endsWith("-")))  {
                                            str = word.substring(0,word.length()-1);
                                        }
                                        else {
                                            str = word;
                                        }
                                    }
                                    else if (j== ROWSYNSET) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG1) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG2) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG3) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG4) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG5) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG6) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG7) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                    else if (j== ROWTAG8) {
                                        if (!splitWord) {
                                              if ((j==1) && (data[i][ROWSTATUS].equals(new Boolean(false)))) {
                                                   str+= " XX";
                                              }
                                              else {
                                                   str +=" " + data[i][j].toString();
                                              }
                                              if (word .equals(".")) {
                                                   str += "\n";
                                              }
                                        }
                                    }
                                }
                                if (!splitWord) {str += "\n";}
                                fos.write(str.getBytes(), 0, str.length());
                                str = "";
                      }
                      fos.close();
                    }
                    catch (Exception e){ e.printStackTrace();}
            }



}
