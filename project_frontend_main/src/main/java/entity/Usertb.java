/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "usertb", catalog = "tour_booking_system", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usertb.findAll", query = "SELECT u FROM Usertb u"),
    @NamedQuery(name = "Usertb.findByUsername", query = "SELECT u FROM Usertb u WHERE u.username = :username"),
    @NamedQuery(name = "Usertb.findByFname", query = "SELECT u FROM Usertb u WHERE u.fname = :fname"),
    @NamedQuery(name = "Usertb.findByLname", query = "SELECT u FROM Usertb u WHERE u.lname = :lname"),
    @NamedQuery(name = "Usertb.findByEmail", query = "SELECT u FROM Usertb u WHERE u.email = :email"),
    @NamedQuery(name = "Usertb.findByPassword", query = "SELECT u FROM Usertb u WHERE u.password = :password")})
public class Usertb implements Serializable {

    @Size(max = 50)
    @Column(name = "fname", length = 50)
    private String fname;
    @Size(max = 50)
    @Column(name = "lname", length = 50)
    private String lname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;
    @Size(max = 500)
    @Column(name = "password", length = 500)
    private String password;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username", nullable = false, length = 100)
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usertb")
    private Collection<Projectgroups> projectgroupsCollection;

    public Usertb() {
    }

    public Usertb(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Collection<Projectgroups> getProjectgroupsCollection() {
        return projectgroupsCollection;
    }

    public void setProjectgroupsCollection(Collection<Projectgroups> projectgroupsCollection) {
        this.projectgroupsCollection = projectgroupsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertb)) {
            return false;
        }
        Usertb other = (Usertb) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usertb[ username=" + username + " ]";
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
