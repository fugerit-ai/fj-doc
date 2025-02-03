package org.fugerit.java.doc.playground.meta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.doc.playground.RestHelper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Path("/meta")
public class MetaRest {

    @ConfigProperty(name = "quarkus.platform.version", defaultValue = "unset")
    String quarkusPlatformVersion;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/version")
    public Response getVersion() {
        return RestHelper.defaultHandle(() -> {
            Properties buildProps = PropsIO.loadFromClassLoader("build.properties");
            log.info("buildProps : {}", buildProps);

            return Response.ok().entity(buildProps).build();
        });
    }

    private static final String[] ADD_PROPS = { "java.version", "java.vendor", "os.name", "os.version", "os.arch" };

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public Response getInfo() {
        return RestHelper.defaultHandle(() -> {
            Map<String, String> info = new LinkedHashMap<>();
            info.put("quarkus.version", io.quarkus.builder.Version.getVersion());
            SafeFunction.applyIfNotNull(this.quarkusPlatformVersion,
                    () -> info.put("quarkus.platform.version", this.quarkusPlatformVersion));
            for (String key : ADD_PROPS) {
                info.put(key, System.getProperty(key));
            }
            return Response.ok().entity(info).build();
        });
    }

}