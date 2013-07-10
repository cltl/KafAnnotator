package eu.kyotoproject.kafannotator.util;

import eu.kyotoproject.kaf.KafSaxParser;
import eu.kyotoproject.kaf.KafWordForm;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;

/**
     * Created by IntelliJ IDEA.
     * User: kyoto
     * Date: 2/2/11
     * Time: 8:57 PM
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
    public class FixTagsWithKaf {



        static public void main (String[] args) {
                String kafFile = args[0];
                String tagFile = args[1];
                KafSaxParser parser = new KafSaxParser();
                parser.parseFile(kafFile);
                AnnotationTableModel tableModel = new AnnotationTableModel();
                tableModel.addJustAnnotations(tagFile);
                int gap = 0;
                int kafpos = 0;
                int oldpos = 0;
                int orderStep = 0;
                int previousOrder = 0;
                int currentOrder = 0;
                int pos = 0;
                for (int i = 0; i < tableModel.nTABLEROWS; i++) {
                    String wordId = (String) tableModel.getValueAt(i, AnnotationTableModel.ROWID);
                    String wordToken = (String) tableModel.getValueAt(i, AnnotationTableModel.ROWWORDTOKEN);
                    currentOrder = (Integer) tableModel.getValueAt(i, AnnotationTableModel.ROWORDER);
                    orderStep = currentOrder-previousOrder;
                    pos += orderStep;
                    kafpos = parser.getWordFormRank(wordId);
                    System.out.println("wordId = " + wordId);
                    System.out.println("wordToken = " + wordToken);
                    System.out.println("kafpos = " + kafpos);
                    System.out.println("currentOrder = " + currentOrder);
                    System.out.println("previousOrder = " + previousOrder);
                    System.out.println("orderStep = " + orderStep);
                    System.out.println("pos = " + pos);
                    if (pos>parser.kafWordFormList.size()) {
                        System.out.println("EXCEEDING KAF");
                    }
                    for (int j = pos; j < parser.kafWordFormList.size(); j++) {
                        KafWordForm kafWordForm =  parser.kafWordFormList.get(j);
                        if (kafWordForm.getWf().equals(wordToken)) {
                            String newId = kafWordForm.getWid();
                            System.out.println("newId = " + newId);
                            pos = j;
                            System.out.println("new pos = " + pos);
                            tableModel.setValueAt(newId, i,AnnotationTableModel.ROWID);
                            break;
                        }
                    }
                    previousOrder = currentOrder;
/*
                    if (kafpos>-1) {
                        pos = kafpos;
                        for (int j = pos; j < parser.kafWordFormList.size(); j++) {
                            KafWordForm kafWordForm =  parser.kafWordFormList.get(j);
                            if (kafWordForm.getWf().equals(wordToken)) {
                                String newId = kafWordForm.getWid();
                                System.out.println("newId = " + newId);
                                pos = j;
                                System.out.println("new pos = " + pos);
                                tableModel.setValueAt(newId, i,AnnotationTableModel.ROWID);
                                break;
                            }
                        }
                    }
                    else {
                        pos++;
                        System.out.println("COULD NOT FIND THE ID IN NEW KAF");
                        for (int j = pos; j < parser.kafWordFormList.size(); j++) {
                            KafWordForm kafWordForm =  parser.kafWordFormList.get(j);
                            if (kafWordForm.getWf().equals(wordToken)) {
                                String newId = kafWordForm.getWid();
                                System.out.println("newId = " + newId);
                                pos = j;
                                System.out.println("new pos = " + pos);
                                tableModel.setValueAt(newId, i,AnnotationTableModel.ROWID);
                                break;
                            }
                        }
                    }
*/
                }
                tableModel.writeTableToTagFile(tagFile+".2");
        }
    }
