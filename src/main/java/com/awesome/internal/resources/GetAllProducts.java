package com.awesome.internal.resources;

import com.awesome.internal.impl.ProductImpl;
import com.awesome.structures.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by sankeerth.reddy on 05/06/15.
 */
@Path(value = "/getAllProducts")
@Produces(MediaType.APPLICATION_JSON)
public class GetAllProducts {

        public GetAllProducts() {}

        @GET
        public ArrayList<Product> getAllProducts() {
            return new ProductImpl().getAllProducts();
        }
}
