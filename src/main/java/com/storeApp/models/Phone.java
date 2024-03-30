package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "main_picture_URL")
    private String mainPictureURL;
    @Column(name = "operation_system")
    private String os;
    @Column(name = "os_verion")
    private String osVersion;
    @Column(name = "screen_size")
    private Double screenSize;
    @Column(name = "resolution")
    private String resolution;
    @Column(name = "main_camera")
    private Short mainCamera;
    @Column(name = "front_camera")
    private Short frontCamera;
    @Column(name = "processor")
    private String processor;
    @Column(name = "count_of_cores")
    private Byte countOfCores;
    @Column(name = "ram")
    private Short ram;
    @Column(name = "rom")
    private Short rom;
    @Column(name = "weight")
    private Short weight;
    @Column(name = "battary_capacity")
    private Short batteryCapacity;
    @Column(name = "count_of_sim_card")
    private Byte countOfSimCard;
    @Column(name = "price")
    private Double price;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "vote_count")
    private Long voteCount;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Column(name = "is_used")
    private boolean isUsed;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @JsonManagedReference
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PhonePictureURL> phonePictureURLS;
    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<MobileCommunicationStandard> standartList;
    @JsonManagedReference
    @OneToMany(mappedBy = "phone")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OtherFeatures> featuresList;

    public Phone() {}



}