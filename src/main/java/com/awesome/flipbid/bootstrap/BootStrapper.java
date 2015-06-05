package com.awesome.flipbid.bootstrap;

import com.awesome.flipbid.config.ServiceConfiguration;
import com.awesome.internal.resources.GetAllProducts;
import com.awesome.internal.resources.GetProduct;
import com.awesome.internal.resources.NewProduct;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */
public class BootStrapper extends Service<ServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new BootStrapper().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-example");
    }

    @Override
    public void run(ServiceConfiguration conf, Environment env) throws Exception {
        // we will add resource classes here
//        env.addResource(new HelloResource(conf.getMessages()));
//        env.addResource(new PostResource());
        env.addResource(new GetAllProducts());
        env.addResource(new GetProduct());
        env.addResource(new NewProduct());

    }

}
