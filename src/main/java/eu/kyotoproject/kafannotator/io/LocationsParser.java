package eu.kyotoproject.kafannotator.io;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 6, 2010
 * Time: 10:43:54 AM
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
public class LocationsParser extends DefaultHandler {

  String value = "";
  public Vector locations = new Vector(1,1);
  public Vector databases  = new Vector(1,1);
  public Vector input  = new Vector(1,1);

   public LocationsParser(String fileName){
       parseFile(fileName);
   }

   public boolean parseFile(String filePath)
    {
    	return parseFile(new File(filePath));
    }

    public boolean parseFile(File file)
    {
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
           }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
            if (qName.equals("database")) {
                databases.add(value.trim());
            }
            if (qName.equals("location")) {
                locations.add(value.trim());
            }
            if (qName.equals("input")) {
                input.add(value.trim());
            }
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        value += new String(ch, start, length);
    }

}


