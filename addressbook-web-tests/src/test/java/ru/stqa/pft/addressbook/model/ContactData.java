package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName = "";

  @Expose
  @Column(name = "middlename")
  private String middleName = "";

  @Expose
  @Column(name = "lastname")
  private String lastName = "";

  @Expose
  @Column(name = "nickname")
  private String nick = "";

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Expose
  @Column(name = "title")
  private String title = "";

  @Expose
  @Column(name = "company")
  private String company = "";

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address = "";

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile = "";

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";

  @Column(name = "fax")
  @Type(type = "text")
  private String fax = "";

  @Transient
  private String allPhones = "";

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email = "";

  @Column(name = "email2")
  @Type(type = "text")
  private String email2 = "";

  @Column(name = "email3")
  @Type(type = "text")
  private String email3 = "";

  @Transient
  private String allEmails;

  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage = "";

  @Expose
  @Column(name = "bday", columnDefinition = "tinyint")
  private String birthDay = "0";

  @Expose
  @Column(name="bmonth")
  private String birthMonth = "-";

  @Expose
  @Column(name="byear")
  private String birthYear = "";

  @Column(name = "aday", columnDefinition = "tinyint")
  private String anniversaryDay = "0";

  @Column(name = "amonth")
  private String anniversaryMonth = "-";

  @Column(name = "ayear")
  private String anniversaryYear = "";

  @Column(name = "address2")
  @Type(type = "text")
  private String addAddress = "";

  @Column(name = "phone2")
  @Type(type = "text")
  private String addPhone = "";

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String notes = "";

  @Column(name = "im")
  @Type(type = "text")
  private final String im = "";

  @Column(name = "im2")
  @Type(type = "text")
  private final String im2 = "";

  @Column(name = "im3")
  @Type(type = "text")
  private final String im3 = "";

  @Column(name = "deprecated", columnDefinition = "datetime")
  private final String deprecated = "0000-00-00 00:00:00";

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNick(String nick) {
    this.nick = nick;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withBirthDay(String birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public ContactData withBirthMonth(String birthMonth) {
    this.birthMonth = birthMonth;
    return this;
  }

  public ContactData withBirthYear(String birthYear) {
    this.birthYear = birthYear;
    return this;
  }

  public ContactData withAnniversaryDay(String anniversaryDay) {
    this.anniversaryDay = anniversaryDay;
    return this;
  }

  public ContactData withAnniversaryMonth(String anniversaryMonth) {
    this.anniversaryMonth = anniversaryMonth;
    return this;
  }

  public ContactData withAnniversaryYear(String anniversaryYear) {
    this.anniversaryYear = anniversaryYear;
    return this;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withAddAddress(String addAddress) {
    this.addAddress = addAddress;
    return this;
  }

  public ContactData withAddPhone(String addPhone) {
    this.addPhone = addPhone;
    return this;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }


  public int getId() {
    return id;
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

  public File getPhoto() {
    return photo != null ? new File(photo) : null;
  }

  public Groups getGroups() {
    return new Groups(groups);
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

  public String getBirthDay() {
    return birthDay;
  }

  public String getBirthMonth() {
    return birthMonth;
  }

  public String getBirthYear() {
    return birthYear;
  }

  public String getAnniversaryDay() {
    return anniversaryDay;
  }

  public String getAnniversaryMonth() {
    return anniversaryMonth;
  }

  public String getAnniversaryYear() {
    return anniversaryYear;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAddAddress() {
    return addAddress;
  }

  public String getAddPhone() {
    return addPhone;
  }

  public String getNotes() {
    return notes;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    return id == that.id &&
    Objects.equals(firstName, that.firstName) &&
    Objects.equals(lastName, that.lastName) &&
    Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public ContactData outOfGroup(GroupData group) {
    groups.remove(group);
    return this;
  }
}
