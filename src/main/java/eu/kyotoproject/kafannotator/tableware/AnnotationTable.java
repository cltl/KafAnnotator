package eu.kyotoproject.kafannotator.tableware;

import eu.kyotoproject.kaf.KafSense;
import eu.kyotoproject.kaf.KafTerm;
import eu.kyotoproject.kafannotator.AnnotatorFrame;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.sensetags.SynsetSelectorFrame;
import eu.kyotoproject.kafannotator.util.Colors;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 4:53:14 PM
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
public class AnnotationTable extends JPanel implements TableModelListener {
    static public boolean READFILE = false;
    static AnnotatorFrame mainFrame = null;
    public   AnnotationTableModel theTable;
    public TableRowSorter<AnnotationTableModel> rowSorter;
    public   JTable table;
    public   JScrollPane scrollPane;
    public   TableSorter sorter;
    public   JComboBox tagCombo;
    JPopupMenu popupMenu = new JPopupMenu();
    MouseListener popupListener = new PopupListener();
    SynsetSelectorFrame synsetSelector;


    void makePopUpMenu () {
         popupListener = new PopupListener();    
         popupMenu = new JPopupMenu();
         JMenuItem ensense = new JMenuItem("English wordnet");
         ensense.addActionListener(new InsertRowsActionAdapter(mainFrame,"wnen30", "en"));
         JMenuItem nlsense = new JMenuItem("Dutch wordnet");
         nlsense.addActionListener(new InsertRowsActionAdapter(mainFrame,"wnnld", "nl"));
         JMenuItem spsense = new JMenuItem("Spanish wordnet");
         spsense.addActionListener(new InsertRowsActionAdapter(mainFrame,"wnspa", "sp"));
         JMenuItem basense = new JMenuItem("Basque wordnet");
         basense.addActionListener(new InsertRowsActionAdapter(mainFrame,"wnbas", "ba"));
         popupMenu.add(ensense);
         popupMenu.add(nlsense);
         popupMenu.add(spsense);
         popupMenu.add(basense);
         table.setComponentPopupMenu(popupMenu);
    }


    class InsertRowsActionAdapter implements ActionListener {
      JFrame adaptee;
      String wndb = "";
      String language = "";
      InsertRowsActionAdapter(JFrame adaptee, String db, String lg) {
        this.adaptee = adaptee;
        wndb = db;
        language = lg;
        }


      public void actionPerformed(ActionEvent e) {
          DO_SelectSenses (wndb, language);
      }

    }

    class PopupListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
          showPopup(e);

      }
      public void mouseReleased(MouseEvent e) {
        showPopup(e);
      }
      private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
    }


    void DO_SelectSenses (String db, String lg) {
        if (table.getSelectedRowCount()>1) {
           String synsetValue = "";
           String wordType = "";
           int[] rows = table.getSelectedRows();
            //// get first value
            for (int i = 0; i < rows.length; i++) {
                int row = rows[i];
                synsetValue = (String) table.getValueAt(row, theTable.ROWSYNSET);
                if (synsetValue.trim().length()>0) {
                    wordType = (String) table.getValueAt(row, theTable.ROWWORDTYPE);
                    break;
                }
            }
            for (int i = 0; i < rows.length; i++) {
                int row = rows[i];
                String type = (String) table.getValueAt(row, theTable.ROWWORDTYPE);
                if (type.equals(wordType)) {
                     table.setValueAt(synsetValue, row, theTable.ROWSYNSET);
                }
            }
        }
        else {
            int theRow = table.getSelectedRow();
            if (theRow>-1) {
                String tokenId = (String) table.getValueAt(theRow, AnnotationTableModel.ROWID);
                String lemma =  (String) table.getValueAt(theRow, AnnotationTableModel.ROWWORDTYPE);
                if (tokenId.length()>0) {
                   // System.out.println("tokenId = " + tokenId);
                    ArrayList<KafSense> synsetMappings = new ArrayList<KafSense>();
                    KafTerm term = null;
                    if (mainFrame.parser.WordFormToTerm.containsKey(tokenId)) {
                        String termId = mainFrame.parser.WordFormToTerm.get(tokenId);
                        term = mainFrame.parser.getTerm(termId);
                    }
                    else {

                    }
                    if (term!=null) {
                        for (int j = 0; j < term.getSenseTags().size(); j++) {
                            KafSense kafSense = term.getSenseTags().get(j);
                            synsetMappings.add(kafSense);
                        //    System.out.println("kafSense.getSensecode() = " + kafSense.getSensecode());
                        //    System.out.println("kafSense.toString() = " + kafSense.toString());
                        }
                    }
                    else {
                        System.out.println("NO Term found");
                    }
                    if (synsetSelector == null) {
                        //synsetSelector = new SynsetSelectorFrame(mainFrame, synsetMappings);
                        synsetSelector = new SynsetSelectorFrame(mainFrame, lemma, db, lg);
                        if (synsetSelector.DOIT) {
                            table.setValueAt((String)synsetSelector.selectedKafSense,theRow, theTable.ROWSYNSET);
                         //   System.out.println("synsetSelector = " + synsetSelector.selectedKafSense);
                        }
                    }
                    else {
                        //synsetSelector.rebuildSensePanel(synsetMappings, db, lg);
                        synsetSelector.rebuildSensePanel(lemma, db, lg);
                        synsetSelector.setVisible(true);
                        if (synsetSelector.DOIT) {
                            table.setValueAt((String)synsetSelector.selectedKafSense,theRow, theTable.ROWSYNSET);
                        //    System.out.println("synsetSelector = " + synsetSelector.selectedKafSense);
                        }
                    }
                }
            }
        }
    }


    public AnnotationTable(TableSettings tableSettings) {
        theTable = new AnnotationTableModel();
        theTable.addTableModelListener(this);
        synsetSelector = null;
        init();
        this.setColumnSize();
        this.hideColumns(tableSettings);
    }

    public AnnotationTable(ArrayList<WordTag> wordTags, TableSettings tableSettings) {
        READFILE = true;
        theTable = new AnnotationTableModel(wordTags);
        theTable.addTableModelListener(this);
        synsetSelector = null;
        reinit();
        //makePopUpMenu();
        //// necessary to access the parser data for the KAF file
        mainFrame = AnnotatorFrame.getInstance(tableSettings);
        this.hideColumns(tableSettings);
    }

    public void init() {
        sorter = new TableSorter(theTable,theTable.nTABLEROWS);
        table = new JTable(sorter);
        rowSorter = new TableRowSorter<AnnotationTableModel>();
        table.getModel().addTableModelListener(this);
        table.setBackground(Colors.BackGroundColor);
        table.setForeground(Colors.ForegroundColor);

        table.addMouseMotionListener(new MouseMotionAdapter(){
/*            public void mouseMoved(MouseEvent e){
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (column==0) {
*//*                   Dimension dim = new Dimension();
                    dim.setSize(200,200);
                    final JPopupMenu popupMenu = new JPopupMenu();
                    JLabel par = new JLabel(getSentenceContext(row));
                    JTextArea area = new JTextArea(getSentenceContext(row));
                    area.setWrapStyleWord(true);
                    area.setMinimumSize(dim);
                    area.setMaximumSize(dim);
                    popupMenu.add(area);
                    popupMenu.setSize(100,100);
                    popupMenu.setVisible(true);*//*

                    table.setToolTipText(String.valueOf(getSentenceContext(row)));
                }
            }*/

            public void mouseMoved(MouseEvent e){
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (column==0) {
                    table.setToolTipText(String.valueOf(getSentenceContext(row)));
                    //table.setToolTipText(String.valueOf(get3SentencesContext(row)));
                }
            }
        });
//        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
/*
        table.setMinimumSize(new Dimension(600, 400));
        table.setPreferredSize(new Dimension(600, 400));
*/

        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 255, 220));
//        scrollPane.setForeground(new Color(80, 0, 160));
        scrollPane.setPreferredSize(new Dimension(800,200));
        scrollPane.setAutoscrolls(true);
        this.setLayout(new GridBagLayout());
        this.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 3.0, 3.0
                      ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 10, 10));
        sorter.addMouseListenerToHeaderInTable(table);
  //      setColumnSize();
        initTagCombo();
        this.setVisible(true);
        this.repaint();
    }



    public void reinit() {
        sorter = new TableSorter(theTable,theTable.getRowCount());
        table = new JTable(sorter);
        rowSorter = new TableRowSorter<AnnotationTableModel>();
        table.setBackground(new Color(255, 255, 220));
        table.setForeground(new Color(80, 0, 160));
        table.setRowSelectionAllowed(true);
        table.addMouseMotionListener(new MouseMotionAdapter(){
/*            public void mouseMoved(MouseEvent e){
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (column==0) {
               //     System.out.println(getSentenceContext(row));
*//*
                    final JPopupMenu popupMenu = new JPopupMenu();
                    JLabel par = new JLabel(get3SentencesContext(row));
                    JTextArea area = new JTextArea(get3SentencesContext(row));
                    area.setWrapStyleWord(true);
                    popupMenu.add(area);
                    popupMenu.setSize(100,100);
                    popupMenu.setVisible(true);
                    popupMenu.show();
*//*
                    table.setToolTipText(String.valueOf(getSentenceContext(row)));
                   // table.setToolTipText(String.valueOf(get3SentencesContext(row)));
                }
            }*/

            public void mouseMoved(MouseEvent e){
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (column==0) {
                    table.setToolTipText(String.valueOf(getSentenceContext(row)));
                   // table.setToolTipText(String.valueOf(get3SentencesContext(row)));
                }
            }
        });

 //       table.setPreferredScrollableViewportSize(new Dimension(600, 400));

        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 255, 220));
        scrollPane.setForeground(new Color(80, 0, 160));
  //      scrollPane.setPreferredSize(new Dimension(600,400));
        this.setLayout(new GridBagLayout());
        this.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 3.0, 3.0
                      ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 10, 10));
        this.setAutoscrolls(true);
        sorter.addMouseListenerToHeaderInTable(table);
        initTagCombo();
        this.setVisible(true);
        this.repaint();
    }

    public void removeAllTags() {
        for (int theRow = 0; theRow<theTable.getRowCount();theRow++) {
            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG1);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID1);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG2);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID2);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG3);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID3);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG4);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID4);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID5);
            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG5);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG6);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID6);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG7);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID7);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);

            sorter.setValueAt("", theRow, AnnotationTableModel.ROWTAG8);
            sorter.setValueAt(0, theRow, AnnotationTableModel.ROWTAGID8);
            sorter.setValueAt(new Boolean(false), theRow, AnnotationTableModel.ROWSTATUS);
        }
    }

    String getSentenceContext (int row) {
        String context = (String) table.getValueAt(row, theTable.ROWWORDTOKEN);
        Integer sentenceId = (Integer)table.getValueAt(row, theTable.ROWSENTENCE);
        Integer rowSentenceId = new Integer(-1);
        int nRow = row-1;
        while ((nRow>0)) {
            rowSentenceId = (Integer)table.getValueAt(nRow, theTable.ROWSENTENCE);
            if (rowSentenceId.equals(sentenceId) || rowSentenceId.intValue()==0) {
                context =  (String) table.getValueAt(nRow, theTable.ROWWORDTOKEN) + " " + context;
                nRow--;
            }
            else {
                break;
            }
        }
        nRow = row+1;
        while ((nRow<theTable.getRowCount())) {
            rowSentenceId = (Integer)table.getValueAt(nRow, theTable.ROWSENTENCE);
            if (rowSentenceId.equals(sentenceId) || rowSentenceId.intValue()==0) {
                context += " " + (String) table.getValueAt(nRow, theTable.ROWWORDTOKEN);
                nRow++;
            }
            else {
                break;
            }
        }
        return context;
    }

    String get3SentencesContext (int row) {
        String context = (String) table.getValueAt(row, theTable.ROWWORDTOKEN);
        Integer sentenceId = (Integer)table.getValueAt(row, theTable.ROWSENTENCE);
        Integer rowSentenceId = new Integer(-1);
        int nRow = row-1;
        while ((nRow>0)) {
            rowSentenceId = (Integer)table.getValueAt(nRow, theTable.ROWSENTENCE);
            if ((rowSentenceId.intValue()>(sentenceId.intValue()-3)) || rowSentenceId.intValue()==0) {
                context =  (String) table.getValueAt(nRow, theTable.ROWWORDTOKEN) + " " + context;
                nRow--;
            }
            else {
                break;
            }
        }
        nRow = row+1;
        while ((nRow<theTable.getRowCount())) {
            rowSentenceId = (Integer)table.getValueAt(nRow, theTable.ROWSENTENCE);
          //  System.out.println("nRow = " + table.getValueAt(nRow, theTable.ROWWORDTOKEN));
            if ((rowSentenceId.intValue()<(sentenceId.intValue()+3)) || rowSentenceId.intValue()==0) {
                context += " " + (String) table.getValueAt(nRow, theTable.ROWWORDTOKEN);
                nRow++;
            }
            else {
                break;
            }
        }
        return context;
    }

    public void setColumnSize() {
		table.getColumnModel().getColumn(AnnotationTableModel.ROWID).setPreferredWidth(30);
		table.getColumnModel().getColumn(AnnotationTableModel.ROWWORDTOKEN).setPreferredWidth(60);
		table.getColumnModel().getColumn(AnnotationTableModel.ROWWORDTYPE).setPreferredWidth(60);
		table.getColumnModel().getColumn(AnnotationTableModel.ROWPOS).setPreferredWidth(10);
		table.getColumnModel().getColumn(AnnotationTableModel.ROWSYNSET).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWORDER).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG1).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID1).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG2).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID2).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG3).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID3).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG4).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID4).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG5).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID5).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG6).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID6).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG7).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID7).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAG8).setPreferredWidth(30);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWTAGID8).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWSTATUS).setPreferredWidth(10);
        table.getColumnModel().getColumn(AnnotationTableModel.ROWSENTENCE).setPreferredWidth(10);
    }

    public void initTagCombo() {
/*
        tagCombo = new JComboBox();
        if (mainFrame!=null) {
            for (int i = 0; i < mainFrame.theTag1Set.size(); i++) {
                String tag = mainFrame.theTag1Set.get(i);
                tagCombo.addItem(tag);
            }
            TableColumnModel columnModel = table.getColumnModel();
            TableColumn column = columnModel.getColumn(AnnotationTableModel.ROWTAG1);
            column.setCellEditor(new DefaultCellEditor(tagCombo));
        }
*/
    }

    public void hideUntaggedRows () {
        for (int i=0; i < theTable.nTABLEROWS; i++) {
            String str = (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG1);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG2);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG3);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG4);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG5);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG6);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG7);
            str += (String) theTable.getValueAt(i,AnnotationTableModel.ROWTAG8);
            RowFilter<AnnotationTableModel, Integer> rf = null;
            rf = makeRowFilter(str.length());
           // RowFilter.notFilter(theTable, str.length()>0);
            //RowFilter.notFilter();
            rowSorter.setRowFilter(rf);
        }
    }



    private RowFilter< AnnotationTableModel, Integer > makeRowFilter(final int annotationLength) {
      return new RowFilter< AnnotationTableModel, Integer >() {
        @Override
        public boolean include(Entry< ? extends AnnotationTableModel, ? extends Integer > entry) {
          int ei = entry.getIdentifier();
          return (annotationLength>0);
        }
      };
    }



    public void tableChanged(TableModelEvent e) {
        if (!READFILE)  {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String word = (String) theTable.getValueAt(row, AnnotationTableModel.ROWWORDTOKEN);
            String tag = (String) theTable.getValueAt(row, AnnotationTableModel.ROWTAG1);
            if (column==theTable.ROWTAG1) {
               mainFrame.tagLexicon.incrementEntry(word, tag);

               //System.out.println("TAG CHANGED");
            }
            else if (column==theTable.ROWTAG1) {
               mainFrame.tagLexicon.incrementEntry(word, tag);

               //System.out.println("TAG CHANGED");
            }
            else if (column==theTable.ROWTAG1) {
               mainFrame.tagLexicon.incrementEntry(word, tag);

               //System.out.println("TAG CHANGED");
            }
            else if (column==theTable.ROWSTATUS) {
                //System.out.println("STATUS CHANGED");
                if (theTable.getValueAt(row,AnnotationTableModel.ROWSTATUS).equals(new Boolean(true))) {
                    mainFrame.tagLexicon.incrementEntry(word, tag);
                }
                else {
                    mainFrame.tagLexicon.decrementEntry(word, tag);
                }
            }
        }
    }

    void hideAColumn (int column) {
        table.getColumnModel().getColumn(column).setMaxWidth(0);
        table.getColumnModel().getColumn(column).setMinWidth(0);
        table.getColumnModel().getColumn(column).setWidth(0);
        table.getColumnModel().getColumn(column).setPreferredWidth(0);
        table.getColumnModel().getColumn(column).setResizable(false);
    }

    public void hideColumns (TableSettings tableSettings) {
        if (tableSettings.hideStatus) {
            hideAColumn(AnnotationTableModel.ROWSTATUS);
        }
        if (tableSettings.hideOrder) {
            hideAColumn(AnnotationTableModel.ROWORDER);
        }
        if (tableSettings.hideTag8) {
            hideAColumn(AnnotationTableModel.ROWTAG8);
            hideAColumn(AnnotationTableModel.ROWTAGID8);
        }
        if (tableSettings.hideTag7) {
            hideAColumn(AnnotationTableModel.ROWTAG7);
            hideAColumn(AnnotationTableModel.ROWTAGID7);
        }
        if (tableSettings.hideTag6) {
            hideAColumn(AnnotationTableModel.ROWTAG6);
            hideAColumn(AnnotationTableModel.ROWTAGID6);
        }
        if (tableSettings.hideTag5) {
            hideAColumn(AnnotationTableModel.ROWTAG5);
            hideAColumn(AnnotationTableModel.ROWTAGID5);
        }
        if (tableSettings.hideTag4) {
            hideAColumn(AnnotationTableModel.ROWTAG4);
            hideAColumn(AnnotationTableModel.ROWTAGID4);
        }
        if (tableSettings.hideTag3) {
            hideAColumn(AnnotationTableModel.ROWTAG3);
            hideAColumn(AnnotationTableModel.ROWTAGID3);
        }
        if (tableSettings.hideTag2) {
            hideAColumn(AnnotationTableModel.ROWTAG2);
            hideAColumn(AnnotationTableModel.ROWTAGID2);
        }
        if (tableSettings.hideTagIds) {
            hideAColumn(AnnotationTableModel.ROWTAGID1);
            hideAColumn(AnnotationTableModel.ROWTAGID2);
            hideAColumn(AnnotationTableModel.ROWTAGID3);
            hideAColumn(AnnotationTableModel.ROWTAGID4);
            hideAColumn(AnnotationTableModel.ROWTAGID5);
            hideAColumn(AnnotationTableModel.ROWTAGID6);
            hideAColumn(AnnotationTableModel.ROWTAGID7);
            hideAColumn(AnnotationTableModel.ROWTAGID8);
        }
        if (tableSettings.hideLabel) {
            hideAColumn(AnnotationTableModel.ROWSYNSET);
        }
        if (tableSettings.hideTerms) {
            hideAColumn(AnnotationTableModel.ROWWORDTYPE);
        }
        if (tableSettings.hidePos) {
            hideAColumn(AnnotationTableModel.ROWPOS);
        }
    }

    void initMatches (int[] boos) {
        for (int i = 0; i < boos.length; i++) {
           boos[i] = -1;
        }
    }

    boolean matchPhrase (int[] boos) {
        for (int i = 0; i < boos.length; i++) {
            if (boos[i]==-1) {
                return false;
            }
        }
        return true;
    }

    public void selectMatchingRows (int col, String selectedText) {
        if (selectedText!=null && !selectedText.isEmpty()) {
            String [] words = selectedText.trim().split(" ");
            int factor = table.getRowHeight();
            int currentRow = table.getSelectedRow();
            int numRows = theTable.nTABLEROWS;
            String str = "";
            if (currentRow==-1) {
                currentRow = 0;
            }
            int [] matches = new int[words.length];
            initMatches(matches);
            for (int i=currentRow+1; i < numRows; i++) {
                str = (String) theTable.getValueAt(i,col);
                if (str.equals(words[0])) {/// check the first word
                    initMatches(matches);
                    matches [0] = i;
                    for (int j = 1; j < words.length; j++) {
                        String word = words[j];
                        /// move to the next row in the table
                        i++;
                        if (i<numRows) {/// check if we do not exceed the rows
                            str = (String) theTable.getValueAt(i,col);
                            if (str.equals(word)) {
                                matches [j] = i;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                    if (matchPhrase(matches)) {
                        //table.setRowSelectionInterval(matches[0],matches[0]);
                        table.setRowSelectionInterval(matches[0],matches[words.length-1]);
                        factor = factor*(matches[0]-10);
                        if (factor<0) {factor = 0;}
                        JScrollBar bar = scrollPane.getVerticalScrollBar();
                        bar.setValue(factor);
                        return; /// we are done
                    }
                }
            }
            if (!matchPhrase(matches)) {
                if (currentRow>0) {
                    for (int i=0 ; i < currentRow; i++) {
                        str = (String) theTable.getValueAt(i,col);
                        if (str.equals(words[0])) {/// check the first word
                            initMatches(matches);
                            matches [0] = i;
                            for (int j = 1; j < words.length; j++) {
                                String word = words[j];
                                /// move to the next row in the table
                                i++;
                                if (i<numRows) {/// check if we do not exceed the rows
                                    str = (String) theTable.getValueAt(i,col);
                                    if (str.equals(word)) {
                                        matches [j] = i;
                                    }
                                    else {
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                            if (matchPhrase(matches)) {
                                table.setRowSelectionInterval(matches[0],matches[words.length-1]);
                                factor = factor*(matches[0]-10);
                                if (factor<0) {factor = 0;}
                                JScrollBar bar = scrollPane.getVerticalScrollBar();
                                bar.setValue(factor);
                                return; /// we are done
                            }
                        }
                    }
                }
            }
        }
    }

    public String searchForString(int col, String value) {
         int factor = table.getRowHeight();
         String message = "";
         int currentRow = table.getSelectedRow();
         int numRows = theTable.nTABLEROWS;
         String str = "";
         if (currentRow==-1) {
            currentRow = 0;
         }
         for (int i=currentRow+1; i < numRows; i++) {
             str = (String) theTable.getValueAt(i,col);
             if (str.equals(value)) {
                 message = "Found it at row: "+i;
                 table.setRowSelectionInterval(i,i);
                 factor = factor*(i-10);
                 if (factor<0) {factor = 0;}
                 JScrollBar bar = scrollPane.getVerticalScrollBar();
                 bar.setValue(factor);
                 return message;
             }
        }
        if (currentRow>0) {
            for (int i=0 ; i < currentRow; i++) {
                str = (String) theTable.getValueAt(i,col);
                if (str.equals(value)) {
                    message = "Found it at row: "+i;
                    table.setRowSelectionInterval(i,i);
                    factor = factor*(i-10);
                    if (factor<0) {factor = 0;}
                    JScrollBar bar = scrollPane.getVerticalScrollBar();
                    bar.setValue(factor);
                    return message;
                }
            }
        }

        message = "Could not find: "+ value;
        return message;
    }

    public String searchForString(int[] col, String value) {
         int factor = table.getRowHeight();
         String message = "";
         int currentRow = table.getSelectedRow();
         int numRows = theTable.nTABLEROWS;
         String str = "";
         if (currentRow==-1) {
            currentRow = 0;
         }
         for (int i=currentRow+1; i < numRows; i++) {
             for (int j = 0; j < col.length; j++) {
                 int c = col[j];
                 str = (String) theTable.getValueAt(i,c);
                 if (str.equals(value)) {
                     message = "Found it at row: "+i;
                     table.setRowSelectionInterval(i,i);
                     factor = factor*(i-10);
                     if (factor<0) {factor = 0;}
                     JScrollBar bar = scrollPane.getVerticalScrollBar();
                     bar.setValue(factor);
                     return message;
                 }
             }
        }
        if (currentRow>0) {
            for (int i=0 ; i < currentRow; i++) {
                for (int j = 0; j < col.length; j++) {
                    int c = col[j];
                    str = (String) theTable.getValueAt(i,c);
                    if (str.equals(value)) {
                        message = "Found it at row: "+i;
                        table.setRowSelectionInterval(i,i);
                        factor = factor*(i-10);
                        if (factor<0) {factor = 0;}
                        JScrollBar bar = scrollPane.getVerticalScrollBar();
                        bar.setValue(factor);
                        return message;
                    }
                }
            }
        }

        message = "Could not find: "+ value;
        return message;
    }

    public String searchForLastString(int[] col) {
         int factor = table.getRowHeight();
         String message = "";
         int numRows = theTable.nTABLEROWS;
         String str = "";
         int lastNonEmptyRow = 0;
         for (int i=0; i < numRows; i++) {
             for (int j = 0; j < col.length; j++) {
                 int c = col[j];
                 str = (String) theTable.getValueAt(i,c);
                 if (str.length()>0) {
                     lastNonEmptyRow = i;
                 }
             }
        }
        if (lastNonEmptyRow>0) {
            message = "Last tag ound it at row: "+lastNonEmptyRow;
            table.setRowSelectionInterval(lastNonEmptyRow,lastNonEmptyRow);
            factor = factor*(lastNonEmptyRow-10);
            if (factor<0) {factor = 0;}
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(factor);
            return message;
        }
        message = "Could not find last tag!";
        return message;
    }

    public String searchForBoolean(int col, boolean value) {
         int factor = table.getRowHeight();
         String message = "";
         int currentRow = table.getSelectedRow();
         int numRows = theTable.nTABLEROWS;
         if (currentRow==-1) {
            currentRow = 0;
         }
         for (int i=currentRow+1; i < numRows; i++) {
//            System.out.println("Row:"+i);
             if (theTable.getValueAt(i,col).equals(new Boolean(value))) {
                 message = "Found it at row: "+i;
                 table.setRowSelectionInterval(i,i);
                 factor = factor*(i-10);
                 if (factor<0) {factor = 0;}
                 JScrollBar bar = scrollPane.getVerticalScrollBar();
                 bar.setValue(factor);
                 return message;
             }
        }
        if (currentRow>0) {
            for (int i=0 ; i < currentRow; i++) {
    //            System.out.println("Row:"+i);
                if (theTable.getValueAt(i,col).equals(new Boolean(value))) {
                    message = "Found it at row: "+i;
                    table.setRowSelectionInterval(i,i);
                    factor = factor*(i-10);
                    if (factor<0) {factor = 0;}
                    JScrollBar bar = scrollPane.getVerticalScrollBar();
                    bar.setValue(factor);
                    return message;
                }
            }
        }

        message = "Could not find: "+ value;
        return message;
    }
    public String searchForInteger(int col, Integer value) {
         int factor = table.getRowHeight();
         String message = "";
         int currentRow = table.getSelectedRow();
         int numRows = theTable.nTABLEROWS;
         if (currentRow==-1) {
            currentRow = 0;
         }
         for (int i=currentRow+1; i < numRows; i++) {
//            System.out.println("Row:"+i);
             if (theTable.getValueAt(i,col).equals(new Integer(value))) {
                 message = "Found it at row: "+i;
                 table.setRowSelectionInterval(i,i);
                 factor = factor*(i-10);
                 if (factor<0) {factor = 0;}
                 JScrollBar bar = scrollPane.getVerticalScrollBar();
                 bar.setValue(factor);
                 return message;
             }
        }
        if (currentRow>0) {
            for (int i=0 ; i < currentRow; i++) {
    //            System.out.println("Row:"+i);
                if (theTable.getValueAt(i,col).equals(new Integer(value))) {
                    message = "Found it at row: "+i;
                    table.setRowSelectionInterval(i,i);
                    factor = factor*(i-10);
                    if (factor<0) {factor = 0;}
                    JScrollBar bar = scrollPane.getVerticalScrollBar();
                    bar.setValue(factor);
                    return message;
                }
            }
        }

        message = "Could not find: "+ value;
        return message;
    }

    public void setBackground() {

        TableCellRenderer renderer = new CustomTableCellRenderer();
        try
        {

            table.setDefaultRenderer( Class.forName
               ( "java.lang.String" ), renderer );
            table.setDefaultRenderer( Class.forName
               ( "java.lang.Integer" ), renderer );
            table.setDefaultRenderer( Class.forName
               ( "java.lang.Boolean" ), renderer );

        }
        catch( ClassNotFoundException ex )
        {
        //    ex.printStackTrace();
        }

    }
    
}
