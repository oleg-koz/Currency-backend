package com.currency.ticker.xml.parser;

import com.currency.ticker.CurrencyTicker;
import com.currency.ticker.CurrencyTickerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@AllArgsConstructor
@Component
public class CurrencyTickerXMLParser {

    private final CurrencyTickerService currencyTickerService;

    public void convertXMLToObject(String xmlFilePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String pavadinimas = eElement.getElementsByTagName("pavadinimas").item(0).getTextContent();
                    String valiutos_kodas = eElement.getElementsByTagName("valiutos_kodas").item(0).getTextContent();
                    String santykis = eElement.getElementsByTagName("santykis").item(0).getTextContent();
                    String data = eElement.getElementsByTagName("data").item(0).getTextContent();

                    CurrencyTicker currencyTicker = new CurrencyTicker(valiutos_kodas, pavadinimas, santykis, data);
                    currencyTickerService.saveCurrencyTicker(currencyTicker).subscribe();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
