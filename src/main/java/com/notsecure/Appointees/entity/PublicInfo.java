package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class PublicInfo {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
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

@OneToOne
private Address address;

}
