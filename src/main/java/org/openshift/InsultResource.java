package org.openshift;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

@RequestScoped
@Path("insult")
public class InsultResource {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Map<String, String> getInsult() {
        return Collections.singletonMap("insult", new InsultGenerator().generateInsultFromDb());
    }
}
