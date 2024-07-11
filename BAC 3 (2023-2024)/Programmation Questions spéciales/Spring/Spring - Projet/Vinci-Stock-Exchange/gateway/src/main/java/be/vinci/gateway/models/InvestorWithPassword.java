package be.vinci.gateway.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InvestorWithPassword {

    @JsonProperty(value = "investor_data")
    private InvestorData investorData;
    private String password;
}
