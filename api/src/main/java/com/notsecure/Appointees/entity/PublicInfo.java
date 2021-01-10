package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode
public class PublicInfo implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@JsonProperty("dateCreated") //for serializing and deserializing -sd
private LocalDateTime dateCreated;


private String businessName;
private String bookingPageTitle;
private String businessCategory;
@Column(length = 1500)
private String businessHighlights;

private String imageUrl;
private String websiteLink;
private String customerSupportEmail;
private String customerSupportPhone;
private boolean activeAccount;

@OneToOne (cascade = CascadeType.ALL)
private Address address;


}
