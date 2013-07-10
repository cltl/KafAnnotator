package eu.kyotoproject.kafannotator.sensetags;

import vu.debvisdicclient.SynsetLight;
import vu.debvisdicclient.WNAccessor;
import vu.debvisdicclient.WNViaDebServerClose;
import org.w3c.dom.Document;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Sep 21, 2010
 * Time: 9:15:22 PM
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
public class DebCalls {

    public Document doc = null;
    public boolean IAMBUSSY = true;
    WNAccessor wnacc = null;
    public ArrayList<SynsetLight> syns;

    public DebCalls () {
        doc = null;
        syns = new ArrayList<SynsetLight>();
        IAMBUSSY = false;
        if (wnacc==null) {
            wnacc = new WNViaDebServerClose("debvisdic.let.vu.nl", 9001, "kyoto", "kyoto");
        }
    }

    public DebCalls (String host, int port, String user, String passw) {
        doc = null;
        syns = new ArrayList<SynsetLight>();
        IAMBUSSY = false;
        if (wnacc==null) {
            wnacc = new WNViaDebServerClose(host, port, user, passw);
        }
    }

    public int getNrOfSenses (String word, String pos) {
        int nSenses = 0;
        ////":" is added to get the exact word
        ArrayList<SynsetLight> synsets = wnacc.getSSExactWord("", word+":", pos);
        if (synsets!=null) {
            nSenses = synsets.size();
        }
        System.out.println("nSenses in Cornetto = " + nSenses+" for POS:"+pos);
        return nSenses;
    }

    public void getLightSynsetsThread (final String word) {
        syns = new ArrayList<SynsetLight>();
        IAMBUSSY = true;
        Thread t = new Thread(new Runnable() {
            public void run() {
                ////":" is added to get the exact word
                syns = wnacc.getSSExactWord("", word+":", "");
                IAMBUSSY = false;
            }
        });
        try {
            t.setDaemon(true);
            t.start();
            t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }
    public void getLightSynsetsThread (final String db, final String lang, final String word) {
        syns = new ArrayList<SynsetLight>();
        IAMBUSSY = true;
        Thread t = new Thread(new Runnable() {
            public void run() {
                ////":" is added to get the exact word
                syns = wnacc.getSSExactWord(db,lang, word+":",  "");
                IAMBUSSY = false;
            }
        });
        try {
            t.setDaemon(true);
            t.start();
            t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

    public void getSynsetThread (final String db, final String lang, final String id) {
        syns = new ArrayList<SynsetLight>();
        IAMBUSSY = true;
        Thread t = new Thread(new Runnable() {
            public void run() {
                doc = wnacc.getSynsetDocByID(db, lang,id,"");
                IAMBUSSY = false;
            }
        });
        try {
            t.setDaemon(true);
            t.start();
            t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

}
