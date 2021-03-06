package org.tbk.vishy.properties.provider.operatingsystem;

import com.github.theborakompanioni.openmrc.OpenMrcExtensions;
import com.github.theborakompanioni.openmrc.impl.ExtensionHttpRequestInterceptorSupport;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.tbk.vishy.utils.ExtractUserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class OperatingSystemRequestInterceptor extends ExtensionHttpRequestInterceptorSupport<OpenMrcExtensions.OperatingSystem> {

    private static final OpenMrcExtensions.OperatingSystem UNKNOWN = OpenMrcExtensions.OperatingSystem.newBuilder()
            .setName("?")
            .setManufacturer("?")
            .setGroupName("?")
            .build();

    private static final Function<OperatingSystem, OpenMrcExtensions.OperatingSystem> TO_PROTO = (operatingSystem) ->
            OpenMrcExtensions.OperatingSystem.newBuilder()
                    .setName(operatingSystem.getName())
                    .setManufacturer(operatingSystem.getGroup().getManufacturer().getName())
                    .setGroupName(operatingSystem.getGroup().getName()) // groupName
                    .build();

    public OperatingSystemRequestInterceptor() {
        this(UNKNOWN);
    }

    public OperatingSystemRequestInterceptor(OpenMrcExtensions.OperatingSystem defaultValue) {
        super(OpenMrcExtensions.OperatingSystem.operatingSystem, Optional.ofNullable(defaultValue));
    }

    @Override
    protected Optional<OpenMrcExtensions.OperatingSystem> extract(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest)
                .map(ExtractUserAgent.fromHttpRequest)
                .flatMap(Supplier::get)
                .map(UserAgent::getOperatingSystem)
                .map(TO_PROTO);
    }

}
