package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "No result!";
        String status = "204";
        if (POST.equals(req.httpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).add(req.getParam());
            text = "";
            status = "201";
        } else if (GET.equals((req.httpRequestType()))) {
            text = queue.getOrDefault(req.getSourceName(), new ConcurrentLinkedQueue<>()).poll();
            if (text == null) {
                text = "";
            } else {
                status = "200";
            }
        }
        return new Resp(text, status);
    }
}