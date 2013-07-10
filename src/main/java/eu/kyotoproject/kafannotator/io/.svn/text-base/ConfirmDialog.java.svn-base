package eu.kyotoproject.kafannotator.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 21, 2010
 * Time: 4:37:53 PM
 * To change this template use File | Settings | File Templates.
 *     This file is part of KafAnnotator.

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
public class ConfirmDialog extends JDialog {
	 JLabel TEXT1;
	 JLabel TEXT2;
     JLabel TEXT3;
     JLabel TEXT4;
     JLabel TEXT5;
     JLabel EMPTY;
	 public int DOIT = 0;
	 int width = 100;
	 int widthFactor = 7;
	 String TAB = "    ";
	 JButton save = new JButton("SAVE");
	 JButton cancel = new JButton("CANCEL");
	 JButton quit = new JButton("CANCEL");

    public ConfirmDialog(Container parent, String text1, String text2) {
         super((JFrame) parent, true);
         this.setTitle("Please Confirm");
         DOIT = 0;
         this.setLocation(300,300);
         this.setModal(true);
         this.toFront();
       // this.setAlwaysOnTop(true);
         init(text1, text2, "", "", "");
    }

    public ConfirmDialog(Container parent, String text1, String text2, String text3) {
         super((JFrame) parent, true);
         this.setTitle("Please Confirm");
         DOIT = 0;
         this.setLocation(300,300);
         this.setModal(true);
         this.toFront();
       // this.setAlwaysOnTop(true);
         init(text1, text2, text3, "", "");
    }

    public ConfirmDialog(Container parent, String text1, String text2, String text3, String text4) {
         super((JFrame) parent, true);
         this.setTitle("Please Confirm");
         DOIT = 0;
         this.setLocation(300,300);
         this.setModal(true);
         this.toFront();
        //this.setAlwaysOnTop(true);
         init(text1, text2, text3, text4, "");
    }

    public ConfirmDialog(Container parent, String text1, String text2, String text3, String text4, String text5) {
         super ((JFrame) parent);
         this.setTitle("Please Confirm");
         DOIT = 0;
         this.setLocation(300,300);
         this.setModal(true);
         this.toFront();
        //this.setAlwaysOnTop(true);
         EMPTY = new JLabel();
         EMPTY.setMinimumSize(new Dimension (0, 25));
         EMPTY.setPreferredSize(new Dimension (0, 25));
         init(text1, text2, text3, text4, text5);
    }

  void init(final String text1, final String text2, final String text3, final String text4, final String text5) {

/*
      Thread t = new Thread(new Runnable() {
      public void run() {
*/
       int nextWidth = 0;
       EMPTY = new JLabel();
       EMPTY.setMinimumSize(new Dimension (0, 25));
       EMPTY.setPreferredSize(new Dimension (0, 25));
	   Box b = Box.createVerticalBox();
       if (text1.length()>0) {
           nextWidth =100+(text1.length()*widthFactor);
           if (nextWidth>width) {width = nextWidth;}
           TEXT1 = new JLabel(TAB+text1);
           TEXT1.setAutoscrolls(true);
           TEXT1.setMinimumSize(new Dimension(width, 25));
           TEXT1.setPreferredSize(new Dimension(width, 25));
           b.add(Box.createGlue());
           b.add(TEXT1);
       }
	   if (text2.length()>0) {
//         b.add(EMPTY);
         nextWidth =100+(text2.length()*widthFactor);
         if (nextWidth>width) {width = nextWidth;}
	     TEXT2 = new JLabel(TAB+text2);
	     TEXT2.setMinimumSize(new Dimension(width, 25));
	     TEXT2.setPreferredSize(new Dimension(width, 25));
	     b.add(Box.createGlue());
	     b.add(TEXT2);
	   }
	   if (text3.length()>0) {
//         b.add(EMPTY);
	     nextWidth =100+(text3.length()*widthFactor);
         if (nextWidth>width) {width = nextWidth;}
	     TEXT3 = new JLabel(TAB+text3);
	     TEXT3.setMinimumSize(new Dimension(width, 25));
	     TEXT3.setPreferredSize(new Dimension(width, 25));
	     b.add(Box.createGlue());
	     b.add(TEXT3);
	   }
       if (text4.length()>0) {
//         b.add(EMPTY);
         nextWidth =100+(text4.length()*widthFactor);
         if (nextWidth>width) {width = nextWidth;}
         TEXT4 = new JLabel(TAB+text4);
         TEXT4.setMinimumSize(new Dimension(width, 25));
         TEXT4.setPreferredSize(new Dimension(width, 25));
         b.add(Box.createGlue());
         b.add(TEXT4);
       }
       if (text5.length()>0) {
//        b.add(EMPTY);
        nextWidth =100+(text5.length()*widthFactor);
        if (nextWidth>width) {width = nextWidth;}
        TEXT5 = new JLabel(TAB+text5);
        TEXT5.setMinimumSize(new Dimension(width, 25));
        TEXT5.setPreferredSize(new Dimension(width, 25));
        b.add(Box.createGlue());
        b.add(TEXT5);
       }
	   b.createRigidArea(new Dimension(300, 250));
	   b.setSize(new Dimension(300, 250));
	   getContentPane().add(b, "Center");

	   JPanel p2 = new JPanel();
       save = new JButton("Save first");
       cancel = new JButton("Cancel");
       quit = new JButton("Quit");
	   save.setMnemonic(KeyEvent.VK_ENTER);

	   p2.add(save);
	   p2.add(cancel);
	   p2.add(quit);
	   getContentPane().add(p2, "South");
	   save.requestFocus();
	   save.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent evt) {
					    DOIT = 0;
					    setVisible (false);
					}
	            }
	   );
	   save.addFocusListener(new FocusListener() {
	           public void focusGained(FocusEvent e) {
			     getRootPane().setDefaultButton(save);
			   }
			   public void focusLost(FocusEvent e) {
			   }
	       }
	   );

	   cancel.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent evt) {
					    DOIT = 1;
					    setVisible (false);
					}
	            }
	   );
	   cancel.addFocusListener(new FocusListener() {
	           public void focusGained(FocusEvent e) {
			     getRootPane().setDefaultButton(cancel);
			   }
			   public void focusLost(FocusEvent e) {
			   }
	       }
	   );

	   quit.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent evt) {
					    DOIT = 2;
					    setVisible (false);
					}
	            }
	   );
	   quit.addFocusListener(new FocusListener() {
	           public void focusGained(FocusEvent e) {
			     getRootPane().setDefaultButton(quit);
			   }
			   public void focusLost(FocusEvent e) {
			   }
	       }
	   );
       setSize(300,250);
       setResizable(false);

       this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         }
       });
       pack();
       setVisible(true);
  }

  }