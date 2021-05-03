package ru.stqa.pft.addressbook.model;

public class ContactData {
  private String firstName;
  private String middleName;
  private String lastName;
  private String nick;
  private String photo;
  private String title;
  private String company;
  private String address;
  private String homePhone;
  private String mobile;
  private String workPhone;
  private String fax;
  private String email;
  private String email2;
  private String email3;
  private String homepage;
  private String group;

  public ContactData(String firstName, String middleName, String lastName, String nick, String photo,
                     String title, String company, String address, String homePhone, String mobile, String workPhone,
                     String fax, String email, String email2, String email3, String homepage, String group)
  {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nick = nick;
    this.photo = photo;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homePhone = homePhone;
    this.mobile = mobile;
    this.workPhone = workPhone;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNick() {
    return nick;
  }

  public String getPhoto() {
    return photo;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getGroup() {
    return group;
  }
}
