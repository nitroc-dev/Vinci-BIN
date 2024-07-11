package be.vinci.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestorData {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String birthdate;
}
