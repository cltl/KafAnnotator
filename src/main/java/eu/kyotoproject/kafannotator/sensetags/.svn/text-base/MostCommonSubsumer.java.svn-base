package eu.kyotoproject.kafannotator.sensetags;

import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;
import eu.kyotoproject.kafannotator.util.Util;
import vu.tripleevaluation.objects.Triple;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Oct 21, 2010
 * Time: 8:57:55 AM
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
public class MostCommonSubsumer {

     static final String TAGSEPARATOR="\t";
     static String database = "";
     static String language = "";
     static DebCalls deb = null;

    /// version that uses a config file to know which column contain tags and what is the EVENT tag and what is the SCOPE tag
     static public void loadAnnotationFile (HashMap<String, ArrayList<String>> tagSynsetMap, String inputFile) {
         AnnotationTableModel tModel = new AnnotationTableModel ();
         String error = "";
         String inputLine = "";
         int nList = 0;
         try {
           FileInputStream fis = new FileInputStream(inputFile);
           InputStreamReader isr = new InputStreamReader(fis);
           BufferedReader in = new BufferedReader(isr);
           while ((inputLine = in.readLine()) != null) {
               //System.out.println("inputLine = " + inputLine);
               nList++;
                String [] fields = inputLine.split(TAGSEPARATOR);
                if (fields.length<tModel.MAXTABLECOLUMNS) {
                    fields = Util.fixFields(fields, tModel.MAXTABLECOLUMNS, tModel.ROWSYNSET);
                }
                if ((fields.length==tModel.MAXTABLECOLUMNS)) {
                       boolean done = true;
                       String id   = fields[tModel.ROWID];
                       String token = fields[tModel.ROWWORDTOKEN];
                       String lemma = fields[tModel.ROWWORDTYPE];
                       String synsetId = fields[tModel.ROWSYNSET];
                       String tag1  = fields[tModel.ROWTAG1];
                       Integer tagId1  = Integer.parseInt(fields[tModel.ROWTAGID1]);
                       String tag2  = fields[tModel.ROWTAG2];
                       Integer tagId2  = Integer.parseInt(fields[tModel.ROWTAGID2]);
                       String tag3  = fields[tModel.ROWTAG3];
                       Integer tagId3  = Integer.parseInt(fields[tModel.ROWTAGID3]);
                       String tag4  = fields[tModel.ROWTAG4];
                       Integer tagId4  = Integer.parseInt(fields[tModel.ROWTAGID4]);
                       String tag5  = fields[tModel.ROWTAG5];
                       Integer tagId5  = Integer.parseInt(fields[tModel.ROWTAGID5]);
                       String tag6  = fields[tModel.ROWTAG6];
                       Integer tagId6  = Integer.parseInt(fields[tModel.ROWTAGID6]);
                       String tag7  = fields[tModel.ROWTAG7];
                       Integer tagId7  = Integer.parseInt(fields[tModel.ROWTAGID7]);
                       String tag8  = fields[tModel.ROWTAG8];
                       Integer tagId8  = Integer.parseInt(fields[tModel.ROWTAGID8]);
                       if (fields[tModel.ROWSTATUS].equals("true")) {
                           done = true;
                       }
                       else {
                           done = false;
                       }
                       WordTag tag = new WordTag();
                       tag.setWordToken(token);
                       tag.setTokenId(id);
                       tag.setWordSynset(synsetId);
                       tag.setTag1(tag1);
                       tag.setTagId1(tagId1);
                       tag.setTag2(tag2);
                       tag.setTagId2(tagId2);
                       tag.setTag3(tag3);
                       tag.setTagId3(tagId3);
                       tag.setTag4(tag4);
                       tag.setTagId4(tagId4);
                       tag.setTag5(tag5);
                       tag.setTagId5(tagId5);
                       tag.setTag6(tag6);
                       tag.setTagId6(tagId6);
                       tag.setTag7(tag7);
                       tag.setTagId7(tagId7);
                       tag.setTag8(tag8);
                       tag.setTagId8(tagId8);
                       String [] synsetFields = synsetId.split("]");
                       if (synsetFields.length==2) {
                           int idx = synsetFields[1].lastIndexOf(":");
                           if (idx>-1) {
                                synsetId = synsetFields[1].substring(1, idx);
                           }
                           else {
                               synsetId = synsetFields[1];
                           }
                       }
                       else {
                           // d_n-26820-n:1
                           int idx = synsetId.lastIndexOf(":");
                           if (idx>-1) {
                               if (database.equals("cdb_syn")) {
                                    synsetId = synsetId.substring(0, idx-2);
                               }
                               else if (database.equals("wnen30")) {
                                   synsetId = synsetId.substring(0, idx);
                               }
                           }
                       }
                        if (synsetId.length()>0) {
                               String key = lemma+"#"+synsetId;
                            System.out.println("key = " + key);
                               if (tag1.length()>0) {
                                addToMap(tagSynsetMap, tag1, key);
                               }
                               if (tag2.length()>0) {
                                addToMap(tagSynsetMap, tag2, key);
                               }
                               if (tag3.length()>0) {
                                addToMap(tagSynsetMap, tag3, key);
                               }
                               if (tag4.length()>0) {
                                addToMap(tagSynsetMap, tag4, key);
                               }
                               if (tag5.length()>0) {
                                addToMap(tagSynsetMap, tag5, key);
                               }
                               if (tag6.length()>0) {
                                addToMap(tagSynsetMap, tag6, key);
                               }
                               if (tag7.length()>0) {
                                addToMap(tagSynsetMap, tag7, key);
                               }
                               if (tag8.length()>0) {
                                addToMap(tagSynsetMap, tag8, key);
                               }
                        }
                }
                else {
                    System.out.println("wrong number of columns inputLine = " + inputLine);
                    System.out.println("fields.length = " + fields.length);
                    System.out.println("tModel.MAXTABLECOLUMNS = " + tModel.MAXTABLECOLUMNS);
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
     }

    static void addToMap(HashMap<String, ArrayList<String>> tagSynsetMap, String tagValue, String synsetId) {
        if (tagSynsetMap.containsKey(tagValue)) {
            ArrayList<String> synsets = tagSynsetMap.get(tagValue);
            if (!synsets.contains(synsetId)) {
                synsets.add(synsetId);
                tagSynsetMap.put(tagValue, synsets);
            }
        }
        else {
            ArrayList<String> synsets = new ArrayList<String>();
            synsets.add(synsetId);
            tagSynsetMap.put(tagValue, synsets);
        }
    }

    static TreeSet sortSynsetsOnFrequency (ArrayList<Triple> Triples) {
        TreeSet sorter = new TreeSet(
                    new Comparator() {
                        public int compare(Object a, Object b) {
                            Triple itemA = (Triple) a;
                            Triple itemB = (Triple) b;
                            if (itemA.getRelation().compareTo(itemB.getRelation())>0) {
                               return -1;
                            }
                            else if (itemA.getRelation().equals(itemB.getRelation())) {
                               return -1;
                            }
                            else {
                                return 1;
                            }
                        }
                    }
        );
        for (int i = 0; i < Triples.size(); i++) {
            Triple trp = Triples.get(i);
            sorter.add(trp);
        }
        return sorter;
    }

    static ArrayList<String> getTopNodes (HashMap<String, SynsetNode> hyperTree, ArrayList<String> childNodes) {
        ArrayList<String> topNodes = new ArrayList<String>();
        Set keyHyperSet = hyperTree.keySet();
        Iterator hpers = keyHyperSet.iterator();
        while(hpers.hasNext()) {
            String hper = (String) hpers.next();
            if (!childNodes.contains(hper)) {
                if (!topNodes.contains(hper)) {
                    topNodes.add(hper);
                   // System.out.println("top node hper = " + hper);
                }
            }
        }
        return topNodes;
    }

    static void getTreeString (HashMap<String, SynsetNode> hyperTree, ArrayList<String> topNodes, int level, FileOutputStream fos) {
        String str = "";
        for (int i = 0; i < topNodes.size(); i++) {
            String hper = topNodes.get(i);
            str = "";
            if (hyperTree.containsKey(hper)) {
                SynsetNode node = hyperTree.get(hper);
                for (int j = 0; j < level; j++) {
                    str += "  ";

                }
                str += node.getSynset()+"\n";
              //  System.out.println("str = " + str);
                try {
                    fos.write(str.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                ArrayList<String> children = node.getChildren();
                if (children.size()>0) {
                    getTreeString(hyperTree, children, level+1, fos);
                }
                else {
               //     System.out.println("leaf node.getSynset() = " + node.getSynset());
                }
            }
        }
    }


    /*
        if (wnacc==null) {
            wnacc = new WNViaDebServerClose(server, 9001, "kyoto", "kyoto");
        }
        ArrayList<SynsetLight> synsets = wnacc.getSSExactWord("wnnld", "nl", word,  "");
        if (synsets!=null) {
            nSenses = synsets.size();
            for (int i = 0; i < synsets.size(); i++) {
                SynsetLight s = synsets.get(i);
                System.out.println("s.getIdent() = " + s.getIdent());
                ArrayList<String> hypers = wnacc.getSynsetHyperonymsByID("wnnld", "nl",s.getIdent(), "");
                for (int j = 0; j < hypers.size(); j++) {
                    String s1 = hypers.get(j);
                    System.out.println("s1 = " + s1);
                }
            }
        }
     */

    static void prepareDeb (String server, int port, String db, String lg, String account, String passw) {
        deb = new DebCalls (server, port, account, passw);
        database = db;
        language = lg;

    }

    public static void getMostCommonSubsumer (HashMap<String, ArrayList<String>> tagSynsetMap, FileOutputStream fos) {
        try {
            String str = "";
            Set keySet = tagSynsetMap.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String keystr = "\nTAG:"+key+"\n";
                System.out.println(keystr);
                fos.write(keystr.getBytes());
                HashMap <String, Integer> hyperCount = new HashMap<String, Integer>();
                HashMap <String, SynsetNode> hyperTree = new HashMap<String, SynsetNode>();
                ArrayList<String> children = new ArrayList<String>();
                ArrayList<String> synsets = tagSynsetMap.get(key);
                for (int i = 0; i < synsets.size(); i++) {
                    String s = synsets.get(i);
                    keystr = s+"\n";
                    fos.write(keystr.getBytes());
                    System.out.println("synset = " + s);
                    int idx  = s.indexOf("#");
                    if (idx>-1) {
                        String synsetId  = s.substring(idx+1);
                        SynsetNode sNode = new SynsetNode();
                        sNode.setSynset(s);
                        sNode.setSynsetId(synsetId);
                        hyperTree.put(synsetId, sNode);
                        SynsetNode hNode = null;
                        ArrayList<String> hypers = new ArrayList<String>();
                        deb.wnacc.getSynsetHyperonymsByID(hypers, database, language,synsetId, "");
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
                            idx = h.indexOf("#");
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
                ArrayList<String> topNodes = getTopNodes(hyperTree, children);
                //System.out.println("topNodes.size() = " + topNodes.size());
                getTreeString(hyperTree, topNodes, 0, fos);
                System.out.println("DONE");

            }
            System.out.println("DONE ALL");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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

    static public void main (String [] args) {
        if (args.length==7) {
            String server = args[0];
            String lg = args[1];
            Integer port = Integer.parseInt(args[2]);
            String db = args[3];
            String account = args[4];
            String passw = args[5];
            String inputFolder = args[6];
            prepareDeb(server, port.intValue(), db, lg, account, passw);
            try {
                FileOutputStream fos = new FileOutputStream (inputFolder+"/"+"mcs.txt");
                HashMap<String, ArrayList<String>> tagSynsetMap = new HashMap<String, ArrayList<String>>();
                ArrayList<String> files = makeFlatFileList(inputFolder, ".tag");
                for (int i = 0; i < files.size(); i++) {
                    String filePath= files.get(i);
                    System.out.println("filePath = " + filePath);
                    loadAnnotationFile(tagSynsetMap, filePath);
                    System.out.println("tagSynsetMap = " + tagSynsetMap.size());
                }
                getMostCommonSubsumer(tagSynsetMap, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        else {
            String usage = "arg1 = server\n"+
                           "arg2 = language\n"+
                           "arg3 = port\n"+
                           "arg4 = database\n"+
                           "arg5 = account\n"+
                           "arg6 = password\n"+
                           "arg7 = input folder with kaf and tag fles\n";
            System.out.println("usage = " + usage);
        }
    }

/*
    static public void main (String [] args) {
        String configFile = args[0];
        String input = args[1];
        TripleConfig config = new TripleConfig(configFile);
        loadAnnotationFile(config, input);
    }
*/
}
