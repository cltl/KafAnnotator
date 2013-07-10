package eu.kyotoproject.kafannotator.util;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 10, 2010
 * Time: 10:12:29 AM
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
public class XmlCharConversion {
    public static final String illegalChars = "<>&\"";


    public static boolean hasIllegalChar (String htmlWord) {
        for (int i=0; i<htmlWord.length();i++) {
            char c = htmlWord.charAt(i);
            if (illegalChars.indexOf(c)!=-1) {
               return true;
            }
        }
        return false;
    }

    static public String replaceXmlChar (String word) {
            String htmlWord = word;
            htmlWord = htmlWord.replaceAll("<", "&lt;");
            htmlWord = htmlWord.replaceAll(">", "&gt;");
            htmlWord = htmlWord.replaceAll("&", "&amp;");
            htmlWord = htmlWord.replaceAll("\"", "&quot;");
        return htmlWord;
    }

    static public String replaceXmlCharKyoto (String word) {
            String htmlWord = word;
            //htmlWord = htmlWord.replaceAll("<", "&lt;");
            //htmlWord = htmlWord.replaceAll(">", "&gt;");
            //htmlWord = htmlWord.replaceAll("&", "&amp;");
            //htmlWord = htmlWord.replaceAll("\"", "&quot;");
            htmlWord = htmlWord.replaceAll("<", "[kyoto:lt]");
            htmlWord = htmlWord.replaceAll(">", "[kyoto:gt]");
            htmlWord = htmlWord.replaceAll("&", "[kyoto:amp]");
            htmlWord = htmlWord.replaceAll("\"", "[kyoto:qt]");
/*
            htmlWord = htmlWord.replaceAll("Ç", "&Ccedil;");
            htmlWord = htmlWord.replaceAll("ç", "&ccdil;");
            htmlWord = htmlWord.replaceAll("Ñ", "&Ntilde;");
            htmlWord = htmlWord.replaceAll("ñ", "&ntilde;");
            htmlWord = htmlWord.replaceAll("Þ", "&THORN;");
            htmlWord = htmlWord.replaceAll("þ", "&thorn;");
            htmlWord = htmlWord.replaceAll("Ý", "&Yacute;");
            htmlWord = htmlWord.replaceAll("ý", "&yacute;");
            htmlWord = htmlWord.replaceAll("ÿ", "&yuml;");
            htmlWord = htmlWord.replaceAll("ß", "&szlig;");
            htmlWord = htmlWord.replaceAll("Æ", "&AElig;");
            htmlWord = htmlWord.replaceAll("Á", "&Aacute;");
            htmlWord = htmlWord.replaceAll("Â", "&Acirc;");
            htmlWord = htmlWord.replaceAll("À", "&Agrave;");
            htmlWord = htmlWord.replaceAll("Å", "&Aring;");
            htmlWord = htmlWord.replaceAll("Ã", "&Atilde;");
            htmlWord = htmlWord.replaceAll("Ä", "&Auml;");
            htmlWord = htmlWord.replaceAll("æ", "&aelig;");
            htmlWord = htmlWord.replaceAll("á", "&aacute;");
            htmlWord = htmlWord.replaceAll("â", "&acirc;");
            htmlWord = htmlWord.replaceAll("à", "&agrave;");
            htmlWord = htmlWord.replaceAll("å", "&aring;");
            htmlWord = htmlWord.replaceAll("ã", "&atilde;");
            htmlWord = htmlWord.replaceAll("ä", "&auml;");
            htmlWord = htmlWord.replaceAll("Ð", "&ETH;");
            htmlWord = htmlWord.replaceAll("É", "&Eacute;");
            htmlWord = htmlWord.replaceAll("Ê", "&Ecirc;");
            htmlWord = htmlWord.replaceAll("È", "&Egrave;");
            htmlWord = htmlWord.replaceAll("Ë", "&Euml;");
            htmlWord = htmlWord.replaceAll("ð", "&eth;");
            htmlWord = htmlWord.replaceAll("é", "&eacute;");
            htmlWord = htmlWord.replaceAll("ê", "&ecirc;");
            htmlWord = htmlWord.replaceAll("è", "&egrave;");
            htmlWord = htmlWord.replaceAll("ë", "&euml;");
            htmlWord = htmlWord.replaceAll("Í", "&Iacute;");
            htmlWord = htmlWord.replaceAll("Î", "&Icirc;");
            htmlWord = htmlWord.replaceAll("Ì", "&Igrave;");
            htmlWord = htmlWord.replaceAll("Ï", "&Iuml;");
            htmlWord = htmlWord.replaceAll("í", "&iacute;");
            htmlWord = htmlWord.replaceAll("î", "&icirc;");
            htmlWord = htmlWord.replaceAll("ì", "&igrave;");
            htmlWord = htmlWord.replaceAll("ï", "&iuml;");
            htmlWord = htmlWord.replaceAll("Ó", "&Oacute;");
            htmlWord = htmlWord.replaceAll("Ô", "&Ocirc;");
            htmlWord = htmlWord.replaceAll("Ò", "&Ograve;");
            htmlWord = htmlWord.replaceAll("Ø", "&Oslash;");
            htmlWord = htmlWord.replaceAll("Õ", "&Otilde;");
            htmlWord = htmlWord.replaceAll("Ö", "&Ouml;");
            htmlWord = htmlWord.replaceAll("ó", "&oacute;");
            htmlWord = htmlWord.replaceAll("ô", "&ocirc;");
            htmlWord = htmlWord.replaceAll("ò", "&ograve;");
            htmlWord = htmlWord.replaceAll("ø", "&oslash;");
            htmlWord = htmlWord.replaceAll("õ", "&otilde;");
            htmlWord = htmlWord.replaceAll("ö", "&ouml;");
            htmlWord = htmlWord.replaceAll("Ú", "&Uacute;");
            htmlWord = htmlWord.replaceAll("Û", "&Ucirc;");
            htmlWord = htmlWord.replaceAll("Ù", "&Ugrave;");
            htmlWord = htmlWord.replaceAll("Ü", "&Uuml;");
            htmlWord = htmlWord.replaceAll("ú", "&uacute;");
            htmlWord = htmlWord.replaceAll("û", "&ucirc;");
            htmlWord = htmlWord.replaceAll("ù", "&ugrave;");
            htmlWord = htmlWord.replaceAll("ü", "&uuml;");
            htmlWord = htmlWord.replaceAll("®", "&reg;");
            htmlWord = htmlWord.replaceAll("±", "&plusmn;");
            htmlWord = htmlWord.replaceAll("µ", "&micro;");
            htmlWord = htmlWord.replaceAll("¶", "&para;");
            htmlWord = htmlWord.replaceAll("·", "&middot;");
            htmlWord = htmlWord.replaceAll("¢", "&cent;");
            htmlWord = htmlWord.replaceAll("£", "&pound;");
            htmlWord = htmlWord.replaceAll("¥", "&yen;");
            htmlWord = htmlWord.replaceAll("¼", "&frac14;");
            htmlWord = htmlWord.replaceAll("½", "&frac12;");
            htmlWord = htmlWord.replaceAll("¾", "&frac34;");
            htmlWord = htmlWord.replaceAll("¹", "&sup1;");
            htmlWord = htmlWord.replaceAll("²", "&sup2;");
            htmlWord = htmlWord.replaceAll("³", "&sup3;");
            htmlWord = htmlWord.replaceAll("¿", "&iquest;");
            htmlWord = htmlWord.replaceAll("°", "&deg;");
            htmlWord = htmlWord.replaceAll("¦", "&brvbar;");
            htmlWord = htmlWord.replaceAll("§", "&sect;");
            htmlWord = htmlWord.replaceAll("«", "&laquo;");
            htmlWord = htmlWord.replaceAll("»", "&raquo;");
*/
        return htmlWord;
    }

}
