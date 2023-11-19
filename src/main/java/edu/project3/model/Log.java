package edu.project3.model;

import java.time.OffsetDateTime;

public record Log(
    String ip,
    String user,
    OffsetDateTime date,
    Request request,
    Response response
) {
}
