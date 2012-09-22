package com.hello;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Jonas Baggio
 * @create 18/05/2012
 */
public class XML {

	/**
	 * Abre o XML no charset especificado
	 * 
	 * @param xml
	 * @param charset
	 * @return
	 */
	public static Node getRoot(String xml, String charset)  {
		try {
			InputStream in = null;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);

			byte[] bytes = xml.getBytes(charset);
			//byte[] bytes = xml.getBytes();
			in = new ByteArrayInputStream(bytes);
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document dom = builder.parse(in);
			Node root = dom.getDocumentElement();
			if (root == null) {
				throw new RuntimeException("XML invalido");
			}

			return root;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static String getAttributeText(Node n, String name) {
		if (n != null) {
			NamedNodeMap attributes = n.getAttributes();
			Node att = attributes.getNamedItem(name);
			if (att != null) {
				return att.getNodeValue();
			}
		}
		return null;
	}
	public static Node getNode(Node node, String tag) {
		if (node == null) {
			return null;
		}
		NodeList childNodes = node.getChildNodes();
		if (childNodes == null) {
			return null;
		}
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);
			if (item != null) {
				String name = item.getNodeName();
				if (tag.equalsIgnoreCase(name)) {
					return item;
				}
			}
		}
		Element e = (Element) node;
		Node ne = getNode(e, tag);
		return ne;
	}
	/**
	 * Retorna o texto dentro da tag
	 * 
	 * <MENSAGEM>retorna este texto</MENSAGEM>
	 * 
	 * @param node
	 * @param tag
	 * @return
	 */
	public static String getText(Node node, String tag) {
		Node n = getNode(node, tag);
		if (n != null) {
			Node text = n.getFirstChild();
			if (text != null) {
				String s = text.getNodeValue();
				return s;
			}
		}
		return "";
	}
	public static List<Node> getChildren(Node node, String name) {
		List<Node> children = new ArrayList<Node>();

		if(node == null) {
			return children;
		}

		NodeList nodes = node.getChildNodes();
		if (nodes != null && nodes.getLength() >= 1) {

			for (int i = 0; i < nodes.getLength(); i++) {

				Node n = nodes.item(i);

				if (name.equals(n.getNodeName())) {
					children.add(n);
				}
			}
		}
		return children;
	}
}

