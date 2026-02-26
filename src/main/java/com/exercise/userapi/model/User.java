package com.exercise.userapi.model;

import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String email;
    private String name;
    private String phone;
    /**
     * Documentar implemente el bloqueo de la contraseña por cuestiones de practica en seguridad
     */
    private String taxId;
    private String createdAt;
    private List<Address> addresses;

    /**
     * Revisa porque tiene que ver uno vacio
     */
    public User(){

    }

    public User(UUID id, String email, String name, String phone,
            String password, String taxId,
            String createdAt, List<Address> addresses) {

    this.id = id;
    this.email = email;
    this.name = name;
    this.phone = phone;
    this.password = password;
    this.taxId = taxId;
    this.createdAt = createdAt;
    this.addresses = addresses;
}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    /**
     * Esto va  hacer que el horario salga como de magadascar
     * @return
     */
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
}