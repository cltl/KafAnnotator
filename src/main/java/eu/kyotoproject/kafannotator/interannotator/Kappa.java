package eu.kyotoproject.kafannotator.interannotator;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;
import eu.kyotoproject.kafannotator.tableware.AnnotationTableModel;
import eu.kyotoproject.kafannotator.util.Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 3/30/11
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Kappa {

    public HashMap<String, ArrayList<TagStat>> tags1;
    public HashMap<String, ArrayList<TagStat>> tags2;
    ArrayList<String> keyTags1 = new ArrayList<String>(); /// ArrayList needed to iterate the HashMap with tags in a fixed order
    ArrayList<Integer> keyTagCorrect1 = new ArrayList<Integer>();
    ArrayList<Integer> keyTagTotals1 = new ArrayList<Integer>();
    ArrayList<String> keyTags2 = new ArrayList<String>();  /// ArrayList needed to iterate the HashMap with tags in a fixed order
    ArrayList<Integer> keyTagCorrect2 = new ArrayList<Integer>();
    ArrayList<Integer> keyTagTotals2 = new ArrayList<Integer>();
    Integer totalAnnotation1 = 0;
    Integer totalCorrect1 = 0;
    Integer totalAnnotation2 = 0;
    Integer totalCorrect2 = 0;
    private boolean coverEmptyTags = false;

    public Kappa (boolean emtpyTags) {
        init();
        this.setCoverEmptyTags(emtpyTags);

    }

    public Kappa() {
       init();
    }

    void init() {
        this.tags1 = new HashMap<String, ArrayList<TagStat>>();
        this.tags2 = new HashMap<String, ArrayList<TagStat>>();
        keyTags1 = new ArrayList<String>();
        keyTagCorrect1 = new ArrayList<Integer>();
        keyTagTotals1 = new ArrayList<Integer>();
        keyTags2 = new ArrayList<String>();
        keyTagCorrect2 = new ArrayList<Integer>();
        keyTagTotals2 = new ArrayList<Integer>();
        totalAnnotation1 = 0;
        totalAnnotation2 = 0;
        totalCorrect1 = 0;
        totalCorrect2 = 0;
        coverEmptyTags = false;
    }
    public boolean isCoverEmptyTags() {
        return coverEmptyTags;
    }

    public void setCoverEmptyTags(boolean coverEmptyTags) {
        this.coverEmptyTags = coverEmptyTags;
    }

    public void addTags (WordTag tag1, WordTag tag2) {
        ArrayList<String> matchedTagValues = tag1.matchedValues(tag2);
        ArrayList<String> misMatchTag1Values = tag1.misMatchedValues(tag2);
        ArrayList<String> misMatchTag2Values = tag2.misMatchedValues(tag1);
        addMatchedTags(matchedTagValues);
        addMisMatchedTags(misMatchTag1Values, misMatchTag2Values);
    }

    public void addTagsLayer (WordTag tag1, WordTag tag2, int layer) {
        String tagValue1 = tag1.getTag(layer);
        String tagValue2 = tag2.getTag(layer);
        if (tagValue1.equals(tagValue2)) {
            if (!tagValue1.isEmpty()) {
                addMatchedTags(tagValue1);
            }
        }
        else {
            if (!coverEmptyTags) {
               if (!tagValue1.isEmpty() && !tagValue2.isEmpty()) {
                   addMisMatchedTags(tagValue1, tagValue2);
               }
            }
            else {
                addMisMatchedTags(tagValue1, tagValue2);
            }
        }
    }

    public void addTagsPerLayer (WordTag tag1, WordTag tag2) {
        for (int i = 0; i < AnnotationTableModel.TAGLEVELS; i++) {
            String tagValue1 = tag1.getTag(i);
            String tagValue2 = tag2.getTag(i);
            if (tagValue1.equals(tagValue2)) {
                if (!tagValue1.isEmpty()) {
                    addMatchedTags(tagValue1);
                }
            }
            else {
                if (!coverEmptyTags) {
                    if (!tagValue1.isEmpty() && !tagValue2.isEmpty()) {
                        addMisMatchedTags(tagValue1, tagValue2);
                    }
                }
                else {
                    addMisMatchedTags(tagValue1, tagValue2);
                }
            }
        }
    }

    private void addMatchedTags (ArrayList<String> matchedTagValues) {
        for (int i = 0; i < matchedTagValues.size(); i++) {
            String s = matchedTagValues.get(i);
            if (tags1.containsKey(s)) {
               ArrayList<TagStat> tagStats = tags1.get(s);
               boolean match = false;
                for (int j = 0; j < tagStats.size(); j++) {
                    TagStat tagStat = tagStats.get(j);
                    if (tagStat.getTag().equals(s)) {
                        tagStat.increaseCounts();
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    TagStat tagStat = new TagStat(s);
                    tagStats.add(tagStat);
                }
                tags1.put(s, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                TagStat tagStat = new TagStat(s);
                tagStats.add(tagStat);
                tags1.put(s, tagStats);
            }
        }
        for (int i = 0; i < matchedTagValues.size(); i++) {
            String s = matchedTagValues.get(i);
            if (tags2.containsKey(s)) {
               ArrayList<TagStat> tagStats = tags2.get(s);
               boolean match = false;
                for (int j = 0; j < tagStats.size(); j++) {
                    TagStat tagStat = tagStats.get(j);
                    if (tagStat.getTag().equals(s)) {
                        tagStat.increaseCounts();
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    TagStat tagStat = new TagStat(s);
                    tagStats.add(tagStat);
                }
                tags2.put(s, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                TagStat tagStat = new TagStat(s);
                tagStats.add(tagStat);
                tags2.put(s, tagStats);
            }
        }

    }
    private void addMisMatchedTags (ArrayList<String> misMatchedTagValues1, ArrayList<String> misMatchedTagValues2) {
        for (int i = 0; i < misMatchedTagValues1.size(); i++) {
            String s = misMatchedTagValues1.get(i);
            if (tags1.containsKey(s)) {
               ArrayList<TagStat> tagStats = tags1.get(s);
                for (int j = 0; j < misMatchedTagValues2.size(); j++) {
                    String m = misMatchedTagValues2.get(j);
                    boolean match = false;
                     for (int t = 0; t < tagStats.size(); t++) {
                         TagStat tagStat = tagStats.get(t);
                          if (tagStat.getTag().equals(m)) {
                             tagStat.increaseCounts();
                             match = true;
                             break;
                         }
                     }
                     if (!match) {
                         TagStat tagStat = new TagStat(m);
                         tagStats.add(tagStat);
                     }

                }
                tags1.put(s, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                for (int j = 0; j < misMatchedTagValues2.size(); j++) {
                    String m = misMatchedTagValues2.get(j);
                    TagStat tagStat = new TagStat(m);
                    tagStats.add(tagStat);
                }
                tags1.put(s, tagStats);
            }
        }
        for (int i = 0; i < misMatchedTagValues2.size(); i++) {
            String s = misMatchedTagValues2.get(i);
            if (tags2.containsKey(s)) {
               ArrayList<TagStat> tagStats = tags2.get(s);
                for (int j = 0; j < misMatchedTagValues1.size(); j++) {
                    String m = misMatchedTagValues1.get(j);
                    boolean match = false;
                     for (int t = 0; t < tagStats.size(); t++) {
                         TagStat tagStat = tagStats.get(t);
                          if (tagStat.getTag().equals(m)) {
                             tagStat.increaseCounts();
                             match = true;
                             break;
                         }
                     }
                     if (!match) {
                         TagStat tagStat = new TagStat(m);
                         tagStats.add(tagStat);
                     }

                }
                tags2.put(s, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                for (int j = 0; j < misMatchedTagValues1.size(); j++) {
                    String m = misMatchedTagValues2.get(j);
                    TagStat tagStat = new TagStat(m);
                    tagStats.add(tagStat);
                }
                tags2.put(s, tagStats);
            }
        }

    }

    private void addMatchedTags (String matchedTagValue) {
            if (tags1.containsKey(matchedTagValue)) {
               ArrayList<TagStat> tagStats = tags1.get(matchedTagValue);
               boolean match = false;
                for (int j = 0; j < tagStats.size(); j++) {
                    TagStat tagStat = tagStats.get(j);
                    if (tagStat.getTag().equals(matchedTagValue)) {
                        tagStat.increaseCounts();
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    TagStat tagStat = new TagStat(matchedTagValue);
                    tagStats.add(tagStat);
                }
                tags1.put(matchedTagValue, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                TagStat tagStat = new TagStat(matchedTagValue);
                tagStats.add(tagStat);
                tags1.put(matchedTagValue, tagStats);
            }

            if (tags2.containsKey(matchedTagValue)) {
               ArrayList<TagStat> tagStats = tags2.get(matchedTagValue);
               boolean match = false;
                for (int j = 0; j < tagStats.size(); j++) {
                    TagStat tagStat = tagStats.get(j);
                    if (tagStat.getTag().equals(matchedTagValue)) {
                        tagStat.increaseCounts();
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    TagStat tagStat = new TagStat(matchedTagValue);
                    tagStats.add(tagStat);
                }
                tags2.put(matchedTagValue, tagStats);
            }
            else {
                ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
                TagStat tagStat = new TagStat(matchedTagValue);
                tagStats.add(tagStat);
                tags2.put(matchedTagValue, tagStats);
            }
    }

    private void addMisMatchedTags (String misMatchedTagValue1, String misMatchedTagValue2) {
        if (tags1.containsKey(misMatchedTagValue1)) {
            ArrayList<TagStat> tagStats = tags1.get(misMatchedTagValue1);
             boolean match = false;
             for (int t = 0; t < tagStats.size(); t++) {
                 TagStat tagStat = tagStats.get(t);
                  if (tagStat.getTag().equals(misMatchedTagValue2)) {
                     tagStat.increaseCounts();
                     match = true;
                     break;
                 }
             }
             if (!match) {
                 TagStat tagStat = new TagStat(misMatchedTagValue2);
                 tagStats.add(tagStat);
             }
             tags1.put(misMatchedTagValue1, tagStats);
        }
        else {
            ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
            TagStat tagStat = new TagStat(misMatchedTagValue2);
            tagStats.add(tagStat);
            tags1.put(misMatchedTagValue1, tagStats);
        }
        if (tags2.containsKey(misMatchedTagValue2)) {
            ArrayList<TagStat> tagStats = tags2.get(misMatchedTagValue2);
            boolean match = false;
             for (int t = 0; t < tagStats.size(); t++) {
                 TagStat tagStat = tagStats.get(t);
                  if (tagStat.getTag().equals(misMatchedTagValue1)) {
                     tagStat.increaseCounts();
                     match = true;
                     break;
                 }
             }
             if (!match) {
                 TagStat tagStat = new TagStat(misMatchedTagValue1);
                 tagStats.add(tagStat);
             }
            tags2.put(misMatchedTagValue2, tagStats);
        }
        else {
            ArrayList<TagStat> tagStats = new ArrayList<TagStat>();
            TagStat tagStat = new TagStat(misMatchedTagValue1);
            tagStats.add(tagStat);
            tags2.put(misMatchedTagValue2, tagStats);
        }
    }

    public void getKeyTags () {
        Set keySet = tags1.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            keyTags1.add(key);
        }
        keySet = tags2.keySet();
        keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            keyTags2.add(key);
        }
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (tags1.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags1.get(s);
                Integer totalS = 0;
                ///// SHOULD NOT WE CHECK AGAINST keyTags2 HERE?????
                for (int k = 0; k < keyTags1.size(); k++) {
                    String keyTag = keyTags1.get(k);
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            if (keyTag.equals(s)) {
                                keyTagCorrect1.add(tagStat.getCounts());
                                totalCorrect1 += tagStat.getCounts();
                            }
                            break;
                        }
                    }
                }
                totalAnnotation1 += totalS;
                keyTagTotals1.add(totalS);
            }
        }
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            if (tags2.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags2.get(s);
                Integer totalS = 0;
                ///// SHOULD NOT WE CHECK AGAINST keyTags1 HERE?????
                for (int k = 0; k < keyTags2.size(); k++) {
                    String keyTag = keyTags2.get(k);
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            if (keyTag.equals(s)) {
                                keyTagCorrect2.add(tagStat.getCounts());
                                totalCorrect2 += tagStat.getCounts();
                            }
                            break;
                        }
                    }
                }
                totalAnnotation2 += totalS;
                keyTagTotals2.add(totalS);
            }
        }
    }

    public void getKeyTagsCorrect () {
        Set keySet = tags1.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            keyTags1.add(key);
        }
        keySet = tags2.keySet();
        keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            keyTags2.add(key);
        }
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (tags1.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags1.get(s);
                Integer totalS = 0;
                ///// SHOULD NOT WE CHECK AGAINST keyTags2 HERE?????
                for (int k = 0; k < keyTags1.size(); k++) {
                    String keyTag = keyTags1.get(k);
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            if (keyTag.equals(s)) {
                                keyTagCorrect1.add(tagStat.getCounts());
                                totalCorrect1 += tagStat.getCounts();
                            }
                            break;
                        }
                    }
                }
                totalAnnotation1 += totalS;
                keyTagTotals1.add(totalS);
            }
        }
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            if (tags2.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags2.get(s);
                Integer totalS = 0;
                ///// SHOULD NOT WE CHECK AGAINST keyTags1 HERE?????
                for (int k = 0; k < keyTags2.size(); k++) {
                    String keyTag = keyTags2.get(k);
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            if (keyTag.equals(s)) {
                                keyTagCorrect2.add(tagStat.getCounts());
                                totalCorrect2 += tagStat.getCounts();
                            }
                            break;
                        }
                    }
                }
                totalAnnotation2 += totalS;
                keyTagTotals2.add(totalS);
            }
        }
    }

    public String toStringTag (String nameAnnotator1, String nameAnnotator2) {
        String str = "";
        String columnStr = "";
        str = nameAnnotator2+"\\"+nameAnnotator1;
        ///// Column names defined by keyTags1
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (s.isEmpty()) s = "EMPTY";
            str += "\t"+s;
            columnStr += "\t";
        }
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            if (keyTags1.contains(s)) {
                str += "\t"+s;
                columnStr += "\t";
            }
        }
        str += "\tTotal\n";
        //// we iterate over the HashMap of tags in a fixed order defined by keyTags1
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (s.isEmpty()) str+= "EMPTY"; else str += s;
            if (tags1.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags1.get(s);
                Integer totalS = 0;
                for (int k = 0; k < keyTags1.size(); k++) {
                    String keyTag = keyTags1.get(k);
                    str += "\t";
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            str +=tagStat.getCounts().toString();
                            break;
                        }
                    }
                }
                str +="\t"+totalS.toString()+"\n";
            }
            else {
                //str += "\t0";
            }
        }
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            if (keyTags1.contains(s)) {
                str += s;
                if (tags2.containsKey(s)) {
                    ArrayList<TagStat> tagStats = tags2.get(s);
                    Integer totalS = 0;
                    for (int k = 0; k < keyTags2.size(); k++) {
                        String keyTag = keyTags2.get(k);
                        str += "\t";
                        for (int j = 0; j < tagStats.size(); j++) {
                            TagStat tagStat = tagStats.get(j);
                            if (keyTag.equals(tagStat.getTag())) {
                                totalS += tagStat.getCounts();
                                str +=tagStat.getCounts().toString();
                                break;
                            }
                        }
                    }
                    str +="\t"+totalS.toString()+"\n";
                }
                else {
                    //str += "\t0";
                }
            }
        }
        Double totalAnnotations = totalAnnotation1.doubleValue()+totalAnnotation2.doubleValue();
        Double precision = (totalCorrect1.doubleValue()+totalCorrect2.doubleValue())/totalAnnotations;
        str += "Total"+columnStr+"\t"+totalAnnotations+"\n";
        str += "Total correct1"+columnStr+ "\t" +(totalCorrect1)+"\n";
        str += "Total correct2"+columnStr+ "\t" +(totalCorrect2)+"\n";
        str+= "Total precision" +columnStr + "\t" + precision+"\n";
/*        for (int i = 0; i < keyTags.size(); i++) {
            String s = keyTags.get(i);
            Integer c = keyTagCorrect.get(i);
            Double p =
        }*/
      //  System.out.println(str);
        return str;
    }

    public String toStringTag1 (String nameAnnotator1, String nameAnnotator2) {
        String str = "";
        String columnStr = "";
        str = nameAnnotator2+"\\"+nameAnnotator1;
        ///// Column names defined by keyTags1
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (s.isEmpty()) s = "EMPTY";
            str += "\t"+s;
            columnStr += "\t";
        }
        str += "\tTotal\n";
        //// we iterate over the HashMap of tags in a fixed order defined by keyTags1
        for (int i = 0; i < keyTags1.size(); i++) {
            String s = keyTags1.get(i);
            if (s.isEmpty()) str+= "EMPTY"; else str += s;
            if (tags1.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags1.get(s);
                Integer totalS = 0;
                for (int k = 0; k < keyTags1.size(); k++) {
                    String keyTag = keyTags1.get(k);
                    str += "\t";
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            str +=tagStat.getCounts().toString();
                            break;
                        }
                    }
                }
                str +="\t"+totalS.toString()+"\n";
            }
            else {
                //str += "\t0";
            }
        }
        Double precision = totalCorrect1.doubleValue()/totalAnnotation1.doubleValue();
        str += "Total"+columnStr+"\t"+totalAnnotation1+"\n";
        str += "Total correct"+columnStr+ "\t" +totalCorrect1+"\n";
        str+= "Total precision" +columnStr + "\t" + precision+"\n";
/*        for (int i = 0; i < keyTags.size(); i++) {
            String s = keyTags.get(i);
            Integer c = keyTagCorrect.get(i);
            Double p =
        }*/
      //  System.out.println(str);
        return str;
    }

    public String toStringTag2 (String nameAnnotator1, String nameAnnotator2) {
        String str = "";
        String columnStr = "";
        str = nameAnnotator1+"\\"+nameAnnotator2;
        ///// Column names defined by keyTags2
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            str += "\t"+s;
            columnStr += "\t";
        }
        str += "\tTotal\n";
        //// we iterate over the HashMap of tags in a fixed order defined by keyTags2
        for (int i = 0; i < keyTags2.size(); i++) {
            String s = keyTags2.get(i);
            str += s;
            if (tags2.containsKey(s)) {
                ArrayList<TagStat> tagStats = tags2.get(s);
                Integer totalS = 0;
                for (int k = 0; k < keyTags2.size(); k++) {
                    String keyTag = keyTags2.get(k);
                    str += "\t";
                    for (int j = 0; j < tagStats.size(); j++) {
                        TagStat tagStat = tagStats.get(j);
                        if (keyTag.equals(tagStat.getTag())) {
                            totalS += tagStat.getCounts();
                            str +=tagStat.getCounts().toString();
                            break;
                        }
                    }
                }
                str +="\t"+totalS.toString()+"\n";
            }
            else {
                //str += "\t0";
            }
        }
        Double precision = totalCorrect2.doubleValue()/totalAnnotation2.doubleValue();
        str += "Total"+columnStr+"\t"+totalAnnotation1+"\n";
        str += "Total correct"+columnStr+ "\t" +totalCorrect1+"\n";
        str+= "Total precision" +columnStr + "\t" + precision+"\n";
/*        for (int i = 0; i < keyTags.size(); i++) {
            String s = keyTags.get(i);
            Integer c = keyTagCorrect.get(i);
            Double p =
        }*/
      //  System.out.println(str);
        return str;
    }

    public String getKappaForOne () {
        String str = "";
        ArrayList<Double> chanceFrequencies = new ArrayList<Double>();
        Double totalAnnotations =  totalAnnotation1.doubleValue();
        Double precision = totalCorrect1.doubleValue()/totalAnnotations;
        str += "Chance agreements for\t"+totalAnnotations+"\tannotations in total\n";
        for (int i = 0; i < keyTags1.size(); i++) {
            String value = keyTags1.get(i);
            Integer keyTotal = keyTagTotals1.get(i);
            Double chanceFrequency = keyTotal.doubleValue()/totalAnnotations;
            str += value+"\t"+keyTotal+"\t"+chanceFrequency+"\n";
            chanceFrequencies.add(chanceFrequency);
        }
        Double chanceAgreement = 0.0;
        for (int i = 0; i < chanceFrequencies.size(); i++) {
            chanceAgreement += chanceFrequencies.get(i);
        }
        chanceAgreement = chanceAgreement/totalAnnotations;
        Double correctedObservedAgreement = precision-chanceAgreement;
        Double correctedPotentialAgreement = 1.0-chanceAgreement;
        Double kappa = correctedObservedAgreement/correctedPotentialAgreement;
        str += "Average chance agreement\t" + chanceAgreement+"\n";
        str += "Corrected observed agreement\t"+correctedObservedAgreement+"\n";
        str += "Corrected potential agreement\t"+correctedPotentialAgreement+"\n";
        str += "Kappa\t"+kappa+"\n";
        return str;
    }

    public String getKappaAverage () {
        String str = "";
        ArrayList<Double> chanceFrequencies = new ArrayList<Double>();
        Double totalAnnotations =  totalAnnotation1.doubleValue() + totalAnnotation2.doubleValue();
        //Double totalAnnotations =  totalCorrect1+(totalAnnotation1.doubleValue()-totalCorrect1) + (totalAnnotation2.doubleValue()-totalCorrect2);
        Double precision = (totalCorrect1.doubleValue()+totalCorrect2.doubleValue())/totalAnnotations;
        str += "Chance agreements for\t"+totalAnnotations+"\tannotations in total\n";
        for (int i = 0; i < keyTags1.size(); i++) {
            String value = keyTags1.get(i);
            Integer keyTotal = keyTagTotals1.get(i);
            int key2Int = keyTags2.indexOf(value);
            if (key2Int>-1) {
                Integer keyTotal2 = keyTagTotals2.get(key2Int);
                ///// why do we multiply with totals of annotator2?
                //keyTotal = keyTotal*keyTotal2;
                //this one take the sum
                keyTotal = keyTotal+keyTotal2;
                //// this one takes the average
                //keyTotal = (keyTotal+keyTotal2)/2;
            }
            else {

            }
            //// we take here the total time it is annotated and divide this by the total annotations
            Double chanceFrequency = keyTotal.doubleValue()/totalAnnotations;
            str += value+"\t"+keyTotal+"\t"+chanceFrequency+"\n";
            chanceFrequencies.add(chanceFrequency);
        }

        /// add values from annotator2 missing for annotator1
        for (int i = 0; i < keyTags2.size(); i++) {
            String value = keyTags2.get(i);
            Integer keyTotal = keyTagTotals2.get(i);
            if (!keyTags1.contains(value)) {
                Double chanceFrequency = keyTotal.doubleValue()/totalAnnotations;
                str += value+"\t"+keyTotal+"\t"+chanceFrequency+"\n";
                chanceFrequencies.add(chanceFrequency);
            }
        }
        Double chanceAgreement = 0.0;
        for (int i = 0; i < chanceFrequencies.size(); i++) {
            chanceAgreement += chanceFrequencies.get(i);
        }
        chanceAgreement = chanceAgreement/totalAnnotations;
        Double correctedObservedAgreement = precision-chanceAgreement;
        Double correctedPotentialAgreement = 1.0-chanceAgreement;
        Double kappa = correctedObservedAgreement/correctedPotentialAgreement;
        str += "Average chance agreement\t" + chanceAgreement+"\n";
        str += "Corrected observed agreement\t"+correctedObservedAgreement+"\n";
        str += "Corrected potential agreement\t"+correctedPotentialAgreement+"\n";
        str += "Kappa\t"+kappa+"\n";
        return str;
    }



    static public void main(String [] args) {
        try {
            Kappa kappa = new Kappa();
            String fileList = args[0];
            String name1 = "";
            String name2 = "";
            ArrayList<String> filePairs = Util.ReadFileToArrayList(fileList);
            for (int i = 0; i < filePairs.size(); i++) {
                String pair = filePairs.get(i);
                String [] fields = pair.split("\t");
                if (fields.length==2) {
                    String tagFile1 = fields[0];
                    String tagFile2 = fields[1];
                    HashMap<String, WordTag> tagMap1 =  TagReader.readAnnotationFileToHashMap(tagFile1);
                    HashMap<String, WordTag> tagMap2 =  TagReader.readAnnotationFileToHashMap(tagFile2);
                    Set keySet = tagMap1.keySet();
                    Iterator keys = keySet.iterator();
                    while (keys.hasNext()) {
                        String tokenId = (String) keys.next();
                        WordTag tag1 = tagMap1.get(tokenId);
                        if (tagMap2.containsKey(tokenId)) {
                            WordTag tag2 = tagMap2.get(tokenId);
                            kappa.addTags(tag1, tag2);
                        }
                        else {
                            ////there is no match for this tag
                        }
                    }

                }
            }
            kappa.getKeyTags();
            String resultFile = fileList+".kpa.xls";
            FileOutputStream fos = new FileOutputStream(resultFile);
            String str = fileList+"\n";
            str += kappa.toStringTag1("1", "2");
            str += kappa.toStringTag2("1", "2");
            //str += kappa.getKappaForOne();
            str += kappa.getKappaAverage();
            str += "\n";
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
