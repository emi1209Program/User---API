package com.exercise.userapi.model;

/**
 * Clase que representa una dirección asociada a un usuario.
 *
 * Class that represents an address associated with a user.
 *
 * Cada usuario puede tener múltiples direcciones almacenadas
 * dentro del atributo "addresses".
 *
 * Each user can have multiple addresses stored
 * inside the "addresses" attribute.
 *
 * Estructura esperada:
 * Expected structure:
 *
 * {
 *   "id": 1,
 *   "name": "workaddress",
 *   "street": "street No. 1",
 *   "country_code": "UK"
 * }
 */
public class Address {

    /**
     * Identificador de la dirección.
     *
     * Address identifier.
     */
    private Integer id;

    /**
     * Nombre de la dirección (ejemplo: casa, oficina).
     *
     * Address name (example: home, office).
     */
    private String name;

    /**
     * Calle o descripción de la dirección.
     *
     * Street or address description.
     */
    private String street;

    /**
     * Código de país en formato ISO.
     *
     * Country code in ISO format.
     *
     * Ejemplos:
     * Examples:
     * MX, US, UK, AU
     */
    private String countryCode;

    /**
     * Constructor vacío requerido por Spring/Jackson
     * para la conversión automática de JSON.
     *
     * Empty constructor required by Spring/Jackson
     * for automatic JSON conversion.
     */
    public Address() {}

    /**
     * Constructor completo para crear direcciones.
     *
     * Full constructor used to create addresses.
     *
     * @param id identificador
     * @param id identifier
     *
     * @param name nombre de la dirección
     * @param name address name
     *
     * @param street calle o descripción
     * @param street street description
     *
     * @param countryCode código de país
     * @param countryCode country code
     */
    public Address(Integer id, String name, String street, String countryCode) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.countryCode = countryCode;
    }

    /**
     * Obtiene el id de la dirección.
     *
     * Returns the address id.
     */
    public Integer getId() { return id; }

    /**
     * Asigna el id de la dirección.
     *
     * Sets the address id.
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Obtiene el nombre de la dirección.
     *
     * Returns the address name.
     */
    public String getName() { return name; }

    /**
     * Asigna el nombre de la dirección.
     *
     * Sets the address name.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene la calle o descripción.
     *
     * Returns the street description.
     */
    public String getStreet() { return street; }

    /**
     * Asigna la calle o descripción.
     *
     * Sets the street description.
     */
    public void setStreet(String street) { this.street = street; }

    /**
     * Obtiene el código de país.
     *
     * Returns the country code.
     */
    public String getCountryCode() { return countryCode; }

    /**
     * Asigna el código de país.
     *
     * Sets the country code.
     */
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
}