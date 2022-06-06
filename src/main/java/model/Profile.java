package model;

import java.util.Objects;

public class Profile {
    private String firstName;
    private String lastName;
    private String nick;
    private int age;
    private TypeProfil typeProfil;
    private String password;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String nick, int age, TypeProfil typeProfil) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.age = age;
        this.typeProfil = typeProfil;
    }

    public Profile(String firstName, String lastName, String nick, int age, TypeProfil typeProfil, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.age = age;
        this.typeProfil = typeProfil;
        this.password = password;
    }

    public String getNick() {
        return nick;
    }


    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TypeProfil getTypeProfil() {
        return typeProfil;
    }

    public void setTypeProfil(TypeProfil typeProfil) {
        this.typeProfil = typeProfil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return age == profile.age && Objects.equals(firstName, profile.firstName) && Objects.equals(lastName, profile.lastName) && typeProfil == profile.typeProfil && Objects.equals(password, profile.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, typeProfil, password);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                ", typeProfil=" + typeProfil +
                ", password='" + password + '\'' +
                '}';
    }
}
