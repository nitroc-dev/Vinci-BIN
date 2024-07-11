package be.vinci.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImpl.class)
public interface User {
    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    int getId();

    void setId(int id);

    int getAge();

    void setAge(int age);

    boolean isMarried();

    void setMarried(boolean married);

    boolean checkPassword(String password);

    String hashPassword(String password);

    @Override
    String toString();
}
