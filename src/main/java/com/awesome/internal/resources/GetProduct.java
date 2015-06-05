package com.awesome.internal.resources;

import com.awesome.internal.impl.ProductImpl;
import com.awesome.structures.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by sankeerth.reddy on 05/06/15.
 */
@Path(value = "/product")

public class GetProduct {

    public GetProduct() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") String id) {
        return new ProductImpl().getProduct(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{productId}")
    public void updateProduct(@PathParam("productId") String id, Product product) {
        new ProductImpl().insertProduct(product);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMaxBid/{productId}")
    public Product get(@PathParam("productId") String id) {
        return new ProductImpl().getMaxBid(id);
    }
}
