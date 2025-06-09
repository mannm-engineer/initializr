package com.sudo.initializr.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private Address address;
}
