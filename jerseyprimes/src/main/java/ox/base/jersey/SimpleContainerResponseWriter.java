package ox.base.jersey;

import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseWriter;
import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class SimpleContainerResponseWriter implements ContainerResponseWriter {
    @Getter
    private ByteArrayOutputStream stream = new ByteArrayOutputStream();

    @Getter
    private Object entity;

    @Override
    public OutputStream writeStatusAndHeaders(long contentLength, ContainerResponse response) throws IOException {
        this.entity = response.getEntity();
        return this.stream;
    }

    @Override
    public void finish() throws IOException {
    }
}
