package eu.kyotoproject.kafannotator.interannotator;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: 3/21/11
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompareAnnotations {

    static Kappa getKappaResultsPerLayer (HashMap<String, WordTag> tagMap1,
                                          HashMap<String, WordTag> tagMap2,
                                          int layer,
                                          boolean emptyTags)  {
        Kappa kappa = new Kappa(emptyTags);
        Set keySet = tagMap1.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String tokenId = (String) keys.next();
            WordTag tag1 = tagMap1.get(tokenId);

            if (tagMap2.containsKey(tokenId)) {
                WordTag tag2 = tagMap2.get(tokenId);
                kappa.addTagsLayer(tag1, tag2, layer);
            }
            else {
                kappa.addTagsLayer(tag1, new WordTag(), layer);
            }
        }
        keySet = tagMap2.keySet();
        keys = keySet.iterator();
        while (keys.hasNext()) {
            String tokenId = (String) keys.next();
            WordTag tag2 = tagMap2.get(tokenId);

            if (!tagMap1.containsKey(tokenId)) {
                kappa.addTagsLayer(new WordTag(), tag2, layer);
            }
            else {
                /// already covered!
            }
        }
        return kappa;
    }


    static String getKappaStringForLayer (HashMap<String, WordTag> tagMap1,
                                          HashMap<String, WordTag> tagMap2,
                                          int layer,
                                          boolean emptTags) {
        String str = "\nLAYER\t"+layer+"\n";
        str +=  "TAG MATRIX FOR KAPPA\n";
        Kappa kappa = getKappaResultsPerLayer(tagMap1, tagMap2, layer, emptTags);
        kappa.getKeyTags();
        str += kappa.toStringTag1("file 2", "file 1");
        str += "\n";
        //str += kappa.getKappaForOne();
        str += kappa.getKappaAverage();
        return str;
    }
    /**
     * This program reads two tag files and compares the individual tokens.
     * @param args
     */
    static public void main(String [] args) {
            String tagFile1 = "";
            String tagFile2 = "";
            boolean matchEmptyTags = false;
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equalsIgnoreCase("--tag-file1") && args.length-1>i) {
                    tagFile1 = args[i+1];
                }
                else if (arg.equalsIgnoreCase("--tag-file2") && args.length-1>i) {
                    tagFile2 = args[i+1];
                }
                else if (arg.equalsIgnoreCase("--union")) {
                     matchEmptyTags = true;
                }
                else if (arg.equalsIgnoreCase("--intersection")) {
                     matchEmptyTags = false;
                }
            }
            if (tagFile1.isEmpty()) {
                System.out.println("Argument --tag-file1 is missing");
                return;
            }
            if (tagFile2.isEmpty()) {
                System.out.println("Argument --tag-file2 is missing");
                return;
            }
        compareTwoTagFiles(tagFile1, tagFile2, matchEmptyTags);
    }

    static public void compareTwoTagFiles (String tagFile1, String tagFile2, boolean matchEmptyTags ) {
        try {
            File tagMismatchFile = null;
            Kappa kappa = new Kappa(matchEmptyTags);
            int nTokenMatch1 = 0;
            int nTokenMatch2 = 0;
            int nTagMatch1= 0;
            int nTagMatch2 = 0;

            String tagMismatchFilePath = new File(tagFile1).getParent()+"/"+ new File(tagFile1).getName()+ ".iaa-details.xls";
            tagMismatchFile = new File(tagMismatchFilePath);
            String kappaFile = "";
            if (matchEmptyTags) {
                kappaFile = new File(tagFile1).getParent()+"/"+ new File(tagFile1).getName()+".kappa-union.xls";
            }
            else {
                kappaFile = new File(tagFile1).getParent()+"/"+new File(tagFile1).getName()+".kappa-intersect.xls";
            }
           /* String comparisonFile = args[0]+args[1]+".iaa.xls";
            FileOutputStream fos = new FileOutputStream(comparisonFile);*/
            FileOutputStream kappafos = new FileOutputStream(kappaFile);
            FileOutputStream fos = new FileOutputStream(tagMismatchFile);
            HashMap<String, WordTag> tagMap1 =  TagReader.readAnnotationFileToHashMap(tagFile1);
            HashMap<String, WordTag> tagMap2 =  TagReader.readAnnotationFileToHashMap(tagFile2);
            String missedTokens = "\nTokens not covered by:\t"+tagFile2+"\n\n";
            String differentTags = "\nTag differences:"+"\n\n";

            String str = "tagFile1\t" + tagFile1+"\n";
            str +=  "Number of tagged tokens\t" + tagMap1.size() + "\n";
            str +=  "tagFile2\t" + tagFile2+"\n";
            str +=  "Number of tagged tokens\t" + tagMap2.size() + "\n";
            kappafos.write(str.getBytes());

            str = getKappaStringForLayer(tagMap1, tagMap2, 1, matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 1\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 1);

            str = getKappaStringForLayer(tagMap1, tagMap2, 2,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 2\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 2);

            str = getKappaStringForLayer(tagMap1, tagMap2, 3,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 3\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 3);

            str = getKappaStringForLayer(tagMap1, tagMap2, 4,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 4\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 4);

            str = getKappaStringForLayer(tagMap1, tagMap2, 5,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 5\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 5);

            str = getKappaStringForLayer(tagMap1, tagMap2, 6,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 6\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 6);

            str = getKappaStringForLayer(tagMap1, tagMap2, 7, matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 7\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 7);

            str = getKappaStringForLayer(tagMap1, tagMap2, 8,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());
            differentTags += "\nLayer 8\ttoken\t"+tagFile1+"\t"+tagFile2+"\n";
            differentTags += getDifferenceString(tagMap1, tagMap2, 8);

            Set keySet = tagMap1.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String tokenId = (String) keys.next();
                WordTag tag1 = tagMap1.get(tokenId);
                if (tagMap2.containsKey(tokenId)) {
                    nTokenMatch1++;
                    WordTag tag2 = tagMap2.get(tokenId);
                    kappa.addTagsPerLayer(tag1, tag2);
                    /// we count if there is no agreement at any level.
                    int nDiff = tag1.countTagDifference(tag2);
                    if (nDiff==0) {
                        nTagMatch1++;
                    }
                    else {     /////
                    }
                }
                else {
                   missedTokens += tag1.toString()+"\n";
                   kappa.addTagsPerLayer(tag1, new WordTag());
                }
            }
            kappa.getKeyTags();
            str =  "TAG MATRIX FOR KAPPA\n";
            str += kappa.toStringTag1(new File (tagFile1).getName(), new File (tagFile2).getName());
            str += "\n";
            kappafos.write(str.getBytes());
            //String kappaString= kappa.getKappaForOne();
            String kappaString= kappa.getKappaAverage();
            kappafos.write(kappaString.getBytes());
            kappafos.close();

            missedTokens += "\nTokens not covered by:\t"+tagFile1+"\n\n";

            keySet = tagMap2.keySet();
            keys = keySet.iterator();
            while (keys.hasNext()) {
                String tokenId = (String) keys.next();
                WordTag tag2 = tagMap2.get(tokenId);
                if (tagMap1.containsKey(tokenId)) {
                    nTokenMatch2++;
                    WordTag tag1 = tagMap1.get(tokenId);
                    int nDiff = tag2.countTagDifference(tag1);
                    if (nDiff==0) {
                        nTagMatch2++;
                    }
                    else {
                        ///already covered
                    }
                }
                else {
                    missedTokens += tag2.toString()+"\n";
                }
            }
            str = "tagFile1\t" + tagFile1+"\n";
            str +=  "tagFile2\t" + tagFile2+"\n\n";
            str +=  "Number of tagged tokens tagFile1\t" + tagMap1.size() + "\n";
            str +=  "nTokenMatch1\t" + nTokenMatch1+"\n";
            str +=  "nTagMatch1\t" + nTagMatch1+"\n";
            str +=  "Number of tagged tokens tagFile2\t" + tagMap2.size() + "\n";
            str +=  "nTokenMatch2\t" + nTokenMatch2+"\n";
            str +=  "nTagMatch2\t" + nTagMatch2+"\n";
            str += "\n";
            fos.write(str.getBytes());
            fos.write(differentTags.getBytes());
            fos.write(missedTokens.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    static public String getDifferenceString (HashMap<String, WordTag> tagMap1,
                                              HashMap<String, WordTag> tagMap2,
                                              int layer) {
        String str = "";
        Set keySet = tagMap1.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String tokenId = (String) keys.next();
            WordTag tag1 = tagMap1.get(tokenId);
            if (tagMap2.containsKey(tokenId)) {
                WordTag tag2 = tagMap2.get(tokenId);
                /// we count if there is no agreement at any level.
                String tagValue1 = tag1.getTag(layer);
                String tagValue2 = tag2.getTag(layer);
                if (!tagValue1.equals(tagValue2)) {
                    str += tag1.getTokenId()+"\t"+tag1.getWordToken()+"\t"+tagValue1+"\t"+tagValue2+"\n";
                }
            }
        }
        return str;
    }

}
