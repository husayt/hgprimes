package ox.base.jersey;//package ox.base.jersey;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.MessageBodyReader;
//import javax.ws.rs.ext.MessageBodyWriter;
//import javax.ws.rs.ext.Provider;
//import com.google.protobuf.GeneratedMessage;
//import com.google.protobuf.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//
///**
// * https://github.com/dropwizard/dropwizard-protobuf/tree/master/src/main/java/io/dropwizard/jersey/protobuf
// * A Jersey provider which enables using Protocol Buffers to parse request
// * entities into objects and generate response entities from objects.
// */
//@Provider
//@Consumes(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
//@Produces(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
//public class ProtocolBufferMessageBodyProvider implements MessageBodyReader<Message>,
//        MessageBodyWriter<Message> {
//
//    @Override
//    public boolean isReadable(final Class<?> type, final Type genericType,
//                              final Annotation[] annotations, final MediaType mediaType) {
//        return Message.class.isAssignableFrom(type);
//    }
//
//    @Override
//    public Message readFrom(final Class<Message> type, final Type genericType,
//                            final Annotation[] annotations, final MediaType mediaType,
//                            final MultivaluedMap<String, String> httpHeaders,
//                            final InputStream entityStream) throws IOException {
//
//        try {
//            final Method newBuilder = type.getMethod("newBuilder");
//            final GeneratedMessage.Builder<?> builder = (GeneratedMessage.Builder<?>) newBuilder
//                    .invoke(type);
//            return builder.mergeFrom(entityStream).build();
//        } catch (Exception e) {
//            throw new WebApplicationException(e);
//        }
//    }
//
//    @Override
//    public long getSize(final Message m, final Class<?> type,
//                        final Type genericType, final Annotation[] annotations,
//                        final MediaType mediaType) {
//        return m.getSerializedSize();
//    }
//
//    @Override
//    public boolean isWriteable(final Class<?> type, final Type genericType,
//                               final Annotation[] annotations, final MediaType mediaType) {
//        return Message.class.isAssignableFrom(type);
//    }
//
//    @Override
//    public void writeTo(final Message m, final Class<?> type,
//                        final Type genericType, final Annotation[] annotations,
//                        final MediaType mediaType,
//                        final MultivaluedMap<String, Object> httpHeaders,
//                        final OutputStream entityStream) throws IOException {
//        entityStream.write(m.toByteArray());
//    }
//}
//
//public class ProtocolBufferMediaType extends MediaType {
//
//    /** "application/x-protobuf" */
//    public final static String APPLICATION_PROTOBUF = "application/x-protobuf";
//    /** "application/x-protobuf" */
//    public final static MediaType APPLICATION_PROTOBUF_TYPE = new MediaType("application","x-protobuf");
//}
//
//@Provider
//public class InvalidProtocolBufferExceptionMapper implements ExceptionMapper<InvalidProtocolBufferException> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidProtocolBufferExceptionMapper.class);
//
//    @Override
//    public Response toResponse(InvalidProtocolBufferException exception) {
//        final ErrorMessage message = ErrorMessage.newBuilder()
//                .setMessage("Unable to process protocol buffer")
//                .setCode(Response.Status.BAD_REQUEST.getStatusCode())
//                .build();
//
//        LOGGER.debug("Unable to process protocol buffer message", exception);
//        return Response.status(Response.Status.BAD_REQUEST)
//                .type(ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE)
//                .entity(message)
//                .build();
//    }
//}