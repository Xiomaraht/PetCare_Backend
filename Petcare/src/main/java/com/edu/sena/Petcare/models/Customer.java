package com.edu.sena.Petcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;

    @Column(nullable = false)
    private String documentNumber;

    private String birthdate;
    private String addressDetail;

    // Relaciones blindadas con @JsonIgnore para evitar errores de serialización Lazy
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_neighborhood")
    private Neighborhood barrioCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Pet> petCustomer;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Bill> facturas;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<PaymentMethod> metodosPagoCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Wishlist> whishlists;

    // Métodos explícitos para asegurar compatibilidad si Lombok falla en el IDE
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    
    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    
    public String getAddressDetail() { return addressDetail; }
    public void setAddressDetail(String addressDetail) { this.addressDetail = addressDetail; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public Neighborhood getBarrioCliente() { return barrioCliente; }
    public void setBarrioCliente(Neighborhood barrioCliente) { this.barrioCliente = barrioCliente; }
}
