package eu.kyotoproject.kafannotator.sensetags;

import eu.kyotoproject.kaf.KafSaxParser;
import eu.kyotoproject.kaf.KafSense;
import eu.kyotoproject.kaf.KafTerm;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 2/14/11
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportSenseTagsIntoKaf {

    /*
"Id","Word token","Word type","Pos","Synset","Tag1","Tag 1 id","Tag 2","Tag id2","Tag 3","Tag id3","Tag4","Tag id4","Tag5","Tag id5","Tag6","Tag id6","Tag7","Tag id7","Tag8","Tag id8","Order","Done","Sentence"
"w50","largest","large","G","{01168845}","EVENT",1,"EVENT-SCOPE",5,,0,,0,,0,,0,,0,,0,3,"true",7
"w51","estuary","estuary","N","eng-30-09274500-n:1.0","has-state",2,"EVENT-SCOPE",5,,0,,0,,0,,0,,0,,0,4,"true",7
"w54","United","United_States","R","{01168845}","LOCATION",4,"EVENT-SCOPE",5,,0,,0,,0,,0,,0,,0,7,"true",7
"w55","States","United_States","R",,"LOCATION",4,"EVENT-SCOPE",5,,0,,0,,0,,0,,0,,0,8,"true",7
"w400","Bay","bay","G","eng-30-00395977-a:1.0","generic-location",9,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,18,"true",26
"w401","watershed","watershed","N","{01168845}","generic-location",9,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,19,"true",26
"w403","home","home","P","{01168845}","EVENT",10,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,21,"true",26
"w406",17,"17 million","N",,"done-by",13,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,24,"true",26
"w407","million","17 million","N",,"done-by",13,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,25,"true",26
"w408","people","people","N","{01168845}","done-by",13,"EVENT-SCOPE",12,,0,,0,,0,,0,,0,,0,26,"true",26
"w411",150,150,"N",,"done-by",6,,0,,0,,0,,0,,0,,0,,0,29,"true",27
"w412","people","people","N","{01168845}","done-by",6,,0,,0,,0,,0,,0,,0,,0,30,"true",27
"w413","move","move","V","{01168845}","EVENT",7,,0,,0,,0,,0,,0,,0,,0,31,"true",27

     */

    static public String fixSynsetId (String id, String pos) {
        String newId = id;
        if (id.startsWith("{")) {
            newId = "eng-30-"+id.substring(1);
            if (newId.endsWith("}")) {
                if (newId.length()<8) {
                    System.out.println("Warning. newId is too short = " + newId);
                }
                newId = newId.substring(0, newId.length()-1);
                if (pos.equalsIgnoreCase("n")) {
                    newId += "-n";
                }
                else if (pos.equalsIgnoreCase("v")) {
                    newId += "-v";
                }
                else if (pos.equalsIgnoreCase("g")) {
                    newId += "-g";
                }
                else if (pos.equalsIgnoreCase("a")) {
                    newId += "-r";
                }
            }
        }
        else {
            int idx = id.lastIndexOf(":");
            if (idx>-1) {
                newId = id.substring(0, idx);
            }
        }
        return newId;
    }

    static public ArrayList<WordTag> readTagFile (String filePath) {
        ArrayList<WordTag> tags = new ArrayList<WordTag>();
        try {
          String inputLine = "";
          FileInputStream fis = new FileInputStream(filePath);
          //InputStreamReader isr = new InputStreamReader(fis, System.getProperty("file.encoding"));
          InputStreamReader isr = new InputStreamReader(fis);
          BufferedReader in = new BufferedReader(isr);
          while ((inputLine = in.readLine()) != null) {
               String [] fields = inputLine.split("\t");
               String tokenId = fields[AnnotationTableModel.ROWID];
               String synsetId = fields[AnnotationTableModel.ROWSYNSET];
               if (synsetId.length()>0) {
                   String pos = fields[AnnotationTableModel.ROWPOS];
                   pos = pos.replace("\"", "");
                   tokenId = tokenId.replace("\"","");
                   synsetId = synsetId.replace("\"","");
                   synsetId = fixSynsetId(synsetId, pos);
                //   System.out.println("tokenId = " + tokenId);
                //   System.out.println("synsetId = " + synsetId);
                   WordTag tag = new WordTag();
                   tag.setTokenId(tokenId.replace("\"",""));
                   tag.setWordSynset(synsetId);
                   tag.setPos(pos);
                   tags.add(tag);
               }
          }
          in.close();
         }
         catch (Exception eee) {
         }
         return tags;
    }


    static public void main (String [] args) {
        String kafFilePath = args[0];
        String tagFilePath = args[1];
        KafSaxParser parser = new KafSaxParser();
        parser.parseFile(kafFilePath);
        ArrayList<WordTag> tags = readTagFile(tagFilePath);
        for (int i = 0; i < tags.size(); i++) {
            WordTag wordTag = tags.get(i);
            if (parser.WordFormToTerm.containsKey(wordTag.getTokenId())) {
                String termId = parser.WordFormToTerm.get(wordTag.getTokenId());
                KafTerm term = parser.getTerm(termId);
                if (term!=null) {
                   boolean hasCode = false;
                    for (int j = 0; j < term.getSenseTags().size(); j++) {
                        KafSense sense = term.getSenseTags().get(j);
                        if (sense.getSensecode().equals(wordTag.getWordSynset())) {
                            hasCode = true;
                            break;
                        }
                    }
                    if (!hasCode) {
                        KafSense kafSense = new KafSense();
                        kafSense.setConfidence(1.0);
                        kafSense.setSensecode(wordTag.getWordSynset());
                        kafSense.setResource("wn30g");
                        term.addSenseTag(kafSense);
                        if (term.getSenseTags().size()>1) {
                            System.out.println("mulitple senses for term.getLemma() = " + term.getLemma()+" : "+term.getTid());
                        }
                    }
                }
                else {
                    System.out.println("Error. Cannot find the term for termId = " + termId);
                }

            }
            else {
                System.out.println("Error. Cannot find the termId for wordTag.getTokenId() = " + wordTag.getTokenId());
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(kafFilePath+".sensetags.kaf");
            parser.writeKafToFile(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
