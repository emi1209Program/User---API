package com.exercise.userapi.model;

import java.util.List;
import java.util.UUID;

public class User {

    /**
     * Identificador único del usuario generado automáticamente.
     *
     * Unique user identifier automatically generated.
     *
     * Se utiliza UUID para garantizar que cada usuario tenga
     * un identificador irrepetible.
     *
     * UUID is used to guarantee that each user has
     * a unique identifier.
     */
    private UUID id;

    /**
     * Correo electrónico del usuario.
     *
     * User email address.
     */
    private String email;

    /**
     * Nombre del usuario.
     *
     * User name.
     */
    private String name;

    /**
     * Número telefónico del usuario.
     *
     * User phone number.
     *
     * Puede incluir código de país.
     *
     * Can include country code.
     *
     * Debe cumplir:
     * Must satisfy:
     *
     * - 10 dígitos reales
     * - Validación AndresFormat
     *
     * - 10 real digits
     * - AndresFormat validation
     */
    private String phone;

    /**
     * RFC del usuario (tax_id).
     *
     * User RFC (tax_id).
     *
     * Este campo funciona como nombre de usuario
     * durante el login.
     *
     * This field works as the username
     * during login.
     *
     * Debe ser único y cumplir formato RFC.
     *
     * Must be unique and follow RFC format.
     */
    private String taxId;

    /**
     * Fecha de creación del usuario.
     *
     * User creation date.
     *
     * Se genera automáticamente en zona horaria Madagascar.
     *
     * Automatically generated using Madagascar timezone.
     *
     * Formato:
     * Format:
     *
     * dd-MM-yyyy HH:mm
     */
    private String createdAt;

    /**
     * Lista de direcciones asociadas al usuario.
     *
     * List of addresses associated with the user.
     *
     * Un usuario puede tener múltiples direcciones.
     *
     * A user can have multiple addresses.
     */
    private List<Address> addresses;

    /**
     * Constructor vacío requerido por Spring/Jackson
     * para convertir automáticamente JSON en objetos Java.
     *
     * Empty constructor required by Spring/Jackson
     * to automatically convert JSON into Java objects.
     */
    public User(){

    }

    /**
     * Constructor completo para crear usuarios.
     *
     * Full constructor used to create users.
     *
     * @param id identificador único
     * @param id unique identifier
     *
     * @param email correo electrónico
     * @param email email address
     *
     * @param name nombre del usuario
     * @param name user name
     *
     * @param phone número telefónico
     * @param phone phone number
     *
     * @param password contraseña encriptada AES256
     * @param password AES256 encrypted password
     *
     * @param taxId RFC del usuario
     * @param taxId user RFC
     *
     * @param createdAt fecha de creación
     * @param createdAt creation date
     *
     * @param addresses lista de direcciones
     * @param addresses address list
     */
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

    /**
     * Obtiene el id del usuario.
     *
     * Returns the user id.
     */
    public UUID getId() { return id; }

    /**
     * Asigna el id del usuario.
     *
     * Sets the user id.
     */
    public void setId(UUID id) { this.id = id; }

    /**
     * Obtiene el email del usuario.
     *
     * Returns the user email.
     */
    public String getEmail() { return email; }

    /**
     * Asigna el email del usuario.
     *
     * Sets the user email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Obtiene el nombre del usuario.
     *
     * Returns the user name.
     */
    public String getName() { return name; }

    /**
     * Asigna el nombre del usuario.
     *
     * Sets the user name.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene el teléfono del usuario.
     *
     * Returns the user phone number.
     */
    public String getPhone() { return phone; }

    /**
     * Asigna el teléfono del usuario.
     *
     * Sets the user phone number.
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Contraseña del usuario.
     *
     * User password.
     *
     * Se almacena encriptada usando AES256.
     *
     * Stored encrypted using AES256.
     *
     * Este campo no debe mostrarse en las respuestas JSON
     * por razones de seguridad.
     *
     * This field should not be exposed in JSON responses
     * for security reasons.
     */
    private String password;

    /**
     * Obtiene la contraseña.
     *
     * Returns the password.
     */
    public String getPassword() { return password; }

    /**
     * Asigna la contraseña.
     *
     * Sets the password.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Obtiene el taxId.
     *
     * Returns the taxId.
     */
    public String getTaxId() { return taxId; }

    /**
     * Asigna el taxId.
     *
     * Sets the taxId.
     */
    public void setTaxId(String taxId) { this.taxId = taxId; }

    /**
     * Obtiene la fecha de creación.
     *
     * Returns the creation date.
     *
     * La fecha se genera en zona horaria Madagascar.
     *
     * Date is generated in Madagascar timezone.
     */
    public String getCreatedAt() { return createdAt; }

    /**
     * Asigna la fecha de creación.
     *
     * Sets the creation date.
     */
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    /**
     * Obtiene las direcciones.
     *
     * Returns the address list.
     */
    public List<Address> getAddresses() { return addresses; }

    /**
     * Asigna las direcciones.
     *
     * Sets the address list.
     */
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
}