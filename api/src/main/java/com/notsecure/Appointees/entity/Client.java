package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@NotNull
private String name;
private String email;
private String phone;
private boolean activeClient ;
@Nullable
@OneToOne(cascade = CascadeType.PERSIST)
private Address address;
}
