package eu.kyotoproject.kafannotator.sensetags;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Sep 21, 2010
 * Time: 6:15:53 PM
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
public class TakeOutput extends Thread
    {
    InputStream in;
    FileOutputStream out;
    String user = "";
    StringBuffer message = new StringBuffer();
    byte b[] = new byte[16*1024];
    int  c;

    public TakeOutput(String user, InputStream in)
    {
       this.in = in;
       this.user = user;
    }

    public TakeOutput(InputStream in)
    {
       this.in = in;
       this.out = null;
    }

    public TakeOutput(InputStream in, FileOutputStream out)
    {
       this.in = in;
       this.out = out;
    }

    public void run()
    {
      try{

        while ((c = in.read(b, 0, b.length)) != -1){
            message = message.append(new String(b, 0, c));
            if (out!=null) {
                out.write(message.toString().getBytes());
            }
            //System.out.println(message);
            message = message.delete(0,c);
        }
        in.close();
      }
      catch (IOException io){
//        System.out.println(io.getMessage());
      }
    }
}
