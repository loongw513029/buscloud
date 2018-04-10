package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.IOHelper;
import com.sztvis.buscloud.model.DataAlarmConfigViewModel;
import com.sztvis.buscloud.service.IConfigService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigService implements IConfigService {
    @Override
    public DataAlarmConfigViewModel GetAllConfigs() throws Exception {
        String rootPath = getClass().getResource("/").getPath().toString()+"com/sztvis/buscloud/config/DataAlarmConfig.xml";
        File config = new File(rootPath);
        FileInputStream is = new FileInputStream(config);
        DocumentBuilderFactory dbf = null;
        dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        Boolean b =config.exists();
        Document docu = db.parse(is);
        NodeList Date = docu.getElementsByTagName("Item");
        Element Xml_item = (Element) Date.item(0);
        DataAlarmConfigViewModel model = new DataAlarmConfigViewModel();
        model.setId(Integer.valueOf(Xml_item.getAttributes().item(0).getNodeValue()));
        model.setDataName(Xml_item.getElementsByTagName("DataName").item(0).getFirstChild().getNodeValue());
        model.setTurn(Boolean.valueOf(Xml_item.getElementsByTagName("Turn").item(0).getFirstChild().getNodeValue()));
        model.setFirstFiter(Xml_item.getElementsByTagName("FirstFiter").item(0).getFirstChild().getNodeValue());
        model.setValue(Xml_item.getElementsByTagName("Value").item(0).getFirstChild().getNodeValue());
        return model;
    }
}
