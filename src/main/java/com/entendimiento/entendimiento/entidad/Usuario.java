package com.entendimiento.entendimiento.entidad;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre No puede estar Vacio")
    @Size(min = 10, max = 200, message
            = "Debe estar entre 10 y 200 Caracteres")
    private String nombre;
    @Min(value = 6)
    @NotEmpty(message = "El Telefono No puede estar Vacio")
    private String telefono;
    @Email
    @NotEmpty(message = "El Email No puede estar Vacio")
    private String email;
    private String direccion;
    
}
