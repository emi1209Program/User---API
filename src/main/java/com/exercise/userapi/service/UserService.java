/**
 * Service layer responsible for managing users in memory.
 *
 * This class initializes a list of users when the application starts
 * and simulates a temporary database using an ArrayList.
 */

package com.exercise.userapi.service;

import com.exercise.userapi.model.Address;
import com.exercise.userapi.model.User;
import com.exercise.userapi.util.AESUtil;

import org.springframework.stereotype.Service;
/**
 * Estas librerias son necesarias para hacer el cambio de hora
 */
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Se genera cambio de libreria java a jakarta por el tipo de versión de Spring Boot, debido a que es 4.0.3
 * 
 */
import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    /**
     * Initializes the in-memory user list with three predefined users
     * as required by the technical assessment specification.
     */

    @PostConstruct
    public void init() {

        /**
         * Genera el tiempo en magadascar
         */
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String madagascarTime =
        ZonedDateTime.now(ZoneId.of("Indian/Antananarivo"))
                     .format(formatter);

        List<Address> addresses1 = Arrays.asList(
                new Address(1, "workaddress", "street No. 1", "UK"),
                new Address(2, "homeaddress", "street No. 2", "AU")
        );

        /**
         * Registro del primer usuario
         */
        User user1 = new User(
                UUID.randomUUID(),
                "user1@mail.com",
                "user1",
                "+15555555555",
                //Encripta la contraseña
                AESUtil.encrypt("password1"),
                "AARR990101XXX",
                madagascarTime,
                addresses1
        );

    /**
     * Registro del segundo usuario
         */
        List<Address> addresses2 = Arrays.asList(
        new Address(1, "office", "Main Street 10", "US")
        );

    User user2 = new User(
            UUID.randomUUID(),
            "user2@mail.com",
            "user2",
            "+5215512345678",
            //Encripta la contraseña
            AESUtil.encrypt("password2"),
            "BERR880202YYY", 
            madagascarTime,            
            addresses2
    );

    /**
     * Se crea el registro del tercer usuario
     */
    List<Address> addresses3 = Arrays.asList(
            new Address(1, "apartment", "Sunset Blvd 22", "MX")
    );

    User user3 = new User(
            UUID.randomUUID(),
            "user3@mail.com",
            "user3",
            "+5215598765432",
            //Encripta la contraseña
            AESUtil.encrypt("password3"),
            "CARR770303ZZZ",
            madagascarTime,
            addresses3
    );

        /**
         * Se mandan a llamar los objetos
         */
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Retrieves users optionally sorted by the specified attribute.
     *
     * This method fulfills the requirement:
     * GET /users?sortedBy=[email|id|name|phone|tax_id|created_at]
     *
     * @param sortedBy attribute used for dynamic sorting
     * @return sorted or unsorted list of users
     */
    public List<User> getUsers(String sortedBy, String filter) {

    List<User> result = new ArrayList<>(users);

    // Apply filtering first
    if (filter != null && !filter.isEmpty()) {
        result = applyFilter(result, filter);
    }

    // Apply sorting after filtering
    if (sortedBy != null && !sortedBy.isEmpty()) {
        result = applySorting(result, sortedBy);
    }

    return result;
}
/**
 * revisar que onda con la validacion de RFC y Telefono
 *
 *  */
    //Valida el RFC con taxID
    private boolean isValidRFC(String rfc){
        if(rfc == null) return false;
        return rfc != null && rfc.matches("^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$");
    }

    //Valida el numero de telefono
    private boolean isValidPhone(String phone) {

    if (phone == null) return false;

    // Solo números
    String digitsOnly = phone.replaceAll("\\D", "");
    
    // Debe tener 10 caracteres
    if(digitsOnly.length() <10) return false;

    // Esto lo que hace es que se lean los ultimos 10 numeros del teléfono real
    String last10 = digitsOnly.substring(digitsOnly.length() -10);

    return last10.matches("\\d{10}");
}
    //Dara formato al número
    private boolean isAndresFormat(String phone) {
        if(phone == null) return false;

    // Quita todo excepto números
    String digits = phone.replaceAll("\\D", "");

    // Permite código país pero requiere 10 dígitos reales
    return digits.length() >= 10 && digits.length() <= 13;
}

/**
 * Documentar crea el usuario
 * @param user
 * @return
 */
public User createUser(User user) {

    System.out.println("PASSWORD recibido = " + user.getPassword());

    if(user.getPassword() == null || user.getPassword().isEmpty()){
        throw new RuntimeException("Password is required");
    }

    if (!isValidPhone(user.getPhone()) || !isAndresFormat(user.getPhone())) {
        throw new RuntimeException("Invalid phone format (AndresFormat)");
    }

    if (!isValidRFC(user.getTaxId())) {
        throw new RuntimeException("Invalid RFC format");
    }

    //Tax_Id unique
    boolean exists = users.stream()
            .anyMatch(u -> u.getTaxId().equals(user.getTaxId()));

    if(exists){
        throw new RuntimeException("tax_id must be unique");
    }


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    String madagascarTime = ZonedDateTime.now(ZoneId.of("Indian/Antananarivo"))
            .format(formatter);

    user.setCreatedAt(madagascarTime);

    user.setPassword(AESUtil.encrypt(user.getPassword()));

    user.setId(UUID.randomUUID());

    users.add(user);

    return user;
}

/**
 * Documentar que hace applysorting, matches, etc
 */
private List<User> applySorting(List<User> list, String sortedBy) {

    switch (sortedBy) {
        case "email":
            list.sort(Comparator.comparing(User::getEmail));
            break;
        case "name":
            list.sort(Comparator.comparing(User::getName));
            break;
        case "phone":
            list.sort(Comparator.comparing(User::getPhone));
            break;
        case "tax_id":
            list.sort(Comparator.comparing(User::getTaxId));
            break;
        case "created_at":
            list.sort(Comparator.comparing(User::getCreatedAt));
            break;
        case "id":
            list.sort(Comparator.comparing(User::getId));
            break;
        default:
            break;
    }

    return list;
}

    private List<User> applyFilter(List<User> list, String filter) {

    String[] parts = filter.split("\\+");

    if (parts.length != 3) {
        return list;
    }

    String field = parts[0];
    String operator = parts[1];
    String value = parts[2];

    return list.stream()
            .filter(user -> matches(user, field, operator, value))
            .toList();
}

private boolean matches(User user, String field, String operator, String value) {

    String fieldValue = switch (field) {
        case "email" -> user.getEmail();
        case "name" -> user.getName();
        case "phone" -> user.getPhone();
        case "tax_id" -> user.getTaxId();
        case "created_at" -> user.getCreatedAt().toString();
        case "id" -> user.getId().toString();
        default -> null;
    };

    if (fieldValue == null) {
        return false;
    }

    return switch (operator) {
        case "co" -> fieldValue.contains(value);
        case "eq" -> fieldValue.equals(value);
        case "sw" -> fieldValue.startsWith(value);
        case "ew" -> fieldValue.endsWith(value);
        default -> false;
    };
}

/**
 * Va a subir nuevos usuarios
 * @param id
 * @param updates
 * @return
 */
public User updateUser(UUID id, Map<String, Object> updates) {

    User user = users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);

    if (user == null) {
        return null;
    }

    updates.forEach((key, value) -> {
        switch (key) {
            case "email" -> user.setEmail((String) value);
            case "name" -> user.setName((String) value);
            case "phone" -> user.setPhone((String) value);
            //Encripta la contraseña
            case "password" -> user.setPassword(AESUtil.encrypt((String) value));
            case "taxId" -> user.setTaxId((String) value);
        }
    });

    return user;
}

/**
 * Se va a encargar de detectar si lo elimino, en caso de que regrese true es que si lo elimino y en caso de que marque 
 * false es que aun encontro el id adentro del arreglo
 * @param id
 * @return
 */
public boolean deleteUser(UUID id) {

    return users.removeIf(user -> user.getId().equals(id));
}

/**
 * Se va a encargar de hacer el login
 */

public User login(String taxId, String password) {

    return users.stream()
            .filter(user ->
                    user.getTaxId().equals(taxId)
                    && user.getPassword().equals(AESUtil.encrypt(password)))
            .findFirst()
            .orElse(null);
}

}