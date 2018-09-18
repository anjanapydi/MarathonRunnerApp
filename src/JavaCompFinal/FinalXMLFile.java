package JavaCompFinal;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * FinalXMLFile.java - This class reads runners data from XML file and returns an arraylist to main method.
 * @author Anjana
 *
 */
public class FinalXMLFile {
	/**
	 * This method reads XML file from given path. If file not present, create it and proceed.
	 * @return arraylist of runners
	 */
	public List<RunnersThread> getFileData() {
		//Below code gives information of input file.
		String dirString = "Resources";
		String fileString = "FinalXMLData.xml";
		Path filePath = Paths.get(dirString,fileString);
		List<RunnersThread> fileData = null;
		//Check if file exists or not. If not, create file & read it otherwise read from existing file.
		if (Files.notExists(filePath)) {
			//Below code is to write to XML file
			writeXMLFileData();
		}
		//Read the contents of XML file
		fileData = readXMLFileData();
		return fileData;
	} // end getFileData method
	/**
	 * This method is to write runners information to XML file.
	 */
	public void writeXMLFileData() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Runners");
			doc.appendChild(rootElement);
			//add TORTOISE details
			Element Runner1 = doc.createElement("Runner");
			rootElement.appendChild(Runner1);
			Attr attr1 = doc.createAttribute("Name");
			attr1.setValue("Tortoise");
			Runner1.setAttributeNode(attr1);
			//add speed
			Element RunnersMoveIncrement1 = doc.createElement("RunnersMoveIncrement");
			Runner1.appendChild(RunnersMoveIncrement1);
			RunnersMoveIncrement1.appendChild(doc.createTextNode("10"));
			//add rest percentage
			Element RestPercentage1 = doc.createElement("RestPercentage");
			Runner1.appendChild(RestPercentage1);
			RestPercentage1.appendChild(doc.createTextNode("0"));
			//add HARE details
			Element Runner2 = doc.createElement("Runner");
			rootElement.appendChild(Runner2);
			Attr attr2 = doc.createAttribute("Name");
			attr2.setValue("Hare");
			Runner2.setAttributeNode(attr2);
			//add speed
			Element RunnersMoveIncrement2 = doc.createElement("RunnersMoveIncrement");
			Runner2.appendChild(RunnersMoveIncrement2);
			RunnersMoveIncrement2.appendChild(doc.createTextNode("100"));
			//add rest percentage
			Element RestPercentage2 = doc.createElement("RestPercentage");
			Runner2.appendChild(RestPercentage2);
			RestPercentage2.appendChild(doc.createTextNode("90"));
			//add DOG details
			Element Runner3 = doc.createElement("Runner");
			rootElement.appendChild(Runner3);
			Attr attr3 = doc.createAttribute("Name");
			attr3.setValue("Dog");
			Runner3.setAttributeNode(attr3);
			//add speed
			Element RunnersMoveIncrement3 = doc.createElement("RunnersMoveIncrement");
			Runner3.appendChild(RunnersMoveIncrement3);
			RunnersMoveIncrement3.appendChild(doc.createTextNode("50"));
			//add rest percentage
			Element RestPercentage3 = doc.createElement("RestPercentage");
			Runner3.appendChild(RestPercentage3);
			RestPercentage3.appendChild(doc.createTextNode("70"));
			//add CAT details
			Element Runner4 = doc.createElement("Runner");
			rootElement.appendChild(Runner4);
			Attr attr4 = doc.createAttribute("Name");
			attr4.setValue("Cat");
			Runner4.setAttributeNode(attr4);
			//add speed
			Element RunnersMoveIncrement4 = doc.createElement("RunnersMoveIncrement");
			Runner4.appendChild(RunnersMoveIncrement4);
			RunnersMoveIncrement4.appendChild(doc.createTextNode("30"));
			//add rest percentage
			Element RestPercentage4 = doc.createElement("RestPercentage");
			Runner4.appendChild(RestPercentage4);
			RestPercentage4.appendChild(doc.createTextNode("75"));
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Resources/FinalXMLData.xml"));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try-catch block
	} //end writeXMLFileData method
	/**
	 * This method is to read runners information from XML file.
	 * @return arraylist of runners
	 */
	public List<RunnersThread> readXMLFileData() {
		List<RunnersThread> readData = new ArrayList<RunnersThread>();
		try {
			File runnersFile = new File("Resources/FinalXMLData.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(runnersFile);
			doc.getDocumentElement().normalize();
			//Get all the runner elements
			NodeList nodeList = doc.getElementsByTagName("Runner");
			for (int i=0; i<nodeList.getLength();i++)	 {
				Node node = nodeList.item(i);
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
					 Element elem = (Element) node;
					 String name = elem.getAttribute("Name");
					 int speed = Integer.parseInt(elem.getElementsByTagName("RunnersMoveIncrement").item(0).getTextContent());
					 int rest = Integer.parseInt(elem.getElementsByTagName("RestPercentage").item(0).getTextContent());
					 RunnersThread r = new RunnersThread(name, speed, rest);
					 readData.add(r);
				 } // end if
			} //end for loop
		} catch(Exception e) {
			e.printStackTrace();
			readData = null;
		} // end try-catch block
		return readData;
	} //end readXMLFileData method
} //end FinalTextFile class


