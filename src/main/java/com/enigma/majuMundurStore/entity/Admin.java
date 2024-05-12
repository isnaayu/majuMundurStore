package com.enigma.majuMundurStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_admin")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "email", length = 100, unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
