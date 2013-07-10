package eu.kyotoproject.kafannotator.tableware;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 8:17:14 PM
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
public class TableSorter extends AbstractTableModel {
    private TableModel model;
    private int sortColumn;
    private Row[] rows;
//    private Row[] remainingrows;
    private int nRowsExcludedAtEnd = 0;
    final int ASCENDING = 0;
    final int DESCENDING = 1;
    int DIRECTION = ASCENDING;


    public TableSorter(TableModel m, int upto) {
		model = m;
		DIRECTION = ASCENDING;
		nRowsExcludedAtEnd = upto;
		rows = new Row[model.getRowCount()];
		for (int i = 0; i < rows.length; i++)
		{	rows[i] = new Row();
			rows[i].index = i;
		}
    }

	public void sort(int c) {
		sortColumn = c;
		Arrays.sort(rows);
		if (DIRECTION == DESCENDING)
		{  DIRECTION = ASCENDING;}
		else
		{ DIRECTION = DESCENDING;}
		fireTableDataChanged();
	}

    public void retro_sort(int c) {
		sortColumn = c;
		Arrays.sort(rows);
		if (DIRECTION == DESCENDING)
		{  DIRECTION = ASCENDING;}
		else
		{ DIRECTION = DESCENDING;}
		fireTableDataChanged();
	}


    // There is no-where else to put this.
    // Add a mouse listener to the Table to trigger a table sort
    // when a column heading is clicked in the JTable.
    public void addMouseListenerToHeaderInTable(final JTable table) {
		table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
				// check for click
//				if (event.getClickCount() < 2) {   return;}
				// find column of click
				int tableColumn = table.columnAtPoint(event.getPoint());
				// translate to table model index and sort
				int modelColumn = table.convertColumnIndexToModel(tableColumn);
				sort(modelColumn);
			}
        });
    }

    // The mapping only affects the contents of the data rows.
    // Pass all requests to these rows through the mapping array: "indexes".

    public Object getValueAt(int r, int c) {
        return model.getValueAt(rows[r].index, c);
    }

    public boolean isCellEditable(int r, int c) {
	return model.isCellEditable(rows[r].index, c);
    }

    public void setValueAt(Object aValue, int r, int c) {
        model.setValueAt(aValue, rows[r].index, c);
    }

	public int getRowCount() {
		return model.getRowCount();
	}

	public int getColumnCount() {
		 return model.getColumnCount();
	}

	public String getColumnName(int c) {
		return model.getColumnName(c);
	}

	public Class getColumnClass(int c) {
		return model.getColumnClass(c);
	}

    public void setRowFilter(RowFilter<AnnotationTableModel,Object> rf) {
    }


    /* This inner class holds the index of the model row
        Rows are compared by looking at the model row entries
        in the sort column
        */

	private class Row implements Comparable
	{	public int index;
		public int compareTo(Object other) {
			Row otherRow = (Row)other;
			Object a = model.getValueAt(index,sortColumn);
			Object b = model.getValueAt(otherRow.index,sortColumn);
//			Object aLabel = model.getValueAt(index,0);
//			Object bLabel = model.getValueAt(otherRow.index,0);
//			System.out.println("Direction is:"+DIRECTION);
//			System.out.println("a:"+a.toString()+"; at index:"+index);
//			System.out.println("b:"+b.toString()+"; at otherRow.index:"+otherRow.index);
//			if (otherRow.index > nRowsExcludedAtEnd)
/*		      if ((aLabel.toString().equals("ALL CLASSES")) || (bLabel.toString().equals("ALL CLASSES")))
		      {  System.out.println("ALL CLASSES");
			   return nRowsExcludedAtEnd+1;
		      }
		      if ((aLabel.toString().equals("BELOW THRESHOLD")) || (bLabel.toString().equals("BELOW THRESHOLD")))
		      {  System.out.println("BELOW THRESHOLD");
			   return nRowsExcludedAtEnd+2;
		      }*/
			if (a instanceof Comparable)
			{   if (DIRECTION == ASCENDING)
			    {  return ((Comparable) a).compareTo(b);
			    }
			    else
			    {  return ((Comparable) b).compareTo(a);
			    }
			}
			else
			{	return index - otherRow.index;}
		}
	}

}
