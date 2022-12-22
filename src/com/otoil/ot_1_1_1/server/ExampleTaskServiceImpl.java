package com.otoil.ot_1_1_1.server;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Path;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.server.entities.documentcard.DocCard;
import com.otoil.ot_1_1_1.server.entities.objectattribute.ObjectAttribute;
import com.otoil.ot_1_1_1.server.entities.treedoccard.TreeDocCard;
import com.otoil.ot_1_1_1.shared.ExampleTaskService;

import lombok.SneakyThrows;
import ru.ep.sdo.Session;
import ru.ep.sdo.SessionFactory;
import ru.ep.sdo.filter.EqualFilter;
import ru.ep.sdo.list.XMLListModel;
import ru.ep.sdo.lob.blob.SDOBlob;
import ru.ot.gwt.sdo.server.beans.BeanConverter;
import ru.ot.gwt.utils.shared.tree.TreeBuilder;
import ru.ot.gwt.utils.shared.tree.TreeNode;


@Path("/ext")
public class ExampleTaskServiceImpl implements ExampleTaskService
{

    private Session getSession()
    {
        Properties properties = new Properties();
        properties.put("user", "ATOLL_SERVICE");
        properties.put("password", "ATOLL_SERVICE");
        properties.put("connectionString",
            "jdbc:oracle:thin:@10.100.24.102:1521:HPDOILDV");
        // "jdbc:oracle:thin:@185.40.76.81:1521:MPDEV");
        properties.put("driverClassName", "oracle.jdbc.driver.OracleDriver");
        properties.put("databaseType", "ora");
        Session session = SessionFactory.getInstance().createSessionFromFile(
            "com/otoil/ot_1_1_1/server/new.session", properties);
        return session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ResponseDocumentCardBean> getDocumentCard()
    {
        Session session = getSession();

        XMLListModel listModel = session.getListModel("ExampleTask.DocCard");

        Iterator<DocCard> iterator = listModel.iterator();

        List<ResponseDocumentCardBean> list = new ArrayList<>();
        while (iterator.hasNext())
        {
            DocCard doc = iterator.next();

            list.add(new ResponseDocumentCardBean(doc.getDcmcrdId().toString(),
                doc.getName(), doc.getOrderNumber().toString(),
                new Date(doc.getChangeDate().getTime()),
                doc.getDcmcrdDcmcrdId() != null
                    ? doc.getDcmcrdDcmcrdId().toString()
                    : "",
                doc.getIcon() != null ? convertBlob(doc.getIcon()) : ""));
        }
        return list;
    }

    @SneakyThrows
    private String convertBlob(SDOBlob blob)
    {

        byte[] bytes = IOUtils.toByteArray(blob.openStream());

        String base64 = Base64.encodeBase64String(bytes);
        base64 = "data:image/png;base64," + base64;

        return base64;
    }

    @Override
    public Boolean saveDocumentCard(RequestDocumentCardBean docCard)
    {
        Session session = getSession();
        XMLListModel listModel = session
            .getListModel("ExampleTask.TreeDocCard");

        listModel.setFilter(new EqualFilter(TreeDocCard.PROPERTYNAME_DCMCRD_ID,
            docCard.getDcmcrdId()));

        listModel.fetchAll();

        TreeDocCard bo = (TreeDocCard) listModel.get(0);
        bo.setDocName(docCard.getName());
        bo.setOrderNumber(new BigDecimal(docCard.getOrderNumber()));
        session.commit();

        return true;
    }

    @Override
    public List<AttributeNameBean> getObjectAttribute(String id)
    {
        Session session = getSession();

        XMLListModel listModel = session
            .getListModel("ExampleTask.ObjectAttribute");

        listModel.setWhereClauseParam(0, id);

        listModel.fetchAll();

        return BeanConverter.load(listModel, ObjectAttribute.converter())
            .join();
    }

    @SuppressWarnings("unchecked")
    @Override
    public TreeNode<ResponseDocumentCardBean> loadTree()
    {
        Session session = getSession();

        XMLListModel listModel = session
            .getListModel("ExampleTask.TreeDocCard");

      //  listModel.fetchAll();
        Iterator<TreeDocCard> iterator = listModel.iterator();
        List<ResponseDocumentCardBean> list = new ArrayList<>();

        while (iterator.hasNext())
        {
            TreeDocCard doc = iterator.next();

            list.add(ResponseDocumentCardBean.builder()
                .id(doc.getDcmcrdId().toString()).name(doc.getDocName())
                .orderNumber(doc.getOrderNumber().toString())
                .changeDate(new Date(doc.getChangeDate().getTime()))
                .parent(doc.getDcmcrdDcmcrdId() != null
                    ? doc.getDcmcrdDcmcrdId().toString()
                    : null)
                .binaryData(
                    doc.getIcon() != null ? convertBlob(doc.getIcon()) : null)
                .build());
        }

        Iterator<ResponseDocumentCardBean> listIterator = list.iterator();

        TreeBuilder<ResponseDocumentCardBean> treeBuilder = TreeBuilder
            .fromIterator(ResponseDocumentCardBean::getId,
                ResponseDocumentCardBean::getParent, listIterator);

        return treeBuilder.build();
    }

}
