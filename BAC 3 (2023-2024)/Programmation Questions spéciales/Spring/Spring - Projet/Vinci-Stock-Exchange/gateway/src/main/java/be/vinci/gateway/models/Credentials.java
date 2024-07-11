package be.vinci.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private String username;
    private String password;
}
