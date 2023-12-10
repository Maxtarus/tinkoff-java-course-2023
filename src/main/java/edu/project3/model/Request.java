package edu.project3.model;

public record Request(String method, String url, String protocol, String userAgent) {
}
