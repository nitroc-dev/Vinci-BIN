package be.vinci.investor.models;

import be.vinci.authentication.models.SafeCredentials;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnsafeCredentials {
    private String username;
    private String password;
}
