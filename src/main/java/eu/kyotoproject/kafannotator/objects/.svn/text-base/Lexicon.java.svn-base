package eu.kyotoproject.kafannotator.objects;

import eu.kyotoproject.kaf.KafSaxParser;
import eu.kyotoproject.kaf.KafSense;
import eu.kyotoproject.kaf.KafTerm;
import eu.kyotoproject.kaf.KafWordForm;
import eu.kyotoproject.kafannotator.util.XmlCharConversion;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 5, 2010
 * Time: 4:56:22 PM
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
public class Lexicon extends DefaultHandler {
    String form = "";
    String tag = "";
    int freq = 0;
    EntryData entryData = new EntryData();
    String value = "";
    public HashMap data;

    public Lexicon () {
     data = new HashMap();
    }

    public boolean parseFile(String filePath)
    {
    	return parseFile(new File (filePath));
    }

    public boolean parseFile(File file)
    {   if (file.exists()) {
            try
            {
                InputSource inp = new InputSource (new FileReader(file));
                return parse(inp);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean parse(InputSource source)
    {
    	try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            SAXParser parser = factory.newSAXParser();
            parser.parse(source, this);
            return true;
        } catch (FactoryConfigurationError factoryConfigurationError) {
            factoryConfigurationError.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
          //  System.out.println("filePath = " + filePath);
         //   System.out.println("XML PARSER ERROR:");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void startElement(String uri, String localName,
                         String qName, Attributes attributes)
        throws SAXException {
           value = "";
           if (qName.equals("entry")) {
               entryData = new EntryData();
               form = "";
           }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
            if (qName.equals("entry")) {
                if (form.length()>0) {
                    upDateLex(form,entryData);
                    form = "";
                }
            }
            else if (qName.equals("form")) {
                form = value;
            }
            else if (qName.equals("tag")) {
                tag = value;
            }
            else if (qName.equals("freq")) {
                freq = Integer.parseInt(value);
            }
            else if (qName.equals("tagData")) {
                if (tag.length()>0) {
                    entryData.upDate(tag, freq);
                    tag = "";
                    freq = -1;
                }
            }
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        value += new String(ch, start, length);
    }

    public void saveLexicon(String outputFile) {
	    try {
          File theFile = new File (outputFile);
          if (theFile.exists()) {
              String outputPath = outputFile+".bu";
              File input = new File(outputFile);
             input.renameTo(new File(outputPath));

          }
          System.out.println("before writing data.size() = " + data.size());
		  FileOutputStream fos = new FileOutputStream(outputFile);
		  String lexOut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                  "\t<lexicon>\n";
		  Set classKeys = data.keySet();
          EntryData entryData;
		  Iterator class_it = classKeys.iterator();
		  while (class_it.hasNext()) {
		     String classKey = (String) class_it.next();
              entryData = (EntryData) data.get(classKey);
             if (XmlCharConversion.hasIllegalChar(classKey)) {
                 classKey = XmlCharConversion.replaceXmlChar(classKey);
             }
		     lexOut += "\t\t<entry>\n";
		     lexOut += "\t\t\t<form>"+classKey+"</form>\n";
             lexOut += entryData.toXmlString();
		     lexOut += "\t\t</entry>\n";
             fos.write(lexOut.getBytes(), 0, lexOut.length());
             lexOut = "";
		  }
		  lexOut += "\t</lexicon>\n";
		  fos.write(lexOut.getBytes(), 0, lexOut.length());
		  lexOut = null;
		  fos.close();
	    }
          catch (Exception e){ e.printStackTrace();}
	}

    public void printLex() {
         Set keys = data.keySet();
         Iterator key_it = keys.iterator();
         while (key_it.hasNext()) {
            String key = (String) key_it.next();
            EntryData keyData = (EntryData) data.get(key);
            System.out.println("\nKey:"+key);
            System.out.println("TagData:"+keyData.toString());
         }
    }

    public void upDateLex (String entry, EntryData entryData) {
        if (data.containsKey(entry))
        {  EntryData old_entryData = (EntryData) data.get(entry);
           old_entryData.upDate(entryData);
           data.put(entry, old_entryData);
        }
        else
        { data.put(entry, entryData);}
    }

    public void incrementEntry (String entry, String tag) {
        if (data.containsKey(entry))
        {  EntryData entryData = (EntryData) data.get(entry);
           entryData.increment(tag);
           data.put(entry, entryData);
        }
        else {
           EntryData entryData = new EntryData();
           entryData.addTagData(tag);
           data.put(entry, entryData);
       //    System.out.println("added entry = "+entry);
       //    System.out.println("data.size() = " + data.size());
        }
    }

    public void decrementEntry (String entry, String tag) {
        if (data.containsKey(entry))
        {  EntryData entryData = (EntryData) data.get(entry);
           entryData.decrement(tag);
           data.put(entry, entryData);
        }
    }
        

    public String getTag(String word) {
        String tag = "";
        if (data.containsKey(word))
        {  EntryData entryData = (EntryData) data.get(word);
           tag = entryData.getTopTag();
        }
        else {
            System.out.println("not in lexicon word = " + word);
        }
        if (tag.length() ==0) {
            //// FIRST TAG IS THE DEFAULT TAG
/*
             if (utilKit.checkStringNumber(word)) {
                 tag = "CARD";
             }
             else
             if (word.endsWith("-")) {
                 tag = "SW";
             }
             else
             if (!utilKit.iniCase(word)) {
                 if ((word.endsWith("en")) || (word.endsWith("t")))
                 {   tag = "VB";}
                 else {
                    tag = "ADJA";
                 }
             }
             else
             { tag = "NN";}
*/
         }
         return tag;
    }

    public int getTopFreq(String word) {
        if (data.containsKey(word))
        {  EntryData entryData = (EntryData) data.get(word);
           return entryData.getTopFreq();
        }
        else {
            return 0;
        }
    }
    public int getTagFreq(String word, String tag) {
        if (data.containsKey(word))
        {  EntryData entryData = (EntryData) data.get(word);
           return entryData.getTopFreq();
        }
        else {
            return 0;
        }
    }

    public WordTag makeWordTag(Integer sentence, String id, String word, String type,String pos,  String synset, int order) {
        WordTag wordTag;
        String  key = word.toLowerCase();
        if (data.containsKey(key))
        {  EntryData entryData = (EntryData) data.get(key);
           tag = entryData.getTopTag();
           freq = entryData.getTopFreq();
        }
        if (tag.length() ==0) {
            //// FIRST TAG IS THE DEFAULT TAG
/*
            if (utilKit.checkStringNumber(key)) {
                        tag = "CARD";
            }
            else
            if (word.endsWith("-")) {
                tag = "SW";
            }
            else
            if (!utilKit.iniCase(word)) {
                if ((word.endsWith("en")) || (word.endsWith("t")))
                {   tag = "VB";}
                else {
                   tag = "ADJA";
                }
            }
            else
           { tag = "NN";}
*/
        }
        wordTag = new WordTag(sentence, id, word, type, pos, synset, order);
        return wordTag;

    }

    /// Words in list....
    public ArrayList<WordTag> tagWordList (ArrayList<KafWordForm> words) {
        ArrayList<WordTag> result = new ArrayList<WordTag>();
        KafWordForm nextWord = null;
        String nextKey = "";
        String tag = "";
        int freq = 0;
        WordTag wordTag;
        for (int i = 0; i < words.size(); i++) {
            nextWord =  words.get(i);
            String synsetId = "";
            wordTag = makeWordTag(Integer.parseInt(nextWord.getSent()), nextWord.getWid(), nextWord.getWf(),"", "",synsetId, i);
            result.add(wordTag);
        }
        return result;
    }

    public ArrayList<WordTag> tagWordList (KafSaxParser parser) {
        ArrayList<WordTag> result = new ArrayList<WordTag>();
        KafWordForm nextWord = null;
        KafTerm kafTerm = null;
        WordTag wordTag;
        for (int i = 0; i < parser.kafWordFormList.size(); i++) {
            nextWord =  parser.kafWordFormList.get(i);
            kafTerm = null;
            if (parser.WordFormToTerm.containsKey(nextWord.getWid())) {
                String termId = parser.WordFormToTerm.get(nextWord.getWid());
                kafTerm = parser.getTerm(termId);
            }
            if (kafTerm != null) {
                String synset = "";
                if (kafTerm.getSenseTags().size()==1) {
                    KafSense sense = kafTerm.getSenseTags().get(0);
                    synset = sense.getSensecode();
                }
                wordTag = makeWordTag(Integer.parseInt(nextWord.getSent()), nextWord.getWid(), nextWord.getWf(), kafTerm.getLemma(), kafTerm.getPos(), synset, i);
            }
            else {
                wordTag = makeWordTag(Integer.parseInt(nextWord.getSent()), nextWord.getWid(), nextWord.getWf(), "", "", "", i);
            }
            result.add(wordTag);
        }
        return result;
    }

}
