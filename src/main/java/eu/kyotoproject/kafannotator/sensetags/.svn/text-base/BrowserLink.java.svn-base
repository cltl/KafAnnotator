package eu.kyotoproject.kafannotator.sensetags;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Sep 21, 2010
 * Time: 6:14:43 PM
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
public class BrowserLink {


    static public String windowsbrowserPath = "C:/Program Files/Mozilla Firefox/firefox.exe -url";
    //static public String macbrowserPath = "/Applications/Firefox.app/Contents/MacOS/firefox-bin -new-tab";
    //
    //
    static public String macbrowserPath = "open";

    static private void runExec(final String cmd, String[] envp, final File dir){
        Thread t = new Thread(new Runnable() {
        public void run() {
              try {
                Process p = Runtime.getRuntime().exec(cmd, null, dir);
                InputStream is = p.getInputStream();
                InputStream es = p.getErrorStream();
                TakeOutput to = new TakeOutput(is),
                              te = new TakeOutput(es);
                to.start();
                te.start();
                p.waitFor();

              }
              catch (IOException e){
                e.printStackTrace();
              }
              catch (InterruptedException ie){
                ie.printStackTrace();
              }
        }    });
        try {
            t.start();
            t.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }

   static String makeQueryCmd (String URL) {
       String cmd = macbrowserPath+" \""+URL+"\"";
       System.out.println(cmd);
       return cmd;
 }
   public static void link (final String url) {
        Thread t = new Thread(new Runnable() {
        public void run() {
            String cmd = makeQueryCmd(url);
            if (cmd.length()> 0)
            {  //System.out.println("Command:"+cmd);
               runExec(cmd, null, new File("."));
            }
        }    });
        t.start();
    }

    
}
