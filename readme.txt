KafAnnotator
version 1.0
Copyright: VU University Amsterdam
email: p.vossen@let.vu.nl


DESCRIPION:
KafAnnotator is a stand-alone Java program that can be used to annotate word-tokens in text files in the KYOTO Annotation Format (www.kyotoproject.eu).
Any set of tags can be used and each word-token can receive up to 8 different annotations. The tags are saved in a separate tag file.
The program also has options to export the annotation to triplets. This option has been used for the evaluation of the KYOTO project.
More details can be read in the manual.

SOURCE CODE:
https://kyoto.let.vu.nl/svn/kyoto/trunk/tools/kafannotator

DEPENDENCIES:
        <dependency>
          <groupId>eu.kyotoproject.kaf</groupId>
          <artifactId>KyotoKafSaxParser</artifactId>
          <version>1.0</version>
          <scope>compile</scope>
        </dependency>
        <dependency>
          <groupId>eu.kyotoproject.evaluation</groupId>
          <artifactId>KybotEvaluation</artifactId>
          <version>1.0</version>
          <scope>compile</scope>
        </dependency>
        <dependency>
          <groupId>vu.debvisdicclient</groupId>
          <artifactId>DebVisDicCLient</artifactId>
          <version>1.0</version>
          <scope>compile</scope>
        </dependency>

The binaries can be built using maven and the pom.xml

> mvn install

BINARIES:
http://kyoto.let.vu.nl/~kyoto/files/kafannotator/kafannotator_v.0.1.zip
WEBSITE:
http://xmlgroup.iit.cnr.it/kyoto/index.php?option=com_content&view=category&id=101:evaluation&Itemid=118&layout=default

REQUIREMENTS
KafAnnotator is compiled with Java 1.6 on MAC OS X. It should run on any platform that supports Java 1.6.

INSTALLATION
For installation, unpack the zip file and use the shell command (Linux/Unix) or the bat file (Windows) to launch the application.
Java 1.6 is required for running the application.

LICENSE:

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
