package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nick;
  private final String photoPath;
  private final String title;
  private final String company;
  private final String address;
  private final String homePhone;
  private final String mobile;
  private final String workPhone;
  private final String fax;
  private final String email;
  private final String email2;
  private final String email3;
  private final String homepage;

  public ContactData(String firstName, String middleName, String lastName, String nick, String photoPath, String title, String company, String address, String homePhone, String mobile, String workPhone, String fax, String email, String email2, String email3, String homepage) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nick = nick;
    this.photoPath = photoPath;
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

  public String getPhotoPath() {
    return photoPath;
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

  public String generateYear() {
    int min = 1950;
    int max = 2000;
    int randomYear = (int)Math.floor(Math.random()*(max-min+1)+min);
    return String.valueOf(randomYear);
  }
}
