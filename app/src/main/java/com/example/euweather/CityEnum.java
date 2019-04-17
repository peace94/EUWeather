package com.example.euweather;

public enum CityEnum {
    MOSCOW (0, "Moscow", "524901"),
    KALUGA (1, "Kaluga", "553915"),
    KALININGRAD (2, "Kaliningrad", "554234"),
    OMSK (3, "Omsk", "1496153"),
    NOVOSIBIRSK(4, "Novosibirsk", "1496747"),
    MURMANSK(5, "Murmansk", "524305");

    private int code;
    private String name;
    private String id;

    private CityEnum(int code, String name, String id) {
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public int getCode() { return code; }
    public String getName() { return name; }
    public String getId() { return id;}

    public CityEnum getCityByCode(int code) {
        for(CityEnum city: CityEnum.values()) {
            if (city.getCode() == code)
                return city;
        }
        return null;
    }

}
