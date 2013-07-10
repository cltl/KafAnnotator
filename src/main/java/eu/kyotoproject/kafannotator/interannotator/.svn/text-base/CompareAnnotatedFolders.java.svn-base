package eu.kyotoproject.kafannotator.interannotator;

import eu.kyotoproject.kafannotator.TagReader;
import eu.kyotoproject.kafannotator.objects.WordTag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kyoto
 * Date: 1/4/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompareAnnotatedFolders {
    static HashMap<String, WordTag> tagMap1;
    static HashMap<String, WordTag> tagMap2;


    /**
     * This program reads two tag files and compares the individual tokens.
     * @param args
     */
    static public void main(String [] args) {
            String tagFolder1 = "";
            String tagFolder2 = "";
            boolean matchEmptyTags = false;
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equalsIgnoreCase("--tag-folder1") && args.length-1>i) {
                    tagFolder1 = args[i+1];
                }
                else if (arg.equalsIgnoreCase("--tag-folder2") && args.length-1>i) {
                    tagFolder2 = args[i+1];
                }
                else if (arg.equalsIgnoreCase("--union")) {
                    matchEmptyTags = true;
                }
                else if (arg.equalsIgnoreCase("--intersection")) {
                    matchEmptyTags = false;
                }
            }
            if (tagFolder1.isEmpty()) {
                System.out.println("Argument --tag-folder1 is missing");
                return;
            }
            if (tagFolder2.isEmpty()) {
                System.out.println("Argument --tag-folder2 is missing");
                return;
            }
            tagMap1 = new HashMap<String, WordTag>();
            tagMap2 = new HashMap<String, WordTag>();

            ArrayList<File> files1 = makeFlatFileList(tagFolder1, ".tag");
            ArrayList<File> files2 = makeFlatFileList(tagFolder2, ".tag");
            for (int i = 0; i < files1.size(); i++) {
                File file1 = files1.get(i);
                for (int j = 0; j < files2.size(); j++) {
                    File file2 = files2.get(j);
                    if (file1.getName().equals(file2.getName())) {
                        //// we do a local file comparison first, the results of this are store on a per file basis
                        CompareAnnotations.compareTwoTagFiles(file1.getAbsolutePath(), file2.getAbsolutePath(), matchEmptyTags);
                        //// we read the tags again in the global hashmap for a global comparison
                        readTwoTagFiles (file1, file2);
                        break;
                    }
                }
                //break;
            }
        /// after reading all file pairs we do the gl;obal comparison
            compareTagFiles(tagFolder1,matchEmptyTags);
    }




    static public ArrayList<File> makeFlatFileList(String inputPath, String extension) {
        ArrayList<File> acceptedFileList = new ArrayList<File>();
        File[] theFileList = null;
        File lF = new File(inputPath);
        if ((lF.canRead()) && lF.isDirectory()) {
            theFileList = lF.listFiles();
            for (int i = 0; i < theFileList.length; i++) {
                if (theFileList[i].isFile()) {
                    if (theFileList[i].getAbsolutePath().endsWith(extension)) {
                        acceptedFileList.add(theFileList[i]);
                    }
                }
            }
        }
        return acceptedFileList;
    }


    static public void readTwoTagFiles (File tagFile1, File tagFile2) {
        HashMap<String, WordTag> localTagMap1 =  TagReader.readAnnotationFileToHashMap(tagFile1.getAbsolutePath());
        Set keySet = localTagMap1.keySet();
        Iterator keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            WordTag wordTag = localTagMap1.get(key);
            key = tagFile1.getName()+key;
            tagMap1.put(key, wordTag);
        }
        HashMap<String, WordTag> localTagMap2 =  TagReader.readAnnotationFileToHashMap(tagFile2.getAbsolutePath());
        keySet = localTagMap2.keySet();
        keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            WordTag wordTag = localTagMap2.get(key);
            key = tagFile2.getName()+key;
            tagMap2.put(key, wordTag);
        }
    }

    /**
     * Comparison of the global tag sets
     * @param kappaFileName
     * @param matchEmptyTags
     */

    static public void compareTagFiles (String kappaFileName, boolean matchEmptyTags ) {
        try {
            Kappa kappa = new Kappa(matchEmptyTags);

            String kappaFile = "";
            if (matchEmptyTags) {
                kappaFile = kappaFileName+"/kappa-union.xls";
            }
            else {
                kappaFile = kappaFileName+"/kappa-intersect.xls";
            }
            /* String comparisonFile = args[0]+args[1]+".iaa.xls";
        FileOutputStream fos = new FileOutputStream(comparisonFile);*/
            FileOutputStream kappafos = new FileOutputStream(kappaFile);


            String str =  "Number of tagged tokens\t" + tagMap1.size() + "\n";
            str +=  "Number of tagged tokens\t" + tagMap2.size() + "\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 1, matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 2,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 3,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 4,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 5,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 6,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 7, matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());

            str = CompareAnnotations.getKappaStringForLayer(tagMap1, tagMap2, 8,matchEmptyTags)+"\n";
            kappafos.write(str.getBytes());


            Set keySet = tagMap1.keySet();
            Iterator keys = keySet.iterator();
            while (keys.hasNext()) {
                String tokenId = (String) keys.next();
                WordTag tag1 = tagMap1.get(tokenId);
                if (tagMap2.containsKey(tokenId)) {
                    WordTag tag2 = tagMap2.get(tokenId);
                    // kappa.addTags(tag1, tag2);
                    kappa.addTagsPerLayer(tag1, tag2);
                    /// we count if there is no agreement at any level.
                }
                else {
                    kappa.addTagsPerLayer(tag1, new WordTag());
                }
            }

            str =  "TAG MATRIX FOR KAPPA\n";
            str += "\n";
            kappafos.write(str.getBytes());
            //String kappaString= kappa.getKappaForOne();
            String kappaString= kappa.getKappaAverage();
            kappafos.write(kappaString.getBytes());
            kappafos.close();


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
