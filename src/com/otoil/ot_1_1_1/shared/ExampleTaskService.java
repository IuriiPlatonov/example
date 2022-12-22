package com.otoil.ot_1_1_1.shared;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import ru.ot.gwt.utils.shared.tree.TreeNode;

@Path("/exampletask/rest/ext")
@Produces(MediaType.APPLICATION_JSON)
public interface ExampleTaskService extends DirectRestService
{

    @GET
    @Path("/documentCard/")
    @Produces(MediaType.APPLICATION_JSON)
    List<ResponseDocumentCardBean> getDocumentCard();

    @PUT
    @Path("/documentCard/")
    @Produces(MediaType.APPLICATION_JSON)
    Boolean saveDocumentCard(RequestDocumentCardBean request);

    @GET
    @Path("/attributeName/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<AttributeNameBean> getObjectAttribute(@PathParam("id") String id);
    
    @GET
    @Path("/tree")
    @Produces(MediaType.APPLICATION_JSON)
    TreeNode<ResponseDocumentCardBean> loadTree();
}
