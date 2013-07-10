package eu.kyotoproject.kafannotator.io;

import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: kyoto
 * Date: Aug 6, 2010
 * Time: 10:43:43 AM
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
public class Locations {

  // vector with the previous 10 databases created
  Vector databases = new Vector(1,1);
  //vector with the previous 10 locations for opened databases
  Vector locations  = new Vector(1,1);
  //vector with the previous 10 locations for input
  public Vector inputs  = new Vector(1,1);

  public Locations(String locationsFile) {

    LocationsParser parser = new LocationsParser(locationsFile);
    databases = parser.databases;
    locations = parser.locations;
    inputs = parser.input;

  }

  /////////////////the serialization of previous locationsin xml file
  public void serialization(String locationsFile) throws java.io.IOException {
    try {
        FileOutputStream file = new FileOutputStream(locationsFile);
        String str = "";
        //s.defaultWriteObject();
         str += "<fileLocations>\n";

          str += "\t<openedDatabases>\n ";

          for (int i=0; i<databases.size()&& i<10; i++){
            str +="\t\t<database>";
               str +=databases.elementAt(i);
            str +="</database>\n";
          }
          str += "\t</openedDatabases>\n";

          str +="\t<previousLocations>\n ";
          for (int i=0; i<locations.size()&& i<10; i++){
            str +="\t\t<location>";
               str +=locations.elementAt(i);
            str +="</location>\n";
          }

          str +="\t</previousLocations>\n";

	    str +="\t<previousInput>\n ";
          for (int i=0; i<inputs.size()&& i<10; i++){
            str +="\t\t<input>";
               str +=inputs.elementAt(i);
            str +="</input>\n";
          }

          str +="\t</previousInput>\n";

        str += "</fileLocations>\n";

        file.write(str.getBytes(), 0, str.length());
        file.flush();
        file.close();
    }//try
    catch (Exception e){
        e.printStackTrace();
    }
  }


}