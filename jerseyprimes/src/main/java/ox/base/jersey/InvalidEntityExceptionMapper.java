/*
 * Copyright (c) 2013. Optimax Software Ltd
 */

package ox.base.jersey;

import ox.base.validation.InvalidEntityException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidEntityExceptionMapper implements ExceptionMapper<InvalidEntityException> {

//    @Context
//    private HttpServletRequest request;


    @Override
    public Response toResponse(InvalidEntityException exception) {
        final StringBuilder writer = new StringBuilder(1024);
        writeValidationErrorPage(writer, exception);

        return Response.status(Status.PRECONDITION_FAILED)
                .type(MediaType.TEXT_HTML_TYPE)
                .entity(writer.toString())
                .build();
    }


    public static void writeValidationErrorPage(StringBuilder writer, InvalidEntityException exception) {
        writer.append("<html>\n<head>Unprocessable Entity</head>\n<body>");

        //final String uri = request.getRequestURI();
        //writeErrorPageMessage(request, writer, 422, "Unprocessable Entity", uri);

        writer.append("<h2>")
                .append(exception.getMessage())
                .append("</h2>\n<ul>");
        for (String error : exception.getErrors()) {
            writer.append("<li>")
                    .append(error)
                    .append("</li>");
        }
        writer.append("</ul>\n</body>\n</html>\n");
    }

}

