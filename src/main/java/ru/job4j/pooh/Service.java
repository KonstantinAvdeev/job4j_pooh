package ru.job4j.pooh;

public interface Service {
    public static final String GET = "GET";
    public static final String POST = "POST";

    Resp process(Req req);
}