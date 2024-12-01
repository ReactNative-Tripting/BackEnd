package org.example.tripting.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteDTO {
    private String id;

    private String name;
    private String info;
    private String url;
    private String latitude;
    private String longitude;
}
