package eu.kyotoproject.kafannotator.sensetags;

import vu.debvisdicclient.SSWord;
import vu.debvisdicclient.SynsetLight;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 2/9/11
 * Time: 1:58 PM
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
public class MostCommonWordSubsumer {

    public static void getMostCommonWordSubsumer (ArrayList<String> wordMap, FileOutputStream fos) {
        try {
            String db = "cdb_syn";
            String lg = "nl";
            String pos = "noun";
            String keystr = "";
            DebCalls deb = new DebCalls ("debvisdic.let.vu.nl", 9002, "gast", "gast");
            HashMap <String, Integer> hyperCount = new HashMap<String, Integer>();
            HashMap <String, SynsetNode> hyperTree = new HashMap<String, SynsetNode>();
            ArrayList<String> children = new ArrayList<String>();
            for (int i = 0; i < wordMap.size(); i++) {
                String word =  wordMap.get(i);
                System.out.println("word = " + word);
                ArrayList<SynsetLight> synsets =  deb.wnacc.getSSLightExactWord(db, lg, word, pos);
                System.out.println("synsets.size() = " + synsets.size());
                String synsetId = "";
                for (int j = 0; j < synsets.size(); j++) {
                    SynsetLight synsetLight = synsets.get(j);
                    for (int k = 0; k < synsetLight.getWords().size(); k++) {
                        SSWord ssWord = (SSWord) synsetLight.getWords().get(k);
                      //  System.out.println("ssWord.getWord() = " + ssWord.getWord());
                        if (ssWord.getWord().equals(word)) {
                            if (ssWord.getSenseNum()==1) {
                               synsetId = synsetLight.getIdent();
                               break;
                            }
                        }
                    }
                    if (synsetId.length()>0) {
                        break;
                    }
                }
                if (synsetId.length()>0) {
                    //// We have a first sense.....
                    keystr = word+"#"+synsetId+"\n";
                    fos.write(keystr.getBytes());
                    SynsetNode sNode = new SynsetNode();
                    sNode.setSynset(word+"#"+synsetId);
                    sNode.setSynsetId(synsetId);
                    hyperTree.put(synsetId, sNode);
                    SynsetNode hNode = null;
                    ArrayList<String> hypers = new ArrayList<String>();
                    deb.wnacc.getSynsetHyperonymsByID(hypers, "cdb_syn", "nl",synsetId, "");
                    for (int j = 0; j < hypers.size(); j++) {
                        String h = hypers.get(j);
                        if (hyperCount.containsKey(h)) {
                            Integer cnt = hyperCount.get(h);
                            cnt++;
                            hyperCount.put(h,cnt);
                        }
                        else {
                            hyperCount.put(h, 1);
                        }
                        String hyperSynsetId = h;
                        int idx = h.indexOf("#");
                        if (idx>-1) {
                            hyperSynsetId = h.substring(0, h.indexOf("#"));
                        }
                        if (hyperTree.containsKey(hyperSynsetId)) {
                            hNode = hyperTree.get(hyperSynsetId);
                            if (!hNode.getChildren().contains(sNode.getSynsetId())) {
                                hNode.addChildren(sNode.getSynsetId());
                                hyperTree.put(hyperSynsetId, hNode);
                                if (!children.contains(sNode.getSynsetId())) {
                                    children.add(sNode.getSynsetId());
                                }
                            }
                        }
                        else {
                            hNode = new SynsetNode();
                            hNode.setSynset(h);
                            hNode.setSynsetId(hyperSynsetId);
                            hNode.addChildren(sNode.getSynsetId());
                            hyperTree.put(hyperSynsetId, hNode);
                            if (!children.contains(sNode.getSynsetId())) {
                                children.add(sNode.getSynsetId());
                            }
                        }
                        sNode = hNode;
                        keystr = "["+h+"]";
                        fos.write(keystr.getBytes());
                    }
                    keystr = "\n";
                    fos.write(keystr.getBytes());
                }
            }
            keystr = "\nOVERVIEW OF HYPERNYMS\n";
            System.out.println(keystr);
            Set keyHyperSet = hyperCount.keySet();
            Iterator hpers = keyHyperSet.iterator();
            while(hpers.hasNext()) {
                String hper = (String) hpers.next();
                Integer cnt = hyperCount.get(hper);
                keystr += hper+"\t"+cnt.toString()+"\n";
            }
            fos.write(keystr.getBytes());
            keystr = "\nHYPERNYM TREES\n";
            System.out.println(keystr);
            fos.write(keystr.getBytes());
            //System.out.println("children = " + children.size());
            ArrayList<String> topNodes = MostCommonSubsumer.getTopNodes(hyperTree, children);
            //System.out.println("topNodes.size() = " + topNodes.size());
            MostCommonSubsumer.getTreeString(hyperTree, topNodes, 0, fos);
            System.out.println("DONE");
            System.out.println("DONE ALL");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    static public ArrayList<String> readFileToArrayList (String filePath) {
          ArrayList<String> words = new ArrayList<String>();
          if (new File(filePath).exists() ) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader in = new BufferedReader(isr);
                String inputLine = "";
                while (in.ready()&&(inputLine = in.readLine()) != null) {
                    if (inputLine.trim().length()>0) {
                        words.add(inputLine.trim());
                    }
                }
            }
            catch (Exception e) {

            }
          }
          return words;
    }

    static public void main (String [] args) {
        String filePath = args[0];
        try {
            ArrayList<String> words = new ArrayList<String>();
            words = readFileToArrayList(filePath);
            FileOutputStream fos = new FileOutputStream (filePath+"mcs.txt");
            HashMap<String, ArrayList<String>> tagSynsetMap = new HashMap<String, ArrayList<String>>();
            getMostCommonWordSubsumer(words, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
