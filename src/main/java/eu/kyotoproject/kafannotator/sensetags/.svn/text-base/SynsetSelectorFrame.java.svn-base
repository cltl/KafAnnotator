package eu.kyotoproject.kafannotator.sensetags;

import vu.debvisdicclient.SynsetLight;
import eu.kyotoproject.kaf.KafSense;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Sep 21, 2010
 * Time: 10:37:40 AM
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
public class SynsetSelectorFrame extends JDialog implements ActionListener {

    private Box b;
    final int edge = 0;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    Insets insets = new Insets(1, 1, 1, 1);
    Border panelBorder = BorderFactory.createEtchedBorder();
    public String selectedKafSense;
    private JButton ok = new JButton("OK");
    private JButton cancel = new JButton("CANCEL");
    public boolean DOIT = false;
    private JPanel synsetPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel gridpanel;
    String wndb = "";
    String language = "";


    public SynsetSelectorFrame(JFrame parent, String lemma, String wndb, String language) {

        super (parent, "General settings", true);
        b = Box.createVerticalBox();
        b.setBorder(panelBorder);
        this.wndb = wndb;
        this.language = language;
        makeOverallBox(lemma);
        init();
    }

    public SynsetSelectorFrame(JFrame parent, ArrayList<KafSense> senseMappings, String wndb, String language) {

        super (parent, "General settings", true);
        b = Box.createVerticalBox();
        b.setBorder(panelBorder);
        this.wndb = wndb;
        this.language = language;
        makeOverallBox(senseMappings);
        init();
    }

    void init() {
        getContentPane().add(b, "Center");
        this.setResizable(true);
        selectedKafSense = "";
        this.setLocation(100,100);
        this.setModal(true);
        this.toFront();
        this.setBackground(new Color(255, 255, 220));
        DOIT = false;

        ok.requestFocus();
        ok.setMnemonic(KeyEvent.VK_ENTER);
        ok.addActionListener(new ActionListener()
           { public void actionPerformed(ActionEvent evt)
             { DOIT = true;
               setVisible (false);
             }
           }
        );
        ok.addFocusListener(new FocusListener()
                {  public void focusGained(FocusEvent e) {
                        getRootPane().setDefaultButton(ok);
                        ok.setMnemonic(KeyEvent.VK_ENTER);
                        cancel.setMnemonic(KeyEvent.VK_BACK_SPACE);
                   }

                   public void focusLost(FocusEvent e) {
                   }
                }
        );

        cancel.addActionListener(new ActionListener()
           {      public void actionPerformed(ActionEvent evt)
                  {  DOIT = false;
                     setVisible (false);
                  }
           }
        );

        cancel.addFocusListener(new FocusListener()
                {     public void focusGained(FocusEvent e) {
                            getRootPane().setDefaultButton(cancel);
                            ok.setMnemonic(KeyEvent.VK_BACK_SPACE);
                            cancel.setMnemonic(KeyEvent.VK_ENTER);
                      }

                      public void focusLost(FocusEvent e) {
                      }
                 }
        );

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
          Object source = evt.getSource();
        System.out.println("source.getClass().getSimpleName() = " + source.getClass().getSimpleName());
          if (source.getClass().getSimpleName().equals("JCheckBox")) {
             if (((JCheckBox) source).isSelected()) {
                selectedKafSense = ((JCheckBox) source).getText();
                // System.out.println("selectedKafSense = " + selectedKafSense);
             }
             else {
                selectedKafSense = "";
             }
              System.out.println("selectedKafSense = " + selectedKafSense);
          }
    }

    public void makeOverallBox (String lemma) {
        makeButtonPanel();
        makeSensePanel(lemma);
        makeGridPanel();
        b.removeAll();
        b.validate();
        b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.createRigidArea(new Dimension(200, 400));
        b.add(gridpanel);
    }

    public void makeOverallBox (ArrayList<KafSense> mappings) {
        makeButtonPanel();
        makeSensePanel(mappings);
        makeGridPanel();
        b.removeAll();
        b.validate();
        b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.createRigidArea(new Dimension(200, 400));
        b.add(gridpanel);
    }

    public void makeButtonPanel() {
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        buttonPanel.setBackground(new Color(255, 255, 200));
    }

    public void makeSensePanel(String lemma) {
        synsetPanel.setLayout(gridBagLayout);

        ButtonGroup synsetGroup = new ButtonGroup();
        ArrayList<SynsetLight> lemmaSynsets = makeDebVisDicCall(lemma);
        for (int i = 0; i < lemmaSynsets.size(); i++) {
                SynsetLight synsetLight = lemmaSynsets.get(i);
                WordNetSenseInfo senseInfo = makeDebVisDicCall(wndb, language, synsetLight.getIdent(), 1);
                JCheckBox synsetBox = new JCheckBox(senseInfo.toString());
                if (i==0) {
                 //   synsetBox.setSelected(true);
                    String [] fields = synsetBox.getText().replace("\n", "").split("]");
                    if (fields.length>1) {
                        selectedKafSense = fields[0]+"]"+fields[1]+"]";
                    }
                    else {
                        selectedKafSense = (String)synsetBox.getText();
                    }
                 //   System.out.println("selectedKafSense = " + selectedKafSense);
                }
                synsetBox.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      JCheckBox cb = (JCheckBox)e.getSource();
                      if (cb.isSelected()) {
                          String [] fields = cb.getText().replace("\n", "").split("]");
                          if (fields.length>1) {
                              selectedKafSense = fields[0]+"]"+fields[1]+"]";
                          }
                          else {
                              selectedKafSense = (String)cb.getText();
                          }
                       //   System.out.println("selectedKafSense = " + selectedKafSense);
                        //  makeBrowserCall(cb.getText());
                      }
                  }
                });
                synsetGroup.add(synsetBox);
                synsetPanel.add(synsetBox, new GridBagConstraints(0, i, 1, 1, edge, edge
                        ,GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, insets, edge, edge));
        }
    }

    public void rebuildSensePanel(String lemma, String db, String lg) {
        synsetPanel.removeAll();
        this.wndb = db;
        this.language = lg;
        ButtonGroup synsetGroup = new ButtonGroup();
        ArrayList<SynsetLight> lemmaSynsets = makeDebVisDicCall(lemma);
        for (int i = 0; i < lemmaSynsets.size(); i++) {
                SynsetLight synsetLight = lemmaSynsets.get(i);
                WordNetSenseInfo senseInfo = makeDebVisDicCall(wndb, language, synsetLight.getIdent(), 1);
                JCheckBox synsetBox = new JCheckBox(senseInfo.toString());
                if (i==0) {
                 //   synsetBox.setSelected(true);
                    String [] fields = synsetBox.getText().replace("\n", "").split("]");
                    if (fields.length>1) {
                        selectedKafSense = fields[0]+"]"+fields[1]+"]";
                    }
                    else {
                        selectedKafSense = (String)synsetBox.getText().replace("\n", "");
                    }
                  //  System.out.println("selectedKafSense = " + selectedKafSense);
                }
                synsetBox.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      JCheckBox cb = (JCheckBox)e.getSource();
                      if (cb.isSelected()) {
                          String [] fields = cb.getText().replace("\n", "").split("]");
                          if (fields.length>1) {
                              selectedKafSense = fields[0]+"]"+fields[1]+"]";
                          }
                          else {
                              selectedKafSense = (String)cb.getText().replace("\n", "");
                          }
                      //    System.out.println("selectedKafSense = " + selectedKafSense);
                        //  makeBrowserCall(cb.getText());
                      }
                  }
                });
                synsetGroup.add(synsetBox);
                synsetPanel.add(synsetBox, new GridBagConstraints(0, i, 1, 1, edge, edge
                        ,GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, insets, edge, edge));
        }
    }

    public void makeSensePanel(ArrayList<KafSense> mappings) {
        synsetPanel.setLayout(gridBagLayout);

        ButtonGroup synsetGroup = new ButtonGroup();
        for (int i = 0; i < mappings.size(); i++) {
            KafSense synsetMapping = mappings.get(i);
            WordNetSenseInfo senseInfo = makeDebVisDicCall(wndb, language, synsetMapping.getSensecode(), synsetMapping.getConfidence());
            JCheckBox synsetBox = new JCheckBox(senseInfo.toString());
            if (i==0) {
               // synsetBox.setSelected(true);
                String [] fields = synsetBox.getText().replace("\n", "").split("]");
                if (fields.length>1) {
                    selectedKafSense = fields[0]+"]"+fields[1]+"]";
                }
                else {
                    selectedKafSense = (String)synsetBox.getText().replace("\n", "");
                }
              //  System.out.println("selectedKafSense = " + selectedKafSense);
            }
            synsetBox.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  JCheckBox cb = (JCheckBox)e.getSource();
                  if (cb.isSelected()) {
                      String [] fields = cb.getText().replace("\n", "").split("]");
                      if (fields.length>1) {
                          selectedKafSense = fields[0]+"]"+fields[1]+"]";
                      }
                      else {
                          selectedKafSense = (String)cb.getText().replace("\n", "");
                      }
                    //  System.out.println("selectedKafSense = " + selectedKafSense);
                   //   makeBrowserCall(cb.getText());
                  }
              }
            });
            synsetGroup.add(synsetBox);
            synsetPanel.add(synsetBox, new GridBagConstraints(0, i, 1, 1, edge, edge
                    ,GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, insets, edge, edge));
        }
    }

    public void rebuildSensePanel(ArrayList<KafSense> mappings, String db, String lg) {
        synsetPanel.removeAll();
        this.wndb = db;
        this.language = lg;
        System.out.println("db = " + db);
        System.out.println("lg = " + lg);
        ButtonGroup synsetGroup = new ButtonGroup();
        for (int i = 0; i < mappings.size(); i++) {
            KafSense synsetMapping = mappings.get(i);
            WordNetSenseInfo senseInfo = makeDebVisDicCall(db, lg,synsetMapping.getSensecode(), synsetMapping.getConfidence());
            JCheckBox synsetBox = new JCheckBox(senseInfo.toString());
            if (i==0) {
             //   synsetBox.setSelected(true);
                String [] fields = synsetBox.getText().replace("\n", "").split("]");
                if (fields.length>1) {
                    selectedKafSense = fields[0]+"]"+fields[1]+"]";
                }
                else {
                    selectedKafSense = (String)synsetBox.getText().replace("\n", "");
                }
            }
            synsetBox.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  JCheckBox cb = (JCheckBox)e.getSource();
                  if (cb.isSelected()) {
                      String [] fields = cb.getText().replace("\n", "").split("]");
                      if (fields.length>1) {
                          selectedKafSense = fields[0]+"]"+fields[1]+"]";
                      }
                      else {
                          selectedKafSense = (String)cb.getText().replace("\n", "");
                      }
                   //   System.out.println("selectedKafSense = " + selectedKafSense);
                     // makeBrowserCall(cb.getText());
                  }
              }
            });
            synsetGroup.add(synsetBox);
            synsetPanel.add(synsetBox, new GridBagConstraints(0, i, 1, 1, edge, edge
                    ,GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, insets, edge, edge));
        }
    }


    public void makeGridPanel() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        scrollPane.setMinimumSize(new Dimension (800, 400));
        scrollPane.setPreferredSize(new Dimension (800, 400));
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.getViewport().add(synsetPanel, null);
        scrollPane.setBorder(panelBorder);
        gridpanel = new JPanel();
        gridpanel.setLayout(gridBagLayout);
        gridpanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, edge, edge
                    ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, edge, edge));
        gridpanel.add(buttonPanel, new GridBagConstraints(0, 1, 1, 1, edge, edge
                    ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, insets, edge, edge));


    }

    ArrayList<SynsetLight> makeDebVisDicCall (String lemma) {
        DebCalls deb = new DebCalls();
        deb.getLightSynsetsThread(wndb, language, lemma);
        while (deb.IAMBUSSY) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.println("deb.syns.size() = " + deb.syns.size());
        return deb.syns;
    }

    WordNetSenseInfo makeDebVisDicCall (String db, String lg, String synsetId, double score) {
        WordNetSenseInfo  senseInfo = new WordNetSenseInfo();
        DebCalls deb = new DebCalls();
        deb.getSynsetThread(db, lg,synsetId);
        while (deb.IAMBUSSY) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        Document doc = deb.doc;
        if (doc!=null) {
            senseInfo.setSynsetId(synsetId);
            senseInfo.setScore(score);
            /*
      <LexicalEntry id="alteratie">
      	<Lemma writtenForm="alteratie" pos="N"/>
      	<!-- attribute "writtenForm" is added in case the id of LexicalEntry is numerical -->
      	<!-- attribute "pos" is attributed to Lemma, in conformance with LMF-->
        <Sense id="alteratie_1" synset="NLD_20_d-n-20309_N">
	    <!-- for Sense id we can use SenseKey -->
        <!-- we use the SYNSET ID syntax as agreed: "language code-version-id-pos tag" -->
        	<MonolingualExternalRef externalSystem="cornetto lu 1.0" externalReference="d_n-10255"/>
        </Sense>
	    <Sense id="alteratie_2" synset="NLD_20_d_n-10009_N">
	    <!-- for Sense id we can use SenseKey -->
        <!-- we use the SYNSET ID syntax as agreed: "language code-version-id-pos tag" -->
        	<MonolingualExternalRef externalSystem="cornetto lu 1.0" externalReference="d_n-10256"/>
	    </Sense>
      </LexicalEntry>
      <Synset id="NLD_20_d_n-10009_N" baseConcept="true">
      <!-- new attribute "baseConcept", with values (true|false) -->
		<Definition gloss="chromatische verhoging of verlaging">
		<!-- the gloss will appear as attribute value of the element <Definition> -->
			<Statement example="nl. chromatische verhoging of verlaging van een akkoordtoon"/>
			<!-- examples will appear as attribute value of the element(s) <Statement> -->
		</Definition>
		<SynsetRelations>
		<!-- new element <SynsetRelations> added for bracketing purposes -->
     			<SynsetRelation targets="NLD_20_d-n-28145_N" relType="causes" cs="0.0"/>
	     		<!-- attributes "relType" and "cs" added -->
     			<!-- rest of features do not seem suitable to be promoted as attributes -->
     			<!-- values of relType = list of ALL relations of various WNs -->
	 		<SynsetRelation targets="NLD_20_d-n-20309_N" relType="has_hyperonym" cs="0.0"/>
	 	</SynsetRelations>
	 <MonolingualExternalRefs>
 	 <!-- new element <MonolingualExternalRefs> added for bracketing purposes -->
	 <MonolingualExternalRef externalSystem="Domain" externalReference="XXX"/>
	 <!-- MonolingualExternalRef is used to express: Domain, link to ontology/ies, etc. -->
	 <MonolingualExternalRef externalSystem="SUMO" externalReference="XXX"/>
	 </MonolingualExternalRefs>
      </Synset>
    </Lexicon>
      <SYNSET>
    <ID>eng-30-00089351-n</ID>
    <POS>n</POS>
    <SYNONYM>
      <LITERAL sense='3'>restitution</LITERAL>
      <WORD>restitution</WORD>
      <LITERAL sense='4'>return</LITERAL>
      <WORD>return</WORD>
      <LITERAL sense='3'>restoration</LITERAL>
      <WORD>restoration</WORD>
      <LITERAL sense='1'>regaining</LITERAL>
      <WORD>regaining</WORD>
    </SYNONYM>
    <ILR type='hypernym' link='eng-30-00077419-n'>acquisition:1</ILR>
    <ILR type='eng_derivative' link='eng-30-02247977-v'>recover:1, retrieve:1, find:14, regain:1</ILR>
    <ILR type='eng_derivative' link='eng-30-02310007-v'>render:7, return:2</ILR>
    <ILR type='eng_derivative' link='eng-30-02310482-v'>restore:3, restitute:1</ILR>
    <DEF>getting something back again</DEF>
    <USAGE>upon the restitution of the book to its rightful owner the child was given a tongue lashing</USAGE>
    <RILR type='hypernym' link='eng-30-00089657-n'>clawback:1</RILR>
    <RILR type='eng_derivative' link='eng-30-02247977-v'>recover:1, retrieve:1, find:14, regain:1</RILR>
    <RILR type='eng_derivative' link='eng-30-02310007-v'>render:7, return:2</RILR>
    <RILR type='eng_derivative' link='eng-30-02310482-v'>restore:3, restitute:1</RILR>
  </SYNSET>
             */
            NodeList nList = doc.getElementsByTagName("LITERAL");
            for (int n = 0; n < nList.getLength(); n++) {
                Node node = nList.item(n);
                String synonym = node.getTextContent()+":";
                NamedNodeMap attributes = node.getAttributes();
                Node term = attributes.getNamedItem("sense");
                if (term!=null) {
                   synonym+= term.getTextContent();
                }
                senseInfo.addSynonyms(synonym);
            }
            nList = doc.getElementsByTagName("DEF");
            for (int n = 0; n < nList.getLength(); n++) {
                Node node = nList.item(n);
                senseInfo.setDef(node.getTextContent());
            }
            nList = doc.getElementsByTagName("USAGE");
            for (int n = 0; n < nList.getLength(); n++) {
                Node node = nList.item(n);
                senseInfo.setUsage(node.getTextContent());
            }
            nList = doc.getElementsByTagName("ILR");
            for (int j = 0; j < nList.getLength(); j++) {
                Node node = nList.item(j);
                NamedNodeMap nSubList = node.getAttributes();
                for (int k = 0; k < nSubList.getLength(); k++) {
                    Node subNode = nSubList.item(k);
                    if (subNode.getNodeName().equals("type")) {
                        String rel = subNode.getTextContent()+"="+node.getTextContent();
                        senseInfo.addRelations(rel);
                    }
                }
            }
        }/// doc!=null
        return senseInfo;
    }

    void makeBrowserCall (String senseInfo) {
        String [] fields = senseInfo.split(":");
        //http://xmlgroup.iit.cnr.it/demos/Wikyoto_Knowledge_Editor/WikyotoKnowledgeEditor.php?lang=en&resource=wnen30&synset_id=eng-30-12612913-n
        String URL ="http://xmlgroup.iit.cnr.it/demos/Wikyoto_Knowledge_Editor/WikyotoKnowledgeEditor.php?lang=en&resource=wnen30&synset_id="+fields[0];
        BrowserLink.link(URL);
    }
   /*
   VALUES: 'en', 'nl', 'es', 'it', 'eu', 'zh', 'jp';
 - resource > VALUES:  'wnen30', 'wnen30_d', 'wnnld',
'wnnld_d','wnspa', 'wnspa_d','wnita', 'wnita_d','wnbas',
'wnbas_d','wnzho', 'wnzho_d','wnjpn', 'wnjpn_d';
    */


    public class WordNetSenseInfo {
        private ArrayList<String> synonyms;
        private ArrayList<String> relations;
        private String synsetId;
        private double score;
        private String def;
        private String usage;
        private String hypernym;

        public WordNetSenseInfo() {
            this.synonyms = new ArrayList<String>();
            this.relations = new ArrayList<String>();
            this.synsetId = "";
            this.score = 0;
            this.def = "";
            this.usage = "";
            this.hypernym = "";
        }

        public ArrayList<String> getRelations() {
            return relations;
        }

        public void setRelations(ArrayList<String> relations) {
            this.relations = relations;
        }

        public void addRelations(String relation) {
            this.relations.add(relation);
        }

        public ArrayList<String> getSynonyms() {
            return synonyms;
        }

        public void setSynonyms(ArrayList<String> synonyms) {
            this.synonyms = synonyms;
        }

        public void addSynonyms(String synonym) {
            this.synonyms.add(synonym);
        }

        public String getSynsetId() {
            return synsetId;
        }

        public void setSynsetId(String synsetId) {
            this.synsetId = synsetId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getDef() {
            return def;
        }

        public void setDef(String def) {
            this.def = def;
        }

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public String getHypernym() {
            return hypernym;
        }

        public void setHypernym(String hypernym) {
            this.hypernym = hypernym;
        }

        public String toString () {
            String str = "";
            str ="[";
            for (int i = 0; i < synonyms.size(); i++) {
                String s = synonyms.get(i);
                if (i>0) {
                    str +=";";
                }
                str+= s;
            }
            str += "]";
            str += "["+this.getSynsetId()+":"+this.getScore()+"]";
            str += "["+this.getDef()+"]";
            str += "["+this.getUsage()+"]";
            str += "[";
            for (int i = 0; i < relations.size(); i++) {
                String s = relations.get(i);
                if (i>0) {
                    str += ";";
                }
                str += s;
            }
            str +="]";
            return str;
        }
    }
}
