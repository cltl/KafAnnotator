package eu.kyotoproject.kafannotator.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 6, 2010
 * Time: 10:45:02 AM
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
public class GetIntegerDialog extends JDialog {
	 public JLabel label;
	 public JTextField field;
     public boolean DOIT = false;
	 JButton ok = new JButton("OK");
	 JButton cancel = new JButton("CANCEL");
	 int width = 0;
	 int widthFactor = 1;
	 String TAB = "    ";

  public GetIntegerDialog(String label, String value) {
	   super (new JFrame(), label, true);
         JFrame parent = (JFrame) this.getParent();
//	   this.setLocationRelativeTo(parent);
	   this.setLocation(400,200);
	   this.setModal(true);
	   this.toFront();
	   DOIT = false;
       Box b = Box.createVerticalBox();
	   b.add(Box.createGlue());
	   this.label = new JLabel(label);
	   field = new JTextField(value);
	   width =(value.length()*widthFactor);
	   field.setMinimumSize(new Dimension(width, 25));
	   field.setPreferredSize(new Dimension(width, 25));
	   b.add(this.label);
	   b.add(field);
	   b.add(Box.createGlue());
	   b.createRigidArea(new Dimension(250, 300));
	   getContentPane().add(b, "Center");

	   JPanel p2 = new JPanel();
//	   save.setMnemonic('o');
	   ok.setMnemonic(KeyEvent.VK_ENTER);
	   p2.add(ok);
	   p2.add(cancel);
	   getContentPane().add(p2, "South");
	   ok.requestFocus();
	   this.setSize(350,300);
	   this.pack();
	   ok.addActionListener(new ActionListener()
	            { public void actionPerformed(ActionEvent evt)
			  {   DOIT = true;
                  setVisible (false);
			  }
	            }
	   );

	   ok.addFocusListener(new FocusListener()
	            {  public void focusGained(FocusEvent e) {
			     getRootPane().setDefaultButton(ok);
			   }

			   public void focusLost(FocusEvent e) {
			   }
	            }
	   );

	   cancel.addActionListener(new ActionListener()
		  { public void actionPerformed(ActionEvent evt)
			{ setVisible (false);}
		  }
	   );

	   cancel.addFocusListener(new FocusListener()
	            {  public void focusGained(FocusEvent e) {
			     getRootPane().setDefaultButton(cancel);
			   }

			   public void focusLost(FocusEvent e) {
			   }
	            }
	   );
  }
}
