package com.awesome.internal.resources;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.awesome.internal.impl.ProductImpl;
import com.awesome.references.ConfigReferences;
import com.awesome.structures.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class NewProduct {

    private static final String SERVER_UPLOAD_LOCATION_FOLDER = ConfigReferences.GeneralReferences.saveImageFilePath;
    ProductImpl productImpl = new ProductImpl();
    /**
     * Upload a File
     */

    @POST
    public Response uploadFile(FormDataMultiPart form, @FormDataParam("file")FormDataContentDisposition fileDetail, @FormDataParam("details") String details) throws IOException {
        FormDataBodyPart filePart = form.getField("file");
        Product product = new Product();
        ObjectMapper mapper = new ObjectMapper();
        product = mapper.readValue(details, Product.class);
        System.out.println("String in product post : " + product.getSeller());
        InputStream fileInputStream = filePart.getValueAs(InputStream.class);
        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + "/" + productImpl.getRandomNumber(6);
        System.out.println("Stored in location : " + filePath);
        productImpl.saveFile(fileInputStream, filePath);
        String output = "File saved to server location using FormDataMultiPart : " + filePath;
        product.setImageURL(filePath);
        productImpl.insertProduct(product);
        return Response.status(200).entity(output).build();
    }
}