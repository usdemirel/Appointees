package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class WeeklyAppointmentsPerServiceProvider {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@OneToOne
@NotNull
private Company company;
@OneToOne
@NotNull
private Branch branch;
@OneToOne
@NotNull
private Service service;
@OneToOne
@NotNull
private ServiceProvider serviceProvider;

private LocalDate firstDay; //represents sunday

/*
Example: "08:00,09:00,09:30,11:00,13:00,13:15,13:30,13:45.."
The provider is providing two different services:
Morning each service takes 30 min in total.
Afternoon, another service takes only 15 minutes in total.
*/

@Nullable
private String sunday;
@Nullable
private String monday;
@Nullable
private String tuesday;
@Nullable
private String wednesday;
@Nullable
private String thursday;
@Nullable
private String friday;
@Nullable
private String saturday;

/*
May be considered later
@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "raw_events_custom", joinColumns = @JoinColumn(name =     "raw_event_id"))
@MapKeyColumn(name = "field_key", length = 50)
@Column(name = "field_val", length = 100)
@BatchSize(size = 20)
private Map<String, String> customValues = new HashMap<String, String>();
*/

}
