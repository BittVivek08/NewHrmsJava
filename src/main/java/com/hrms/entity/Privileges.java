package com.hrms.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "main_privileges")
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "object")
    private int obj;

    @Column(name = "role")
    private int role;

    @Column(name = "addpermission")
    private String addpermission;

    @Column(name = "editpermission")
    private String editpermission;

    @Column(name = "deletepermission")
    private String deletepermission;

    @Column(name = "viewpermission")
    private String viewpermission;

    @Column(name = "uploadattachments")
    private String uploadattachments;

    @Column(name = "viewattachments")
    private String viewattachments;

    @Column(name = "isactive")
    private int isActive;

    // @Column(name="createdby")
    // private int createdBy;
    // @Column(name="modifiedby")
    // private int modifiedBy;
    //
    // @Column(name="createddate")
    // private Timestamp createdDate;
    //
    //
    // @Column(name="modifieddate")
    // private Timestamp modifiedDate;


}
