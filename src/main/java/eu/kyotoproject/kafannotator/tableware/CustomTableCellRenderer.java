package eu.kyotoproject.kafannotator.tableware;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Sep 3, 2010
 * Time: 4:04:59 PM
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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
public class CustomTableCellRenderer extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent
       (JTable table, Object value, boolean isSelected,
       boolean hasFocus, int row, int column)
    {
        Component cell = super.getTableCellRendererComponent
           (table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            cell.setForeground(Color.black);
            cell.setBackground(Color.gray);
        }
        else {
            cell.setForeground(new Color(80, 0, 160));
            TableColumnModel colModel = table.getColumnModel();

            int nCol = colModel.getColumnCount();
            Integer sentenceId = -1;
            try {
              //  System.out.println("row = " + row);
              //  System.out.println("nCol = " + nCol);
                //sentenceId = (Integer)table.getValueAt(row, nCol-1);
                sentenceId = (Integer)table.getValueAt(row, AnnotationTableModel.ROWSENTENCE);
            } catch (Exception e) {
              //  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            String str = (String) table.getValueAt(row,AnnotationTableModel.ROWTAG1);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG2);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG3);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG4);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG5);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG6);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG7);
            str += (String) table.getValueAt(row,AnnotationTableModel.ROWTAG8);
            if (str.length()>0) {
                cell.setBackground(Color.white);
            }
            //  System.out.println("sentenceId = " + sentenceId);
            else if (sentenceId==0) {
                cell.setBackground(Color.lightGray);
            }
            else if (sentenceId%2==0) {
                cell.setBackground(new Color(238, 222, 255));
            }
            else {
                cell.setBackground(new Color(253, 248, 205));
            }
        }
        return cell;
    }
}
