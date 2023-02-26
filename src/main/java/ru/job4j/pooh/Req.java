package ru.job4j.pooh;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] lines = content.split(System.lineSeparator());
        String[] reqTypeAndModeAndName = lines[0].split(" ");
        String reqType = reqTypeAndModeAndName[0];
        String[] modeAndName = reqTypeAndModeAndName[1].split("/");
        String mode = modeAndName[1];
        String name = modeAndName[2];
        String text = "";
        if (Service.POST.equals(reqType)) {
            text = lines[7];
        } else if (Service.GET.equals(reqType) && "topic".equals(mode)) {
            text = modeAndName[modeAndName.length - 1];
        }
        return new Req(reqType, mode, name, text);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}